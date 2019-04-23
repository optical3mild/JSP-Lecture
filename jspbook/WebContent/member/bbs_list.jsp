<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- 필요한 자바 클래스와 정의한 클래스, 컨트롤러를 import -->
<%@ page import="java.util.*, member.*" %>
<%
	BbsDAO bDao = new BbsDAO();
	//selectContentsAll로 DB의 정보를 읽어온다.
	List<BbsMember> contentsList = bDao.selectContentsAll(10);
	bDao.close();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>BBS게시판</title>
	<style>
		td, th { text-align: center;}
/* 		table td,th { border: 1px solid; border-collapse:collapse} */
	</style>
</head>
<body>
	<div align=center>
		<h3>BBS게시판</h3>
		<%=(String)session.getAttribute("memberName")%> 회원님 반갑습니다.<br>
		<!-- 메인화면 -->
		<a href="loginMain.jsp">메인화면</a>&nbsp;&nbsp;&nbsp;
		<!-- 트위터 목록 Link -->
		<a href="twitter_list.jsp">트윗</a>&nbsp;&nbsp;&nbsp;
		<a href="/jspbook/member/MemberProcServlet?action=logout">로그아웃</a>
		<hr>
		<table border="1" style="border-collapse:collapse;">
		<tr><th>아이디</th><th>이름</th><th>제목</th><th>최종날짜</th></tr>
		
		<%
		for (BbsMember content: contentsList) {
 			//url 특수문자 "?" == "query" 
 			//--> servlet 컨트롤러에 request: "action=update" & "id=수신한 값"
 			//post표기가 없지만, 컨트롤러의 doGet에서 doPost를 실행하도록 정의되어 있음.
 			String contentUri = "BbsProcServlet?action=contentView&contentId=" + content.getId();
 			//out.println(content.getId()); --> 나옴
		%>
			<tr onclick="location.href='<%=contentUri%>'">
			<td><%=content.getId()%></td>
			<td><%=content.getName()%></td>
			<td><%=content.getTitle()%></td>
			<td><%=content.getDate()%></td>

			</tr>
		<%	
		}
		%>
		</table>
	</div>
</body>
</html>