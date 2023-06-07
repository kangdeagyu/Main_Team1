// 모달 열기
function openEditModal(pid, pprice, pstock, c_name, pname, pcontent) {
    var modal = document.getElementById('myModal');
    var pidInput = document.getElementById('editPid');
    var ppriceInput = document.getElementById('editPprice');
    var pstockInput = document.getElementById('editPstock');
    var categoryInput = document.getElementById('editCname');
    var pnameInput = document.getElementById('editPname');
    var pcontentTextarea = document.getElementById('editPcontent');

    pidInput.value = pid;
    ppriceInput.value = pprice;
    pstockInput.value = pstock;
    categoryInput.textContent = c_name;
    pnameInput.value = pname;
    pcontentTextarea.value = pcontent;

    modal.style.display = "block";

    // 스크롤 막기
    document.body.style.overflow = "hidden";
}

// 모달 닫기
function closeModal() {
  var modal = document.getElementById("myModal");
  modal.style.display = "none";
  
  // 스크롤 허용하기
  document.body.style.overflow = "auto";
}