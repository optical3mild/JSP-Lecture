<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
1.고전적인 방식 : 스크립트릿을 사용한 자바코드 <br>
	<%
		String str1 = request.getParameter("num1");
		String str2 = request.getParameter("num2");
		
		int num1 = Integer.parseInt(str1);
		int num2 = Integer.parseInt(str2);
	%>
	<%=num1 %> + <%=num2 %> = <%=num1+num2 %>
	
<hr>

2.EL방식 : 표현언어를 사용, 코드를 간결하게 정리 (형변환이 자동으로 실행됨) <br>
${param.num1 } + ${param.num2 } = ${param.num1 + param.num2 }
</body>
</html>