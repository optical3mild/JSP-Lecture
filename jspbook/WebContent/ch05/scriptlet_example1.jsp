<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Scriptlet으로 표 만들기</title>
</head>
<body>
	<div align="center">
	<center>
	<table border="1">
		<thead>
			<tr><th><b>X</b></th><td><b>Y(=X*X)</b></td></tr>
		</thead>
		<tbody>
			<%
				for(int i=1; i<=10; i++){ 
			%>
				<tr><td width="20" align=center><%=i%></td>
				<td width="40" align=center><%=i*i%></td>
			<%
				}
			%>
		</tbody>
	</table>
	</center>
	</div>
</body>
</html>