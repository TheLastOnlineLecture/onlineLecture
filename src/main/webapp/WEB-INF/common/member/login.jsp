<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${error != null}">
    <script>
        alert('${error}');
    </script>
</c:if>
<!DOCTYPE html> 
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3> 로그인  </h3>
<form action="${pageContext.request.contextPath}/member/common/login.do" method="post">
    <input type="text" name="userId" placeholder="아이디">
    <input type="password" name="userPwd" placeholder="비밀번호">
    <button type="submit">로그인</button>   
</form>
</body>
</html>