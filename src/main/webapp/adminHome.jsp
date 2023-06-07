<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 홈 화면</title>
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

	
</head>



<body data-new-gr-c-s-check-loaded="14.1111.0 data-gr-ext-installed">
	<div class="headbar">
 		<jsp:include page="admin_01_header.jsp" />
	</div>


		
		
	<div class="container_mk">	
		<div class="sidebar">
			<jsp:include page="admin_01_sidebar.jsp" />
		</div>
		 
		 
		<div class="wrapper">
			<div>
				<h2>매출 관리</h2>
			
				<div class="content">
		
			
							<div class="array">
								<div class="card" style="width: 350px; height: 300px;">
								    <h5 class="card-title"> 월간 매출/판매 현황</h5>
								  <canvas id="SaleChart_month"></canvas>
								  <div class="card-body">
								    <!-- <a href="#" class="btn btn-primary">Go somewhere</a> -->
								  </div>
								</div>
							</div>
							
							
			
							
							<div class="array">
								<div class="card" style="width: 350px; height: 300px;">
								    <h5 class="card-title"> 주간 매출/판매 현황</h5>
								  <canvas id="SaleChart_weekly"></canvas>
								  <!-- <div class="card-body">
									<a href="#" class="btn btn-primary">Go somewhere</a>
								  </div> -->
								</div>
							</div>
							
							
							<div class="array">
								<div class="card" style="width: 350px; height: 300px;">
								    <h5 class="card-title"> 일일 매출/판매 현황</h5>
								  <canvas id="SaleChart_daily"></canvas>
								  <!-- <div class="card-body">
									<a href="#" class="btn btn-primary">Go somewhere</a>
								  </div> -->
								
								</div>
							</div>	
								
					</div> <!-- content  -->
					
				<div class="content">
		
			
							<div class="array">
								<div class="card" style="width: 350px; height: 300px;">
								    <h5 class="card-title"> 일일 신규 가입자수/탈퇴자수</h5>
								  <canvas id="NewScriberChart_daily"></canvas>
								  <div class="card-body">
								    <!-- <a href="#" class="btn btn-primary">Go somewhere</a> -->
								  </div>
								</div>
							</div>	
					
					
					
					
					
					
					
					
					
					
					
					
				</div>
				
				
				<div>
							<p>받아온 data 검증하기 위한 파트</p>
							<p>날짜 :${requestScope.dailyDate}</p>
							<p>주문 :${requestScope.dailyOrder}</p>
							<p>매출 :${requestScope.dailySale}</p>
							<p>신규 :${requestScope.dailyNS}</p>
								
							<p>월별 :${requestScope.monthlyMonth}</p>
							<p>주문 :${requestScope.monthlyOrder}</p>
							<p>매출 :${requestScope.monthlySale}</p>
				</div>
				
		</div> <!--wrapper 끝 -->
	</div>


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
















	<!-- SaleChart_daily 그리기   -->





	<script>
		var context = document.getElementById('SaleChart_daily');

		var xlabels = ${requestScope.dailyDate};

		var ydatas_01 = ${requestScope.dailySale};
		var ydatas_02 = ${requestScope.dailyOrder};

		const datas = {
			labels : xlabels, /* x축을 선언하는 파트. - 위에서 선언한 labels 로 대체할 수 있다.*/

			datasets : [ /* 그려질 그래프의 정보를 입력하는 부분이다. 그래프의 배경색, 테두리색, data 값들이 입력된다. */
			{
				label : '일일 매출 현황', /* 그래프(차트)의 제목이다.*/
				fill : false,
				backgroundColor : 'rgb(255, 0, 0)',
				borderColor : 'rgb(255, 0, 0)',
				data : ydatas_01,
				yAxisID: 'y-axis-1'
		
			}, //첫번째 data 셋 입력 끝.(매출액 데이터))
			
				{
				label : '일일 주문건수 현황', /* 그래프(차트)의 제목이다.*/
				fill : false,
				backgroundColor : 'rgb(0, 0, 255)',
				borderColor : 'rgb(0, 0, 255)',
				data : ydatas_02,
				yAxisID: 'y-axis-2'
		
			}
	
			
			] //dataset 끝
		};

		/* 차트 발생 코드에 넣을 값들을 하단에 몰아 넣는다면  여기에는 이 코드가 들어가야 한다.
		
		var context = document      
		  .getElementById('myChart') 
		  .getContext('2d');	
		
		 */

		const config = {
			type : 'line',
			data : datas,
			options : {
				maintainAspectRatio : false, /*  가로세로 비율 무시하기*/

				scales : {
					yAxes: [
						{
							id: 'y-axis-1',
							position: 'left',
							ticks: {
								fontSize: 8,
								fontColor : 'rgb(255,0,0)',
								max : 1600000
							}
						},
						{
							id: 'y-axis-2',
							position: 'right',
							ticks: {
								fontSize: 8,
								fontColor : 'rgb(0,0,255)',
								max : 16
							}
						}
					],
					
					
					xAxes : [ {
						ticks : {
							fontSize : 8, // x축 레이블의 글씨 크기를 10으로 설정 (원하는 크기로 조정)
						}
					} ]
				},//scale 끝
				
				layout : {
					padding : {
						top : 0,
						right : 0,
						bottom : 30,
						left : 0
					}

				},//layout 끝

				legend : {
					display : true,
					labels : {
						fontSize : 8,
						boxWidth : 5,// 범례의 글씨 크기 설정
					},
					elements : {
						line : {
							borderWidth : 1, // 선의 두께 설정 (기본값: 3)
						}
					}

				}//legend 끝 

			}//option 끝
		};//config 끝

		const SaleChart_daily = new Chart(context, config);
	</script>








	<!-- SaleChart_monthly 그리기   -->

	<script>
		var context_m = document.getElementById('SaleChart_month');

		var xlabels_m = ${requestScope.monthlyMonth};

		var ydatas_m01 = ${requestScope.monthlySale};
		var ydatas_m02 = ${requestScope.monthlyOrder};

		const datas_m = {
			labels : xlabels_m, /* x축을 선언하는 파트. - 위에서 선언한 labels 로 대체할 수 있다.*/

			datasets : [ /* 그려질 그래프의 정보를 입력하는 부분이다. 그래프의 배경색, 테두리색, data 값들이 입력된다. */
			{
				label : '월 매출 현황', /* 그래프(차트)의 제목이다.*/
				fill : false,
				backgroundColor : 'rgb(255, 0, 0)',
				borderColor : 'rgb(255, 0, 0)',
				data : ydatas_m01,
				yAxisID: 'y-axis-1'
		
			}, //첫번째 data 셋 입력 끝.(매출액 데이터))
			
				{
				label : '월 주문수 현황', /* 그래프(차트)의 제목이다.*/
				fill : false,
				backgroundColor : 'rgb(0, 0, 255)',
				borderColor : 'rgb(0, 0, 255)',
				data : ydatas_m02,
				yAxisID: 'y-axis-2'
		
			}
	
			
			] //dataset 끝
		};

		/* 차트 발생 코드에 넣을 값들을 하단에 몰아 넣는다면  여기에는 이 코드가 들어가야 한다.
		
		var context = document      
		  .getElementById('myChart') 
		  .getContext('2d');	
		
		 */

		const config_m = {
			type : 'line',
			data : datas_m,
			options : {
				maintainAspectRatio : false, /*  가로세로 비율 무시하기*/

				scales : {
					yAxes: [
						{
							id: 'y-axis-1',
							position: 'left',
							ticks: {
								fontSize: 8,
								fontColor : 'rgb(255,0,0)'
							}
						},
						{
							id: 'y-axis-2',
							position: 'right',
							ticks: {
								fontSize: 8,
								fontColor : 'rgb(0,0,255)',
								max : 400
							}
						}
					],
					
					
					xAxes : [ {
						ticks : {
							fontSize : 8, // x축 레이블의 글씨 크기를 10으로 설정 (원하는 크기로 조정)
						}
					} ]
				},//scale 끝
				
				layout : {
					padding : {
						top : 0,
						right : 0,
						bottom : 30,
						left : 0
					}

				},//layout 끝

				legend : {
					display : true,
					labels : {
						fontSize : 8,
						boxWidth : 5,// 범례의 글씨 크기 설정
					},
					elements : {
						line : {
							borderWidth : 1, // 선의 두께 설정 (기본값: 3)
						}
					}

				}//legend 끝 

			}//option 끝
		};//config 끝

		const SaleChart_monthly = new Chart(context_m, config_m);
	</script>


