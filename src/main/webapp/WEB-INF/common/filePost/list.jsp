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
<h3>자료실</h3>
<table border="1">
	<tr>
		<th>제목</th>
		<th>작성자</th>
		<th>작성일</th>
	</tr>
	<c:choose>
		<c:when test="${not empty boardList}">
			<c:forEach var="board" items="${boardList}">
				<tr>
					<td><a href="gotoPostDetail.do?idx=${board.boardIdx}">${board.boardTitle}</a></td>
					<td>${board.boardWriter}</td>
					<td>${board.boardRegdate}</td>
				</tr>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<tr>
				<td colspan=3>등록된 게시글 x</td>
			</tr>
		</c:otherwise>
	</c:choose>
	<tr>
		<td colspan=3>
			<div class="pagination">
                <c:if test="${pagination.hasFirstPage()}">
                    <a href="?pageNo=1&type=${boardType}"><<</a>
                </c:if>
                <c:if test="${pagination.hasPreviousBlock()}">
                    <a href="?pageNo=${pagination.blockStartPage - 1}&type=${boardType}"><</a>
                </c:if>
                
                <c:forEach var="i" begin="${pagination.blockStartPage}" end="${pagination.blockEndPage}">
                    <a href="?pageNo=${i}&type=${boardType}">${i}</a>
                </c:forEach>

                <c:if test="${pagination.hasNextBlock()}">
                    <a href="?pageNo=${pagination.blockEndPage + 1}&type=${boardType}">></a>
                </c:if>
                <c:if test="${pagination.hasLastPage()}">
                    <a href="?pageNo=${pagination.totalPages}&type=${boardType}">>></a>
                </c:if>
            </div>
		</td>
	</tr>
</table>
<a href="/goto.do?page=post/write&type=${boardType}">작성</a>


</body>
</html>