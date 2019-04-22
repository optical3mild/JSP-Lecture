<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	//utf-8
	request.setCharacterEncoding("utf-8");
	
	//html폼에서 전달된 msg값을 가지고 옴
	String msg = request.getParameter("msg");
	
	//session에 저장된 로그인 사용자 이름을 가지고 옴
	Object username = session.getAttribute("user");
	
	//메시지 저장을 위해 application에서 msg로 저장된 ArrayList가지고 옴
	ArrayList<String> msgs = (ArrayList<String>) application.getAttribute("msgs");
	
	//null인 경우 새로운 ArrayList 객체를 생성
	if(msgs == null) {
		msgs = new ArrayList<String>();
		//application에 ArrayList 저장
		application.setAttribute("msgs",msgs);
	}
	
	//사용자 이름, 메시지, 날짜 정보를 포함하여 ArrayList에 추가
	msgs.add(username + " :: " +msg+" , "+new java.util.Date());
	
	//톰캣 콘솔을 통한 로깅
	application.log(msg+"추가됨");
	
	//목록 화면으로 리다이렉팅
	response.sendRedirect("twitter_list.jsp");
%>