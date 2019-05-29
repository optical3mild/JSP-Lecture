<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<form action="loginProc.jsp">
			<label for="userId">아이디&nbsp;:&nbsp;</label>
			<input type="text" name="userId">
			
			<br>
			
			<label for="passWd">암호&nbsp;:&nbsp;</label>
			<input type="text" name="passWd">
			
			<br>
			
			<input type="radio" name="loginType" id="user" value="user">
			<label for="user">사용자&nbsp;&nbsp;</label>
			<input type="radio" name="loginType" id="admin" value="admin">
			<label for="admin">관리자&nbsp;&nbsp;</label>
			
			<br>
			
			<input type="submit" value="로그인">
		</form>
	</div>
</body>
</html>