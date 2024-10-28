<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>내 강의 목록</title>
</head>
<body>
    <h1>내 강의 목록</h1>
    <c:if test="${not empty lectureList}">
        <table border="1">
            <tr>
                <th>강의 코드</th>
                <th>강의명</th>
                <th>가격</th>
                <th>제한 날짜</th>
                <th>선생님</th>
            </tr>
            <c:forEach var="lecture" items="${lectureList}">
                <tr>
                    <td>${lecture.lectureCode}</td>
                    <td>${lecture.lectureName}</td>
                    <td>${lecture.lecturePrice}원</td>
                    <td>${lecture.lectureLimitDate}</td>
                    <td>${lecture.teacherName}</td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
    <c:if test="${empty lectureList}">
        <p>등록된 강의가 없습니다.</p>
    </c:if>
</body>
</html>

