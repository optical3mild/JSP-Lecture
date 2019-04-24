package member;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/member/BbsProcServlet")
public class BbsProcServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public BbsProcServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request,response);
	}
	
	protected void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BbsDAO bDao = null;
		BbsDTO bDto = null;
		BbsMember bMem = null;
		RequestDispatcher rd = null;
		
		int contentId = 0;
		int contentMemId = 0;
		String message = null;
		String url = null;
		String title = null;
		String content = null;
		
		//현재 page저장
		int curPage = 1;
		List<BbsMember> contentsList = null;
		
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		//로그인 정보관리 HttpSession객체 생성 : 사용자의 상태정보를 서버에서 관리함
		HttpSession session = request.getSession();
		
		//세션 만료 시 다시 로그인 하도록
		//세션 만료 테스트 필요
		try {
			//memberId : MemberProcServlet에서 session에 저장.
			contentMemId = (int)session.getAttribute("memberId");
		} catch (NullPointerException e) {
			System.out.println("세션이 만료되었습니다.");
		}
		if (contentMemId == 0) {
			rd = request.getRequestDispatcher("login.jsp");
	        rd.forward(request, response);
		}
		
		switch(action) {
		case "getList":
			bDao = new BbsDAO();
//			contentMemId = (Integer)session.getAttribute("memberId");
			if(request.getParameter("contentMemId") == null){
				//selectContentsAll로 DB의 정보를 읽어온다.
				contentsList = bDao.selectContentsAll(10);
				bDao.close();
			} else {
				contentMemId = Integer.parseInt(request.getParameter("contentMemId"));
				contentsList = bDao.selectContentsByMemberId(contentMemId, 10);
				bDao.close();
			}
			request.setAttribute("contentsList", contentsList);
			rd = request.getRequestDispatcher("bbs_list.jsp");
			rd.forward(request, response);
			break;
		
		case "contentView":
			//클릭된 글의 id를 가져온다(members의 id와 다름)
			//request로 받은 parameter는 변수에 저장하여 사용하여야 한다.
			contentId = Integer.parseInt(request.getParameter("contentId"));
			bDao = new BbsDAO();
			bMem = bDao.detailsearch(contentId);
			request.setAttribute("selectedContent", bMem);
			rd = request.getRequestDispatcher("bbs_contents.jsp");
			rd.forward(request, response);
			break;
		
		case "update":		// 수정 버튼 클릭 시
			bDao = new BbsDAO();
			//해당 글의 id로 memberId확인
			contentId = Integer.parseInt(request.getParameter("contentId"));
			contentMemId = bDao.selectContentById(contentId).getMemberId();
			if (contentMemId != (Integer)session.getAttribute("memberId")) {
				message = "id = " + contentId + " 에 대한 수정 권한이 없습니다.";
				rd = request.getRequestDispatcher("alertMsg.jsp");
				request.setAttribute("message", message);
				
				url = "BbsProcServlet?action=contentView&contentId="+contentId;
				request.setAttribute("url",url);
				
				rd.forward(request, response);
				break;
			}
			bMem = bDao.detailsearch(contentId);
			request.setAttribute("selectedContent", bMem);
			rd = request.getRequestDispatcher("bbs_con_modi.jsp");
			rd.forward(request, response);
			break;
			
		case "delete":		// 삭제 버튼 클릭 시
			bDao = new BbsDAO();
			//해당 글의 id로 memberId확인
			contentId = Integer.parseInt(request.getParameter("contentId"));
			contentMemId = bDao.selectContentById(contentId).getMemberId();
			if (contentMemId != (Integer)session.getAttribute("memberId")) {
				message = "id = " + contentId + " 에 대한 삭제 권한이 없습니다.";
				rd = request.getRequestDispatcher("alertMsg.jsp");
				request.setAttribute("message", message);
				
				url = "BbsProcServlet?action=contentView&contentId="+contentId;
				request.setAttribute("url",url);
				
				//오류메세지 뜨지 않음 : Attribute설정 무시하고 바로 연결
//				response.sendRedirect(url);
				
				rd.forward(request, response);
				break;
			}
			message = "글번호 " +contentId + "가 삭제되었습니다.";
			bDao.deleteText(contentId);
			bDao.close();
			
			url = "bbs_list.jsp";
			request.setAttribute("message", message);
			request.setAttribute("url", url);
			rd = request.getRequestDispatcher("alertMsg.jsp");
			rd.forward(request, response);
			break;
	
		
		case "write":		// 글쓰기
			//memberId획득. --> session에서
			contentMemId = (Integer)session.getAttribute("memberId");
			title = request.getParameter("title");
			//textarea에서 줄바꿈 까지 받아와서 저장 : replace함수 사용
			content = request.getParameter("content").replace("\r\n", "<br>");
			bDto = new BbsDTO(contentMemId, title, content);
			bDao = new BbsDAO();
			bDao.insertText(bDto);
			
			message = "다음과 같이 등록되었습니다.\\n" + bDto.toString();
			url = "bbs_list.jsp";
			
			bDao.close();
			
			request.setAttribute("message", message);
			//url을 매개변수로 전달하지 않고 직접 입력하면 (= "url","bbs_list.jsp") 실행되지 않음
			request.setAttribute("url", url);
			rd = request.getRequestDispatcher("alertMsg.jsp");
	        rd.forward(request, response);
			break;
			
			
		case "execute":			// 게시글 수정후 저장
			//memberId검사 재실행 필요?
			//파라미터 값 얻은다음 : memberId는 세션에서 획득 (DAO의 update method실행 시 필요)
			//DTO에 저장
			//DAO의 update실행, DB에 업로드
			
			bDao = new BbsDAO();
			//해당 글의 id로 memberId확인
			contentId = Integer.parseInt(request.getParameter("contentId"));
			contentMemId = bDao.selectContentById(contentId).getMemberId();
			if (contentMemId != (Integer)session.getAttribute("memberId")) {
				message = "id = " + contentId + " 에 대한 수정 권한이 없습니다.";
				rd = request.getRequestDispatcher("alertMsg.jsp");
				request.setAttribute("message", message);
				
				url = "BbsProcServlet?action=contentView&contentId="+contentId;
				request.setAttribute("url",url);
				
				rd.forward(request, response);
				break;
			}
			
			title = request.getParameter("title");
			content = request.getParameter("content").replace("\r\n", "<br>");
			
			//contentId에 해당하는 객체
			bDto = bDao.selectContentById(contentId);
			//입력값 field에 저장
			bDto.setTitle(title);
			bDto.setContent(content);
			//DAO의 method 실행.
			bDao.updateText(bDto, contentMemId);
			
			message = "다음과 같이 수정하였습니다.\\n" + bDto.toString();
			url = "BbsProcServlet?action=contentView&contentId="+contentId;
			System.out.println(contentId);
			System.out.println(url);
			bDao.close();
			
			request.setAttribute("message", message);
			request.setAttribute("url", url);
			rd = request.getRequestDispatcher("alertMsg.jsp");
	        rd.forward(request, response);
			break;
			
		default:
		}
	}
	
	//DB에 저장할 content의 문자변환: '\r' to <br> 
	protected String lf2Br(String content) {
		StringBuffer sb = new StringBuffer();
		for (int i=0; i<content.length(); i++) {
			if (content.charAt(i) == '\r') {
				sb.append("<br>");
				sb.append(content.charAt(i));
			} else
				sb.append(content.charAt(i));
		}
		return sb.toString();
	}
	//DB에 저장된 content를 화면에 보여줄 때 문자변환: <br> to '\r'
	protected String br2Lf(String content) {
		StringBuffer sb = new StringBuffer(content);
		int count = 0;
		while (true) {
			int index = sb.indexOf("<br>", count);
			if (index < 0)
				break;
			sb.delete(index, index+4);
			count += 4;
		}
		return sb.toString();
	}

}
