<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"   %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>제품 리뷰 목록</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">

<style>
    body {
        background-color: white;
        padding: 20px;
    }

    h1 {
        color: indigo;
        margin-bottom: 30px;
    }

    th {
        background-color: indigo;
        color: white;
        padding: 10px;
        text-align: center;
    }

    td {
        padding: 10px;
        text-align: center;
    }

    a {
        color: indigo;
        text-decoration: none;
    }

    .table-striped tbody tr:nth-of-type(odd) {
        background-color: #f8f8ff; /* 연보라색과 어울리는 밝은 연보라색 */
    }

    .table-striped tbody tr:hover {
        background-color: #f1f1f1; /* 연보라색과 어울리는 더 밝은 연보라색 */
    }
</style>

</head>
<body>
	<c:if test="${ftype eq 1}">
		<h1>리뷰 게시판</h1>
	</c:if>
 
	<c:if test="${ftype eq 2}">
		<h1>QnA 게시판</h1>
	</c:if> 
	
	<c:if test="${ftype eq 1}">
	<form action="Kms_WriteForum.jsp">
		<input type="submit" value="리뷰쓰기">
	</form></c:if>
	<c:if test="${ftype eq 2}">
	<form action="Kms_WriteForum.jsp">
		<input type="submit" value="QnA쓰기">
	</form></c:if>

	<div style="text-align: right;">
    <form action="forumsearch.do" method="post">
        <input type="hidden" name="ftype" value="${ftype}">
        <input type="text" name="content" placeholder="원하시는 상품을 검색하세요!">
        <input type="submit" value="검색">
    </form>
</div>

		<table class="table table-striped table-bordered">
    		<thead class="thead-light">
        		<tr>
            		<th style="width: 100px; background-color: lavender; color: purple;">
            		<c:if test="${ftype eq 1}"> Review No. </c:if> 
            		<c:if test="${ftype eq 2}">QnA No.</c:if> 
            		</th>
            		<th style="width: 200px;background-color: lavender;color: purple;">작성자</th>
            		<th style="width: 200px;background-color: lavender;color: purple;">제품</th>
            		<th style="background-color: lavender;color: purple;">제목</th>
            		<th style="width: 300px; background-color: lavender;color: purple;">작성일</th>
        	</tr>
    	</thead>
    	<tbody>
        	<c:forEach items="${RList}" var="dto">
            	<tr>
                <td>${dto.fid}</td>
                <td>${dto.cname}</td>
                <td>${dto.pname}</td>
                <td><a href="ForumView.do?fid=${dto.fid}">${dto.ftitle}</a></td>
                <td>${dto.finsertdate}</td>
            	</tr>
        </c:forEach>
    </tbody>
</table>
<%@ include file="bottom.jsp" %>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>

</body>
</html>