<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:choose>
		<c:when test="${param.loginType == 'user'}">
			<span>사용자로 로그인 되었습니다.</span>
		</c:when>
		<c:when test="${param.loginType == 'admin' && param.userId == 'root' && param.passWd == '1233'}">
			<span>관리자로 로그인 되었습니다.</span>
		</c:when>
		<c:otherwise>
			<span>잘못된 접근입니다.</span>
		</c:otherwise>
	</c:choose>
</body>
</html>