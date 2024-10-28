<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>관리자 페이지</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f5f5f5;
        }
        /* 네비게이션 바 스타일 */
        .navbar {
            background-color: #333;
            overflow: hidden;
        }
        .navbar a {
            float: left;
            display: block;
            color: white;
            text-align: center;
            padding: 14px 20px;
            text-decoration: none;
            font-size: 18px;
        }
        .navbar a:hover {
            background-color: #575757;
        }
        /* 페이지 콘텐츠 스타일 */
        .container {
            padding: 20px;
        }
        h1 {
            text-align: center;
            margin-bottom: 20px;
        }
        .search-container {
            text-align: center;
            margin-bottom: 20px;
        }
        .search-container input {
            padding: 10px;
            width: 200px;
            margin-right: 10px;
        }
        .search-container button {
            padding: 10px 15px;
            background-color: #333;
            color: white;
            border: none;
            cursor: pointer;
        }
        .search-container button:hover {
            background-color: #575757;
        }
        /* 테이블 스타일 */
        table {
            width: 100%;
            border-collapse: collapse;
            margin: 20px 0;
            background-color: white;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 10px;
            text-align: center;
        }
        th {
            background-color: #f0f0f0;
        }
        /* 페이징 스타일 */
        .pagination {
            text-align: center;
            margin-top: 10px;
        }
        .pagination a {
            margin: 0 5px;
            text-decoration: none;
            color: #333;
        }
    </style>
</head>
<body>
    
    <!-- 네비게이션 바 -->
    <div class="navbar">
        <a href="#">회원 관리</a>
        <a href="${pageContext.request.contextPath}/admin/lecture/manage?action=list">강의 관리</a>
        <a href="#">게시판 관리</a>
    </div>
    
    <!-- 메인 콘텐츠 -->
    <div class="container">
        <h1>회원 관리</h1>
    
        <!-- 검색 기능 -->
        <form action="${pageContext.request.contextPath}/admin/member/memberList.do" method="get">
            <div class="search-container">
                <select name="searchType">
                    <option value="name" ${searchType == 'name' ? 'selected' : ''}>이름</option>
                    <option value="id" ${searchType == 'id' ? 'selected' : ''}>아이디</option>
                </select>
                <input type="text" name="searchKeyword" value="${searchKeyword}">
                <button type="submit">검색</button>
            </div>
        </form>
    
        <!-- 회원 관리 테이블 -->
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>이름</th>
                    <th>닉네임</th>
                    <th>회원 유형</th>
                    <th>마일리지</th>
                    <th>전화번호</th>
                    <th>관리</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="member" items="${memberList}">
                    <tr>
                        <td>${member.userId}</td>
                        <td>${member.userName}</td>
                        <td>${member.userNickname}</td>
                        <td>${member.userType}</td>
                        <td>${member.mileage}</td>
                        <td>${member.userPhone}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/member/admin/gotoMemberModify.do?userId=${member.userId}">수정</a>
                            <button onclick="deleteMember('${member.userId}')">삭제</button>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    
        <!-- 페이징 -->
        <div class="pagination">
            <c:if test="${currentPage > 1}">
                <a href="?page=${currentPage - 1}&searchType=${searchType}&searchKeyword=${searchKeyword}">이전</a>
            </c:if>
            <c:forEach begin="1" end="${totalPages}" var="i">
                <c:choose>
                    <c:when test="${i == currentPage}">
                        <strong>${i}</strong>
                    </c:when>
                    <c:otherwise>
                        <a href="?page=${i}&searchType=${searchType}&searchKeyword=${searchKeyword}">${i}</a>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <c:if test="${currentPage < totalPages}">
                <a href="?page=${currentPage + 1}&searchType=${searchType}&searchKeyword=${searchKeyword}">다음</a>
            </c:if>
        </div>
    </div>
    <script>
        function deleteMember(userId) {
            if(confirm('정말로 이 회원을 삭제하시겠습니까?')) {
                fetch('${pageContext.request.contextPath}/member/admin/memberDelete.do', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded',
                    },
                    body: 'userId=' + encodeURIComponent(userId)
                })
                .then(response => response.json())
                .then(result => {
                    if(result.success) {
                        alert('회원이 삭제되었습니다.');
                        location.reload();
                    } else {
                        alert('회원 삭제에 실패했습니다.');
                    }
                })
                .catch(error => {
                    console.error('Error:', error);
                    alert('요청 처리 중 오류가 발생했습니다.');
                });
            }
        }
    </script>
</body>
</html>
