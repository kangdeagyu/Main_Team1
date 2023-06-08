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
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link href="css/admin_kkg.css" rel="stylesheet">
    <link href="css/modifymodal.css" rel="stylesheet">
    <meta http-equiv="Content-Type" content="text/html;" charset="UTF-8">
    <title>상품 리스트</title>
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

            // 선택된 상품들의 값을 'pid'라는 이름으로 서버로 전송
            var form = document.createElement('form');
            form.method = 'POST';
            form.action = 'deleteProduct.do';

            for (var i = 0; i < selectedItems.length; i++) {
                var input = document.createElement('input');
                input.type = 'hidden';
                input.name = 'pid';
                input.value = selectedItems[i];
                form.appendChild(input);
            }

            document.body.appendChild(form);
            form.submit();

            return false; // 폼 제출 중지
        }

        function openEditModal(pid, pprice, pstock, cname, pname, pcontent, pfilename) {
            var product = {
                pid: pid,
                pprice: pprice,
                pstock: pstock,
                cname: cname,
                pname: pname,
                pcontent: pcontent,
                pfilename: pfilename
            };

            populateEditModal(product);

            var modal = document.getElementById('myModal');
            modal.style.display = 'block';
        }

        function populateEditModal(product) {
            document.getElementById('editPid').value = product.pid;
            document.getElementById('editPidDisplay').innerText = product.pid;
            document.getElementById('editPprice').value = product.pprice;
            document.getElementById('editPstock').value = product.pstock;
            document.getElementById('editCname').innerText = product.cname;
            document.getElementById('editPname').value = product.pname;
            document.getElementById('editPcontent').value = product.pcontent;

            var imageContainer = document.getElementById('editImageContainer');
            imageContainer.innerHTML = ""; // 이미지 컨테이너 초기화

            if (product.pfilename) {
                var image = document.createElement('img');
                image.src = product.pfilename;
                image.alt = "Product Image";
                image.width = 100;
                imageContainer.appendChild(image);
            }
        }

        function saveChanges() {
            var form = document.getElementById('editForm');
            form.action = 'editProduct.do'; // 저장 처리 액션 URL로 변경
            form.submit();
        }
        function closeModal() {
            var modal = document.getElementById('myModal');
            modal.style.display = 'none';
        }
    </script>
</head>
<body>
    <div class="sidebar">
        <jsp:include page="admin_01_sidebar.jsp" />
    </div>
    <div class="wrapper">
        <h3>상품 리스트</h3>
        <form action="productQuery.do" method="post">
            <select name="list" class="form-select">
                <option value="pname" selected="selected">상품명</option>
                <option value="pcategory">카테고리</option>
            </select>
            <input type="text" name="query">
            <input type="submit" name="action" value="검색">
        </form>
        <form action="APinsert.do" method="post" enctype="multipart/form-data">
            <input type="submit" name="action" value="상품 추가하기">
        </form>
        <form>
		    <input type="checkbox" id="selectAllCheckbox" onchange="selectAll()"> 전체 선택
		    <input type="button" value="삭제" onclick="deleteSelectedItems()">
		</form>
            <table border=1>
                <tr>
                    <th>상품선택</th>
                    <th>사진</th>
                    <th>카테고리</th>
                    <th>제품명</th>
                    <th>가격</th>
                </tr>
                <c:forEach items="${list}" var="dto">
                    <tr>
                        <td><input type="checkbox" name="selectedItems" value="${dto.pid}"></td>
                        <td><img src="${dto.pfilename}" alt="Product Image" width="100" /></td>
                        <td>${dto.c_name }</td>
                        <td>${dto.pname }</td>
                        <td>${dto.pprice }</td>
                        <td>
                            <button onclick="openEditModal('${dto.pid}', '${dto.pprice}', '${dto.pstock}','${dto.c_name}', '${dto.pname}', '${dto.pcontent}')">편집하기</button>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        <br />
        <hr>
        <br />
    </div>
    <div id="myModal" class="modal">
    <div id="editImageContainer"></div>
        <div class="modal-content">
            <form id="editForm">
                <table border="1">
                    <tr>
                        <td>상품번호</td>
                        <td>
                            <input type="hidden" id="editPid" name="pid">
                            <span id="editPidDisplay"></span>
                        </td>
                        <td>상품가격</td>
                        <td><input type="text" id="editPprice" name="pprice"></td>
                    </tr>
                    <tr>
                        <td>종류</td>
                        <td>
                            <span id="editCname"></span>
                        </td>
                        <td>모델명</td>
                        <td><input type="text" id="editPname" name="pname"></td>
                    </tr>
                    <tr>
                        <td>재고</td>
                        <td><input type="text" id="editPstock" name="pstock"></td>
                        <td>이미지 변경</td>
                        <td><input type="file" id="pfilename" name="pfilename"></td>
                    </tr>
                </table>
                <p>상세 설명</p>
                <p><textarea id="editPcontent" name="pcontent"></textarea></p>
                <input type="button" name="action" value="저장" onclick="saveChanges()">
            </form>
            <span class="close" onclick="closeModal()">&times;</span>
        </div>
    </div>
</body>
</html>