package member;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UpdateMemberServlet
 */
@WebServlet("/member/updateMemberServlet")
public class UpdateMember extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateMember() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//update page에서 연결됨 (submit버튼)
		//요청한 정보를 받는 참조변수의 formatting설정 : 한글폰트
		request.setCharacterEncoding("UTF-8");
		
		//getParameter : HTML태그에서 name="id"로 정의되어 있는 parameter의 value값을 수신
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String birthday = request.getParameter("birthday");
		String address = request.getParameter("address");
		
		//DTO(Data Transfer Object)의 필드에 수신한 parameter값을 저장한 객체 생성
		MemberDTO member = new MemberDTO(id, "*", name, birthday, address);
//		System.out.println(member.toString());
		
		//DAO(Data Access Object) 객체를 생성, 
		//updateMember() method를 이용하여 DB에 객체정보를 업데이트
		MemberDAO mDao = new MemberDAO();
		mDao.updateMember(member);
		mDao.close();
		
		//loginMain.jsp로 다시 연결
		response.sendRedirect("loginMain.jsp");
	}
}
