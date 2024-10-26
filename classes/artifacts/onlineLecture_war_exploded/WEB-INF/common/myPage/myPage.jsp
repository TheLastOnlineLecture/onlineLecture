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
<link rel="stylesheet" href="<c:url value="/stylegroup/main/styles.css" />" />
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css" />
<link rel="icon" href="/public/channels4_profile.jpg" type="image/png" />
<link rel="stylesheet" href="/stylegroup/myPage/myPage.css" />

<!-- link 태그 // -->
</head>
<body>
	<div class="boxContainer">
		<!-- // 상단 이미지 -->
		<jsp:include page="../commonArea/pageTopImageArea.jsp" />
		<!-- 상단 이미지 // -->
		
		<!-- // navbar 영역 -->
		<jsp:include page="../commonArea/header.jsp" />
		<!-- navbar 영역 // -->

		<!-- // 메인 콘텐츠 영역 -->
		<main>
        <h1 class="textCenter" style="margin-bottom: 30px">나의 학습방</h1>
        <div class="ccenter">
          <div class="myPageMenu">
            <div class="myPageMenuGroup">
              <div class="myPageMenuGroupContent">
                <img src="/public/myprofile.png" alt="" /> &nbsp;내 프로필
              </div>
              <div class="myPageMenuGroupContent">
                <img src="/public/myplan.png" alt="" /> &nbsp;학습 현황
              </div>
              <div class="myPageMenuGroupContent">
                <img src="/public/event.png" alt="" /> &nbsp;이벤트
              </div>
            </div>
            <div class="myPageMenuGroup">
              <div class="myPageContent">
                <div class="ccenter">
                  <img
                    src="/public/channels4_profile.jpg"
                    alt=""
                    class="myProfileImg"
                  />
                  <div>
                    <h3>userName님</h3>
                    <p>등급 : 학생</p>
                    <p>포인트 : X점</p>
                    <p>새 쪽지 : 0개</p>
                  </div>
                </div>
              </div>
              <div>
                <div class="myPageMenuGroup">
                  <div class="myPageContent">
                    <div class="gang">
                      <p>현재 수강중인<br />강의 갯수</p>
                      <span>${lectureTotalCount}</span>
                    </div>
                  </div>
                </div>
              </div>
              <div>
                <div class="myPageMenuGroup">
                  <div class="myPageContent" style="background-color: #6e46ff">
                    <div>
                      <img src="/public/myPageEvent.jpg" alt="" width="100%" />
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="navMainBoundary" style="margin: 20px 0 20px 0"></div>
        <div class="ccenter">
          <div class="myStudyVideo">
            <h3>현재 수강중인 강좌</h3>
            <div class="myStudyArea">
              <c:choose>
                <c:when test="${empty lectureList}">
                  <div>현재 수강중인 강의가 없습니다.</div>
                </c:when>
                <c:otherwise>
                  <div class="myStudyVideoList">
                    <c:forEach var="lecture" items="${lectureList}">
                      <div class="studyCard">
                        <div class="cardImage">${lecture.lectureName}</div>
                        <h4>${lecture.lectureCode}</h4>
                        <p>강사: ${lecture.teacherName}</p>
                        <p>시작일: ${lecture.lectureStartDate}</p>
                      </div>
                    </c:forEach>
                  </div>
                </c:otherwise>
              </c:choose>
            </div>
          </div>
        </div>
        <div class="ccenter">
          <div class="myStudyVideo" style="margin: 50px 0 50px 0">
            <div class="myStudyVideo">
              <h3>강좌 프로모션</h3>
              <img src="/public/mainSlider.png" alt="" />
            </div>
          </div>
        </div>
      </main>
		<!-- 메인 콘텐츠 영역 // -->

		<!-- // 푸터 영역 -->
		<jsp:include page="../commonArea/footer.jsp" />
		<!-- 푸터 영역 // -->
		<script src="/jsgroup/myPage/myPage.js"></script>
		
	</div>
</body>
</html>
