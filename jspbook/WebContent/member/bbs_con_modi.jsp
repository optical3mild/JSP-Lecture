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
	<form name="contentModiForm" action=/jspbook/member/BbsProcServlet?action=update method=post>
		<!--34:form 내에서 값을 주고 받기위해 필요 -->
		<!--  <input type="hidden" name="memberId" value=""> -->
		
		<label><span>제목:</span>
			<input type="text" name="title" value="<%=content.getTitle() %>" size="50">
		</label>
		<label><span>글번호:</span>
			<%=content.getId() %>
			<input type="hidden" name="contentId" value="<%=content.getId()%>">
		</label>
		<label><span>작성자:</span>
			<%=content.getName() %>
			<input type="hidden" name="getName" value="<%=content.getName()%>">
		</label>
		<label><span>최종수정:</span>
			<%=content.getDate() %>
			<input type="hidden" name="Date" value="<%=content.getDate()%>">
		</label>
		<label><span>본문:</span><br>
			<input type="text" name="content" value="<%=content.getContent() %>"size="400">
		</label>
		<label>
			<span></span>
			<input type="submit" value="저장" name="B1">&nbsp;&nbsp;
 			<input type="reset" value="재작성" name="B2">
		</label>
	</form>
	</div>
		
	

</body>
</html>