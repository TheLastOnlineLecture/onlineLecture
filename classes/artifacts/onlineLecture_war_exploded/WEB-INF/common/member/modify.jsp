<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3> 회원정보수정  </h3>
<form action="${pageContext.request.contextPath}/member/common/modify.do" method="post">
<input type="hidden" name="userType" value="${user.userType}">
<input type="text" name="userId" value="${user.userId}" readonly>
<input type="text" name="userPwd" value="${user.userPwd}" readonly>
<input type="text" name="userName" value="${user.userName}" readonly>
<input type="text" name="userNickname" value="${user.userNickname}">
<input type="text" name="userEmail" value="${user.userEmail}">
<input type="text" name="userPhone" value="${user.userPhone}">
<input type="submit" value="수정하기">
</form>
</body>
</html>