<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/stylegroup/list/list.css" />
<title>Insert title here</title>
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
			<!-- <h3 class="textCenter">자유게시글 작성</h3>  -->
	        <div class="postWriteContainer">
	          <h2 class="postTitle">글 작성</h2>
	          <form
	            action="postWrite.do"
	            method="post"
	            class="postForm"
	            enctype="multipart/form-data"
	            onsubmit="return validateForm()"
	          >
	          <input type="hidden" name="type" value="${boardType}"/>
	          <input type="hidden" name="boardWriter" value="${sessionScope.user.userId}"/>
	            <!-- 제목 입력 -->
	            <div class="formGroup">
	              <label for="postTitle" class="postLabel">제목</label>
	              <input
	                type="text"
	                id="postTitle"
	                name="boardTitle"
	                class="postInput"
	                placeholder="제목을 입력하세요"
	                oninput="updateCharCount('title')"
	                required
	              />
	              <span id="titleCharCount" class="charCount">0/30</span>
	            </div>
	
	            <!-- 내용 입력 -->
	            <div class="formGroup">
	              <label for="postContent" class="postLabel">내용</label>
	              <textarea
	                id="postContent"
	                name="boardContent"
	                class="postTextarea"
	                placeholder="내용을 입력하세요"
	                oninput="updateCharCount('content')"
	                required
	              ></textarea>
	              <span id="contentCharCount" class="charCount">0/3000</span>
	            </div>
	
	            <!-- 등록 및 취소 버튼 -->
	            <div class="formActions">
	              <button type="submit" class="submitBtn">등록</button>
	              <button
	                type="button"
	                class="cancelBtn"
	                onclick="window.location.href='/post/common/gotoPostList.do'"
	              >
	                취소
	              </button>
	            </div>
	            <c:if test="${boardDTO.boardType != 'R' && boardDTO.boardType != 'N' && boardDTO.boardType != 'C'}">
					<input type="file" name="attachedFile">
				</c:if>
	          </form>
	        </div>
	      </main>
		<!-- 메인 콘텐츠 영역 // -->

		<!-- // 푸터 영역 -->
		<jsp:include page="../commonArea/footer.jsp" />
		<!-- 푸터 영역 // -->
		
	</div>
	

</body>
</html>