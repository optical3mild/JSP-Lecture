<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="member.*" %>
<%
	request.setCharacterEncoding("UTF-8");
	int id = Integer.parseInt(request.getParameter("id"));
	String password = request.getParameter("password");
	
	//경우에 따른 오류문이 제대로 출력되지 않음
	MemberDAO mDao = new MemberDAO();
	int result = mDao.verifyIdPassword(id, password);
	String errorMessage = null;
	switch (result) {
	case MemberDAO.ID_PASSWORD_MATCH:
		break;
	case MemberDAO.ID_DOES_NOT_EXIST:
		errorMessage = "ID가 없음";break;
	case MemberDAO.PASSWORD_IS_WRONG:
		errorMessage = "패스워드가 틀렸음";break;
	case MemberDAO.DATABASE_ERROR:
		errorMessage = "DB오류";
	}
	mDao.close();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
</head>
<body>
	<%
		if(result == MemberDAO.ID_PASSWORD_MATCH) {
			//response.sendRedirect("include_test.jsp");
			response.sendRedirect("loginMain.jsp");
		} else {
	%>		
			<jsp:forward page = "login.jsp">
				<jsp:param name="error" value="<%=errorMessage%>"/>
			</jsp:forward>
	<%
	}
	%>
</body>
</html>