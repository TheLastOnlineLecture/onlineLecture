<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>스마트 해법</title>

<!-- // link태그 -->
<link rel="stylesheet"
	href="<c:url value="/stylegroup/main/styles.css" />" />
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css" />
<link rel="icon" href="/public/channels4_profile.jpg" type="image/png" />
<link rel="stylesheet" href="/stylegroup/teacher/teacherDetail.css" />

<!-- link 태그 // -->
</head>
<body>
	<script>
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
	<div class="boxContainer">
		<!-- // 상단 이미지 -->
		<jsp:include page="../commonArea/pageTopImageArea.jsp" />
		<!-- 상단 이미지 // -->

		<!-- // navbar 영역 -->
		<jsp:include page="../commonArea/header.jsp" />
		<!-- navbar 영역 // -->

		<!-- // 메인 콘텐츠 영역 -->
		<main>
			<div class="center">
				<div class="teacherDetailImg" style="background-color:#EBF7EB">
					<div class="center">
						<img src="/public/teacher_math/math_park.jpg" alt="" />
						<div class="teacherInfo">
							<!-- 선생님 이름 동적으로 변경 -->
							<div class="teacherName">${teacherInfo.userName} 선생님</div>
							<p class="teacherProfile">약력</p>
							<p>
							    성균관대학교 수학과 졸업<br />
							    現) 천재교육 밀크T중학 수학 강사<br />
							    現) (주)올림피아드 수학 강사<br />
							    前) 수박씨닷컴 수학 대표강사<br />
							    前) 이투스ECI 수학 대표강사<br />
							    前) 대성N학원 수학과 대표강사<br />
							    前) 청솔학원 수학과 대표강사<br />
							    前) 국제중/고 심화 수학 강의
							</p>
							<p class="teacherProfile">저서</p>
							<p>
							    (주) 두산동아- 일등예감 집필<br />
							    (주) 두산동아- 빨리 이해하는 수학 집필
							</p>
						</div>
					</div>
				</div>
			</div>
			<div class="center" style="margin-top: 30px">
				<div class="teacherDetailContainer"></div>
			</div>
			<img
				src="https://high.milkt.co.kr/UploadFile/LecturerCurriculum/f2741957-69e6-43ba-a715-51cee6274a6220230612030849.jpg"
				alt="" width="100%" />

			<div class="center">
				<div class="teacherLectureList">
					<div class="teacherDetailContainer">
						<h2>개설 강좌 목록</h2>
						<div class="navMainBoundary" style="margin-bottom: 30px"></div>
						<c:if test="${not empty sessionScope.user}">
							<c:forEach var="lecture" items="${lectures}">
								<form class="teacherLectureListContent" method="post"
									id="lectureForm">
									<input type="hidden" name="lectureCode" value="강의 코드 값" /> <input
										type="hidden" name="lectureTitle" value="강의 제목" /> <input
										type="hidden" name="teacherName" value="권순구" />
									<div>
										<img
											src="https://high.milkt.co.kr/UploadFile/LecturerImg/193_%EB%B0%95%EC%A0%95%EC%9D%80_Img10_0.png"
											alt="" class="teacherCircleImg" />
										<div class="textCenter">
											<span>수학</span> ${teacherInfo.userName}
										</div>
									</div>
									<div class="teacherLectureArea">
										<div class="di">
											<div>
												<div id="lectureCode">강의 코드 : ${lecture.lectureCode}</div>
												<div>
													<span class="best">BEST</span> <span
														class="lectureTitleBold"><a
														href="${pageContext.request.contextPath}/lecture/common/lectureDetail.do?code=${lecture.lectureCode}">${lecture.lectureName}</a></span>
												</div>
												<div>
													<a href="#">강의 수강평</a>
												</div>
												<div>강의 등록일 : ${lecture.lectureRegdate}</div>
											</div>
										</div>
										<div>
											<button class="lecturePayBtn" type="button"
												onclick="addToCart('${lecture.lectureCode}')">
												장바구니</button>
											<div class="lecturePayValue">
												<span>${lecture.lecturePrice}원</span>
											</div>
										</div>
									</div>
								</form>
							</c:forEach>
						</c:if>
						<c:if test="${empty sessionScope.user}">
							<!-- 강의 목록 (비로그인 사용자용) -->

							<c:forEach var="lecture" items="${lectures}">
								<div class="teacherLectureListContent">
									<div>
										<img
											src="https://high.milkt.co.kr/UploadFile/LecturerImg/193_%EB%B0%95%EC%A0%95%EC%9D%80_Img10_0.png"
											alt="" class="teacherCircleImg" />
										<div class="textCenter">
											<span>수학</span> ${teacherInfo.userName}
										</div>
									</div>
									<div class="teacherLectureArea">
										<div class="di">
											<div>
												<div id="lectureCode">강의 코드 : ${lecture.lectureCode}</div>
												<div>
													<span class="best">BEST</span> <span
														class="lectureTitleBold"><a
														href="${pageContext.request.contextPath}/lecture/common/lectureDetail.do?code=${lecture.lectureCode}">${lecture.lectureName}</a></span>
												</div>
												<div>
													<a href="#">강의 수강평</a>
												</div>
												<div>강의 등록일 : ${lecture.lectureRegdate}</div>
											</div>
										</div>
										<div>
											<div class="lecturePayValue ccenter">
												<span>금액 : ${lecture.lecturePrice}원</span>
											</div>
										</div>
									</div>
								</div>
							</c:forEach>
						</c:if>
					</div>
				</div>
			</div>
		</main>
		<!-- 메인 콘텐츠 영역 // -->

		<!-- // 푸터 영역 -->
		<jsp:include page="../commonArea/footer.jsp" />
		<!-- 푸터 영역 // -->

	</div>
</body>
</html>