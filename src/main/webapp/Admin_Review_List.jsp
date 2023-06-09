<%@ page import="com.javalec.bbs.dao.Admin_Product_Dao"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.io.File"%>
<%@ page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@ page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="css/admin_kkg.css" rel="stylesheet">
<!-- CSS 파일 추가 -->
<link href="css/modal.css" rel="stylesheet">
  
<meta http-equiv="Content-Type" content="text/html;" charset="UTF-8">
<title>리뷰 목록</title>
<script src="js/modal.js"></script>
<script>
function selectAll() {
    var checkboxes = document.getElementsByName('selectedItems');
    var selectAllCheckbox = document.getElementById('selectAllCheckbox');

    for (var i = 0; i < checkboxes.length; i++) {
        checkboxes[i].checked = selectAllCheckbox.checked;
    }
}


function deleteSelectedItems() {
    var checkboxes = document.getElementsByName('selectedItems');
    var selectedItems = [];

    for (var i = 0; i < checkboxes.length; i++) {
        if (checkboxes[i].checked) {
            selectedItems.push(checkboxes[i].value);
        }
    }

    if (selectedItems.length === 0) {
        alert('삭제할 상품을 선택해주세요.');
        return;
    }

    // 선택된 상품들의 값을 'fid'라는 이름으로 서버로 전송
    var form = document.createElement('form');
    form.method = 'POST';
    form.action = 'deleteReview.do';

    for (var i = 0; i < selectedItems.length; i++) {
        var input = document.createElement('input');
        input.type = 'hidden';
        input.name = 'fid';
        input.value = selectedItems[i];
        form.appendChild(input);
    }

    document.body.appendChild(form);
    form.submit();

    return false; // 폼 제출 중지
}
</script>
</head>
<body>
	<div class="sidebar">
		<jsp:include page="admin_01_sidebar.jsp" />
	</div>
	<div class="wrapper">
		<h3>리뷰 목록</h3>
		<form action="reviewQuery.do" method="post">
			<select name="list" class="form-select">
				<option value="pname" selected="selected">상품명</option>
				<option value="pcategory">카테고리</option>
			</select> <input type="text" name="query"> <input type="submit"
				name="action" value="검색">
		</form>
		 <input type="checkbox" id="selectAllCheckbox" onchange="selectAll()">
			<input type="button" value="삭제" onclick="deleteSelectedItems()">
			<table border=1>
				<tr>
					<th></th>
					<th>ID</th>
					<th>작성일</th>
					<th>고객정보</th>
					<th>상품</th>
					<th>리뷰 상세 내용</th>
					<th>리뷰 관리</th>
				</tr>
				<c:forEach items="${reviewlist}" var="dto">
					<tr>
						<td><input type="checkbox" name="selectedItems" value="${dto.fid}"></td>
						<td>${dto.fid}</td>
						<td>${dto.finsertdate}</td>
						<td>${dto.cname}</td>
						<td><a href="#" onclick="openModal('${dto.fid}')"> <img
								src="${dto.pfilename}" alt="Product Image" width="100" />
						</a></td>
						<td><a href="#" onclick="openModal2('${dto.fid}')">
								${dto.ftitle} </a></td>
						<td>
							<form action="ForumView.do?fid=${dto.fid}" method="post">
								<input type="hidden" name=fid value="${dto.fid}"> <input
									type="submit" name="action" value="관리">
							</form>
						</td>
						<tr id="productInfo-${dto.fid}" style="display: none;">
					    <td colspan="7">
					        <h3>제품 정보</h3>
					        <p>주요정보</p>
					        <p>상품사진</p> 
					        <a href="productInformation.do?query=${dto.pname}">
					            <img src="${dto.pfilename}" alt="Product Image" width="100" />
					        </a>
					        <table border="1">
					            <tr>
					                <td>상품번호</td>
					                <td>${dto.f_pid}</td>
					                <td>상품가격</td>
					                <td>${dto.pprice}</td>
					            </tr>
					            <tr>
					                <td>종류</td>
					                <td>${dto.categoryName}</td>
					                <td>모델명</td>
					                <td>${dto.pname}</td>
					            </tr>
					        </table>
					        <p>상세 설명</p>
					        <p>${dto.pcontent}</p>
					    </td>
					</tr>

					<tr id="reviewInfo-${dto.fid}" style="display: none;">
						<td colspan="4">
							<h3>리뷰</h3>
							<table border="1">
								<tr>
									<td>작성자</td>
									<td>${dto.cname}</td>
								</tr>
								<tr>
									<td>제목</td>
									<td>${dto.ftitle}</td>
								</tr>
								<tr>
									<td>작성일</td>
									<td>${dto.finsertdate}</td>
								</tr>
								<tr>
									<td>내용</td>
									<td>${dto.fcontent}</td>
								</tr>
							</table>
				</c:forEach>
			</table>
	</div>

	<div id="myModal" class="modal">
	  <span class="close" onclick="closeModal()">&times;</span>
	  <div id="modalContent"></div>
	</div>

</body>
</html>