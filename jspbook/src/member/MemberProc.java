package member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MemberProc
 */
@WebServlet("/member/memberProcServlet")
public class MemberProc extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberProc() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberDAO mDao;
		MemberDTO member;
		RequestDispatcher rd;
		String action = request.getParameter("action");
		String strId = request.getParameter("id");
		System.out.println(action + " , "+ strId);
		switch(action) {
		case "update": //수정버튼 클릭 시
			mDao = new MemberDAO();
			member = mDao.selectMemberById(Integer.parseInt(strId));
			mDao.close();
			request.setAttribute("member", member);
			rd = request.getRequestDispatcher("update.jsp");
			rd.forward(request, response);
			break;
			
		case "delete": //삭제버튼 클릭 시
			mDao = new MemberDAO();
			member = mDao.selectMemberById(Integer.parseInt(strId));
			mDao.deleteMember(member);
			mDao.close();
			String message = "id" + member + "가 삭제 되었습니다";
//			response.sendRedirect("loginMain.jsp");
			String url = "loginMain.jsp";
			request.setAttribute("message", message);
			request.setAttribute("url", url);
			rd = request.getRequestDispatcher("alertMsg.jsp");
			rd.forward(request, response);
			break;
			
		default:
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
