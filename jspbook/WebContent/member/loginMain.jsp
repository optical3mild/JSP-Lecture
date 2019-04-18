<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- 필요한 자바 클래스와 정의한 클래스, 컨트롤러를 import -->
<%@ page import="java.util.*, member.*" %>
<%
	MemberDAO mDao = new MemberDAO();
	//selectAll()로 DB의 정보를 읽어온다.
	List<MemberDTO> list = mDao.selectAll();
	mDao.close();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>메인 페이지</title>
	<style>
		td, th { text-align: center;}
/* 		table td,th { border: 1px solid; border-collapse:collapse} */
	</style>
</head>
<body>
	<h3>회원명단</h3>
	<hr>
	<table border="1" style="border-collapse:collapse;">
	<tr><th>아이디</th><th>이름</th><th>생일</th><th>주소</th><th>액션</th></tr>
	<%
	for (MemberDTO member: list) {
	%>
		<tr>
		<td><%=member.getId()%></td>
		<td><%=member.getName()%></td>
		<td><%=member.getBirthday()%></td>
		<td><%=member.getAddress()%></td>
		<%
		//url 특수문자 "?" == "query" 
		//--> servlet 컨트롤러에 request: "action=update" & "id=수신한 값"
		//post표기가 없지만, 컨트롤러의 doGet에서 doPost를 실행하도록 정의되어 있음.
		String uri = "memberProcServlet?action=update&id=" + member.getId();
		%>
		<td>&nbsp;<button onclick="location.href='<%=uri%>'">수정</button>&nbsp;</td>
		</tr>
	<%	
	}
	%>
	</table>
</body>
</html>