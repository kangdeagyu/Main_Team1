<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
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

<link rel="stylesheet" type="text/css" href="aQnA_style.css">
<script>
        // 전체 데이터 개수
        var totalData = ${ListSize};

        // 데이터를 테이블에 렌더링하는 함수
        function renderData(data) {
            var tableBody = $("#tableBody");
            tableBody.empty();
            tableBody.append(data);
        }

        // 특정 페이지 번호에 해당하는 데이터를 가져오는 함수
        function GetTarget(pageNumber) {
            var dataPerPage = 10;
            var startIndex = (pageNumber - 1) * dataPerPage;
            var endIndex = startIndex + dataPerPage;

            var rows = $("#tableBody tr.data-row");
            rows.addClass("hidden-row"); // 모든 행 숨기기
            rows.slice(startIndex, endIndex).removeClass("hidden-row"); // 현재 페이지에 해당하는 행 보이기

            paging(totalData, pageNumber);
        }

        // 페이지네이션 링크 생성 함수
        function paging(totalData, currentPage) {
            var dataPerPage = 10;
            var pageCount = 10;

            var totalPage = Math.ceil(totalData / dataPerPage);
            var pageGroup = Math.ceil(currentPage / pageCount);

            var last = pageGroup * pageCount;
            var first = last - (pageCount - 1);
            var next = last + 1;
            var prev = first - 1;

            var pages = $("#pages");
            pages.empty();

            // 이전 링크 추가 (이전 페이지가 있는 경우)
            if (first > 10) {
                pages.append("<li class=\"pagination-item\">" +
                    "<a onclick=\"GetTarget(" + prev + ");\" style='margin-left: 2px'>이전</a></li>");
            }

            // 페이지 번호 링크 생성
            for (var i = first; i <= last; i++) {
                if (i > totalPage) {
                    break;
                }
                if (i == currentPage) {
                    pages.append("<li class=\"pagination-item\">" +
                        "<a class=\"active\">" + i + "</a></li>"); // 현재 페이지를 강조 표시
                } else {
                    pages.append("<li class=\"pagination-item\">" +
                        "<a onclick=\"GetTarget(" + i + ");\">" + i + "</a></li>"); // 다른 페이지에 대한 링크 추가
                }
            }

            // 다음 링크 추가 (더 많은 페이지가 있는 경우)
            if (last < totalPage) {
                pages.append("<li class=\"pagination-item\">" +
                    "<a onclick=\"GetTarget(" + next + ");\" style='margin-left: 2px'>다음</a></li>");
            }
        }

        $(document).ready(function() {
            GetTarget(1); // 초기 페이지를 1로 설정
        });
    </script>
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
								<input type="text" name="startDate" id="startDate"
									placeholder="시작일" autocomplete="off"> <input
									type="text" name="endDate" id="endDate" placeholder="종료일"
									autocomplete="off"> <input type="button" value="확 인"
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
							<thead class="thead-light">
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
							<tbody id="tableBody">


								<c:forEach items="${orderList}" var="order" varStatus="status">
									<tr class="data-row hidden-row" id="dataRow${status.index}">
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
						<div class="pagination-wrapper clearfix">
							<ul class="pagination float--right" id="pages">
							</ul>
						</div>
  			<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
					</div>












				</main>
			</div>
		</div>
	</div>


</body>
</html>