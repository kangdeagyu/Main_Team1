<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 홈 화면</title>


<link href="admin_kkg.css" rel="stylesheet"> 
</head>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"></script>


<body>

	<div class="container">
	  <div class="box">	
	  월간 매출/판매 현황 <br/>
	  <canvas id="SaleChart_month" ></canvas>
	  </div>
	  
	  <div class="box">	
	  주간 매출/판매 현황  <br/>
	  <canvas id="SaleChart_weekly" ></canvas>
	  </div>  
	
	  <div class="box">	
	  일간 매출/판매 현황  <br/>
	  <canvas id="SaleChart_daily" ></canvas>
	  </div>
	

	
	</div>




















<!-- SaleChart_daily 그리기   -->
	
	
	<script>
	
			
	
	
	
	
	
	
	
			var context = document.getElementById('myChart').getContext('2d');			
			
			const labels = [];
				
			const data = {
				labels: req
				['January', 'February', 'March',  'April',  'May',  'June',] /* x축을 선언하는 파트. - 위에서 선언한 labels 로 대체할 수 있다.*/
			    
			    datasets: [  					/* 그려질 그래프의 정보를 입력하는 부분이다. 그래프의 배경색, 테두리색, data 값들이 입력된다. */
			    	{
			      label: 'My First dataset',   /* 그래프(차트)의 제목이다.*/ 
			      backgroundColor: 'rgb(255, 99, 132)',
			      borderColor: 'rgb(255, 99, 132)',
			      data: [0, 10, 5, 2, 20, 30, 25],      /* 리스트 형식으로 data들은 [ ] 안에 입력된 값들로 줘야한다. */
			    },
			    
			   	{
			      label: 'My Second dataset',
			      backgroundColor: 'rgb(120, 100, 100)',
			      borderColor: 'rgb(120, 100, 100)',
			      data: [5, 20, 15, 2, 12, 30, 15],
			    }
		
			    ]
			  };
			
		
			  /* 차트 발생 코드에 넣을 값들을 하단에 몰아 넣는다면  여기에는 이 코드가 들어가야 한다.
			  
			  var context = document      
			    .getElementById('myChart') 
			    .getContext('2d');	
			 
			  */
			  
			  
			    const config = {
					    type: 'line',
					    data: data,
					    options: {}
					  };
		
				
		  		const myChart = new Chart(document.getElementById('myChart').getcontext('2d'), config);
	
			
			
	
	</script>
	
	
	
</body>
</html>