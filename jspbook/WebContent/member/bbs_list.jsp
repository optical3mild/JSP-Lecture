<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- jstl core라이브러리 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	<!-- scriptlet에서 jstl-EL로 변경: session에 설정된 attribute를 호출/출력-->
	${memberName} 회원님 반갑습니다.<br>
	<a href="bbs_write.jsp">글쓰기</a>&nbsp;&nbsp;&nbsp;
	<a href="FileProcServlet?action=bbsList&fileName=BBSLIST">다운로드</a>&nbsp;&nbsp;
	<a href="MemberProcServlet?action=getMemList&page=1">회원목록</a>&nbsp;&nbsp;&nbsp;
	<a href="twitter_list.jsp">트윗</a>&nbsp;&nbsp;&nbsp;
	<a href="MemberProcServlet?action=logout">로그아웃</a>
	<hr>
	<table border="1" style="border-collapse:collapse;">
		<tr bgcolor="skyblue"><th height="30">글번호</th><th>제목</th><th>작성자</th><th>최종수정시각</th><th>액션</th></tr>
		
		<!-- request.getAtt -> jstl_core형식으로 변경 -->
		<!-- <c:set var="bmList" value="${requestScope.bbsMemberList}" /> -->
		
		<!-- for (BbsMember bm: bmList){}와 동일 -->
		<c:forEach var="bm" items="${requestScope.bbsMemberList}">
			<tr>
				<!-- 중복부분 처리필요. -->
				<td onclick="location.href='BbsProcServlet?action=contentView&contentId=${bm.id}'">${bm.id}</td>
				<td onclick="location.href='BbsProcServlet?action=contentView&contentId=${bm.id}'">${bm.title}</td>
				<td onclick="location.href='BbsProcServlet?action=contentView&contentId=${bm.id}'">${bm.name}</td>
				<td onclick="location.href='BbsProcServlet?action=contentView&contentId=${bm.id}'">${bm.date}</td>
				<td>&nbsp;
					<button onclick="location.href='BbsProcServlet?action=update&contentId=${bm.id}'">수정</button>&nbsp;&nbsp;
					<button onclick="location.href='BbsProcServlet?action=delete&contentId=${bm.id}'">삭제</button>&nbsp;
				</td>
			</tr>
		</c:forEach>
	</table>
	<c:set var="pageList" value="${requestScope.pageList}"/>
	<c:forEach var="pageNo" items="${pageList}">
		${pageNo}
	</c:forEach>
	</div>
</body>
</html>