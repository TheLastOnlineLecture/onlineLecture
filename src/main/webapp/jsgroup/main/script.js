let currentIndex = 0;

function showSlide(index) {
  const slides = document.querySelector(".slides");
  const slideWidth = document.querySelector(".slider").offsetWidth;
  slides.style.transform = `translateX(${-slideWidth * index}px)`;
  currentIndex = index;
}

// --- 수능 날짜 계산 영역
const dDay = new Date("2024-11-14");

const today = new Date();

const timeDifference = dDay.getTime() - today.getTime();

const daysRemaining = Math.ceil(timeDifference / (1000 * 60 * 60 * 24));

document.getElementById("dDayCount").textContent = `D-${daysRemaining}`;
// 수능 날짜 계산 영역 ---
