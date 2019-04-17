package jspbook.ch04;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CreateMemberServlet
 */
@WebServlet(description = "ch04CreateMember", urlPatterns = { "/CreateMemberServlet" })
public class CreateMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateMemberServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html; charset=UTF-8");

		String name ="";
		String eMail="";
		String pNum ="";
		
		int id;
		
		req.setCharacterEncoding("UTF-8"); //정보 수신 전 세팅
		
		name = req.getParameter("name");
		eMail = req.getParameter("eMail");
		pNum = req.getParameter("pNum");
		
		Member newMem = new Member();
		newMem.setName(name.trim());
		newMem.seteMail(eMail.trim());
		newMem.setpNum(pNum.trim());
		
		id=newMem.getId();
		
		PrintWriter out = res.getWriter();
		out.println("<html>");
		out.println("<head><title>가입정보</title></head>");
		out.println("<body><center>");
		out.println("<h2>가입정보</h2>");
		out.println("<hr>");
		out.println("아래와 같이 가입되셨습니다.");
		out.println("<table>"
				+"<tr>"
				+"<td><b>이름</b><td><td><b>:</b><td><td>"+name+"<td>"
				+"</tr>"
				+"<tr>"
				+"<td><b>이메일</b><td><td><b>:</b><td><td>"+eMail+"<td>"
				+"</tr>"
				+"<tr>"
				+"<td><b>전화번호</b><td><td><b>:</b><td><td>"+pNum+"<td>"
				+"</tr>"
				+"<tr>"
				+"<td><b>아이디</b><td><td><b>:</b><td><td>"+id+"<td>"
				+"</tr>"
				+"</table>");
		out.println("</body></html>");
		
	}

}
