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
 <form action="/gotoTotalPostList.do" method="get" class="search-container">
        <label for="type">타입:</label>
        <select name="type" id="type">
            <option value="">전체</option>
            <option value="P">일반 게시글</option>
            <option value="D">자료실</option>
            <option value="N">공지사항</option>
            <option value="C">강의 공지</option>
            <option value="R">강의 리뷰</option>
        </select>

        <label for="searchType">검색 조건:</label>
        <select name="searchType" id="searchType">
            <option value="category">강의코드</option>
            <option value="title">제목</option>
            <option value="writer">작성자</option>
            <option value="title_content">제목+내용</option>
        </select>

        <label for="keyword">검색어:</label>
        <input type="text" name="keyword" id="keyword" value="${param.keyword}" />

        <button type="submit">검색</button>
    </form>
<button onclick="location.href='/goto.do?page=post/write&type=N'">공지사항 작성</button>
<button onclick="deleteSelected()">삭제</button>
    <!-- 검색 결과 -->
    <table>
        <thead>
            <tr>
                 <th><input type="checkbox" id="allCheck" onclick="allChecks(this)"></th>
                <th>번호</th>
                <th>타입</th>
                <th>카테고리</th>
                <th>제목</th>
                <th>작성자</th>
                <th>등록일</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="board" items="${boardList}">
                <tr>
                    <td><input type="checkbox" name="boardCheck" value="${board.boardIdx}"></td>
                    <td>${board.boardIdx}</td>
                    <td>${board.boardType}</td>
                    <td>${board.boardCategory}</td>
                    <td><a href="gotoPostDetail.do?idx=${board.boardIdx}">${board.boardTitle}</a></td>
                    <td>${board.boardWriter}</td>
                    <td>${board.boardRegdate}</td>
                </tr>
            </c:forEach>
            <c:if test="${empty boardList}">
                <tr>
                    <td colspan="6" style="text-align:center;">검색 결과가 없습니다.</td>
                </tr>
            </c:if>
        </tbody>
    </table>
	<div class="pagination">
	   <c:if test="${pagination.hasFirstPage()}">
	       <a href="?pageNo=1"><<</a>
	   </c:if>
	   <c:if test="${pagination.hasPreviousBlock()}">
	       <a href="?pageNo=${pagination.blockStartPage - 1}"><</a>
	   </c:if>
	
	   <c:forEach var="i" begin="${pagination.blockStartPage}" end="${pagination.blockEndPage}">
			<a href="?pageNo=${i}&type=${param.type}&searchType=${param.searchType}&keyword=${param.keyword}&category=${param.category}">${i}</a>
	   </c:forEach>
	
	   <c:if test="${pagination.hasNextBlock()}">
	       <a href="?pageNo=${pagination.blockEndPage + 1}">></a>
	   </c:if>
	   <c:if test="${pagination.hasLastPage()}">
	       <a href="?pageNo=${pagination.totalPages}">>></a>
	   </c:if>
	</div>
<table>

<script>
    // 전체 체크/해제 기능
    function allChecks(checkbox) {
        const checkboxes = document.querySelectorAll('input[name="boardCheck"]');
        checkboxes.forEach((item) => {
            item.checked = checkbox.checked;
        });
    }
    
    // 선택된 게시글 삭제
    function deleteSelected() {
        const checkboxes = document.querySelectorAll('input[name="boardCheck"]:checked');
        const idx = Array.from(checkboxes).map(checkbox => checkbox.value);
        
        if (idx.length === 0) {
            alert('삭제할 게시글을 선택해주세요.');
            return;
        }
        
        if (confirm('선택한 게시글을 삭제하시겠습니까?')) {
            const form = document.createElement('form');
            form.method = 'POST';
            form.action = '/deleteList.do'; 
            
            const input = document.createElement('input');
            input.type = 'hidden';
            input.name = 'idx'; 
            input.value = idx.join(','); 
            
            form.appendChild(input);
            document.body.appendChild(form);
            form.submit(); 
        }
    }
    
</script>
</body>
</body>
</html>