<!--  신규 가입자수  --> 
	<script>
		var context_DNS = document.getElementById('NewScriberChart_daily');
		var xlabels_DNS = ${requestScope.Date};
		var ydatas_01_DNS = ${requestScope.dailyNS};
/* 		var ydatas_02 = ${requestScope.dailyOrder}; */


		const datas_DNS = {
			labels : xlabels_DNS, /* x축을 선언하는 파트. - 위에서 선언한 labels 로 대체할 수 있다.*/

			datasets : [ /* 그려질 그래프의 정보를 입력하는 부분이다. 그래프의 배경색, 테두리색, data 값들이 입력된다. */
			{
				label : '일일 매출 현황', /* 그래프(차트)의 제목이다.*/
				fill : false,
				backgroundColor : 'rgb(255, 0, 0)',
				borderColor : 'rgb(255, 0, 0)',
				data : ydatas_01_DNS,
				yAxisID: 'y-axis-1'
		
			}, //첫번째 data 셋 입력 끝.(매출액 데이터))
			
	
			
			] //dataset 끝
		};

		/* 차트 발생 코드에 넣을 값들을 하단에 몰아 넣는다면  여기에는 이 코드가 들어가야 한다.
		
		var context = document      
		  .getElementById('myChart') 
		  .getContext('2d');	
		
		 */

		const config_DNS = {
			type : 'line',
			data : datas_DNS,
			options : {
				maintainAspectRatio : false, /*  가로세로 비율 무시하기*/

				scales : {
					yAxes: [
						{
							id: 'y-axis-1',
							position: 'left',
							ticks: {
								fontSize: 8,
								fontColor : 'rgb(255,0,0)',
							}
						},
						
						}
		 
					],
					
					
					xAxes : [ {
						ticks : {
							fontSize : 8, // x축 레이블의 글씨 크기를 10으로 설정 (원하는 크기로 조정)
						}
					} ]
				},//scale 끝
				
				layout : {
					padding : {
						top : 0,
						right : 0,
						bottom : 30,
						left : 0
					}

				},//layout 끝

				legend : {
					display : true,
					labels : {
						fontSize : 8,
						boxWidth : 5,// 범례의 글씨 크기 설정
					},
					elements : {
						line : {
							borderWidth : 1, // 선의 두께 설정 (기본값: 3)
						}
					}

				}//legend 끝 

			}//option 끝
		};//config 끝

		const SaleChart_daily = new Chart(context_DNS, config_DNS);
	</script>



















</body>
</html>