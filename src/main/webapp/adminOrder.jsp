<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"></script>


<!-- 프로젝트내 js 파일  -->
<script src="js/admin_kkg.js" type="text/javascript"></script>

<!--  bootsstrap link -->

<link href="css/admin_kkg.css" rel="stylesheet">
<link href="css/bootstrap.css" rel="stylesheet">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/bootstrap.rtl.css" rel="stylesheet">
<link href="css/bootstrap.rtl.min.css" rel="stylesheet">

<link href="css/bootstrap-grid.css" rel="stylesheet">
<link href="css/bootstrap-grid.min.css" rel="stylesheet">
<link href="css/bootstrap-grid.rtl.css" rel="stylesheet">
<link href="css/bootstrap-grid.min.rtl.css" rel="stylesheet">


<link href="css/bootstrap-reboot.css" rel="stylesheet">
<link href="css/bootstrap-reboot.min.css" rel="stylesheet">
<link href="css/bootstrap-reboot.rtl.css" rel="stylesheet">
<link href="css/bootstrap-reboot.rtl.min.css" rel="stylesheet">

<link href="css/bootstrap-utilities.css" rel="stylesheet">
<link href="css/bootstrap-utilities.min.css" rel="stylesheet">
<link href="css/bootstrap-utilities.rtl.css" rel="stylesheet">
<link href="css/bootstrap-utilities.rtl.min.css" rel="stylesheet">

<!-- 여기서부터 복사하시면 됩니다~~~~~~~~~!!!! -->

<jsp:include page="admin_01_header.jsp" />


</head>

<body>

	<div class="container-fluid">
		<div class="row">
			<div class="col-md-1">
				<jsp:include page="admin_01_sidebar.jsp" />
			</div>

			<div class="col-md-10" style="margin-left: 12%; margin-top: 30px;">
				<main class="ms-sm-auto px-md-4">

					<!-- 요기서부터 본문 내용 입력하면 됩니다아~~!!!!!  하단에  </div> 및 </main> 자리 맞춰서 넣는거만 기억하면 됩니다.-->
					
					<div
						class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
						<h1 class="h2">주문 목록 관리</h1>


						<div>

							<form action="Salemanage.do" name="date" method="post">
								<input type="text" name="startDate" id="startDate" placeholder="시작일" autocomplete="off"> 
								<input type="text" name="endDate" id="endDate" placeholder="종료일" autocomplete="off"> <input type="button" value="확 인"
									onclick="checkDate()">
							</form>

						</div>


						<div class="btn-toolbar mb-2 mb-md-0">
							<div class="btn-group me-2"></div>
							<button type="button"
								class="btn btn-sm btn-outline-secondary dropdown-toggle">

								This week</button>
						</div>
					</div>
					
					
				
					
					<div class="table-responsive">
						<table class="table table-striped table-sm">
							<thead>
								<tr>
									<th scope="col">No</th>
									<th scope="col">주문번호</th>
									<th scope="col">제품</th>
									<th scope="col">수량</th>
									<th scope="col">결재금액</th>
									<th scope="col">고객아이디</th>
									<th scope="col">고객이름</th>
									<th scope="col">주소</th>
									<th scope="col">연락처</th>
									<!-- <th scope="col">정보 수정/삭제</th> -->

								</tr>
							</thead>
							<tbody>


								<c:forEach items="${orderList}" var="order">
									<tr>
										<td>${order.seq}</td>
										<td>${order.oid}</td>
										<td>${order.pname}</td>
										<td>${order.oqty}</td>
										<td>${order.oprice}</td>
										<td>${order.cid}</td>
										<td>${order.cname}</td>
										<td>${order.caddress}</td>
										<td>${order.cphone}</td>
							
									</tr>

								</c:forEach>
							</tbody>
						</table>

					</div>












				</main>
			</div>
		</div>
	</div>


</body>
</html>