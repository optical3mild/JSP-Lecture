<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
 th, td { width:2em; height:2em; text-align:center}
</style>
<title>Scriptlet으로 구구단 표 만들기</title>
</head>
<body>
	<div align="center">
	<center>
	<table border="1" align=center>
	<%
	for(int i=1; i<10; i+=2){
		if(i==3) {
			%>
			<tbody>
			<%
		} 
		if(i==1) {
			%>
			<thead>
			<tr><th></th>
			<% 
			for(int k=3; k<10; k+=2){
			%>
			<th><b><%=k%></b></th>
			<%
			}
			%>
			</tr>
			</thead>
		<%
		} else {
		%>
			<tr>
			<td><b><%=i%></b></td>
			<%
			for(int k=3; k<10; k+=2){
			%>
			<td><%=i*k%></td>
			<%
			}
			%>
			</tr>
		<%
		}
	}
	%>
			</tbody>
	</table>
	</center>
	</div>
</body>
</html>