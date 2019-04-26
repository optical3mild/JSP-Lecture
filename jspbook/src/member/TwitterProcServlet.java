package member;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class TweeterProc
 */
@WebServlet("/member/TwitterProcServlet")
public class TwitterProcServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TwitterProcServlet() {
        super();
        // TODO Auto-generated constructor stub
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
		
		//utf-8
		request.setCharacterEncoding("utf-8");
		
		//html폼에서 전달된 msg값을 가지고 옴
		String msg = request.getParameter("msg");
		
		//session에 저장된 로그인 사용자 이름을 가지고 옴
//		Object username = session.getAttribute("user");
		
		//객체생성
		//session에 저장된 로그인 사용자 이름을 가지고 옴
		HttpSession session = request.getSession();
		
		//세션에 저장된 속성값을 가져옴
		String username = (String)session.getAttribute("memberName");
		
		//ServletContext:
		//하나의 서블릿이 서블릿 컨테이너와 통신하기 위해서 사용되어지는 메서드들을 가지고 있는 클래스
		//webApp전역에서 사용가능.
		ServletContext application = request.getServletContext();
		
		//메시지 저장을 위해 application에서 msg로 저장된 ArrayList가지고 옴
		List<String> msgs = (ArrayList<String>) application.getAttribute("msgs");
		
		//null인 경우 새로운 ArrayList 객체를 생성
		if(msgs == null) {
			msgs = new ArrayList<String>();
			//application에 ArrayList 저장
			application.setAttribute("msgs",msgs);
		}
		
		//사용자 이름, 메시지, 날짜 정보를 포함하여 ArrayList에 추가
//		msgs.add(username + " :: " +msg+" , "+new java.util.Date());
		Date date = new Date();
		SimpleDateFormat f = new SimpleDateFormat("MM월 dd일(E) HH:mm", Locale.KOREA);
		msgs.add(username+" :: "+msg+" , "+ f.format(date));
		
		//톰캣 콘솔을 통한 로깅
//		application.log(msg+"추가됨");
		application.log(msg+" , "+username);
		
		//목록 화면으로 리다이렉팅
		response.sendRedirect("twitter_list.jsp");
	}

}
