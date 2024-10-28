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
<!-- link 태그 // -->

<style>
  /* 사이드 바 및 버튼 CSS */
  .sidebar {
    position: fixed;
    bottom: 76px;
    left: 50px;
    width: 60px;
    height: 250px;
    background-color: #008cd6;
    color: #fff;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    border-top-left-radius: 40px;
    border-top-right-radius: 40px;
    transform: translateY(100%);
    transition: transform 0.3s ease, background-color 0.3s ease, opacity 0.5s ease;
    opacity: 0;
    overflow: hidden; /* 이미지가 영역에 맞게 */
  }

  .sidebar.visible {
    transform: translateY(0);
    background-color: #008cd6;
    opacity: 1;
  }

  .sidebar ul {
    list-style: none;
    padding: 0;
    margin: 0;
    display: flex;
    flex-direction: column;
    align-items: center;
  }

  .sidebar ul li {
    margin: 10px 0;
  }

  .sidebar ul li a {
    color: #fff;
    text-decoration: none;
    font-size: 14px;
  }

  .sidebar ul li a:hover {
    color: #ddd;
  }

  .toggleButton {
    position: fixed;
    bottom: 50px;
    left: 50px;
    width: 60px;
    height: 60px;
    background-color: #008cd6;
    color: white;
    border: none;
    border-radius: 50%;
    display: flex;
    align-items: center;
    justify-content: center;
    cursor: pointer;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
    transition: background-color 0.3s ease;
    overflow: hidden;
    z-index: 30;
  }

  .toggleButton:hover {
    background-color: #008acc;
  }

  .hiddenToggleBottom {
    position: fixed;
    bottom: 0px;
    left: 44px;
    width: 66px;
    height: 80px;
    overflow: hidden;
    background-color: white;
    z-index: 15;
  }
</style>
</head>
<body>
        <div class="sideBarContainer">
            <div class="sidebar visible">
                <ul>
                    <li><a href="#">버튼1</a></li>
                    <li><a href="#">버튼2</a></li>
                    <li><a href="#">버튼3</a></li>
                    <li><a href="#">버튼4</a></li>
                </ul>
            </div>
            <button class="toggleButton">메뉴</button>
            <div class="hiddenToggleBottom"></div>
        </div>
</body>
</html>
