<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.util.*, member.*" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	//content에 받은정보를 저장
	BbsMember content = (BbsMember) request.getAttribute("selectedContent");

	int curPage = (int)session.getAttribute("currentBbsPage");
	String pageUrl = "BbsProcServlet?action=getList&page=" + curPage;
%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>새글 작성</title>
	<!-- 
	<style>
		label { display:block; /*padding:5px;*/}
		label span { 
			display:inline-block; padding:10px;
			text-align:right; width:90px;}
	</style>
	 -->
</head>
<body>
	<div align=center>
	<h3>새글 작성</h3>
	<%=(String)session.getAttribute("memberName")%> 회원님 반갑습니다.<br>
	<a href="<%=pageUrl%>">게시판으로 되돌아가기</a>&nbsp;&nbsp;&nbsp;
	<a href="MemberProcServlet?action=logout">로그아웃</a>
	<hr>
	<br>
	<form name="registerForm" action=/jspbook/member/BbsProcServlet?action=write method=post>
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