<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- 필요한 자바 클래스와 정의한 클래스, 컨트롤러를 import -->
<%@ page import="java.util.*, member.*" %>
<%
//	BbsDAO bDao = new BbsDAO();
//	//selectContentsAll로 DB의 정보를 읽어온다.
//	List<BbsMember> contentsList = bDao.selectContentsAll(10);
//	bDao.close();

	request.setCharacterEncoding("UTF-8");
	//BbsProcServlet에서 리스트를 받아온다.
	//[1] BbsMember의 List : 10개씩 묶어 리스트로 받아옴 (<-BbsProcServlet).
	//List<BbsMember> contentsList = (List<BbsMember>)request.getAttribute("contentsList");
	//[2] 한번에 보여줄 목록의 묶음(=페이지 구성)의 번호목록 (<-BbsProcServlet).
	//List<String> pageList = (List<String>)request.getAttribute("pageList");
	
	List<BbsMember> bmList = (List<BbsMember>)request.getAttribute("bbsMemberList");
	List<String> pageList = (List<String>)request.getAttribute("pageList");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>BBS게시판</title>
	<style>
		td, th { text-align: center;}
	</style>
</head>
<body>
	<div align=center>
		<h3>BBS게시판</h3>
		<%=(String)session.getAttribute("memberName")%> 회원님 반갑습니다.<br>
		<a href="bbs_write.jsp">글쓰기</a>&nbsp;&nbsp;&nbsp;
		<a href="loginMain.jsp">메인화면</a>&nbsp;&nbsp;&nbsp;
		<a href="twitter_list.jsp">트윗</a>&nbsp;&nbsp;&nbsp;
		<a href="MemberProcServlet?action=logout">로그아웃</a>
		<hr>
		<table border="1" style="border-collapse:collapse;">
		<tr bgcolor="skyblue"><th height="30">글번호</th><th>제목</th><th>작성자</th><th>최종날짜</th><th>액션</th></tr>
		<!--  --><c:set var="bmList" value="${requestScope.bbsMemberList}" />
		
		<%
		for (BbsMember bm: bmList) {
 			//url 특수문자 "?" == "query" 
 			//--> servlet 컨트롤러에 request: "action=update" & "id=수신한 값"
 			//post표기가 없지만, 컨트롤러의 doGet에서 doPost를 실행하도록 정의되어 있음.
 			String contentUri = "BbsProcServlet?action=contentView&contentId=" + bm.getId();
			String updateUri = "BbsProcServlet?action=update&contentId=" + bm.getId();
			String deleteUri = "BbsProcServlet?action=delete&contentId=" + bm.getId();
		%>
			<tr>
			<td onclick="location.href='<%=contentUri%>'"><%=bm.getId()%></td>
			<td onclick="location.href='<%=contentUri%>'"><%=bm.getTitle()%></td>
			<td onclick="location.href='<%=contentUri%>'"><%=bm.getName()%></td>
			<td onclick="location.href='<%=contentUri%>'"><%=bm.getDate().substring(0, 16) %></td>
			<td>&nbsp;<button onclick="location.href='<%=updateUri%>'">수정</button>&nbsp;
			&nbsp;<button onclick="location.href='<%=deleteUri%>'">삭제</button>&nbsp;</td>
			</tr>
		<%	
		}
		%>
		</table>
		<%
			//페이지 번호 출력
			for (String bmPage: pageList)
				out.print(bmPage);
		%>
	</div>
</body>
</html>