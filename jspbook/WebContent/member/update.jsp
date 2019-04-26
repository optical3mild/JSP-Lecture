<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- jstl core라이브러리 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>회원수정</title>
	<style>
		label {
			display : block; /* 새 라인에서 시작 */
			padding : 5px;
		}
		label span {
			display : inline-block;
			width : 90px;
			text-align : right;
			padding : 10px;
		}
	</style>
</head>
<body>
	<h3>회원수정</h3>
	${memberName} 회원님 반갑습니다.<br>
	<a href="MemberProcServlet?action=getMemList&page=${curMemListPage}">회원목록으로 되돌아가기</a>&nbsp;&nbsp;&nbsp;
	<a href="MemberProcServlet?action=logout">로그아웃</a>
	<hr>
	<c:set var="member" value="${requestScope.member}"/>
	<form name="registerForm" action=MemberProcServlet?action=execute method=post>
		<!--34:form 내에서 값을 주고 받기위해 필요 -->
		<input type="hidden" id="id" name="id" value="${member.id}">
		<label><span>아이디:</span>
			${member.id}
		</label>
		<label><span>이름:</span>
			<input type="text" name="name" value="${member.name}" size="10">
		</label>
		<label><span>생년월일:</span>
			<input type="text" name="birthday" value="${member.birthday}" size="10">
		</label>
		<label><span>주소:</span>
			<input type="text" name="address" value="${member.address}" size="20">
		</label>
		<label>
			<span></span>
			<input type="submit" value="회원 수정" name="B1">&nbsp;&nbsp;
 			<input type="reset" value="재작성" name="B2">
		</label>
	</form>

</body>
</html>