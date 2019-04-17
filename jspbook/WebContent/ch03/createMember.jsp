<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8"); %>
<jsp:useBean id="member" scope="page" class="jspbook.ch03.Member"/>
<jsp:setProperty property="*" name="member"/>
<% member.getId(); %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원가입</title>
</head>
<body>
 	<center>
	<h3>회원가입</h3>
	<hr>
	<form name="form1" method=post>
		<table>
		<tr>
			<td>이름</td>
			<td>:</td>
			<td><input type="text" name="name" width="200" size="12"></td>
		</tr>
		<tr>
			<td>이메일</td>
			<td>:</td>
			<td><input type="text" name="eMail" width="200" size="12"></td>
		</tr>
		<tr>
			<td>전화번호</td>
			<td>:</td>
			<td><input type="text" name="pNum" width="200" size="12"></td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td>
 			<input type="submit" value="가입" name="B1">
 			<input type="reset" value="다시입력" name="B2">
			</td>
		</tr>
		</table>
 	</form>
 		ID는 <jsp:getProperty name="member" property="id"/>입니다.
 	</center>
</body>
</html>