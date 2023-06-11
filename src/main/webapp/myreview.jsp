<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>리뷰 게시판</title>
</head>
<body>
    <h1>나의 리뷰</h1>
    <h4>리뷰 목록</h4>
    <table>
        <thead>
            <tr>
                  <th>번호</th>
            <th>작성자</th>
            <th>상품</th>
            <th>제목</th>
            <th>내용</th>
            <th>작성일</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach items="${Myreview}" var="dto">
          <td class="product-image">
            <img alt="상품 이미지" style="width: 500px; height: 500px;" src="${dto.pfilename}">
            </td>
            </c:forEach>
            <c:forEach items="${Myreview}" var="dto">
                <tr>
                       <td>${dto.fid}</td>
                <td>${dto.f_cid}</td>
                <td>${dto.pname}</td>
                <td>${dto.ftitle}</td>
                <td>${dto.ftcontent}</td>
                <td>${dto.finsertdate}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>