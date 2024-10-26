<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ page import="java.net.URLEncoder" %>
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
	<input type="hidden" name="type" value="${boardDTO.boardType}"/>
	<h2>${boardDTO.boardTitle}</h2>
	<p><strong>작성자:</strong> ${boardDTO.boardWriter}</p>
	<p><strong>등록일:</strong> ${boardDTO.boardRegdate}</p>
	<p><strong>내용:</strong></p>
	<div>${boardDTO.boardContent}</div>
<!-- 	<img src="/uploads/board/4bde963a-2510-4357-8d85-2b61161d4b2e_119850714.jpeg" /> -->
	<button type="button" onclick="gotoModify()">수정</button>
	<button onclick="gotoDelete()">삭제</button>
</form>
<h3>첨부 파일</h3>
<c:if test="${!empty fileList}">
    <c:set var="file" value="${fileList[0]}" />
    <ul>
        <li>
            <c:choose>
                <c:when test="${file.fileName.endsWith('.jpg') || file.fileName.endsWith('.jpeg') || file.fileName.endsWith('.png') || file.fileName.endsWith('.gif')}">
                    <img src="uploads/${file.filePath}" alt="${file.fileName}" width="200px" height="200px">
                	 <a href="downloadFile.do?filePath=${file.filePath}&fileName=${file.fileName}">
					    ${file.fileName}
					</a>
                </c:when>
                <c:otherwise>
                    <a href="downloadFile.do?filePath=${file.filePath}&fileName=${file.fileName}">
                        ${file.fileName}
                    </a>
                </c:otherwise>
            </c:choose>
        </li>
    </ul>
</c:if>
<c:if test="${empty fileList}">
    <p>첨부된 파일이 없습니다.</p>
</c:if>
<c:if test="${boardDTO.boardType != 'R'}">
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
</c:if>
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