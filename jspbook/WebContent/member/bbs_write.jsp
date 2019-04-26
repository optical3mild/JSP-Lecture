<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- jstl core라이브러리 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>새글 작성</title>
</head>
<body>
	<div align=center>
	<h3>새글 작성</h3>
	${memberName} 회원님 반갑습니다.<br>
	<a href="BbsProcServlet?action=getList&page=${currentBbsPage}">게시판으로 되돌아가기</a>&nbsp;&nbsp;&nbsp;
	<a href="MemberProcServlet?action=logout">로그아웃</a>
	<hr>
	<br>
	<form name="registerForm" action="BbsProcServlet?action=write" method=post>
		<label><span>제목:</span>
			<input type="text" name="title" size="50">
		</label><br><br>
		<label><span>내용:</span>
			<textarea name="content" rows="30" cols="51" ></textarea>
		</label><br><br>
		<label>
			<span></span>
			<!-- form에 입력된 정보를 정의된 컨트롤러에 전달, action 요청 -->
			<input type="submit" value="작성완료" name="B1">&nbsp;&nbsp;
 			<input type="reset" value="재작성" name="B2">
		</label>
	</form>
	</div>
</body>
</html>