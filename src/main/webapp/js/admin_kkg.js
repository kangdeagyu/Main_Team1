/**
 * 
 */


function DoublebarChart(context, xlabel, ylabel_01, ylabel_02) {

	const datas = {
		labels: xlabel,
		datasets: [
			{
				label: "남",
				backgroundcolor: "blue",
				data: ylabel_01

			},
			{
				label: "여",
				fillcolor: "red",
				data: ylabel_02

			}
		]

	}

	const config = {
		type: 'bar',
		data: datas,




	}

	const barChart_double = new Chart(context, config);
	return barChart_double;
}



// 한개의 데이터를 한개의 바차트로 보여주는 함수.
function SingleBarChart(context, xlabels, y01, ydatas_01) {

	const datas = {
		labels: xlabels, /* x축을 선언하는 파트. - 위에서 선언한 labels 로 대체할 수 있다.*/

		datasets: [ /* 그려질 그래프의 정보를 입력하는 부분이다. 그래프의 배경색, 테두리색, data 값들이 입력된다. */
			{
				label: y01, /* 그래프(차트)의 제목이다.*/
				fill: false,
				backgroundColor: 'rgb(255, 0, 0)',
				borderColor: 'rgb(255, 0, 0)',
				data: ydatas_01,
				yAxisID: 'y-axis-1'

			} //첫번째 data 셋 입력 끝.(매출액 데이터))

		] //dataset 끝
	};

	/* 차트 발생 코드에 넣을 값들을 하단에 몰아 넣는다면  여기에는 이 코드가 들어가야 한다.
	
	var context = document      
	  .getElementById('myChart') 
	  .getContext('2d');	
	
	 */

	const config = {
		type: 'bar',
		data: datas,
		options: {
			maintainAspectRatio: false, /*  가로세로 비율 무시하기*/

			scales: {
				x: {
					grid: {
						display: false
					}
				},
				y: {
					grid: {
						display: false
					}

				},
				yAxes: [
					{
						id: 'y-axis-1',
						position: 'left',
						ticks: {
							fontSize: 8,
							fontColor: 'rgb(255,0,0)',
							suggestedMax: 10
						}
					}

				],


				xAxes: [{
					ticks: {
						fontSize: 8, // x축 레이블의 글씨 크기를 10으로 설정 (원하는 크기로 조정)
					}
				}]
			},//scale 끝

			layout: {
				padding: {
					top: 0,
					right: 0,
					bottom: 30,
					left: 0
				}

			},//layout 끝

			legend: {
				display: true,
				labels: {
					fontSize: 8,
					boxWidth: 5,// 범례의 글씨 크기 설정
				},
				elements: {
					line: {
						borderWidth: 1, // 선의 두께 설정 (기본값: 3)
					}
				}

			}//legend 끝 

		}//option 끝
	};//config 끝

	const barChart_single = new Chart(context, config);
	return barChart_single;


}




function PieChart(Title, context, xlabel, ydatas_01) {


	const dataset = {
		label: Title,
		data: ydatas_01

	}

	const labels = xlabel

	const datas = { datasets: [dataset], lavels: labels }




	const config = {
		type: 'pie',
		data: datas, //데이터 셋 
		options: {
			responsive: true,
			maintainAspectRatio: false, //true 하게 되면 캔버스 width,height에 따라 리사이징된다.
			legend: {
				position: 'top',
				fontColor: 'black',
				align: 'center',
				display: true,
				fullWidth: true,
				labels: {
					fontColor: 'rgb(0, 0, 0)'
				}
			},
			plugins: {
				labels: {//두번째 script태그를 설정하면 각 항목에다가 원하는 데이터 라벨링을 할 수 있다.
					render: 'value',
					fontColor: 'black',
					fontSize: 15,
					precision: 2
				}

			}
		}
	}

	const PieChart = new Chart(context, config);
	return PieChart;
}
































