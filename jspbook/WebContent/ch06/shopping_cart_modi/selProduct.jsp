<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>selProduct</title>
</head>
	<%
	request.setCharacterEncoding("utf-8");
	
	session.setAttribute("username", request.getParameter("username"));
	%>
<body>
	<div align=center>
		<h2>상품선택</h2>
		<hr>
		<form name="form1" method="post" action="add.jsp">
			<select name="product">
				<option>사과</option>
				<option>귤</option>
				<option>파인애플</option>
				<option>자몽</option>
				<option>레몬</option>
			</select>
			&nbsp;&tynbsp;수량: <input type="text" name="quantity" size="4" value="0">&nbsp;&nbsp;
			<input type="submit" value="추가"/>
		</form>
		<a href="checkOut.jsp">계산</a>
	</div>
</body>
</html>