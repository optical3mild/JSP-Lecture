<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	//한글 캐릭터셋 변환
	request.setCharacterEncoding("utf-8");
	//HTML폼에서 username으로 전달된 값을 가지고 옴
	String username = request.getParameter("username");
	
	//username이 null이 아닌경우 세션에 값을 저장
	if(username != null){
		session.setAttribute("user", username);
	}
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Twitter</title>
</head>
<body>
	<div align=center>
	<h3>Simple twitter</h3>
	<hr>
	<form action="tweet.jsp" method="post">
		<!-- 세션에 저장될 이름 출력 -->
		@<%=session.getAttribute("user") %> <input type="text" name="msg">
		<input type="submit" value="Tweet">
	</form>
	</div>
	<hr>
	<div align=left>
	<ul>
	<%
		//application 내장객체를 통해 msgs이름으로 저장된 ArrayList를 가지고 옴
		ArrayList<String> msgs = (ArrayList<String>) application.getAttribute("msgs");
	
		//msgs가 null이 아닌경우에만 목록 출력
		if(msgs != null) {
			for(String msg: msgs) {
				out.println("<li>"+msg+"</li>");
			}
		}
	%>
	</ul>
	</div>
</body>
</html>