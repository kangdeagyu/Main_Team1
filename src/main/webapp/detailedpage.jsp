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

</style>
<script>
function updateTotalAmount() {
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
	}
</script>
</head>
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
  					<input type="number" id="quantity" value="1" min="1" max="${dto.pstock}" onchange="updateTotalAmount()">
				</td>

              </tr>
              <tr>
                <td colspan="2" class="purchase-details">
              <div class="money">
                  <span>총 구매 금액: </span>
                  <span id="total-amount">${dto.pprice}</span>
                </div>
        </td>
      </tr>
      
    <tr>
      <td colspan="2" class="form-button">
        <form action=".jsp" method="get">
           <input type="submit" name="WriteForum" value="구매하기" >
        </form>
        <form action="userCart.jsp" method="get">
        <a class="nav-link" href="userCart.do?pid=${dto.pid }"></a>
            <input type="submit" name="Cart" value="장바구니">
            
		</form>
      </td>
    </tr>
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
          <div style="padding-top: 10px; ">      
          <table border="0" cellpadding="0" cellspacing="0" width="100%">
          <tr><td align="center"><img src="/상품 상세페이지에 들어가는 첫 이미지!!" align="absmiddle" border="0" /></td></tr>
          </table></div>

<div>
 <img src="image/dog.png" alt="제품 이미지">
</div>





<div id="detailGoodsInfo">상세 상품 내용</div>
<div id="detailReview">리뷰 게시판 내용</div>

<div id="detailQna">Q&amp;A 게시판 내용</div>
</body>

</html>