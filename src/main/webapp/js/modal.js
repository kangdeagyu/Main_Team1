
// 모달 열기
function openModal(fid) {
  var productInfo = document.getElementById("productInfo-" + fid);
  var modalContent = document.getElementById("modalContent");
	
  // 모달 창에 제품 정보를 표시
  modalContent.innerHTML = productInfo.innerHTML;
  // 스크롤 막기
  document.body.style.overflow = "hidden";
  // 모달 창 열기
  var modal = document.getElementById("myModal");
  modal.style.display = "flex"; // display 속성을 flex로 변경
  modal.style.alignItems = "center"; // 수직 가운데 정렬
  modal.style.justifyContent = "center"; // 수평 가운데 정렬
}

function closeModal() {
  var modal = document.getElementById("myModal");
  // 스크롤 허용하기
  document.body.style.overflow = "auto";
  // 모달 창 닫기
  modal.style.display = "none";
}