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
        <h1 class="textCenter" style="margin-bottom: 30px">마이페이지</h1>
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
                  <div class="myProFile">
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
                      <span>0</span>
                      <!-- 여기 총 강의 갯수 들어갈 예정 -->
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
            <div class="studyCardUpBar">
              <h3>현재 수강중인 강좌</h3>
              <button id="myLectureButton">나의 학습방으로 이동▶</button>
            </div>
            <div class="myStudyArea">
              <div class="myStudyVideoList">
                <!-- 아무것도 없으면 -->
                <!-- <div class="noStudyVideo">현재 수강중인 강의가 없습니다.</div> -->
                <a
                  href="/lecture/common/gotoLectureDetail.do?"
                  class="studyCardLink"
                >
                  <div class="studyCard">
                    <div class="cardImage">강의 썸네일 1</div>
                    <h4>강의 제목 1</h4>
                    <p>
                      강의 설명강의 설명강의 설명강의 설명강의 설명강의 설명강의
                      설명강의 설명강의 설명강의 설명 강의 설명강의 설명강의
                      설명강의 설명 강의 설명
                    </p>
                  </div>
                </a>
                <a
                  href="/lecture/common/gotoLectureDetail.do?"
                  class="studyCardLink"
                >
                  <div class="studyCard">
                    <div class="cardImage">강의 썸네일 2</div>
                    <h4>강의 제목 2</h4>
                    <p>강의 설명</p>
                  </div>
                </a>
                <a
                  href="/lecture/common/gotoLectureDetail.do?"
                  class="studyCardLink"
                >
                  <div class="studyCard">
                    <div class="cardImage">강의 썸네일 3</div>
                    <h4>강의 제목 3</h4>
                    <p>강의 설명</p>
                  </div>
                </a>
                <a
                  href="/lecture/common/gotoLectureDetail.do?"
                  class="studyCardLink"
                >
                  <div class="studyCard">
                    <div class="cardImage">강의 썸네일 4</div>
                    <h4>강의 제목 4</h4>
                    <p>강의 설명</p>
                  </div>
                </a>
              </div>
            </div>
          </div>
        </div>
        <div class="ccenter">
          <div class="myStudyVideo" style="margin: 50px 0 50px 0">
            <div class="myStudyVideo marginTop100">
              <h3>내가 작성한 글</h3>
              <div>
                <table class="boardTable">
                  <thead>
                    <tr>
                      <th>분류</th>
                      <th>제목</th>
                      <th>등록일</th>
                    </tr>
                  </thead>
                  <tbody>
                    <tr>
                      <td><span class="label free">자유게시판</span></td>
                      <td class="titleSummary">
                        <a href="/gotoPostDetail.do?idx=?">졸린 사람?</a>
                      </td>
                      <td>2024-10-21</td>
                    </tr>
                    <tr>
                      <td><span class="label qna">1:1 QnA</span></td>
                      <td class="titleSummary">
                        <a href="/gotoPostDetail.do?idx=?"
                          >선생님 저 이거 모르겠어요</a
                        >
                      </td>
                      <td>2024-10-11</td>
                    </tr>
                    <tr>
                      <td><span class="label free">자유게시판</span></td>
                      <td class="titleSummary">
                        <a href="/gotoPostDetail.do?idx=?">제목</a>
                      </td>
                      <td>등록일</td>
                    </tr>
                  </tbody>
                </table>
                <div class="pagination">
                  <!-- "[" + pageNum + "]" 형식으로 들어올 예정 -->
                  <button>[1]</button>
                  <button>[2]</button>
                  <button>[3]</button>
                </div>
              </div>
            </div>
            <div class="myStudyVideo marginTop100">
              <h3>내가 작성한 댓글</h3>
              <div>
                <table class="boardTable">
                  <thead>
                    <tr>
                      <th>분류</th>
                      <th>원문</th>
                      <th>댓글 내용</th>
                      <th>등록일</th>
                      <th>작성자</th>
                    </tr>
                  </thead>
                  <tbody>
                    <tr>
                      <td><span class="label free">자유게시판</span></td>
                      <td>
                        <a href="/gotoPostDetail.do?idx=?">졸린 사람?</a>
                      </td>
                      <td class="commentSummary">졸리면 주무세요</td>
                      <td>2024-10-21</td>
                      <td>한덕용</td>
                    </tr>
                    <tr>
                      <td><span class="label qna">1:1 QnA</span></td>
                      <td class="titleSummary">
                        <a href="/gotoPostDetail.do?idx=?"
                          >선생님 저 이거 모르겠어요</a
                        >
                      </td>
                      <td class="commentSummary">
                        이걸 이렇게 하고 저걸 저렇게 하면 된단다. 개념과 같이
                        동작원리를
                        <!-- 대충 길이 정해놓고 잘리도록 자바스크립트 수정 -->
                      </td>
                      <td>2024-10-11</td>
                      <td>한덕용</td>
                    </tr>
                    <tr>
                      <td><span class="label qna">1:1 QnA</span></td>
                      <td class="titleSummary">
                        <a href="/gotoPostDetail.do?idx=?"
                          >제목이 엄청 길어요 많이 길어요제목이 엄청 길어요 많이
                          길어요제목이 엄청 길어요 많이 길어요</a
                        >
                      </td>
                      <td class="commentSummary">
                        댓글도 엄청 길어요 많이 길어요댓글도 엄청 길어요 많이
                        길어요댓글도 엄청 길어요 많이 길어요댓글도 엄청 길어요
                        많이 길어요댓글도 엄청 길어요 많이 길어요
                        <!-- 대충 길이 정해놓고 잘리도록 자바스크립트 수정 -->
                      </td>
                      <td>2024-10-11</td>
                      <td>한덕용</td>
                    </tr>
                    <tr>
                      <td><span class="label free">자유게시판</span></td>
                      <td class="titleSummary">
                        <a href="/gotoPostDetail.do?idx=?">원문 제목</a>
                      </td>
                      <td class="commentSummary">댓글 내용</td>
                      <td>등록일</td>
                      <td>작성자</td>
                    </tr>
                  </tbody>
                </table>
                <div class="pagination">
                  <!-- "[" + pageNum + "]" 형식으로 들어올 예정 -->
                  <button>[1]</button>
                  <button>[2]</button>
                  <button>[3]</button>
                </div>
              </div>
            </div>
            <div class="myStudyVideo marginTop100">
              <h3>나의 결제 목록</h3>
              <div>
                <table class="boardTable">
                  <thead>
                    <tr>
                      <th>과목</th>
                      <th>강의 제목</th>
                      <th>강의 코드</th>
                      <th>구매일</th>
                      <th>금액</th>
                    </tr>
                  </thead>
                  <tbody>
                    <tr>
                      <td><span class="label kor">국어</span></td>
                      <td>
                        <a href="/gotoPostDetail.do?idx=?">국어 강의</a>
                      </td>
                      <td class="commentSummary">졸리면 주무세요</td>
                      <td>2024-10-21</td>
                      <td>10,000</td>
                    </tr>
                    <tr>
                      <td><span class="label mat">수학</span></td>
                      <td class="titleSummary">
                        <a href="/gotoPostDetail.do?idx=?"
                          >선생님 저 이거 모르겠어요</a
                        >
                      </td>
                      <td class="commentSummary">수학 강의</td>
                      <td>2024-10-11</td>
                      <td>10,000</td>
                    </tr>
                    <tr>
                      <td><span class="label eng">영어</span></td>
                      <td class="titleSummary">
                        <a href="/gotoPostDetail.do?idx=?">영어 강의</a>
                      </td>
                      <td class="commentSummary">
                        영어 강의 코드
                        <!-- 대충 길이 정해놓고 잘리도록 자바스크립트 수정 -->
                      </td>
                      <td>2024-10-11</td>
                      <td>10,000</td>
                    </tr>
                    <tr>
                      <td><span class="label soc">사탐</span></td>
                      <td class="titleSummary">
                        <a href="/gotoPostDetail.do?idx=?">원문 제목</a>
                      </td>
                      <td class="commentSummary">댓글 내용</td>
                      <td>등록일</td>
                      <td>10,000</td>
                    </tr>
                    <tr>
                      <td><span class="label sci">과탐</span></td>
                      <td class="titleSummary">
                        <a href="/gotoPostDetail.do?idx=?">원문 제목</a>
                      </td>
                      <td class="commentSummary">댓글 내용</td>
                      <td>등록일</td>
                      <td>10,000</td>
                    </tr>
                  </tbody>
                </table>
                <div class="pagination">
                  <!-- "[" + pageNum + "]" 형식으로 들어올 예정 -->
                  <button>[1]</button>
                  <button>[2]</button>
                  <button>[3]</button>
                </div>
              </div>
            </div>
            <div class="myStudyVideo marginTop100">
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
