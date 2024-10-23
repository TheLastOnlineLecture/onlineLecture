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
<link rel="stylesheet" href="<%= request.getContextPath() %>/stylegroup/main/styles.css" />
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css" />
<link rel="icon" href="/public/channels4_profile.jpg" type="image/png" />
<!-- link 태그 // -->
</head>
<body>
	<div class="boxContainer">
		<jsp:include page="common/commonArea/pageTopImageArea.jsp" />

		<!-- // navbar 영역 -->
		<div class="center">
			<div class="navTop">
				<div class="navTopContent">
					<a href="https://www.chunjae.co.kr/#/main" target="_blank">천재교육</a><span>|</span>
					<a href="https://www.milkt.co.kr/HME/sellpa" target="_blank">T셀파</a>
					<span>|</span> <a href="https://mall.chunjae.co.kr/#/main/main"
						target="_blank">천재교육 쇼핑몰</a> <span>|</span> <a
						href="https://www.genia.academy/" target="_blank">천재IT교육센터</a>
				</div>
				<div class="navTopContent">
					<span class="dDay">2025학년도 수능 <strong
						id="dDayCount">수능날짜가 동적으로 바뀜</strong></span> <span>|</span> <a href="#">협업기업</a><span>|</span>
					<a href="/inquiy/user/gotoInquiy.do
            ">고객센터</a>
				</div>
			</div>
		</div>
		<div class="navMainBoundary"></div>
		<div class="menuArea">
			<div class="center">
				<div>
					<nav class="navbarContainer">
						<div class="navbarMain">
							<div>
								<img src="public/logo.png" alt="logo" class="logoImg" />
							</div>
							<div style="width: 200px"></div>
							<ul class="navbarGroup">
								<li>브랜드 소개</li>
								<li>강의 목록</li>
								<li>프로그래밍</li>
								<li>게시판</li>
								<li>선생님</li>
								<li>자료실</li>
							</ul>
							<div class="submenu">
								<ul>
									<li class="submenuContentGroup1"><a href="#">브랜드 소개</a><a
										href="#">오시는 길</a></li>
									<li class="submenuContentGroup2"><a href="#">국어</a> <a
										href="#">수학</a> <a href="#">영어</a> <a href="#">한국사</a> <a
										href="#">사회</a> <a href="#">과학</a> <a href="#">제2외국어</a></li>
									<li class="submenuContentGroup3"><a href="#">FrontEnd</a>
										<a href="#">BackEnd</a> <a href="#">Cloud</a></li>
									<li class="submenuContentGroup4"><a
										href="/inquiy/user/gotoInquiy.do
                          ">1대1
											QnA 게시판</a> <a
										href="/post/user/gotoPostList.do
                          ">자유게시판</a>
									</li>
									<li class="submenuContentGroup5"><a href="#">국어</a> <a
										href="#">수학</a> <a href="#">영어</a> <a href="#">한국사</a> <a
										href="#">사회</a> <a href="#">과학</a> <a href="#">제2외국어</a></li>
									<li class="submenuContentGroup6">
										<!-- 근데 여기가 사이트 자료실인가 선생님 자료실인가? --> <a href="#">사이트 자료실</a>
									</li>
								</ul>
							</div>
							<div class="userButton">
								<button class="loginBtn">
									<img src="/public/loginBtn.png" alt="" />
								</button>
								<div class="dropdown-content">
									<p class="userName" style="padding-bottom: 30px">Login</p>
									<!-- 로그인하면 여기 유저 이름 들어올 예정 -->
									<button onclick="// 나의 학습방 가는 컨트롤러 이건 jsp에 옮기고 jstl로 넣자">
										나의 학습방</button>
									<a href="#">마이페이지</a> <a href="#">성적관리</a> <a href="#">결제내역</a>
									<a href="#">장바구니 목록</a>
								</div>
							</div>
						</div>
					</nav>
				</div>
			</div>
		</div>
		<!-- navbar 영역 // -->

		<!-- // 메인 콘텐츠 영역 -->
		<main>
			<div>
				<div class="swiper2 mySwiper underSlide">
					<div class="swiper-wrapper">
						<div class="swiper-slide">
							<a href="#"><img src="public/mainSlider_1.png" alt="" /></a>
						</div>
						<div class="swiper-slide">
							<a href="#"><img src="public/mainSlider_2.png" alt="" /></a>
						</div>
					</div>
				</div>
			</div>
			<div class="cheerUp">
				<div>
					<p>"수험생 여러분들! 수능 화이팅입니다!"</p>
					<span>수험 공부는 스마트 해법과 함께!</span>
				</div>
			</div>
			<div class="mainContainer1">
				<div class="sliderContainer">
					<div class="swiper mySwiper">
						<div class="swiper-wrapper">
							<div class="swiper-slide">
								<a href="#"><img
									src="https://high.milkt.co.kr/UploadFile/Banner/6b4e2b7e-462b-4940-b321-33253b7db1f920241022090551.jpg"
									alt="" /></a>
							</div>
							<div class="swiper-slide">
								<a href="#"><img
									src="https://high.milkt.co.kr/UploadFile/Banner/03b5b0f1-65f5-405c-b84d-084055ee307920241002112028.jpg"
									alt="" /></a>
							</div>
							<div class="swiper-slide">
								<a href="#"><img
									src="https://high.milkt.co.kr/UploadFile/Banner/ddb12eb2-1a52-429c-b2f1-e9a42f5fd54120240903085826.jpg"
									alt="" /></a>
							</div>
							<div class="swiper-slide">
								<a href="#"><img
									src="https://high.milkt.co.kr/UploadFile/Banner/06598473-c03b-40d6-a5ba-f5ed6ba9abb620240926124434.jpg"
									alt="" /></a>
							</div>
							<div class="swiper-slide">
								<a href="#"><img
									src="https://high.milkt.co.kr/UploadFile/Banner/8aecd4aa-431b-44d1-b730-cf81af60edac20240709091850.jpg"
									alt="" /></a>
							</div>
						</div>
						<div class="swiper-pagination"></div>
					</div>
					<div>두 번째 슬라이드 들어갈 영역</div>
				</div>
			</div>
			<div class="boardGroup">
				<!-- 게시판 요약 섹션 -->
				<div class="container">
					<!-- 체험 후기 섹션 -->
					<div class="box review">
						<h2>체험 후기</h2>
						<div class="user-info">
							<img src="/public/channels4_profile.jpg" alt="User Image" />
							<div>
								<strong>26살 한덕용 학습생</strong> <span>(duk****)</span>
							</div>
						</div>
						<p>저는 프론트엔드 개발자가 되고 싶었습니다. 그런데 사실 ui 작업도 그리 싫어하는 편이 아니긴 합니다.
							아니, 나름 좋아합니다. 그런데 짧은 시간 안에 이 사이트 디자인을 다 짜려니 좀 벅차네요 시발. 지금 새벽
							세시입니다. 살려주세요.</p>
					</div>

					<!-- 공지사항 섹션 -->
					<div class="box notice">
						<h2>공지사항</h2>
						<ul>
							<li><a href="#">[강좌] 2028 수능
									통합사회... <span>24.10.22</span>
							</a></li>
							<li><a href="#">[일반] AI 트레이닝 앰스트롱
									2학... <span>24.10.15</span>
							</a></li>
							<li><a href="#">[이벤트] AI 트레이닝 앰스트롱
									2학... <span>24.10.11</span>
							</a></li>
						</ul>
					</div>

					<!-- 고객센터 섹션 -->
					<div class="box notice">
						<h2>공지사항</h2>
						<ul>
							<li><a href="#">[강좌] 2028 수능
									통합사회... <span>24.10.22</span>
							</a></li>
							<li><a href="#">[일반] AI 트레이닝 앰스트롱
									2학... <span>24.10.15</span>
							</a></li>
							<li><a href="#">[이벤트] AI 트레이닝 앰스트롱
									2학... <span>24.10.11</span>
							</a></li>
						</ul>
					</div>
				</div>
			</div>
		</main>
		<!-- 메인 콘텐츠 영역 // -->

		<!-- // 푸터 영역 -->
		<footer class="footerContainer">
			<div class="footerContent">
				<img src="/public/logo.png" alt="로고" id="footerLogo" />
				<div>
					<p class="footerLink">
						<a href="#" style="padding-left: 0px">회사소개</a> | <a href="#">이용약관</a>
						| <a href="#">개인정보처리방침</a> | <a href="#">스마트해법수학 학습관</a>
					</p>
					<p>(주)천재교과서 대표자 : 박정과, 임형진 사업자등록번호 : 119-81-70643 부가통신사업 신고 번호
						: 016590</p>
					<p>통신판매신고서 : 제2016-서울금천-1306호 주소 : 서울 금천구 가산로9길 54 (가산동) 대표번호 :
						1661-0901</p>
					<p style="color: rgb(153, 153, 153)">Copyright ©
						CHUNJAETEXTBOOK Corp. All Rights Reserved</p>
				</div>
				<img src="/public/footerRight.png" alt="가맹문의" />
			</div>
		</footer>
	</div>
	<!-- 푸터 영역 // -->

	<script src="jsgroup/main/script.js"></script>

	<!-- Swiper JS -->
	<script
		src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>

	<!-- Initialize Swiper -->
	<script>
      var swiper = new Swiper(".mySwiper", {
        pagination: {
          el: ".swiper-pagination",
          dynamicBullets: true,
        },
        autoplay: {
          delay: 5000,
        },
      });
    </script>
</body>
</html>
