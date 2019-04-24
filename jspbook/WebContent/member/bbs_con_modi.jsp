<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.util.*, member.*" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	//content에 받은정보를 저장
	BbsMember content = (BbsMember) request.getAttribute("selectedContent");

	int curPage = (int)session.getAttribute("currentBbsPage");
	String url = "BbsProcServlet?action=getList&page=" + curPage;
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>글 수정</title>
</head>
<body>
	<div align=center>
	<h3><%=content.getTitle() %></h3>
	<form name="contentModiForm" action=BbsProcServlet?action=execute method=post>
		<!--34:form 내에서 값을 주고 받기위해 필요 -->
		<input type="hidden" name="contentId" value="<%=content.getId()%>">
		<label><span>제목:</span>
			<input type="text" name="title" value="<%=content.getTitle() %>" size="50">
		</label><br><br>
		<label><span>내용:</span>
			<textarea name="content" rows="30" cols="51" ><%=content.getContent() %></textarea>
		</label><br>
		<label>
			<span></span>
			<input type="submit" value="저장" name="B1">&nbsp;&nbsp;
 			<input type="reset" value="재작성" name="B2">
		</label>
	</form>
	<br>
	<!--  
	<button onclick="location.href='BbsProcServlet?action=getList&page=1'">BBS게시판</button>-->
	<button onclick="location.href='<%=url%>'">BBS게시판</button>
	</div>
</body>
</html>