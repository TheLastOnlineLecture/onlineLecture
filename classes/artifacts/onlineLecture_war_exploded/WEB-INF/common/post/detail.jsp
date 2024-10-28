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
<link rel="stylesheet" href="/stylegroup/list/list.css" />
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
                <div class="moveList"><a href="gotoPostList.do?type=${boardDTO.boardType}" class="boardTitle" data-board-type="${boardDTO.boardType}">${boardDTO.boardType} ></a></div>
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
                    <span style="font-weight: bold">댓글 (${commentCount})</span>
                    <span style="position: relative">
                      <img
                        src="/public/menuBar.png"
                        alt=""
                        class="menuQ"
                        onclick="toggleBottomBar()"
                      />
                      <form id="postFrm" action="deletePost.do" method="POST">
                        <input type="hidden" name="idx" value="${boardDTO.boardIdx}"/>
						<input type="hidden" name="type" value="${boardDTO.boardType}"/>
	                      <div class="bottomBar" id="bottomBar">
	                        <div>
	                        	<button type="button" onclick="location.href = 'gotoPostModify.do?idx=${boardDTO.boardIdx}'" style="border:none; background-color:white; font-size:16px; cursor:pointer;">수정</button>
	                        </div>
	                        <div
	                          class="navMainBoundary"
	                          style="margin: 10px 0 10px 0"
	                        ></div>
	                        <div>
	                          <a href="#" onclick="confirmDelete()">삭제</a>
	                        </div>
	                      </div>
                      </form>
                    </span>
                  </div>
                </div>
              </div>
              <div class="navMainBoundary"></div>
              <div class="boardMainContent">
                ${boardDTO.boardContent}
              </div>
                    <c:if test="${!empty fileList}">
					    <c:set var="file" value="${fileList[0]}" />
					    <ul>
					        <li>
					            <c:choose>
					                <c:when test="${file.fileName.endsWith('.jpg') || file.fileName.endsWith('.jpeg') || file.fileName.endsWith('.png') || file.fileName.endsWith('.gif')}">
					                    <img src="/uploads/${file.filePath}" alt="${file.fileName}" width="100%" height="auto">
					                	 <a href="downloadFile.do?filePath=${file.filePath}&fileName=${file.fileName}">
										    ${file.fileName}
										</a>
					                </c:when>
					                <c:otherwise>
					                    <a href="downloadFile.do?filePath=${file.filePath}&fileName=${file.fileName}">
					                        ${file.fileName}
					                    </a>
					                </c:otherwise>
					            </c:choose>
					        </li>
					    </ul>
					</c:if>
              <div class="navMainBoundary"></div>
              <c:if test="${boardDTO.boardType != 'R' && boardDTO.boardType != 'N' && boardDTO.boardType != 'D' && boardDTO.boardType != 'C'}">
              <div class="boardDetailDown">
                <div style="margin-bottom: 30px">
                  <img src="/public/comment.png" class="boardCommentImg" />
                  <span style="font-weight: bold">댓글 (${commentCount})</span>
                </div>
                <c:forEach var="comment" items="${commentList}">
                <div>
                  <div style="margin: 10px 0 10px 0">
                    <div class="commentUserArea">
                      <img
                        src="/public/myprofile.png"
                        alt=""
                        class="commentProfile"
                      />
                      <div style="margin-left: 5px">${comment.userId}</div>
                    </div>
                    <div>${comment.commentContent}</div>
                    <!-- <div><img src="" /></div> -->
                    <div class="boardRegdate">${comment.commentRegdate}</div>
                  </div>
                  <div class="navMainBoundary"></div>
                </div>
                </c:forEach>
                <div class="commentWrite">
                  <form
                    action="postCommentWrite.do"
                    method="post"
                    id="commentFrm"
                    onsubmit="return validateComment()"
                  >
                  	<input type="hidden" name="boardIdx" value="${boardDTO.boardIdx}"/> 
                    <div class="commentMyNickName" id="userNickname">
                      ${sessionScope.user.userId}
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
              </c:if>
            </div>
          </div>
        </div>
      </main>
      <!-- 메인 콘텐츠 영역 // -->

		<!-- // 푸터 영역 -->
		<jsp:include page="../commonArea/footer.jsp" />
		<!-- 푸터 영역 // -->
		<script src="/jsgroup/list/listDetail.js"></script>
</body>
</html>
