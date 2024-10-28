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
<h3> 자유게시글 작성  </h3>
${boardType}
<form action="postWrite.do" enctype="multipart/form-data" method="post">
<input type="hidden" name="type" value="${boardType}"/>
<!-- 	<label for="boardType">게시판 유형:</label> -->
<!-- 	<input type="text"  name="boardType" required><br> -->
	
	<label for="boardTitle">제목:</label>
	<input type="text" name="boardTitle" required><br>
	
	<label for="boardContent">내용:</label><br>
	<textarea  name="boardContent" rows="5" cols="40" required></textarea><br>
	
	<label for="boardWriter">작성자:</label>
	<input type="text" name="boardWriter" required><br>
	
	<input type="submit" value="등록">
	<c:if test="${boardDTO.boardType != 'R' && boardDTO.boardType != 'N' && boardDTO.boardType != 'C'}">
		<input type="file" name="attachedFile">
	</c:if>
</form>

</body>
</html>