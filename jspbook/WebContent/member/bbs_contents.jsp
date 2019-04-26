<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- jstl core라이브러리 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
		${memberName} 회원님 반갑습니다.<br>
		<a href="BbsProcServlet?action=getList&page=${currentBbsPage}">게시판으로 되돌아가기</a>&nbsp;&nbsp;&nbsp;
		<a href="MemberProcServlet?action=logout">로그아웃</a>
		<hr>
		<c:set var="content" value="${requestScope.selectedContent}"/>
		<table border="1" style="border-collapse:collapse;" width="500">
			<tr bgcolor="skyblue"><th height="30" width="80">항목</th><th>내용</th></tr>
			<tr><td>글번호</td><td>${content.id}</td></tr>
			<tr><td>제목</td><td>${content.title}</td></tr>
			<tr><td>글쓴이</td><td>${content.name}</td></tr>
			<tr><td>수정일시</td><td>${content.date}</td></tr>
			<tr><td height="100">내용</td><td>${content.content}</td></tr>
		</table>
		<br>
		<button onclick="location.href='BbsProcServlet?action=update&contentId=${content.id}'">수정</button>&nbsp;&nbsp;
		<button onclick="location.href='BbsProcServlet?action=delete&contentId=${content.id}'">삭제</button>
	</div>
</body>
</html>