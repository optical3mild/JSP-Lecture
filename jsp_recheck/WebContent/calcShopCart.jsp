<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align=center>
		<h3>물품을 선택하세요</h3>
		<hr>
		<form action="calcControl.jsp" method=post>
			<input type=checkbox name="inCartItem" value="0">신발&nbsp;&nbsp;
			<input type=checkbox name="inCartItem" value="1">모자&nbsp;&nbsp;
			<input type=checkbox name="inCartItem" value="2">가방&nbsp;&nbsp;
			<input type=checkbox name="inCartItem" value="3">TV
			<br>
			<br>
			<button type="submit">제출</button>
		</form>
	</div>
</body>
</html>