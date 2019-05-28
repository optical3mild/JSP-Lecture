<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	request.setCharacterEncoding("UTF-8");
	String name = request.getParameter("name");
	String gender = request.getParameter("gender");
//	String season = request.getParameter("season");
	
	
	switch(gender){
	case "male":
		gender="남자";
		break;
	case "female":
		gender="여자";
		break;
	default:
		break;
	}
	
// 	switch(season){
// 	case "1":
// 		season="봄";
// 		break;
// 	case "2":
// 		season="여름";
// 		break;
// 	case "3":
// 		season="가을";
// 		break;
// 	case "4":
// 		season="겨울";
// 		break;
// 	default:
// 		break;
// 	}

	//checkbox의 경우
	String season[] = request.getParameterValues("season");
	
	out.println("이름: "+name+"<br>");
	out.println("성별: "+gender+"<br>");
//	out.println("좋아하는 계절: "+season+"<br>");
	out.println("좋아하는 계절: ");
	for(int i=0; i<season.length;i++){ //배열에 저장된것을 모두 처리
		int n = Integer.parseInt(season[i]);
		switch(n){
		case 1: out.print("봄"); break;
		case 2: out.print("여름"); break;
		case 3: out.print("가을"); break;
		case 4: out.print("겨울"); break;
		}
		if(season.length > 1){
			if(i < season.length-1)
				out.print(", ");
		}
	}
	
%>

</body>
</html>