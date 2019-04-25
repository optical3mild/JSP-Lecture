package member;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/member/MemberProcServlet")
public class MemberProcServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberProcServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}
	
	protected void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberDAO mDao = null;
		MemberDTO member = null;
		RequestDispatcher rd = null;
		int id = 0;
		String password = null;
		String name = null;
		String birthday = null;
		String address = null;
		String message = null;
		String url = null;
		
		//현재 page저장
		int curMemListPage = 1;
		List<String> memPageList = new ArrayList<String>();
		
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		//로그인 정보관리 HttpSession객체 생성 : 사용자의 상태정보를 서버에서 관리함
		HttpSession session = request.getSession();
		
		switch(action) {
		case "getMemList":
			mDao = new MemberDAO();
			if (!request.getParameter("page").equals("")) {
				curMemListPage = Integer.parseInt(request.getParameter("page"));
			}
			
			int count = mDao.getCount();
			if (count == 0)			// 데이터가 없을 때 대비
				count = 1;
			int pageNo = (int)Math.ceil(count/10.0);
			if (curMemListPage > pageNo)	// 경계선에 걸렸을 때 대비
				curMemListPage--;
			
			//세션에 현재페이지 번호 저장
			session.setAttribute("curMemListPage", curMemListPage);
			
			// 리스트 페이지의 하단 페이지 데이터 만들어 주기
			String page = null;
			
			page = "<a href=#>&laquo;</a>&nbsp;";
			memPageList.add(page);
			
			for (int i=1; i<=pageNo; i++) {
				page = "&nbsp;<a href=MemberProcServlet?action=getMemList&page=" + i + ">" + i + "</a>&nbsp;";
				memPageList.add(page);
			}
			
			page = "&nbsp;<a href=#>&raquo;</a>";
			memPageList.add(page);
			
			List<MemberDTO> memberList = mDao.makeMemList(curMemListPage);
			request.setAttribute("memberList", memberList);
			request.setAttribute("memPageList", memPageList);
			rd = request.getRequestDispatcher("loginMain.jsp");
	        rd.forward(request, response);
			break;
		
		
		case "update":		// 수정 버튼 클릭 시
			if (!request.getParameter("id").equals("")) {
				id = Integer.parseInt(request.getParameter("id"));
			}
			if (id != (Integer)session.getAttribute("memberId")) {
				message = "id = " + id + " 에 대한 수정 권한이 없습니다.";
				
				curMemListPage = (int)session.getAttribute("curMemListPage");
				url = "MemberProcServlet?action=getMemList&page=" + curMemListPage;
				
				request.setAttribute("message", message);
				request.setAttribute("url", url);
				rd = request.getRequestDispatcher("alertMsg.jsp");
				rd.forward(request, response);
				break;
			}
			mDao = new MemberDAO();
			member = mDao.selectMemberById(id);
			mDao.close();
			request.setAttribute("member", member);
			rd = request.getRequestDispatcher("update.jsp");
	        rd.forward(request, response);
	        break;
	        
		case "delete":		// 삭제 버튼 클릭 시
			if (!request.getParameter("id").equals("")) {
				id = Integer.parseInt(request.getParameter("id"));
			}
			if (id != (Integer)session.getAttribute("memberId")) {
				message = "id = " + id + " 에 대한 삭제 권한이 없습니다.";
				
				curMemListPage = (int)session.getAttribute("curMemListPage");
				url = "MemberProcServlet?action=getMemList&page=" + curMemListPage;
				
				request.setAttribute("message", message);
				request.setAttribute("url", url);
				rd = request.getRequestDispatcher("alertMsg.jsp");
				rd.forward(request, response);
				break;
			}
			mDao = new MemberDAO();
			mDao.deleteMember(id);
			mDao.close();
			message = "id = " + id + " 이/가 삭제되었습니다.";
			
			curMemListPage = (int)session.getAttribute("curMemListPage");
			url = "MemberProcServlet?action=getMemList&page=" + curMemListPage;
			
			request.setAttribute("message", message);
			request.setAttribute("url", url);
			rd = request.getRequestDispatcher("alertMsg.jsp");
			rd.forward(request, response);
			break;
			
		case "login":		// login 할 때
			mDao = new MemberDAO();
			if (!request.getParameter("id").equals("")) {
				id = Integer.parseInt(request.getParameter("id"));
			}
			password = request.getParameter("password");
			
			mDao = new MemberDAO();
			int result = mDao.verifyIdPassword(id, password);
			String errorMessage = null;
			switch (result) {
			case MemberDAO.ID_PASSWORD_MATCH:
				break;
			case MemberDAO.ID_DOES_NOT_EXIST:
				errorMessage = "ID가 없음"; break;
			case MemberDAO.PASSWORD_IS_WRONG:
				errorMessage = "패스워드가 틀렸음"; break;
			case MemberDAO.DATABASE_ERROR:
				errorMessage = "DB 오류";
			}
			
			if (result == MemberDAO.ID_PASSWORD_MATCH) {
				member = mDao.selectMemberById(id);
				session.setAttribute("memberId", id);
				session.setAttribute("memberName", member.getName());
				
				response.sendRedirect("MemberProcServlet?action=getMemList&page=1");
			} else {
				url = "login.jsp";
				request.setAttribute("message", errorMessage);
				request.setAttribute("url", url);
				rd = request.getRequestDispatcher("alertMsg.jsp");
				rd.forward(request, response);
			}
			mDao.close();
			break;
		
		case "logout":			// 로그아웃할 때
			session.removeAttribute("memberId");
			session.removeAttribute("memberName");
			response.sendRedirect("login.jsp");
			break;
			
		case "register":		// 회원 등록할 때
			password = request.getParameter("password");
			name = request.getParameter("name");
			birthday = request.getParameter("birthday");
			address = request.getParameter("address");
			member = new MemberDTO(password, name, birthday, address);
			System.out.println(member.toString());
			
			mDao = new MemberDAO();
			mDao.insertMember(member);
			member = mDao.recentId();
			session.setAttribute("memberId", member.getId());
			session.setAttribute("memberName", name);
			
			message = "귀하의 아이디는 " + member.getId() + " 입니다.";
			url = "loginMain.jsp";
			request.setAttribute("message", message);
			request.setAttribute("url", url);
			rd = request.getRequestDispatcher("alertMsg.jsp");
			rd.forward(request, response);
			mDao.close();
			break;
			
		case "execute":			// 회원 수정정보 입력 후 실행할 때
			if (!request.getParameter("id").equals("")) {
				id = Integer.parseInt(request.getParameter("id"));
			}
			name = request.getParameter("name");
			birthday = request.getParameter("birthday");
			address = request.getParameter("address");
			
			member = new MemberDTO(id, "*", name, birthday, address);
			System.out.println(member.toString());
			
			mDao = new MemberDAO();
			mDao.updateMember(member);
			mDao.close();
			
			message = "다음과 같이 수정하였습니다.\\n" + member.toString();
			request.setAttribute("message", message);
			request.setAttribute("url", "loginMain.jsp");
			rd = request.getRequestDispatcher("alertMsg.jsp");
	        rd.forward(request, response);
			//response.sendRedirect("loginMain.jsp");
			break;
			
		default:
		}
	}
}
