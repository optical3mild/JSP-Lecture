<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList" %>
<%@ page import="jspbook.ch06.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>add</title>
</head>
<body>
<%
	request.setCharacterEncoding("utf-8");
	String productname = request.getParameter("product");
	int quantity = Integer.parseInt(request.getParameter("quantity"));
	
	//session에 저장된 장바구니 list를 불러온다.
	ArrayList<CartDTO> list = (ArrayList<CartDTO>)session.getAttribute("productlist");
	if(list==null){ //list가 없을 시, 새로 생성
		list = new ArrayList<CartDTO>();
		session.setAttribute("productlist",list);	//새list 이름을 productlist로 하여 저장. 
	}
	CartDTO cart = new CartDTO();
	cart.setProductName(productname);
	cart.setQuantity(quantity);
	list.add(cart);
%>
	<script>
	alert("<%=productname %>이(가) <%=quantity%>개 추가되었습니다.");
	history.go(-1);
	</script>
</body>
</html>