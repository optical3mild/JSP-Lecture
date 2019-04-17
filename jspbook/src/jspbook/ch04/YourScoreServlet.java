package jspbook.ch04;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class YourScoreServlet
 */
@WebServlet(description = "점수계산", urlPatterns = { "/YourScoreServlet" })
public class YourScoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public YourScoreServlet() {}

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
		//변수 선언
		int lang, eng, math;
		int[] result;
		
		res.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = res.getWriter();
		
		lang=Integer.parseInt(req.getParameter("lang"));
		eng=Integer.parseInt(req.getParameter("eng"));
		math=Integer.parseInt(req.getParameter("math"));
		
		CalcScore calc = new CalcScore();
		result=calc.calculate(lang,eng,math);
		
		out.println("<html>");
		out.println("<head><title>계산결과</title></head>");
		out.println("<body><center>");
		out.println("<h2>계산결과</h2>");
		out.println("<hr>");
		out.println("<table>"
				+"<tr>"
				+"<td><b>국어</b><td><td><b>영어</b><td><td><b>수학<b><td><td><b>합계</b><td><td><b>평균</b><td>"
				+"</tr>"
				+"<tr>"
				+"<td>"+lang+"<td><td>"+eng+"<td><td>"+math+"<td><td>"+result[0]+"<td><td>"+result[1]+"<td>"
				+"</tr>"
				+"</table>");
		out.println("</body></html>");
	}

}
