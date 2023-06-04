<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 홈 화면</title>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"></script>


</head>

<style>
.container {
	width: 100%;
	overflow: hidden;
}

.box {
	float: left;
	width: 300px;
	height: 200px;
	background-color: rgb(240, 248, 255);
	margin-right: 1%;
	box-sizing: border-box;
}

.sidebar{
	float: left;
	width: 100px;
	height: 100%;
	background-color: rgb(150, 150, 255);
	margin-right: 1%;
	box-sizing: border-box;

}
</style>



<body>
	<jsp:include page="admin_01_header.jsp" />


	<div class="container">
		<div class="sidebar">
			<jsp:include page="admin_01_sidebar.jsp" />
		</div>




		<div>
			<h2>매출 관리</h2>
			<div class="box">
				최근 1년 월간 매출/판매 현황
				<canvas id="SaleChart_month"></canvas>
			</div>

			<div class="box">
				최근 12 주 주간 매출/판매 현황
				<canvas id="SaleChart_weekly"></canvas>
			</div>

			<div class="box">
				최근 2주간 매출/판매 현황
				<canvas id='SaleChart_daily'></canvas>
			</div>
		</div>
	</div>
	<br />

	<p>매출 :${requestScope.dailySale}</p>
	<p>날짜 :${requestScope.dailyDate}</p>








	<!-- SaleChart_daily 그리기   -->


	<script>
		var context = document.getElementById('SaleChart_daily');

		var xlabels = $
		{
			requestScope.dailyDate
		};

		var ydatas_01 = $
		{
			requestScope.dailySale
		};

		const datas = {
			labels : xlabels, /* x축을 선언하는 파트. - 위에서 선언한 labels 로 대체할 수 있다.*/

			datasets : [ /* 그려질 그래프의 정보를 입력하는 부분이다. 그래프의 배경색, 테두리색, data 값들이 입력된다. */
			{
				label : '일일 매출 현황', /* 그래프(차트)의 제목이다.*/
				fill : false,
				backgroundColor : 'rgb(255, 0, 0)',
				borderColor : 'rgb(255, 0, 0)',
				data : ydatas_01
			/* 리스트 형식으로 data들은 [ ] 안에 입력된 값들로 줘야한다. */
			} ]
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
					xAxes : [ {
						ticks : {
							fontSize : 8, // x축 레이블의 글씨 크기를 10으로 설정 (원하는 크기로 조정)
						}
					} ]
				},/* scale 끝 */
				layout : {
					padding : {
						top : 0,
						right : 0,
						bottom : 20,
						left : 0
					}

				},/*  layout 끝*/

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

				}
			//legend 끝 

			}
		/* option 끝 */
		};

		const SaleChart_daily = new Chart(context, config);
	</script>



</body>
</html>