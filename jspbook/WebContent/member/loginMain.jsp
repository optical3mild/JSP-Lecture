<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- jstl core라이브러리 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
	<div align=center>
	<h3>회원명단</h3>
	${memberName} 회원님 반갑습니다.<br>
	<!-- 게시판 이동, 파라메터 전달 -->
	<a href="BbsProcServlet?action=getList&page=1">게시판</a>&nbsp;&nbsp;&nbsp;
	<!-- 트위터 목록 Link -->
	<a href="twitter_list.jsp">트윗</a>&nbsp;&nbsp;&nbsp;
	<a href="FileProcServlet?action=MemberList&fileName=MEMBERLIST">다운로드</a>&nbsp;&nbsp;
	<a href="MemberProcServlet?action=logout">로그아웃</a>
	<hr>
	<table border="1" style="border-collapse:collapse;">
		<tr><th>아이디</th><th>이름</th><th>생일</th><th>주소</th><th>액션</th></tr>
	<!-- 	<c:set var="memberList" value="${requestScope.memberList}"/>  -->
		
		<c:forEach var="member" items="${requestScope.memberList}">
			<tr>
			<td>${member.id}</td>
			<td>${member.name}</td>
			<td>${member.birthday}</td>
			<td>${member.address}</td>
			<td>&nbsp;
				<button onclick="location.href='MemberProcServlet?action=update&id=${member.id}'">수정</button>&nbsp;&nbsp;
				<button onclick="location.href='MemberProcServlet?action=delete&id=${member.id}'">삭제</button>&nbsp;
			</td>
			</tr>
		</c:forEach>
	</table>
	<c:set var="memPageList" value="${requestScope.memPageList}"/>
	<c:forEach var="memPageNo" items="${memPageList}">
		${memPageNo}
	</c:forEach>
	</div>
</body>
</html>