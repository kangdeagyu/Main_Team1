<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html lang="ko">
<head>
<style>

body, html {
  height: 100%;
}

.container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
}

.product-table {
  display: flex;
  align-items: center;
  align-items: flex-start; /* 좌측 정렬 */
}

.product-image {
  margin-right: 200px; /* 이미지와 정보 사이의 간격 조정 */
}

.form-button {
 color: #666666;
width: 170px;
padding: 10px 10px 10px;
position: absolute;
font-size: 50pt;
margin-top: 10px;
letter-spacing: -1px;
}

.form-button input[type="submit"] {
  padding: 10px 30px; /* 버튼의 패딩을 조정 */
  width: 150px; /* 버튼의 너비를 조정 */
  justify-content: center;
}

.money {
  display: flex;
  justify-content: flex-end;
  
}
 /* 하단페이지 */
 .detailTab{
   text-align: center;
  margin: 0 auto;
}
html {
  scroll-behavior: smooth;
}
.nav-link {
  background: none;
  border: none;
  padding: 0;
  font: inherit;
  color: inherit;
  text-decoration: underline;
  cursor: pointer;
}

</style>

</head>
<script>
function updateTotalAmount() { // 수량을 늘렸을 때 db에 남아있는 갯수만큼만 가능
	  var quantity = parseInt(document.getElementById('quantity').value);
	  var price = parseInt(document.getElementById('price').innerText);
	  var maxQuantity = 30; // 최대 수량 설정
	  if (quantity > maxQuantity) {
	    quantity = maxQuantity; // 최대 수량을 초과하는 경우 최대 수량으로 설정
	    document.getElementById('quantity').value = maxQuantity; // 수량 입력 필드에 최대 수량 설정
	    alert('최대 수량을 초과할 수 없습니다.');
	  }
	  var totalAmount = quantity * price;
	  document.getElementById('total-amount').innerText = totalAmount;
	  var quantity = document.getElementById("quantity").value;
	}
</script>
<body>
<div class="container">
  <table class="product-table">
    <thead>
      <tr>
        <c:forEach items="${DetailedProduct}" var="dto">
          <td class="product-image">
            <img alt="상품 이미지" style="width: 500px; height: 500px;" src="${dto.pfilename}">
          </td>
          <td>
            <table>
              <tr>
              	<th>상품 이름</th>
              	<td>${dto.pname}</td>
              	</tr>
              	<tr>
                <th>상품 설명</th>
                <td>${dto.pcontent}</td>
              </tr>
              <tr>
                <th>상품 가격</th> 
                <td id="price">${dto.pprice}</td>
              </tr>
              <tr>
                <th>수량</th> 
            	<td>
            	<form action="userCart.jsp" method="get">
 						 <input type="hidden" name="pid" value="${dto.pid}">
 						 <input type="number" id="quantity" value="1" min="1" max="${dto.pstock}" onchange="updateTotalAmount()">
				</form>
   
  			   <tr>
 				 <td colspan="2" class="purchase-details" >
   					 <div class="money">
   						<span>총 구매 금액: </span>
     					<span id="total-amount">${dto.pprice}</span>
					</div>
 				</td>
			</tr>

				<tr>
  					<td colspan="4" class="form-button">
   					 <form id="cartForm" action="order.do" method="get">
    				  <input type="submit" name="WriteForum" value="구매하기">
   						<a id="cartLink" class="nav-link" href="OrderView.do?pid=${dto.pid }&qty=1" onclick="updateQty()">구매하기</a>
   						
   						<a id="cartLink" class="nav-link" href="productcart.do?pid=${dto.pid}&qty=1" onclick="updateQty()">장바구니</a>
   					 </form>
            </table>
          </td>
        </c:forEach>
      </tr>
    </thead>
  </table>
</div>

 <div class="detailTab">
          <a href="#detailGoodsInfo" class="active">DETAIL PRODUCT</a>
         <a href="#detailReview">REVIEW BOARD</a>
          <a href="#detailQna">Q&amp;A BOARD</a>                   
          </div>
       <!--    <div style="padding-top: 10px; ">      
          <table border="0" cellpadding="0" cellspacing="0" width="100%">
          <tr><td align="center"><img src="/상품 상세페이지에 들어가는 첫 이미지!!" align="absmiddle" border="0" /></td></tr>
          </table></div> -->

<div>
 <img src="image/dog.png" alt="제품 이미지">
</div>





<div id="detailGoodsInfo">상세 상품 내용</div>
<div id="detailReview">
		<table>
        		<tr>
            		<th style="background-color: lavender; color: purple;">ReviewNo.</th>
            		<th style="background-color: lavender;color: purple;">작성자</th>
            		<th style="background-color: lavender;color: purple;">제품</th>
            		<th style="background-color: lavender;color: purple;">제목</th>
            		<th style="background-color: lavender;color: purple;">내용</th>
            		<th style="background-color: lavender;color: purple;">작성일</th>
        	</tr>
        	<c:choose>
    <c:when test="${empty Rdto}">
        <tr>
            <td colspan="6">아직 게시물이 없습니다.</td>
        </tr>
    </c:when>
    <c:otherwise>
        	<c:forEach items="${Rdto}" var="dto">
            	<tr>
                <td><a href="ForumView.do?fid=${dto.fid}">${dto.fid}</a></td>
                <td>${dto.f_cid}</td>
                <td>${dto.f_pid}</td>
                <td>${dto.ftitle}</td>
                <td>${dto.fcontent}</td>
                <td>${dto.finsertdate}</td>
            	</tr>
        	</c:forEach>
        	 </c:otherwise>
</c:choose>
		</table>
	</div>

<div id="detailQna"><table>
        		<tr>
            		<th style="background-color: lavender; color: purple;">QnAno.</th>
            		<th style="background-color: lavender;color: purple;">작성자</th>
            		<th style="background-color: lavender;color: purple;">제품</th>
            		<th style="background-color: lavender;color: purple;">제목</th>
            		<th style="background-color: lavender;color: purple;">내용</th>
            		<th style="background-color: lavender;color: purple;">작성일</th>
        	</tr>
        	<c:choose>
    <c:when test="${empty Qdto}">
        <tr>
            <td colspan="6">아직 게시물이 없습니다.</td>
        </tr>
    </c:when>
    <c:otherwise>
        	<c:forEach items="${Qdto}" var="dto">
            	<tr>
                <td><a href="ForumView.do?fid=${dto.fid}">${dto.fid}</a></td>
                <td>${dto.f_cid}</td>
                <td>${dto.f_pid}</td>
                <td>${dto.ftitle}</td>
                <td>${dto.fcontent}</td>
                <td>${dto.finsertdate}</td>
            	</tr>
        </c:forEach>
         </c:otherwise>
</c:choose>
</table></div>
<script>
<script>
function updateQty() {
  var quantity = document.getElementById("quantity").value;

  // 장바구니 링크 URL 가져오기
  var cartLink = document.getElementById("cartLink").getAttribute("href");

  // 수량 정보를 URL에 추가
  var updatedCartLink = cartLink + "&qty=" + quantity;

  // 장바구니 페이지로 이동
  window.location.href = updatedCartLink;
}
</script>


</body>

</html>