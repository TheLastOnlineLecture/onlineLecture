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
<h3> 자유 게시글 상세보기 내가 쓴글 체크해서 지우는 버튼 </h3>
<form id="postFrm" action="deletePost.do" method="POST">
	<input type="hidden" name="idx" value="${boardDTO.boardIdx}"/>
	<h2>${boardDTO.boardTitle}</h2>
	<p><strong>작성자:</strong> ${boardDTO.boardWriter}</p>
	<p><strong>등록일:</strong> ${boardDTO.boardRegdate}</p>
	<p><strong>내용:</strong></p>
	<div>${boardDTO.boardContent}</div>
	
	<button type="button" onclick="gotoModify()">수정</button>
	<button onclick="gotoDelete()">삭제</button>
</form>
<c:if test="${not empty commentList}">
	<h3>댓글 목록</h3>
	<ul>
	    <c:forEach var="comment" items="${commentList}">
	        <li>${comment.commentContent} - <strong>${comment.userId}</strong> ${comment.commentRegdate}</li>
	    </c:forEach>
	</ul>
</c:if>
<form id="commentFrm" action="postCommentWrite.do" method="POST">
    <input type="hidden" name="boardIdx" value="${boardDTO.boardIdx}"/> 
    <textarea name="commentContent" rows="4" cols="50" placeholder="댓글을 입력하세요" required></textarea><br>
    <button type="submit">댓글작성</button>
</form>

</body>

<script>
function gotoModify() {
    location.href = "gotoPostModify.do?idx=${boardDTO.boardIdx}"; 
}
function gotoDelete() {
    if (confirm("정말 삭제하시겠습니까?")) { 
    	document.getElementById("postFrm").submit();
    }
}
</script>
</html>