<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
1.스크립트릿
<%
	int num1 = (Integer)request.getAttribute("num1");
	int num2 = (Integer)request.getAttribute("num2");
%>
	<%=num1 %> + <%=num2 %> = <%=num1 + num2%>
	
	<hr>
2.EL표현식	
${num1 }+${num2 }=${add }
</body>
</html>