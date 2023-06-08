package com.javalec.bbs.command;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalec.bbs.dao.chartDao_kkg;
import com.javalec.bbs.dto.aExtraDto_kkg;

public class aUserListCommnad_kkg implements MCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		// x축 (날짜) 리스트 만들기.
		// String startDate ;
		// String endDate;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat tempFormat = new SimpleDateFormat("MM/dd/yyyy");

		Timestamp startday = null; 
//		Timestamp.valueOf(request.getAttribute("startDate").toString());
		Timestamp endday = null;
//		Timestamp.valueOf(request.getAttribute("endDate").toString());
		// String replacedStr = str.replace("-", "/");
		// request.startDate = null일때. startDate = 7일전으로 설정 endDate = 오늘로 설정.

		// 시작 날짜와 종료 날짜를 LocalDate로 변환
		
		try {
			startday=Timestamp.valueOf(tempFormat.format(request.getParameter("startDate")) + " 00:00:00");
			endday=Timestamp.valueOf(tempFormat.format(request.getParameter("endDate")) + " 00:00:00");
			
		} catch (Exception e) {
			
			startday=Timestamp.valueOf(dateFormat.format(request.getAttribute("startDate")) + " 00:00:00");
			endday=Timestamp.valueOf(dateFormat.format(request.getAttribute("endDate")) + " 00:00:00");
			// TODO: handle exception
		}


	

		// String 배열로 바꿀거임.
		List<Date> dateList = getDateList(startday, endday); // method01. Timestamp 2개로 List<DATE> 만들어오기.
		List<String> dateListStr = new ArrayList<>();
		List<Integer> dailyNSList = new ArrayList<>();
		for (Date date : dateList) {
			String dateString = dateFormat.format(date);

			System.out.println("저장되는 날짜 확인 : " + dateString);
			dateListStr.add("'" + dateString + "'");

		}

		chartDao_kkg dao = new chartDao_kkg();

		ArrayList<aExtraDto_kkg> DNrs = dao.dailyNSGraph(startday, endday); // Daily New subscriber
		System.out.println("DNrs 의 값 : " + DNrs.get(0).getDate());
		dailyNSList = getDailySaleList(dateList, DNrs); // method02. dailySale 랑 같이써도 상관 없음.
		// 추후에 탈퇴자 내용포함 시킬 것.

		// request 셋팅하기
		request.setAttribute("dailyDate", dateListStr);
		System.out.println("여기는 왔니?");
		request.setAttribute("dailyNS", dailyNSList);

	}// body end
		// ----------function----------

	// ----------------- method01. 실제로 JSP로 넘겨줄 Date 변수들의 리스트 목록 dateList 만들기.
	// ----------------
	private static List<Date> getDateList(Timestamp startday, Timestamp endday) {
		List<Date> dateList = new ArrayList<>();

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startday);

		while (!calendar.getTime().after(endday)) {
			Date currentDate = new Date(calendar.getTimeInMillis());
			dateList.add(currentDate);
			calendar.add(Calendar.DATE, 1);
		}

		return dateList;
	}

	// -------------------------method02. DailySale 저장하기 getDailySaleList 메소드
	// 시작-------------------------------------------
	// -------------------------method02. g회원수 가져올때 사용해도
	// 됨-------------------------------------------

	private List<Integer> getDailySaleList(List<Date> dateList, ArrayList<aExtraDto_kkg> ddrs) {

		List<Integer> saleList = new ArrayList<>();

		// 날짜 별로 판매금액 가져오는 중. / 판매 없는 날은 0넣어야 함.

		// while 문을 돌려서, 매출액 리스트를 채울거임. while 문 조건은 다음과 같음.
		// 실제 기간내 모든 날짜가 담긴 리스트 : dateList 의 길이를 len 으로 지정함.
		// k는 dateList 내 날짜를 탐색할 번호임. 이 번호가 dateList의 길이보다 작아야함.(배열의 시작은 0이기 때문임)
		// j는 DB에서 받아온 Arraylist<dto> 안에 있는 날짜들을 탐색하는 번호임.
		// 판매가 없는 날은 포함되지 않았을 것이기에, 탐색번호를 j,k로 구분하였음.

		int j = 0;
		int k = 0;
		int len = dateList.size(); // 실제 기간내 모든 기간이

		// --------------while문 설명 : while문이 돌아가는 조건은 위에서 이야기하였음.----------------
		// try 문을 쓴 이유는 db에서 가져온 값에 null point exception 이 발생할 것이기 때문임.
		// 최근 몇일간 판매량이 없었다면,혹은 당일 판매량이 아직 없다면 null point exception이 발생할 수 밖에 없음.
		// 따라null point exception 이 발생하면, k값만 늘려서 반복문을 지속하기 위함. (근데 j값 늘려도 상관 없음. 어차피 db
		// 에서 가져온 데이터는 끝일거라서.)
		// try 문 DB 값의 날짜와, real 날짜 리스트의 값을 탐색함.

		// ------ try 안의 if 문 설---------
		// if 문안에서 위에 탐색한 두 날짜가 없으면, saleList에다가 0 을 추가함. ( 해당하는 Real_date에 해당하는 sale 값이
		// 없다는 소리기 때문)
		// DB 값의 날짜는 그대로 두고, real 날짜만 다음 날짜를 탐색해 오기 위해 k에만 1을 더해줌.

		// 위에 탐색한 두 날짜가 같으면 해당하는 db 값의 sale 데이터를 saleList에 추가함.
		// j와 k를 둘다 ++함.

		while (k < len) {
//System.out.println("j 값 : " + j);
//System.out.println("k 값 : " + k);

			try {
				// DB에서 가져온 목록에서 오늘 날짜의 날이 있는지 없는지 검증.
				Date DB_date = ddrs.get(j).getDate(); // DB 데이터의 날짜.
				Date Real_date = dateList.get(k); // 실제 있어야 하는 날짜.
//System.out.println("DB 날짜 : " + DB_date);
//System.out.println("실제 날짜 : " + Real_date);
//System.out.println(DB_date.compareTo(Real_date));

//DateTimeFormatter 객체를 생성하고, 날짜 형식을 설정합니다.
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

//날짜를 문자열로 변환합니다.

				if (dtf.format(DB_date.toLocalDate()).equals(dtf.format(Real_date.toLocalDate()))) {

					saleList.add(ddrs.get(j).getSales()); // 날짜가 있으면
					System.out.println("일치한 데이터베이스 날짜 : " + DB_date);
					System.out.println("일치한 실제날짜리스 날짜 : " + Real_date);
					System.out.println("일치한 날짜의 매출액 : " + ddrs.get(j).getSales());
					j++;
					k++;
				}

				else if (DB_date.compareTo(Real_date) > 0) {
					saleList.add(0); // 날짜가 없으면 (같지 않으면) 0 더하기.
					k++;

				} else {
					j++;
				}

			} catch (IndexOutOfBoundsException e) {
				saleList.add(0);
				// j++;
				k++;
			} // outofbound에러 처리 끝.
		} // daily sale을 위한 while 끝

		return saleList;

	} // -------------------------DailySale 저장하기 getDailySaleList
		// 끝-------------------------------------------

}// end
