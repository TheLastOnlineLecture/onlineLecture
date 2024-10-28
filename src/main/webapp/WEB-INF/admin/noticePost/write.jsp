<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3> 공지사항 작성  </h3>
${boardType}
<form action="postWrite.do" enctype="multipart/form-data" method="post">
<input type="hidden" name="type" value="${boardType}"/>
<!-- 	<label for="boardType">게시판 유형:</label> -->
<!-- 	<input type="text"  name="boardType" required><br> -->
	
	<label for="boardTitle">제목:</label>
	<input type="text" name="boardTitle" required><br>
	
	<label for="boardContent">내용:</label><br>
	<textarea  name="boardContent" rows="5" cols="40" required></textarea><br>
	
	<input type="hidden" name="boardWriter" value=${sessionScope.user.userId}><br>
	
	<input type="submit" value="등록">
</form>
</body>
</html>