<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3> 자유게시글 수정 내가 쓴것만 </h3>

<form method="post" action="modifyPost.do">
    <input type="hidden" name="idx" value="${boardDTO.boardIdx}"/> <!-- 게시글 ID를 hidden 필드로 전송 -->
    <p><strong>제목:</strong></p>
    <input type="text" name="title" value="${boardDTO.boardTitle}" required/>
    <p><strong>내용:</strong></p>
    <textarea name="content" rows="10" cols="30" required>${boardDTO.boardContent}</textarea>
    <br/>
    <button type="submit">수정 하기</button>
</form>
</body>
</html>