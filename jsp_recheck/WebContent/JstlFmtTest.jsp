<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<pre>
		<c:set var="now" value="<%=new java.util.Date() %>" />
		\${now}: ${now}
		<fmt:formatDate value="${now}" />
		date: <fmt:formatDate value="${now}" type="date"/>
		time: <fmt:formatDate value="${now}" type="time"/>
		both: <fmt:formatDate value="${now}" type="both"/>
		default: <fmt:formatDate value="${now}" type="both" dateStyle="default" timeStyle="default"/>
		short: <fmt:formatDate value="${now}" type="both" dateStyle="short" timeStyle="default"/>
		medium: <fmt:formatDate value="${now}" type="both" dateStyle="medium" timeStyle="default"/>
		long: <fmt:formatDate value="${now}" type="both" dateStyle="long" timeStyle="default"/>
		full: <fmt:formatDate value="${now}" type="both" dateStyle="full" timeStyle="default"/>
		pattern="yyyy년 MM월 dd일 hh시 mm분 ss초" : <fmt:formatDate value="${now}" pattern="yyyy년 MM월 dd일 hh시 mm분 ss초"/>
	</pre>
	
	<hr>
	<h3>Time Zone</h3>
	<!-- JSP에서 java class를 사용하는 방법 -->
	<jsp:useBean id="now" class="java.util.Date"></jsp:useBean>
	<pre>
		default: <c:out value="${now}"  />
		
		Korea KST: <fmt:formatDate value="${now}" type="both" dateStyle="full" timeStyle="full"/>
		
		<fmt:timeZone value="GMT">
		Swiss GMT: <fmt:formatDate value="${now}" type="both" dateStyle="full" timeStyle="full"/>
		</fmt:timeZone>
		
		<fmt:timeZone value="GMT-8">
		NewYork GMT-8: <fmt:formatDate value="${now}" type="both" dateStyle="full" timeStyle="full"/>
		</fmt:timeZone>
	</pre>
	<hr>
	<h3>Locale</h3>
	<c:set var="now" value="<%=new java.util.Date() %>" />
	<pre>
		톰캣 서버의 기본 로케일: <%=response.getLocale() %>
		<fmt:setLocale value="ko_kr"/>
		로케일을 한국어로 설정 후 로케일 확인: <%=response.getLocale() %>
		통화: <fmt:formatNumber value="10000" type="currency"></fmt:formatNumber>
		날짜: <fmt:formatDate value="${now}"/>
		<br>
		<fmt:setLocale value="ja_jp"/>
		로케일을 일본어로 설정 후 로케일 확인: <%=response.getLocale() %>
		통화: <fmt:formatNumber value="10000" type="currency"></fmt:formatNumber>
		날짜: <fmt:formatDate value="${now}"/>
		<br>
		<fmt:setLocale value="en_US"/>
		로케일을 영어(미국)로 설정 후 로케일 확인: <%=response.getLocale() %>
		통화: <fmt:formatNumber value="10000" type="currency"></fmt:formatNumber>
		날짜: <fmt:formatDate value="${now}"/>
	</pre>
	<hr>
	<h3>requestEncoding</h3>
	<pre>
		check 필요
	</pre>
	<hr>
	
</body>
</html>