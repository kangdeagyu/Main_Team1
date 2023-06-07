<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
		<title>물건 정보</title>
		</head>
	<body>
	<h3>제품 정보</h3>
	주요정보<br/>
	상품사진<br/>
	<img src="${dto.pfilename}" alt="Product Image"
							width="100" />
	<table border="1">
	<tr>
	<td>상품번호</td>
	<td>${dto.pid}</td>
	<td>상품가격</td>
	<td>${dto.pprice}</td>
	</tr>
	<tr>
	<td>종류</td>
	<td>${dto.cname}</td>
	<td>모델명</td>
	<td>${dto.pname }</td>
	</table>
	상세 설명<br/>
	${dto.pcontent}
	</body>
</html>