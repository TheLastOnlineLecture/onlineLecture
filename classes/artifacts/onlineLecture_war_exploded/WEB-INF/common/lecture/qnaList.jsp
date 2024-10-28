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
<h3> 강의실 qna 리스트  </h3>
<form action="gotoQnaList.do" method="get">
    <input type="hidden" name="type" value="${qnaType}"/>
    <label for="searchType">검색 유형:</label>
    <select name="searchType" id="searchType">
        <option value="title">제목</option>
        <option value="writer">작성자</option>
        <option value="title_content">제목 + 내용</option>
    </select>
    <input type="text" name="searchKeyword" placeholder="검색어 입력" />
    <input type="submit" value="검색" />
</form>
<table border="1">
	<tr>
		<th>제목</th>
		<th>작성자</th>
		<th>작성일</th>
	</tr>
	<c:choose>
		<c:when test="${not empty qnaList}">
			<c:forEach var="qna" items="${qnaList}">
				<tr>
					<td><a href="gotoQnaDetail.do?idx=${qna.qnaIdx}">${qna.qnaTitle}</a></td>
					<td>${qna.qnaWriter}</td>
					<td>${qna.qnaRegdate}</td>
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
                    <a href="?pageNo=1&type=${qnaType}"><<</a>
                </c:if>
                <c:if test="${pagination.hasPreviousBlock()}">
                    <a href="?pageNo=${pagination.blockStartPage - 1}&type=${qnaType}"><</a>
                </c:if>
                
                <c:forEach var="i" begin="${pagination.blockStartPage}" end="${pagination.blockEndPage}">
                    <a href="?pageNo=${i}&type=${qnaType}">${i}</a>
                </c:forEach>

                <c:if test="${pagination.hasNextBlock()}">
                    <a href="?pageNo=${pagination.blockEndPage + 1}&type=${qnaType}">></a>
                </c:if>
                <c:if test="${pagination.hasLastPage()}">
                    <a href="?pageNo=${pagination.totalPages}&type=${qnaType}">>></a>
                </c:if>
            </div>
		</td>
	</tr>
</table>
<a href="/goto.do?page=user/qna/write&type=${qnaType}&qnaCategory=${qnaCategory}">작성</a>
</body>
</html>