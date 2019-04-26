<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%request.setCharacterEncoding("utf-8");%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Twitter</title>
</head>
<body>
	<div align=center>
	<h3>Simple twitter</h3>
	${memberName} 회원님 반갑습니다.<br>
	<a href="BbsProcServlet?action=getList&page=1">게시판</a>&nbsp;&nbsp;&nbsp;
	<a href="MemberProcServlet?action=getMemList&page=1">회원목록</a>&nbsp;&nbsp;&nbsp;
	<a href="MemberProcServlet?action=logout">로그아웃</a>
	<hr>
	<!-- action : 프로젝트 폴더부터 전체 경로 필요 : servlet파일의 경로는 프로젝트 폴더가 빠져있음. -->
	<form action="TwitterProcServlet" method="post">
		<!-- 세션에 저장될 이름 출력 -->
		@${memberName}
		<input type="text" name="msg">
		<input type="submit" value="Tweet">
	</form>
	</div>
	<hr>
	<div align=left>
	<ul>
		<!-- application 내장객체를 통해 msgs 이름으로 저장된 ArrayList를 가지고 옴 -->
		<c:set var="msgs" value="${applicationScope.msgs}"/>
		<!-- msgs가 null 이 아닌 경우에만 목록 출력 -->
		<c:if test="${not empty msgs}">
			<c:forEach var="msg" items="${msgs}">
				<li>${msg}</li>
			</c:forEach>
		</c:if>
	</ul>
	</div>
</body>
</html>