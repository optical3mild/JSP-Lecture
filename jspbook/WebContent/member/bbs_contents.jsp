<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.util.*, member.*" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	//content에 받은정보를 저장
	BbsMember content = (BbsMember) request.getAttribute("selectedContent");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><%=content.getTitle() %></title>
</head>
<body>
	<div align=center>
		<h3><%=content.getTitle() %></h3>
		<div align=right>
			글번호: <%=content.getId() %><br>
			작성자: <%=content.getName() %><br>
			최종수정: <%=content.getDate() %><br>
		</div>
		<hr>
		<%

		out.println(content.getContent());
		String updateUri = "BbsProcServlet?action=update&contentId=" + content.getId();
		String deleteUri = "BbsProcServlet?action=delete&contentId=" + content.getId();
		%>
		<br>
		<hr>
		<button onclick="location.href='bbs_list.jsp'">BBS게시판</button>&nbsp;
			&nbsp;<button onclick="location.href='<%=updateUri%>'">수정</button>&nbsp;
			&nbsp;<button onclick="location.href='<%=deleteUri%>'">삭제</button>
	</div>
		
	

</body>
</html>