//한개 데이터를 한화면에 보여주는 함수
function SingleLineChart(context, xlabels, y01, ydatas_01) {


	const datas = {
		labels: xlabels, /* x축을 선언하는 파트. - 위에서 선언한 labels 로 대체할 수 있다.*/

		datasets: [ /* 그려질 그래프의 정보를 입력하는 부분이다. 그래프의 배경색, 테두리색, data 값들이 입력된다. */
			{
				label: y01, /* 그래프(차트)의 제목이다.*/
				fill: false,
				backgroundColor: 'rgb(255, 0, 0)',
				borderColor: 'rgb(255, 0, 0)',
				data: ydatas_01,
				yAxisID: 'y-axis-1'

			} //첫번째 data 셋 입력 끝.(매출액 데이터))

		] //dataset 끝
	};

	/* 차트 발생 코드에 넣을 값들을 하단에 몰아 넣는다면  여기에는 이 코드가 들어가야 한다.
	
	var context = document      
	  .getElementById('myChart') 
	  .getContext('2d');	
	
	 */

	const config = {
		type: 'line',
		data: datas,
		options: {
			maintainAspectRatio: false, /*  가로세로 비율 무시하기*/

			scales: {
				x: {
					grid: {
						display: false
					}
				},
				y: {
					grid: {
						display: false
					}

				},
				yAxes: [
					{
						id: 'y-axis-1',
						position: 'left',
						ticks: {
							fontSize: 8,
							fontColor: 'rgb(255,0,0)',
							/* max : 1600000 */
						}
					}

				],


				xAxes: [{
					ticks: {
						fontSize: 8, // x축 레이블의 글씨 크기를 10으로 설정 (원하는 크기로 조정)
					}
				}]
			},//scale 끝

			layout: {
				padding: {
					top: 0,
					right: 0,
					bottom: 30,
					left: 0
				}

			},//layout 끝

			legend: {
				display: true,
				labels: {
					fontSize: 8,
					boxWidth: 5,// 범례의 글씨 크기 설정
				},
				elements: {
					line: {
						borderWidth: 1, // 선의 두께 설정 (기본값: 3)
					}
				}

			}//legend 끝 

		}//option 끝
	};//config 끝

	const LineChart_single = new Chart(context, config);
	return LineChart_single;


}

// 두개 데이터차트를 한 화면에 보여주는 함수.
function DoubleLineChart(context, xlabels, y01, ydatas_01, y02, ydatas_02) {




	/*		var xlabels = ${requestScope.dailyDate};
	
			var ydatas_01 = ${requestScope.dailySale};
			var ydatas_02 = ${requestScope.dailyOrder};
	*/
	const datas = {
		labels: xlabels, /* x축을 선언하는 파트. - 위에서 선언한 labels 로 대체할 수 있다.*/

		datasets: [ /* 그려질 그래프의 정보를 입력하는 부분이다. 그래프의 배경색, 테두리색, data 값들이 입력된다. */
			{
				label: y01, /* 그래프(차트)의 제목이다.*/
				fill: false,
				backgroundColor: 'rgb(255, 0, 0)',
				borderColor: 'rgb(255, 0, 0)',
				data: ydatas_01,
				yAxisID: 'y-axis-1'

			}, //첫번째 data 셋 입력 끝.(매출액 데이터))

			{
				label: y02, /* 그래프(차트)의 제목이다.*/
				fill: false,
				backgroundColor: 'rgb(0, 0, 255)',
				borderColor: 'rgb(0, 0, 255)',
				data: ydatas_02,
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
		type: 'line',
		data: datas,
		options: {
			maintainAspectRatio: false, /*  가로세로 비율 무시하기*/

			scales: {
				x: {
					grid: {
						display: false,
					}
				},
				y: {
					grid: {
						display: false,
					}

				},
				yAxes: [
					{
						id: 'y-axis-1',
						position: 'left',
						ticks: {
							fontSize: 8,
							fontColor: 'rgb(255,0,0)',
							/* max : 1600000 */
						}
					},
					{
						id: 'y-axis-2',
						position: 'right',
						ticks: {
							fontSize: 8,
							fontColor: 'rgb(0,0,255)',
							/* max : 16 */
						}
					}
				],


				xAxes: [{
					ticks: {
						fontSize: 8, // x축 레이블의 글씨 크기를 10으로 설정 (원하는 크기로 조정)
					}
				}]
			},//scale 끝

			layout: {
				padding: {
					top: 0,
					right: 0,
					bottom: 30,
					left: 0
				}

			},//layout 끝

			legend: {
				display: true,
				labels: {
					fontSize: 8,
					boxWidth: 5,// 범례의 글씨 크기 설정
				},
				elements: {
					line: {
						borderWidth: 1, // 선의 두께 설정 (기본값: 3)
					}
				}

			}//legend 끝 

		}//option 끝
	};//config 끝

	const SaleChart_double = new Chart(context, config);
	return SaleChart_double;
}
