<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 관리</title>

<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"></script>
	
	
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
	<!-- 달력부분 -->
	
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script src="https://code.jquery.com/ui/1.13.1/jquery-ui.min.js"></script>
	<link rel="stylesheet" href="https://code.jquery.com/ui/1.13.1/themes/base/jquery-ui.css">
	
</head>

<script type="text/javascript">
			function checkDate(){
				const regstartdate = /^(0[1-9]|1[0-2])\/(0[1-9]|1\d|2\d|3[01])\/(19|20)\d{2}$/;
				const regenddate = /^(0[1-9]|1[0-2])\/(0[1-9]|1\d|2\d|3[01])\/(19|20)\d{2}$/;				
					
				if(!regstartdate.test(startDate)){
						alert("날짜를 입력해 주세요.")
						form.id.select()
						return
					}
					
				if(!regenddate.test(endDate)){
						alert("날짜를 입력해 주세요")
						form.id.select()
						return
					}
				
				
					form.submit()
</script>




<body data-new-gr-c-s-check-loaded="14.1111.0 data-gr-ext-installed">
<%-- <div class="container_mk">
		<div class="headbar">
			<jsp:include page="admin_01_header.jsp" />
		</div>
		<div class="sidebar">
			<jsp:include page="admin_01_sidebar.jsp" />
		</div>

	</div> --%>

<!--  ************* Headbar && Sidebar 종료 ************* -->


	<div class="container-fluid">
		<div class="row">

			<main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
				<div class="chartjs-size-monitor">
					<div class="chartjs-size-monitor-expand">
						<div class=""></div>
					</div>
					<div class="chartjs-size-monitor-shrink">
						<div class=""></div>
					</div>
				</div>
				<div
					class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
					<h1 class="h2">회원관리</h1>
					<div>
					
						<form action="AUserlist.do" method="get">
							<input type="text" name ="startDate" id="startDate" placeholder="시작일">
	 						<input type="text" name ="endDate" id="endDate" placeholder="종료일">
	 						<input type="button" value="확인" onclick="checkDate()">
						</form>
						
					</div>


					<div class="btn-toolbar mb-2 mb-md-0">
						<div class="btn-group me-2">
							<button type="button" class="btn btn-sm btn-outline-secondary">Share</button>
							<button type="button" class="btn btn-sm btn-outline-secondary">Export</button>
						</div>
						<button type="button"
							class="btn btn-sm btn-outline-secondary dropdown-toggle">
							<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24"
								viewBox="0 0 24 24" fill="none" stroke="currentColor"
								stroke-width="2" stroke-linecap="round" stroke-linejoin="round"
								class="feather feather-calendar align-text-bottom"
								aria-hidden="true">
								<rect x="3" y="4" width="18" height="18" rx="2" ry="2"></rect>
								<line x1="16" y1="2" x2="16" y2="6"></line>
								<line x1="8" y1="2" x2="8" y2="6"></line>
								<line x1="3" y1="10" x2="21" y2="10"></line></svg>
							This week
						</button>
					</div>
					<!-- 여기까지가 버튼 과 관련된 부분 이아래가 그래프다 -->
				</div>
				<div>
				<span>신규 가입자 현황</span>
				</div>
				<div>





				<canvas class="my-4 w-100 chartjs-render-monitor" id="userChart" width="500" height="300" 
						style="display: block; height: 275px; width: 652px;"></canvas>




				</div>
				<h2>Section title</h2>
				<div class="table-responsive">
					<table class="table table-striped table-sm">
						<thead>
							<tr>
								<th scope="col">#</th>
								<th scope="col">Header</th>
								<th scope="col">Header</th>
								<th scope="col">Header</th>
								<th scope="col">Header</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>1,001</td>
								<td>random</td>
								<td>data</td>
								<td>placeholder</td>
								<td>text</td>
							</tr>
							<tr>
								<td>1,002</td>
								<td>placeholder</td>
								<td>irrelevant</td>
								<td>visual</td>
								<td>layout</td>
							</tr>
							<tr>
								<td>1,003</td>
								<td>data</td>
								<td>rich</td>
								<td>dashboard</td>
								<td>tabular</td>
							</tr>
							<tr>
								<td>1,003</td>
								<td>information</td>
								<td>placeholder</td>
								<td>illustrative</td>
								<td>data</td>
							</tr>
							<tr>
								<td>1,004</td>
								<td>text</td>
								<td>random</td>
								<td>layout</td>
								<td>dashboard</td>
							</tr>
							<tr>
								<td>1,005</td>
								<td>dashboard</td>
								<td>irrelevant</td>
								<td>text</td>
								<td>placeholder</td>
							</tr>
							<tr>
								<td>1,006</td>
								<td>dashboard</td>
								<td>illustrative</td>
								<td>rich</td>
								<td>data</td>
							</tr>
							<tr>
								<td>1,007</td>
								<td>placeholder</td>
								<td>tabular</td>
								<td>information</td>
								<td>irrelevant</td>
							</tr>
							<tr>
								<td>1,008</td>
								<td>random</td>
								<td>data</td>
								<td>placeholder</td>
								<td>text</td>
							</tr>
							<tr>
								<td>1,009</td>
								<td>placeholder</td>
								<td>irrelevant</td>
								<td>visual</td>
								<td>layout</td>
							</tr>
							<tr>
								<td>1,010</td>
								<td>data</td>
								<td>rich</td>
								<td>dashboard</td>
								<td>tabular</td>
							</tr>
							<tr>
								<td>1,011</td>
								<td>information</td>
								<td>placeholder</td>
								<td>illustrative</td>
								<td>data</td>
							</tr>
							<tr>
								<td>1,012</td>
								<td>text</td>
								<td>placeholder</td>
								<td>layout</td>
								<td>dashboard</td>
							</tr>
							<tr>
								<td>1,013</td>
								<td>dashboard</td>
								<td>irrelevant</td>
								<td>text</td>
								<td>visual</td>
							</tr>
							<tr>
								<td>1,014</td>
								<td>dashboard</td>
								<td>illustrative</td>
								<td>rich</td>
								<td>data</td>
							</tr>
							<tr>
								<td>1,015</td>
								<td>random</td>
								<td>tabular</td>
								<td>information</td>
								<td>text</td>
							</tr>
						</tbody>
					</table>
				</div>
			</main>
		</div>
	</div>

	<script>

	    $(function() {
	      $("#startDate").datepicker();
	      $("#endDate").datepicker();
	    });
    </script>
	    
    <script>
	    var userContext = document.getElementById('userChart');
		SingleBarChart(userContext, ${requestScope.dailyDate}, '신규가입자' ,${requestScope.dailyNS});  
    </script>










</body>
</html>