<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>

<html>
<head>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<style>
    table {
        border-collapse: collapse;
        width: 100%;
    }

    th {
        text-align: center;
        padding: 10px;
    }

    th:first-child {
        text-align: left;
        width: 50%;
    }

    td {
        text-align: center;
        padding: 10px;
    }

    td:first-child {
        text-align: left;
    }
</style>

<title>장바구니</title>
</head>
<body>
	
		<div class="container">
			<figure class="text">
				<h3 class="text-dark">장바구니</h3>
			</figure>
		</div>
		
<main>

	
		<table border="1">
	    <tr>
	        <th>상품 정보</th>
	        <th>수량</th>
	        <th>주문 금액</th>
	        <th>배송 정보</th>
    	</tr>

		<c:forEach items="${list }" var="dto">
			<tr>
				<td><img src="${dto.pfilename }" style="width: 100px; height: 100px; margin-bottom: 10px;"  alt="...">
									${dto.pname }</td>
				<td>${dto.bqty }</td>
				<td>${dto.pprice }</td>
				<td>배송료<br/>3,000원</td>
	
			</tr> 
		</c:forEach>
		
		
		</table>
	
</main>




	
	<!-- footer -->
	<%@ include file="bottom.jsp" %>
	

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

</body>
</html>