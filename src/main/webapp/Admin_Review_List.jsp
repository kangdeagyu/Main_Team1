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
    <title>리뷰 목록</title>
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
		<h3>리뷰 목록</h3>
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
		<form action="hide.do" method="post">
               <input type="hidden" name=fid value="${dto.fid }">
              <input type="submit" name="action" value="숨김">
        </form>
		
		
		
		<form action="deleteProduct.do" method="post">
	    <input type="submit" name="action" value="삭제">
	    <table border=1>
        <tr>
            <th><input type="checkbox" id="selectAllCheckbox" onchange="selectAll()"> </th>
            <th>ID</th>
            <th>작성일</th>
            <th>고객정보</th>
            <th>상품</th>
            <th>리뷰 상세 내용</th>
            <th>리뷰 관</th>
        </tr>
        <c:forEach items="${list}" var="dto">
            <tr>
                <td>
                    <input type="checkbox" name="selectedItems" value="${dto.fid}">
                </td>
                <td>${dto.fid}</td>
                <td>${dto.finsertdate}</td>
                <td>${dto.cname }</td>
                <td><img src="${dto.pfilename}" alt="Product Image" width="100"/>
                </br>
                ${dto.pname }</br>
                판매가 : ${dto.pprice}원
                </td>
                    
                <td>${dto.fcontent}</td> 
                <td>
                    <form action="editProduct.do" method="post">
                        <input type="hidden" name=fid value="${dto.fid }">
                        <input type="submit" name="action" value="관리">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</form>
<br/>
<hr>
<br/>

</body>
</html>