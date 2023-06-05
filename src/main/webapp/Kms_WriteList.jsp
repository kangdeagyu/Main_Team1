<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"   %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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

<h1>리뷰 게시판</h1>
<table class="table table-striped table-bordered">
    <thead class="thead-light">
        <tr>
            <th style="background-color: lavender; color: purple;">Review No.</th>
            <th style="background-color: lavender;color: purple;">작성자</th>
            <th style="background-color: lavender;color: purple;">제품</th>
            <th style="background-color: lavender;color: purple;">제목</th>
            <th style="background-color: lavender;color: purple;">내용</th>
            <th style="background-color: lavender;color: purple;">작성일</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${RList}" var="dto">
            <tr>
                <td>${dto.fid}</td>
                <td>${dto.f_cid}</td>
                <td>${dto.f_pid}</td>
                <td>${dto.ftitle}</td>
                <td>${dto.fcontent}</td>
                <td>${dto.finsertdate}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>

</body>
</html>