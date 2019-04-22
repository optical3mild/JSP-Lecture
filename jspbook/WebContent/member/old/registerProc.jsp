<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="member.*" %>
<%request.setCharacterEncoding("UTF-8"); %>
<jsp:useBean id="member" scope="page" class="member.MemberDTO" />
<jsp:setProperty property="*" name="member"/>

<%
	//System.out.println(member.toString()); //확인용도. console에 출력
	MemberDAO mDao = new MemberDAO();
	mDao.insertMember(member);
	mDao.close();
	response.sendRedirect("loginMain.jsp");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
</head>
<body>

</body>
</html>