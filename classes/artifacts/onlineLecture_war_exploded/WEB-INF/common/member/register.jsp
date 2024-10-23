<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3> 회원가입  </h3>
<form action="member/common/register.do" method="post">
    <input type="text" name="userId" placeholder="아이디" required><br>
    <input type="password" name="userPwd" placeholder="비밀번호" required><br>
    <input type="text" name="userName" placeholder="이름" required><br>
    <input type="email" name="userEmail" placeholder="이메일" required><br>
    <input type="tel" name="userPhone" placeholder="전화번호" required><br>
    <input type="date" name="userBirth" placeholder="생년월일" required><br>
    <input type="text" name="userNickname" placeholder="닉네임" required><br>
    <button type="submit">회원가입</button>
</form>
</body>
</html>