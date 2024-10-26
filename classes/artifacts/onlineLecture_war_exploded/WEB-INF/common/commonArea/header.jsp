<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/stylegroup/main/styles.css" />
<link rel="stylesheet" href="/stylegroup/main/loginStyle.css" />
<meta charset="UTF-8">
<title>chunjaeHaebup</title>
</head>
<body>
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
				<span class="dDay">2025학년도 수능 <strong id="dDayCount">수능날짜가
						동적으로 바뀜</strong></span> <span>|</span> <a href="#">협업기업</a><span>|</span> <a
					href="/inquiy/user/gotoInquiy.do
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
							<img src="/public/logo.png" alt="logo" class="logoImg" />
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
								<li class="submenuContentGroup3"><a href="#">FrontEnd</a> <a
									href="#">BackEnd</a> <a href="#">Cloud</a></li>
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
								<button class="loginPopupButton">Login</button>
								<!-- 로그인하면 여기 유저 이름 들어올 예정 -->
								<span id="changeLogin"></span>
								<a href="/goto.do?page=mypage">나의 학습방</a> <a href="/goto.do?page=modify">회원정보 수정</a> <a href="#">결제내역</a>
								<a href="#">장바구니 목록</a>
							</div>
						</div>
					</div>
				</nav>
			</div>
		</div>
	</div>
	<div class="loginContainer"></div>
	<div class="navMainBoundary"></div>
	<script src="/jsgroup/main/script.js"></script>
	<!-- navbar 영역 // -->
</body>
</html>