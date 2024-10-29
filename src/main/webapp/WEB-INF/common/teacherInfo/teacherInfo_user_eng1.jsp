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

<style>
	.link:hover {
		text-decoration: underline;
	}
</style>

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
				<div class="teacherDetailImg" style="background-color: #FEF1E8">
					<div class="center">
						<img src="/public/teacher_eng/eng_kim.jpg" alt="" />
						<div class="teacherInfo">
							<!-- 선생님 이름 동적으로 변경 -->
							<div class="teacherName">${teacherInfo.userName} 선생님</div>
							<p class="teacherProfile">약력</p>
							<p>
							    前) 한일초등학교 영어회화 전문강사<br />
							    現) 고촌사관학원 영어과 고등부 팀장
							</p>
							<p class="teacherProfile">저서</p>
							<p>
							    수능특강 상세분석집 큐 공동저서<br />
							    고등학생이 알아야할 중학교문법 알파고 고1 모의고사 변형문제
							</p>
						</div>
					</div>
				</div>
			</div>
			<div class="center" style="margin-top: 30px">
				<div class="teacherDetailContainer"></div>
			</div>
			<div class="center" style="display:flex; justify-content: space-around; margin-bottom:50px">
					<div>
					<a href="/gotoPostList.do?type=D&teacherId=${teacherInfo.userId}" class="link">${teacherInfo.userName} 선생님의 자료실 +</a>
					</div>
					<div>
					<a href="/gotoQnaList.do?type=T&teacherId=${teacherInfo.userId}" class="link">${teacherInfo.userName} 선생님의 QnA +</a>
					</div>
			</div>
			<img
				src="https://high.milkt.co.kr/UploadFile/LecturerCurriculum/599d90b0-dab4-495e-8c30-c3f62745d78d20230703090225.jpg"
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
											src="https://high.milkt.co.kr/UploadFile/LecturerImg/Img10_295x335_0.png"
											alt="" class="teacherCircleImg" />
										<div class="textCenter">
											<span>영어</span> ${teacherInfo.userName}
										</div>
									</div>
									<div class="teacherLectureArea">
										<div class="di">
											<div>
												<div id="lectureCode">강의 코드 : ${lecture.lectureCode}</div>
												<div>
													<span class="best">BEST</span> <span
														class="lectureTitleBold">
													<a href="${pageContext.request.contextPath}/lecture/common/lectureDetail.do?code=${lecture.lectureCode}">${lecture.lectureName}</a></span>
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
											src="https://high.milkt.co.kr/UploadFile/LecturerImg/Img10_295x335_0.png"
											alt="" class="teacherCircleImg" />
										<div class="textCenter">
											<span>영어</span> ${teacherInfo.userName}
										</div>
									</div>
									<div class="teacherLectureArea">
										<div class="di">
											<div>
												<div id="lectureCode">강의 코드 : ${lecture.lectureCode}</div>
												<div>
													<span class="best">BEST</span> <span
														class="lectureTitleBold"><a
														href="${pageContext.request.contextPath}/lecture/common/lectureDetail.do?lectureCode=${lecture.lectureCode}">${lecture.lectureName}</a></span>
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
