<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3> 자유게시판 목록  </h3>

<table border="1">
        <tr>
            <th>번호</th>
            <th>제목</th>
            <th>작성자</th>
            <th>작성일</th>
        </tr>
		<c:choose>
			<c:when test="${not empty boardList}">
				<c:forEach var="board" items="${boardList}">
		            <tr>
		                <td>${board.id}</td>
		                <td><a href="detail?id=${board.id}">${board.title}</a></td>
		                <td>${board.writer}</td>
		                <td>${board.regDate}</td>
		            </tr>
		        </c:forEach>
			</c:when>
			<c:otherwise>
				<p>게시글 x </p>
			</c:otherwise>
		</c:choose>        
    </table>
</body>
</html>