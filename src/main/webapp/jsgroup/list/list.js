const searchOption = document.getElementById("searchOption");
const searchInput = document.getElementById("searchInput");

searchOption.addEventListener("change", function () {
  const selectedOption = searchOption.value;

  if (selectedOption === "board_title") {
    searchInput.placeholder = "제목";
  } else if (selectedOption === "board_writer") {
    searchInput.placeholder = "아이디";
  } else if (selectedOption === "board_content") {
    searchInput.placeholder = "내용";
  } else if (selectedOption === "board_title_content") {
    searchInput.placeholder = "제목 + 내용";
  }
});

function goToWritePage() {
  location.href = "/inquiry/common/gotoWrite.do";
}

// 나중에 현재 페이지 찾고 색 바꾸기
const paginationButtons = document.querySelectorAll(".pagination button");

paginationButtons.forEach((button) => {
  // [1] 여기에 현재 페이지 들어가기
  if (button.innerText === "[1]") {
    button.classList.add("current-page");
  }
});
