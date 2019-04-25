<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.util.*, member.*" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	//content에 받은정보를 저장
	BbsMember content = (BbsMember) request.getAttribute("selectedContent");
	//화면이 전환되기 전의 게시판 페이지 번호를 불러온다.
	int curPage = (int)session.getAttribute("currentBbsPage");
	
	String updateUri = "BbsProcServlet?action=update&contentId=" + content.getId();
	String deleteUri = "BbsProcServlet?action=delete&contentId=" + content.getId();
	String pageUrl = "BbsProcServlet?action=getList&page=" + curPage;
%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>게시글 상세보기</title>
	<style>
		td, th { text-align: center;}
	</style>
</head>
<body>
	<div align=center>
		<h3>게시글 상세보기</h3>
		<%=(String)session.getAttribute("memberName")%> 회원님 반갑습니다.<br>
		<a href="<%=pageUrl%>">게시판으로 되돌아가기</a>&nbsp;&nbsp;&nbsp;
		<a href="MemberProcServlet?action=logout">로그아웃</a>
		<hr>
		<table border="1" style="border-collapse:collapse;" width="500">
			<tr bgcolor="skyblue"><th height="30" width="80">항목</th><th>내용</th></tr>
			<tr><td>글번호</td><td><%=content.getId() %></td></tr>
			<tr><td>제목</td><td><%=content.getTitle() %></td></tr>
			<tr><td>글쓴이</td><td><%=content.getName() %></td></tr>
			<tr><td>수정일시</td><td><%=content.getDate().substring(0, 16)%></td></tr>
			<tr><td height="100">내용</td><td><%=content.getContent()%></td></tr>
		</table>
		<br>
			<button onclick="location.href='<%=updateUri%>'">수정</button>&nbsp;&nbsp;
			<button onclick="location.href='<%=deleteUri%>'">삭제</button>
	</div>
</body>
</html>