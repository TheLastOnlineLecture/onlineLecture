<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3> 1:1 qna작성 작성  </h3>
<form action="qnaWrite.do" enctype="multipart/form-data" method="post">
<input type="hidden" name="type" value="${qnaType}"/>
<!-- 	<label for="boardType">게시판 유형:</label> -->
<!-- 	<input type="text"  name="boardType" required><br> -->
	
	<label for="qnaTitle">제목:</label>
	<input type="text" name="qnaTitle" required><br>
	
	<label for="qnaContent">내용:</label><br>
	<textarea  name="qnaContent" rows="5" cols="40" required></textarea><br>
	
	<label for="qnaWriter">작성자:</label>
	<input type="text" name="qnaWriter" required><br>
	
	<input type="submit" value="등록">
</form>

</body>
</html>