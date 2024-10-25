<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>결제페이지</title>

</head>
<body>
    <h1>장바구니</h1>
    <!-- action 경로 수정 -->
    <form id="paymentForm" action="${pageContext.request.contextPath}/payments/user/payments.do" method="post">
        <table>
            <thead>
                <tr>
                    <th>선택</th>
                    <th>강의 코드</th>
                    <th>강의명</th>
                    <th>가격</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="item" items="${cartItems}">
                    <tr>
                        <td>
                            <input type="checkbox" name="selectedLectures" value="${item.lectureCode}" 
                                   data-price="${item.lecturePrice}" onchange="updateTotal()" checked>
                        </td>
                        <td>${item.lectureCode}</td>
                        <td>${item.lectureName}</td>
                        <td><fmt:formatNumber value="${item.lecturePrice}" type="currency" currencySymbol="₩" /></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <div class="total">
            총 결제 금액: <span id="totalAmount">₩0</span>
            현재 잔액: <span id="mileage">₩${mileage}</span>
            결제 후 잔액: <span id="afterMileage">₩0</span>
        </div>
        <button type="submit" id="payButton" disabled>결제하기</button>
    </form>

    <script>
        // 결제 후 잔액 계산
        function calculateAfterMileage() {
            const totalAmount = document.getElementById('totalAmount').textContent.replace('₩', '');
            const afterMileage = document.getElementById('mileage').textContent.replace('₩', '') - totalAmount;
            document.getElementById('afterMileage').textContent = '₩' + afterMileage.toLocaleString();
        }
        document.querySelector('input[name="selectedLectures"]').addEventListener('change', calculateAfterMileage);
        
        window.onload = function() {
            updateTotal();
        }
        function updateTotal() {
            const checkboxes = document.querySelectorAll('input[name="selectedLectures"]:checked');
            let total = 0;
            checkboxes.forEach(checkbox => {
                total += parseInt(checkbox.dataset.price);
            });
            document.getElementById('totalAmount').textContent = '₩' + total.toLocaleString();
            document.getElementById('payButton').disabled = checkboxes.length === 0;
        }
    </script>
</body>
</html>
