<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>강의 목록</title>
    <script>
    <c:if test="${message != null}">
        alert('${message}');
    </c:if>
    function addToCart(lectureCode) {
        fetch('${pageContext.request.contextPath}/lecture/user/insertCart.do', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
            },
            body: 'lectureCode=' + encodeURIComponent(lectureCode)
        })
        .then(response => response.json())
        .then(result => {
            alert(result.message);
        })
        .catch(error => {
            console.error('Error:', error);
            alert('서버와의 통신 중 오류가 발생했습니다.');
            });
        }
    </script>
</head>
<body>
    <h1>강의 목록</h1>
    
    <!-- 과목별 링크 -->
    <div>
        <h2>강의 목록</h2>
        <ul>
            <li><a href="${pageContext.request.contextPath}/lecture/common/lectureList.do?filterBy=subject&filterValue=KOR">국어</a></li>
            <li><a href="${pageContext.request.contextPath}/lecture/common/lectureList.do?filterBy=subject&filterValue=MATH">수학</a></li>
            <li><a href="${pageContext.request.contextPath}/lecture/common/lectureList.do?filterBy=subject&filterValue=ENG">영어</a></li>
            <li><a href="${pageContext.request.contextPath}/lecture/common/lectureList.do?filterBy=subject&filterValue=KOH">한국사</a></li>
            <li><a href="${pageContext.request.contextPath}/lecture/common/lectureList.do?filterBy=subject&filterValue=SOC">사회</a></li>
            <li><a href="${pageContext.request.contextPath}/lecture/common/lectureList.do?filterBy=subject&filterValue=SCI">과학</a></li>
            <li><a href="${pageContext.request.contextPath}/lecture/common/lectureList.do?filterBy=subject&filterValue=SEC">제2외국어</a></li>
        </ul>
    </div>

    <!-- 정렬 및 필터링 폼 -->
    <form action="${pageContext.request.contextPath}/lecture/common/lectureList.do" method="get">
        <select name="sortBy">
            <option value="price_asc" ${sortBy == 'price_asc' ? 'selected' : ''}>가격 낮은순</option>
            <option value="price_desc" ${sortBy == 'price_desc' ? 'selected' : ''}>가격 높은순</option>
            <option value="date_desc" ${sortBy == 'date_desc' ? 'selected' : ''}>최신순</option>
            <option value="date_asc" ${sortBy == 'date_asc' ? 'selected' : ''}>오래된순</option>
        </select>
        <select name="filterBy">
            <option value="">필터 선택</option>
            <option value="title" ${filterBy == 'title' ? 'selected' : ''}>강의명 검색</option>
            <option value="teacher" ${filterBy == 'teacher' ? 'selected' : ''}>선생님별 검색</option>
        </select>
        <input type="text" name="filterValue" value="${filterValue}" placeholder="필터 값 입력">
        <input type="submit" value="적용">
    </form>

    <c:if test="${not empty sessionScope.user}">
        <form action="${pageContext.request.contextPath}/lecture/user/insertMultCart.do" method="post">
            <!-- 강의 목록 -->
            <table border="1">
                <tr>
                    <th>선택</th>
                    <th>강의 코드</th>
                    <th>강의명</th>
                    <th>가격</th>
                    <th>등록일</th>
                    <th>선생님</th>
                    <th>장바구니</th>
                </tr>
                <c:forEach var="lecture" items="${lectures}">
                    <tr>
                        <td><input type="checkbox" name="selectedLectures" value="${lecture.lectureCode}"></td>
                        <td>${lecture.lectureCode}</td>
                        <td><a href="${pageContext.request.contextPath}/lecture/common/lectureDetail.do?code=${lecture.lectureCode}">${lecture.lectureName}</a></td>
                        <td>${lecture.lecturePrice}원</td>
                        <td>${lecture.lectureRegdate}</td>
                        <td><a href="${pageContext.request.contextPath}/lecture/common/lectureList.do?filterBy=teacher&filterValue=${lecture.teacherName}">${lecture.teacherName}</a></td>
                        <td>
                            <button type="button" onclick="addToCart('${lecture.lectureCode}')">장바구니에 담기</button>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <input type="submit" value="선택한 강의 장바구니에 담기">
        </form>
    </c:if>

    <c:if test="${empty sessionScope.user}">
        <!-- 강의 목록 (비로그인 사용자용) -->
        <table border="1">
            <tr>
                <th>강의 코드</th>
                <th>강의명</th>
                <th>가격</th>
                <th>등록일</th>
                <th>선생님</th>
            </tr>
            <c:forEach var="lecture" items="${lectures}">
                <tr>
                    <td>${lecture.lectureCode}</td>
                    <td><a href="${pageContext.request.contextPath}/lecture/common/lectureDetail.do?code=${lecture.lectureCode}">${lecture.lectureName}</a></td>
                    <td>${lecture.lecturePrice}원</td>
                    <td>${lecture.lectureRegdate}</td>
                    <td><a href="${pageContext.request.contextPath}/gotoTeacherInfo.do?teacherId=${lecture.teacherId}">${lecture.teacherName}</a></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>

    <!-- 페이징 -->
    <div>
        <c:if test="${currentPage > 1}">
            <a href="${pageContext.request.contextPath}/lecture/common/lectureList.do?page=${currentPage - 1}&sortBy=${sortBy}&filterBy=${filterBy}&filterValue=${filterValue}">이전</a>
        </c:if>
        
        <c:forEach begin="1" end="${totalPages}" var="i">
            <c:choose>
                <c:when test="${i == currentPage}">
                    <strong>${i}</strong>
                </c:when>
                <c:otherwise>
                    <a href="${pageContext.request.contextPath}/lecture/common/lectureList.do?page=${i}&sortBy=${sortBy}&filterBy=${filterBy}&filterValue=${filterValue}">${i}</a>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        
        <c:if test="${currentPage < totalPages}">
            <a href="${pageContext.request.contextPath}/lecture/common/lectureList.do?page=${currentPage + 1}&sortBy=${sortBy}&filterBy=${filterBy}&filterValue=${filterValue}">다음</a>
        </c:if>
    </div>
</body>
</html>
