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
<link rel="stylesheet" href="/stylegroup/main/styles.css" />
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css" />
<link rel="icon" href="/public/channels4_profile.jpg" type="image/png" />
<link rel="stylesheet" href="/stylegroup/lecture/lecture.css" />

<!-- link 태그 // -->

</head>
<body>
	<div class="boxContainer">
		<!-- // 상단 이미지 -->
		<jsp:include page="../../common/commonArea/pageTopImageArea.jsp" />
		<!-- 상단 이미지 // -->
		
		<!-- // navbar 영역 -->
		<jsp:include page="../../common/commonArea/header.jsp" />
		<!-- navbar 영역 // -->

		<!-- // 메인 콘텐츠 영역 -->
		<main class="center" id="mainContent">
		<div class="lectureCon">
			<div class="fl">
			<div>
		    <div class="lecture-header">
		        <h1>강의명 : ${lecture.lectureName}</h1>
		        <h4 class="nameT" data-code="${lecture.teacherName}">강사: ${lecture.teacherName}</h4>
		    </div>
			<div>
				<img src="/public/teacher_kor/kor_kwon.jpg" id="imgT" />
			</div>
			</div>
		    <div class="lecture-content">
		    	<div>
			        <c:if test="${not empty lectureDetails}">
			            <c:forEach var="detail" items="${lectureDetails}">
			                <div class="lecture-detail">
			                    <p>강의 설명 : ${detail.lectureDetailContent}</p>
			                    <c:if test="${not empty detail.lectureDetailFileName}">
			                        <div class="file-download">
			                            <a href="/lecture/common/watchLecture.do?lectureCode=${detail.lectureCode}&detailIdx=${detail.lectureDetailIdx}" 
	                                   class="watch-btn">강의 시청하기</a>
			                        </div>
			                    </c:if>
			                </div>
			            </c:forEach>
			        </c:if>
		    	</div>
		    </div>
			</div>
		
		    <a href="/lecture/common/lectureList.do" class="back-btn">강의 목록으로 돌아가기</a>
		    
		    </div>
		</main>
		<!-- 메인 콘텐츠 영역 // -->

		<!-- // 푸터 영역 -->
		<jsp:include page="../../common/commonArea/footer.jsp" />
		<!-- 푸터 영역 // -->
		
	</div>
<script>
    document.addEventListener("DOMContentLoaded", function() {
        const teacherName = "${lecture.teacherName}";
        const imgElement = document.getElementById("imgT");
        const mainContent = document.getElementById("mainContent");

        switch (teacherName) {
            case '권순구':
                imgElement.src = "/public/teacher_kor/kor_kwon.jpg";
                mainContent.style.backgroundColor = "#E0F8FA";
                break;
            case '손유빈':
                imgElement.src = "/public/teacher_kor/kor_son.jpg";
                mainContent.style.backgroundColor = "#E0F8FA";
                break;
            case '엄은나':
                imgElement.src = "/public/teacher_kor/kor_um.jpg";
                mainContent.style.backgroundColor = "#E0F8FA";
                break;
            case '강력한 형제':
                imgElement.src = "/public/teacher_math/math_bro.jpg";
                mainContent.style.backgroundColor = "#EBF7EB";
                break;
            case '박정은':
            	imgElement.src = "/public/teacher_math/math_park.jpg";
            	mainContent.style.backgroundColor = "#EBF7EB";
                break;
            case '전준홍':
            	imgElement.src = "/public/teacher_math/math_jeon.jpg";
            	mainContent.style.backgroundColor = "#EBF7EB";
                break;
            case '김연하':
                imgElement.src = "/public/teacher_eng/eng_kim.jpg";
                mainContent.style.backgroundColor = "#FFF1E8";
                break;
            case '권오륜':
            	imgElement.src = "/public/teacher_eng/eng_ryun.jpg";
            	mainContent.style.backgroundColor = "#FFF1E8";
                break;
            case '윤빈':
            	imgElement.src = "/public/teacher_eng/eng_yoon.jpg";
            	mainContent.style.backgroundColor = "#FFF1E8";
                break;
            case '한소유':
                imgElement.src = "/public/teacher_society/society_han.jpg";
                mainContent.style.backgroundColor = "#ECF7FD";
                break;
            case '차목언':
            	imgElement.src = "/public/teacher_society/society_cha.jpg";
            	mainContent.style.backgroundColor = "#ECF7FD";
                break;
            case '문병일':
            	imgElement.src = "/public/teacher_society/society_moon.jpg";
            	mainContent.style.backgroundColor = "#ECF7FD";
                break;
            case '황유하':
                imgElement.src = "/public/teacher_science/science_hwang.jpg";
                mainContent.style.backgroundColor = "#F4EFEB";
                break;
            case '정구륜':
            	imgElement.src = "/public/teacher_science/science_jeong.jpg";
            	mainContent.style.backgroundColor = "#F4EFEB";
                break;
            case '마진호':
            	imgElement.src = "/public/teacher_science/science_ma.jpg";
            	mainContent.style.backgroundColor = "#F4EFEB";
                break;
            default:
                imgElement.src = "/public/teacher_kor/kor_kwon.jpg";
            	mainContent.style.backgroundColor = "#E0F8FA";
                break;
        }
    });
</script>
</body>
</html>
