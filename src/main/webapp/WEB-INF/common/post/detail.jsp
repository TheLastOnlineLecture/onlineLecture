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
        <div class="mainArea">
          <div class="boardDetailContainer">
            <div class="boardDetailContent">
              <div class="boardDetailUp">
                <div class="moveList"><a href="gotoPostList.do?type=${boardType}" class="boardTitle" data-board-type="${boardType}">${boardType} ></a></div>
                <div class="boardDetailTitle">${boardDTO.boardTitle}</div>
                <div
                  class="boardDetailUserInfo"
                  style="justify-content: space-between"
                >
                  <div class="boardDetailUserInfo">
                    <img
                      src="/public/myprofile.png"
                      alt=""
                      class="boardProfileImg"
                    />
                    <div class="boardDetailUserInfoCon">
                      <span class="boardNick">${boardDTO.boardWriter}</span>
                      <div>
                        <span class="boardRegdate">${boardDTO.boardRegdate}</span>
                      </div>
                    </div>
                  </div>
                  <div style="margin-right: 30px">
                    <img src="/public/comment.png" class="boardCommentImg" />
                    <span style="font-weight: bold">댓글 (2)</span>
                    <span style="position: relative">
                      <img
                        src="/public/menuBar.png"
                        alt=""
                        class="menuQ"
                        onclick="toggleBottomBar()"
                      />
                      <div class="bottomBar" id="bottomBar">
                        <div><a href="#">수정</a></div>
                        <div
                          class="navMainBoundary"
                          style="margin: 10px 0 10px 0"
                        ></div>
                        <div>
                          <a href="#" onclick="confirmDelete()">삭제</a>
                        </div>
                      </div>
                    </span>
                  </div>
                </div>
              </div>
              <div class="navMainBoundary"></div>
              <div class="boardMainContent">
                컨텐츠 영역 컨텐츠 영역 컨텐츠 영역 컨텐츠 영역 컨텐츠 영역
                컨텐츠 영역 컨텐츠 영역 컨텐츠 영역 컨텐츠 영역 컨텐츠 영역
                컨텐츠 영역 컨텐츠 영역 컨텐츠 영역 컨텐츠 영역 컨텐츠 영역
                컨텐츠 영역 컨텐츠 영역 컨텐츠 영역 컨텐츠 영역 컨텐츠 영역
                컨텐츠 영역 컨텐츠 영역 컨텐츠 영역 컨텐츠 영역 컨텐츠 영역
                컨텐츠 영역 컨텐츠 영역 컨텐츠 영역 컨텐츠 영역 컨텐츠 영역
                컨텐츠 영역 컨텐츠 영역 컨텐츠 영역 컨텐츠 영역 컨텐츠 영역
                컨텐츠 영역 컨텐츠 영역
              </div>
              <div class="navMainBoundary"></div>
              <div class="boardDetailDown">
                <div style="margin-bottom: 30px">
                  <img src="/public/comment.png" class="boardCommentImg" />
                  <span style="font-weight: bold">댓글 (2)</span>
                </div>
                <div>
                  <div style="margin: 10px 0 10px 0">
                    <div class="commentUserArea">
                      <img
                        src="/public/myprofile.png"
                        alt=""
                        class="commentProfile"
                      />
                      <div style="margin-left: 5px">댓글 닉네임1</div>
                    </div>
                    <div>댓글 내용0</div>
                    <div class="boardRegdate">regDate</div>
                  </div>
                  <div class="navMainBoundary"></div>
                </div>
                <div>
                  <div style="margin: 10px 0 10px 0">
                    <div class="commentUserArea">
                      <img
                        src="/public/myprofile.png"
                        alt=""
                        class="commentProfile"
                      />
                      <div style="margin-left: 5px">댓글 닉네임2</div>
                    </div>
                    <div>댓글 내용0</div>
                    <div class="boardRegdate">regDate</div>
                  </div>
                  <div class="navMainBoundary"></div>
                </div>
                <div class="commentWrite">
                  <form
                    action="#"
                    method="post"
                    onsubmit="return validateComment()"
                  >
                    <div class="commentMyNickName" id="userNickname">
                      내 닉네임
                    </div>
                    <textarea
                      id="commentContent"
                      name="commentContent"
                      placeholder="댓글을 남겨보세요."
                      oninput="updateCharCount()"
                    ></textarea>
                    <div class="formActions">
                      <button class="submitBtn" type="submit">등록</button>
                      <span id="charCount" class="charCount">0/1000</span>
                    </div>
                  </form>
                </div>
              </div>
            </div>
          </div>
        </div>
      </main>
      <!-- 메인 콘텐츠 영역 // -->

		<!-- // 푸터 영역 -->
		<jsp:include page="../commonArea/footer.jsp" />
		<!-- 푸터 영역 // -->
		<script src="/jsGroup/post/listDetail.js"></script>
	</div>
</body>
</html>
