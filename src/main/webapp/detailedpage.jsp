<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang=ko>
<head>
<style>
  .container {
    display: flex;
    align-items: center;
     justify-content: center; /* 추가된 부분 */
    height: 100vh; /* 추가된 부분 */
  }

  .product-image {
    float: left;
    margin-right: 100px;
  }

  .product-info {
    float: right;
  }
</style>
</head>
<body>
   <table>
        <thead>
            <tr>
                <th>번호</th>
                <th>상품 이름</th>
                <th>상품 사진</th>
                <th>상품 설명</th>
                <th>상품 가격</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${DetailedProduct}" var="dto">
                <tr>
                    <td>${dto.pid}</td>
                    <td>${dto.pname}</td>
                       <td><img alt="" style="width: 100px; height: 100px;" src="${dto.pfilename}"> </td>
                    <td>${dto.pcontent}</td>
                    <td>${dto.pprice}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
 
 <form action="">
      <input type="button" value="장바구니">
      <input type="button" value="구매하기">
      </form>
    
     
</body>
</html>