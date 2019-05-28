<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%=b %>

<%!
	//디클레이션 : java코드 작성
	//변수선언 : 전역변수	-> 서버 연결이 끊길 때 까지 유지
	//선언 : 함수
	
	int b=10;
	int global_cnt=0;
%>

<%
	//스크립트릿 : java코드 작성
	//변수선언 : 지역변수	-> view를 다시 불러올때 마다 초기화
	
	int a=10;
	int local_cnt=0;
	out.print("<br> local_cnt : ");
	out.print(++local_cnt);

	out.print("<br> global_cnt : ");
	out.print(++global_cnt);
%>

<%=a %>
<!-- html주석 -->
<%-- jsp주석 --%>


</body>
</html>