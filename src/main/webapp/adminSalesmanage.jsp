<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>매출 관리</title>
</head>

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
<!-- 달력부분 -->

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://code.jquery.com/ui/1.13.1/jquery-ui.min.js"></script>
<link rel="stylesheet"
	href="https://code.jquery.com/ui/1.13.1/themes/base/jquery-ui.css">


<script type="text/javascript">
			

				
				/* 날짜 입력의 정규화를 위함 */
			function checkDate(){
				const regstartdate = /^(19|20)\d{2}-(0[1-9]|1[0-2])-(0[1-9]|[12]\d|3[01])$/;
				const regenddate = /^(19|20)\d{2}-(0[1-9]|1[0-2])-(0[1-9]|[12]\d|3[01])$/;	
					
				const formdate = document.date;
				const startDate = formdate.startDate.value;
				const endDate = formdate.endDate.value;
				
				
				if(!regstartdate.test(startDate)){
						alert("시작 날짜를 입력해 주세요.")
						formdate.startDate.select();
						return;
					}
					
				if(!regenddate.test(endDate)){
						alert("마지막 날짜를 입력해 주세요")
						formdate.endDate.select();
						return;
					}
				if (endDate < startDate) {
				    alert("종료일은 시작일보다 뒷날짜여야 합니다.");
				    formdate.endDate.select();
				    return;
				}
 
				formdate.submit();

			}


			/* 페이징 처리를 위한 버튼 */
			
		
		
			
			
</script>

<body>




	<div class="container_mk">
		<div class="headbar">
			<jsp:include page="admin_01_header.jsp" />
		</div>
		<div class="sidebar">
			<jsp:include page="admin_01_sidebar.jsp" />
		</div>
	</div>



	<div>
		<p>일별 매출/주문 현황</p>
		<canvas class="my-4 w-100 chartjs-render-monitor" id="dailychart"
			width="700" height="400"
			style="display: block; width: 400px; height: 275px;"></canvas>
	</div>









	<script>
	    
		DoubleLineChart(document.getElementById('dailychart'),${requestScope.dateList},'매출액',${requestScope.dailySales},'결재건',${requestScope.dailyOrders});  
		DoubleLineChart(document.getElementById('monthlychart'), ${requestScope.monthList}, '매출액' ,${requestScope.monthlySales},'결재건',${requestScope.monthlyOrders});  
    	PieChart("카테고리별 매출 비중 분석",document.getElementById('categorychart_sale'),${requestScope.categoryList},${requestScope.categorySales} );
		SingleBarChart(document.getElementById('categorychart_order'),${requestScope.categoryList},'판매량',${requestScope.categoryOrders})		;
		DoublebarChart(document.getElementByUd('categorygenderchart_sale'),${requestScope.categoryGenderSales_male},${requestScope.categoryGenderSales_female});
		DoublebarChart(document.getElementByUd('categorygenderchart_order'),${requestScope.categoryGenderOrder_male},${requestScope.categoryGenderOrder_female});
		
		
    	PieChart("남녀 매출 비교",document.getElementById('gender_sale'),${requestScope.genderList},${requestScope.genderSales} );
    	PieChart("남녀 주문량 비교",document.getElementById('gender_order'),${requestScope.genderList},${requestScope.genderOrders} );

		SingleBarChart(document.getElementById('orderTopChart'),${requestScope.maxPnameOrders},'판매량',${requestScope.MaxOrders})		;
		SingleBarChart(document.getElementById('categorychart_order'),${requestScope.maxPnameSales},'매출액',${requestScope.maxSales})		;

		
		
	</script>





</body>
</html>