<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 홈 화면</title>
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


</head>


<body data-new-gr-c-s-check-loaded="14.1111.0 data-gr-ext-installed">
	<div class="container_mk">
		<div class="headbar">
			<jsp:include page="admin_01_header.jsp" />
		</div>
		<div class="sidebar">
			<jsp:include page="admin_01_sidebar.jsp" />
		</div>

	</div>

	<div class="container_mk">

		<div class="wrapper">
			<div>
				<h2>매출 관리</h2>
			</div>
			<div class="content">


				<div class="array">
					<div class="card shadw-lg"
						style="width: 500px; height: 300px; background-color: #F7F7F7;">
						<h5 class="card-title">월간 매출/판매 현황</h5>
						<canvas id="SaleChart_month"></canvas>
						<div class="card-body">
							<!-- <a href="#" class="btn btn-primary">Go somewhere</a> -->
						</div>
					</div>
				</div>




				<!-- 							<div class="array">
								<div class="card" style="width: 350px; height: 300px;">
								    <h5 class="card-title"> 주간 매출/판매 현황</h5>
								  <canvas id="SaleChart_weekly"></canvas>
								  <div class="card-body">
									<a href="#" class="btn btn-primary">Go somewhere</a>
								  </div>
								</div>
							</div> -->


				<div class="array">
					<div class="card shadow-lg"
						style="width: 500px; height: 300px; background-color: #F7F7F7;">
						<h5 class="card-title">일일 매출/판매 현황</h5>
						<canvas id="SaleChart_daily"></canvas>
						<!-- <div class="card-body">
									<a href="#" class="btn btn-primary">Go somewhere</a>
								  </div> -->

					</div>
				</div>

			</div>
			<!-- content  -->
			<br />
			<hr />
			<br />
			<div>
				<h2>회원 관리</h2>
			</div>
			<div class="content">


				<div class="array">
					<div class="card shadow-lg"
						style="width: 500px; height: 300px; background-color: #F7F7F7;">
						<h5 class="card-title">일일 신규 가입자수/탈퇴자수</h5>
						<canvas id="NewScriberChart_daily"></canvas>
						<div class="card-body">
							<!-- <a href="#" class="btn btn-primary">Go somewhere</a> -->
						</div>
					</div>
				</div>
			</div>
			<br />
			<hr />
			<br />


			<div>
				<p>받아온 data 검증하기 위한 파트</p>
				<p>
					날짜 :${requestScope.dailyDate} <input type="hidden" id="dailyDate"
						value="${requestScope.dailyDate}">
				</p>
				<p>
					주문 :${requestScope.dailyOrder} <input type="hidden" id="dailyOrder"
						value="${requestScope.dailyOrder}">
				</p>
				<p>
					매출 :${requestScope.dailySale} <input type="hidden" id="dailySale"
						value="${requestScope.dailySale}">
				</p>
				<p>
					신규 :${requestScope.dailyNS} <input type="hidden" id="dailyNS"
						value="${requestScope.dailyNS}">
				</p>

				<p>
					월별 :${requestScope.monthlyMonth} <input type="hidden"
						id="monthlyMonth" value="${requestScope.monthlyMonth}">
				</p>
				<p>
					주문 :${requestScope.monthlyOrder} <input type="hidden"
						id="MonthlyOrder" value="${requestScope.monthlyOrder}">
				</p>
				<p>
					매출 :${requestScope.monthlySale} <input type="hidden"
						id="MonthlySale" value="${requestScope.monthlySale}">
				</p>
			</div>

		</div>
		<!--wrapper 끝 -->
	</div>





	<!-- SaleChart_daily 그리기   -->
	<script>
	   /* 데일리 매출/결재건수 차트 */
	
	var dsContext = document.getElementById('SaleChart_daily');
    DoubleLineChart(dsContext, ${requestScope.dailyDate},'매출', ${requestScope.dailySale},'결재건' ,${requestScope.dailyOrder});

    /* monthly 매출/결재건수 차트 */
    var msContext = document.getElementById('SaleChart_month');
    DoubleLineChart(msContext, ${requestScope.monthlyMonth},'매출',${requestScope.monthlySale},'결재건', ${requestScope.monthlyOrder});

    /* 신규 가입자수 차트 */
    var DNSContext = document.getElementById('NewScriberChart_daily');
    SingleBarChart(DNSContext, ${requestScope.dailyDate}, '신규가입자' ,${requestScope.dailyNS});
	</script>




	<script>
        // 스크롤 이벤트 리스너
        window.addEventListener('scroll', function() {
            var header = document.querySelector('header');

            if (window.scrollY > 0) {
                header.classList.add('scrolled');
            } else {
                header.classList.remove('scrolled');
            }
        });
    </script>


</body>
</html>