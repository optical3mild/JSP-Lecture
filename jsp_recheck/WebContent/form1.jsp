<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align=center>
		<h2>설문조사</h2>
		<hr>
		<form action="test1Control.jsp" method="post" id="testTable">
			<table>
				<tr>
					<td>이름:&nbsp;</td>
					<td><input type=text name="name"></td>
				</tr>
				<tr>
					<td>성별:&nbsp;</td>
					<td>
						<input type=radio name="gender" value="male">남자&nbsp;&nbsp;
						<input type=radio name="gender" value="female">여자
					</td>
				</tr>
				<tr>
					<td>좋아하는 계절:&nbsp;</td>
					<td>
						<input type=checkbox name="season" value="1">봄&nbsp;&nbsp;
						<input type=checkbox name="season" value="2">여름&nbsp;&nbsp;
						<input type=checkbox name="season" value="3">가을&nbsp;&nbsp;
						<input type=checkbox name="season" value="4">겨울
					</td>
				</tr>
			</table>
			<button type="submit">제출</button>
		</form>
	</div>

</body>
</html>