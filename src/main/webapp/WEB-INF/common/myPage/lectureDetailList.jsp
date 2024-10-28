<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>강의 상세 정보</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #f5f5f5;
        }
        .lecture-info {
            margin-bottom: 30px;
        }
        .detail-list {
            margin-top: 20px;
        }
    </style>
</head>
<body>
    <h1>강의 상세 정보</h1>
    <c:if test="${not empty lectureDTOs}">
        <c:forEach var="lecture" items="${lectureDTOs}">
            <div class="lecture-info">
                <h2>${lecture.lectureName}</h2>
                <table>
                    <tr>
                        <th>강의 코드</th>
                        <td>${lecture.lectureCode}</td>
                    </tr>
                    <tr>
                        <th>선생님</th>
                        <td>${lecture.teacherName}</td>
                    </tr>
                    <tr>
                        <th>제한 날짜</th>
                        <td>${lecture.lectureLimitDate}</td>
                    </tr>
                </table>

                <div class="detail-list">
                    <h3>강의 자료</h3>
                    <c:if test="${not empty lecture.lectureDetails}">
                        <table>
                            <thead>
                                <tr>
                                    <th>회차</th>
                                    <th>내용</th>
                                    <th>자료</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="detail" items="${lecture.lectureDetails}" varStatus="status">
                                    <tr>
                                        <td>${status.count}회차</td>
                                        <td>${detail.lectureDetailContent}</td>
                                        <td>
                                            <a href="${pageContext.request.contextPath}/lecture/download?detailIdx=${detail.lectureDetailIdx}">
                                                ${detail.lectureDetailFileName}
                                            </a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </c:if>
                    <c:if test="${empty lecture.lectureDetails}">
                        <p>등록된 강의 자료가 없습니다.</p>
                    </c:if>
                </div>
            </div>
        </c:forEach>
    </c:if>
    <c:if test="${empty lectureDTOs}">
        <p>강의 정보를 불러올 수 없습니다. 결제 여부를 확인해 주세요.</p>
    </c:if>
</body>
</html>
