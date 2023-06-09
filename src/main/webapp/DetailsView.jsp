<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<style>

    td {
        text-align: left;
        padding: 10px;
    }

    td:first-child {
        text-align: left;
    }

</style>

<meta charset="UTF-8">
<title>구매내역</title>
</head>
<body>
<div>
	<h3>구매내역</h3>
	<table>
		<c:forEach items="${orderList}" var="dto" varStatus="status">
			  <tr>
			   	 <td>
			    	<img src="${dto.pfilename}" style="width: 100px; height: 100px; margin-bottom: 10px;" alt="..." />
			    </td>
			    <td>${dto.pname}<br/>
			    	${dto.pcontent} - ${dto.bqty}개<br/>
			    	<fmt:formatNumber value="${dto.price * dto.bqty}" pattern="#,##0원" />
			    </td>
			  </tr>
		</c:forEach> 	
    </table><br/>
</div>

<c:forEach items="${detail}" var="dto" >
			<input type="text" name="cname" id="cname" size="20" value="${dto.cname }" ><br/>
		    <input type="text" name="cphone"  size="20" value="${dto.cphone }" ><br/>
		    <input type="email" name="cid"  size="42" value="${cid }"  readonly><br/>
		    <input type="text"  name="cpostnum"  value="${dto.postnum }" readonly><br>

			<input type="text"  name="caddress1" value="${dto.address1 }" readonly><br>
			<input type="text"  name="caddress2" value="${dto.address2 }"><br/>
			<input type="text"  name="memo" value="${Memo }"><br/>
			<input type="text"  name="payment" value="${Payment }"><br/>
</c:forEach>
<c:set var="totalprice" value="0"></c:set>
	<c:forEach items="${orderList}" var="dto" varStatus="status">	
	  	<c:set var="subtotal" value="${dto.price * dto.bqty}"></c:set>
	  	<c:set var="totalprice" value="${totalprice + subtotal}"></c:set>
	</c:forEach>
	
	상품가격 ${totalprice }<br/>
	배송비 ${totalprice >= 150000 ? 0 : 3000  }	<br/>

	총 주문 금액 ${(totalprice) + (totalprice >= 150000 ? 0 : 3000)}	<br/>


	<%@ include file="bottom.jsp" %>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>