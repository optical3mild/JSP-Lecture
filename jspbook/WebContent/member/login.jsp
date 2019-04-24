<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Login</title>
	<style>
			input[type=text] { /* text 창에만 적용 */
				color : red; 
			}
			input:hover, textarea:hover { /* 마우스 올라 올 때 */
				background : aliceblue;
			}
			input[type=text]:focus, input[type=password]:focus { /* 포커스 받을 때 */
				font-size : 120%; 
			}
			label {
				display : block; /* 새 라인에서 시작 */
				padding : 5px;
			}
			label span {
				display : inline-block;
				width : 90px;
				text-align : right;
				padding : 10px;
			}
	</style>
</head>
<body>
<%
	//request.setCharacterEncoding("UTF-8");

	//LoginProc에 parameter값을 요청, 참조변수에 저장.
	String error = request.getParameter("error");
	//String error = (String)request.getAttribute("error"); //LoginProc.java에서 setAttribute로 보낸것을 수신
	if(error!=null) {
		System.out.println(error);
		out.println("<script>alert('"+error+"')</script>");
	}
%>
	<div align=center>
	<br>
	<h3>Member Login</h3>
	<hr>
	
	<!-- jsp방식의 컨트롤러 이용 시
	<form name="loginForm" action=/jspbook/member/loginProc.jsp method=post> -->
	
	<!-- servlet방식의 컨트롤러 이용 시
		 :소스 경로이므로, 확장자 없이 servlet파일에 기술된 경로를 삽입 
		 (LoginProc.java파일의 상단참조) 
		 action을 요청할 컨트롤러 경로, 방식(doPost로 정의된 메소드) 지정.
	<form name="loginForm" action=/jspbook/member/loginProcServlet method=post>-->
	
	<form name="loginForm" action="/jspbook/member/MemberProcServlet?action=login" method=post>
		<label><span>ID:</span>
			<input type="text" name="id" size="10">
		</label>
		<label><span>Password:</span>
			<input type="password" name="password" size="10">
		</label>
		<label>
			<span></span>
			<!-- submit버튼 : 입력된 값을 form에 정의된 컨트롤러에 전달. 'doPost()' -->
			<input type="submit" value="로그인" name="B1">&nbsp;&nbsp;
	 		<input type="reset" value="재작성" name="B2">
		</label>
		<!-- form 내부의 링크 버튼 
		<button><a href="/jspbook/member/register.html">회원가입</a></button> -->
	</form>
	<!-- 하이퍼 링크로 연결 : form내부에 있을 경우, loginProc 작동, 회원가입 페이지 연결불가. -->
	<br><br><button onclick="location.href='register.html'">회원 가입</button>
	</div>
</body>
</html>