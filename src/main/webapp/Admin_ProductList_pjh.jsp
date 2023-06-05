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
    </script>
</head>
<body>
<h3>상품 리스트</h3>
<form action="productQuery.do" method="post">
     <select name="list" class="form-select">
        <option value="pname" selected="selected">상품명</option>
        <option value="pcategory">카테고리</option>
    </select>
    <input type="text" name="query">
    <input type="submit" name="action" value="검색">

</form>
<form action="uploadAction.do" method="post">
    <input type="submit" name="action" value="상품 추가하기">
</form>
<form action="deleteProduct.do" method="post">
    <input type="checkbox" id="selectAllCheckbox" onchange="selectAll()"> 전체 선택
    <input type="submit" name="action" value="삭제">
    <table border=1>
        <tr>
            <th>상품선택</th>
            <th>사진</th>
            <th>카테고리</th>
            <th>제품명</th>
            <th>가격</th>
            <th>상태</th>
        </tr>
        <c:forEach items="${list}" var="dto">
            <tr>
                <td>
                    <input type="checkbox" name="selectedItems" value="${dto.pid}">
                </td>
                <td>
                    <img src="${dto.pfilename}" alt="Product Image" width="100"/>
                </td>
                <td>${dto.c_name }</td>
                <td>${dto.pname }</td>
                <td>${dto.pprice }</td>
                <td>판매</td>
                <td>
                    <form action="editProduct.do" method="post">
                        <input type="hidden" name=pid value="${dto.pid }">
                        <input type="submit" name="action" value="편집하기">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</form>
<br/>
<hr>
<br/>
총 구매액 : ${total}원

</body>
</html>