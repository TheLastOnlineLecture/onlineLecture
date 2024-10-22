<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="net.haebup.utils.DBConnPool"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%

try {
	DBConnPool pool = new DBConnPool();
	
	out.println("==================================<br>");
	out.println("DB 커넥션 풀 생성 <br> ");
	out.println("DBConnPool : " + pool+"<br>");
	out.println("==================================");
	pool.close();
} catch(Exception e) {
	out.println("==================================<br>");
	out.println("DB 커넥션 풀 오류 <br> ");
	out.println("에러 : "+e.getMessage()+"<br>");
	out.println("==================================");
}
%>
</body>
</html>