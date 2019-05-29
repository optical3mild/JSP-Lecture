<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 변수선언 1 -->
	<c:set var="msg" value="hello" />
	${msg}<br>
	
	<!-- 변수선언 2 : 태그 사이에-->
	<c:set var="age">30</c:set>
	${age}<br>
	<hr>
	<!-- 객체생성 : member라는 변수에 객체생성-->
	<c:set var="member" value="<%=new com.saeyan.javabean.MemberBean()%>" />
	<c:set target="${member }" property="name" value="재우" />
	<c:set target="${member }" property="userId">jwe</c:set>
	${member }<hr>
	
	<!-- 변수선언, 연산 -->
	<c:set var="add" value="${10+5 }" />
	\${add} = ${add} <br>
	<c:set var="flag" value="${10>5 }" />
	\${flag} = ${flag} <hr>
	
	<!-- 색상선택 -->
	<form action="12_colorSelect.jsp">
		<label for="color">색상을 선택하세요</label><br>
		<select id="color" name="color">
			<option value="1">빨강</option>
			<option value="2">초록</option>
			<option value="3">파랑</option>
		</select>
		<input type="submit" value="전송">
	</form>
	<hr>
	
	<!-- 그림선택 -->
	<form action="13_fruitSelect.jsp">
		<label for="fruit">과일을 선택하세요</label><br>
		<select id="fruit" name="fruit">
			<option value="1">사과</option>
			<option value="2">메론</option>
			<option value="3">바나나</option>
		</select>
		<input type="submit" value="전송">
	</form>
	<hr>
	<%
		String[] movieList = {"타이타닉", "시네마천국", "혹성탈출", "킹콩"};
		pageContext.setAttribute("movieList", movieList);
	%>
	<c:forEach var="movie" items="${movieList}">
		${movie}<br>
	</c:forEach>
	<br>
	<span>index,count값</span>
	<table border="1" style="text-align:center">
		<tr>
			<th>index</th><th>count</th><th>title</th>
		</tr>
		<c:forEach var="movie" items="${movieList}" varStatus="status">
			<tr>
				<td>${status.index }</td>
				<td>${status.count }</td>
				<td>${movie}</td>
			</tr>
		</c:forEach>
	</table>
	<hr>
	<span>집합체의 first, last 선택</span>
	<ul>
		<c:forEach var="movie" items="${movieList}" varStatus="status">
			<c:choose>
				<c:when test="${status.first}">
					<li style="font-weight: bold; color:red">${movie}</li>
				</c:when>
				<c:otherwise>
					<li>${movie}</li>
				</c:otherwise>
			</c:choose>
		</c:forEach>
	</ul>
	<c:forEach var="movie" items="${movieList }" varStatus="status">
		${movie }
		<c:if test="${not status.last }" >,</c:if>
	</c:forEach>
	<hr>
	<h2>악세사리</h2>
	관심항목을 선택하세요
	<br>
	<form method="get" action="17_paramValues.jsp"> <!-- post에서 한글깨지는 이유? -->
		<input type="checkbox" name="item" value="신발">신발 
		<input type="checkbox" name="item" value="가방">가방
		<input type="checkbox" name="item" value="벨트">벨트<br>
		<input type="checkbox" name="item" value="모자">모자 
		<input type="checkbox" name="item" value="시계">시계
		<input type="checkbox" name="item" value="쥬얼리">쥬얼리<br>
		<input type="submit" value="전송">
	</form>
	<hr>
	<c:forTokens var="city" items="서울.인천,대구.부산" delims=",">
		${city}<br>
	</c:forTokens>
	<br>
	<c:forTokens var="city" items="서울.인천,대구.부산" delims=",.">
		${city}<br>
	</c:forTokens>
	<hr>
	<h3>url import</h3>
	<c:import url="http://localhost:8080/Jsp_recheck/form1.jsp" var="data"></c:import>
	${data }
	<hr>
	<c:url value="img/img1.jpg" var="data" />
	<h3>${data}</h3>
	<img src="${data}" width='150' height='150'>
	<hr>
	c:redirect 예제, 주석처리로 막아놓음.
	<%--
	<c:redirect url="form1.jsp"></c:redirect>
	--%>
	<hr>
	<h3>c:out, c:remove, c:catch</h3>
	
	<c:set var="age" value="30" scope="page" />
	나이:<c:out value="${age }">10</c:out>
	<br>
	
	<c:remove var="age" scope="page"/>
	나이:<c:out value="${age }">10</c:out>
	<br>
	
	<c:catch var="errmsg">
	예외발생 전<br>
	<%=1/0 %><br>
	예외발생 후<br>
	</c:catch>
	<c:out value="${errmsg}"></c:out>
	
</body>
</html>