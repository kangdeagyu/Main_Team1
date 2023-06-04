package com.javalec.bbs.command;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalec.bbs.dao.chartDao_kkg;
import com.javalec.bbs.dto.aExtraDto_kkg;


public class aHomeCommand_kkg implements MCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		
		// 오늘날짜 00:00:00을 end day로 선언. (어제날짜까지의 매출을 구하기 위함.)
        Date sqlDate = new Date(System.currentTimeMillis());
        Timestamp endday = new Timestamp(sqlDate.getTime());
        
        Calendar tempday = Calendar.getInstance();
        tempday.setTime(endday);
        tempday.add(Calendar.DAY_OF_MONTH, -14);
        
        // 시작날짜 기준으로 14일전 00:00:00 을 시작 날짜로 선언. 
        // 오늘이 15일이면  1일~14일이 범위가 되겠죠.
        Timestamp startday = new Timestamp(tempday.getTimeInMillis());
        
        
        chartDao_kkg dao = new chartDao_kkg();
		
        ArrayList<aExtraDto_kkg> ddrs = dao.dailyGraph(startday, endday);
  
        
        List<Date> dateList = getDateList(startday, endday);
        
        List<Integer> saleList = new ArrayList<>();
        
        // String 배열로 바꿀거임.
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd");
        List<String> dateListStr = new ArrayList<>();

        for (Date date : dateList) {
            String dateString = dateFormat.format(date);
            dateListStr.add("'"+dateString+"'");
        }

        // 날짜 별로 판매금액 가져오는 중. / 판매 없는 날은 0넣어야 함.
        Calendar calendar = Calendar.getInstance();
        //saleList.add(ddrs.get(0).getSales());
     System.out.println("첫번째 날짜 : "+ddrs.get(0).getDate());
     System.out.println("마지막 날 : "+sqlDate);
        int j = 0;
        int k = 0;
        int len = dateList.size();
        
        
//     while (!dateList.get(j).equals(sqlDate)) {
        while (k<len) {
    System.out.println("j 값 : " + j);
    System.out.println("k 값 : " + k);

        	
        	// DB 날짜 이후에 판매데이터가 있는지 없는지 확인해서 없으면 0으로 다 채우기.
//        	if (ddrs.get(j) == null) {
//				saleList.add(0);
//				j++;
//				k++;
//			} else {

    
    
    try {
				// DB에서 가져온 목록에서 오늘 날짜의 날이 있는지 없는지 검증.


				Date DB_date = ddrs.get(j).getDate(); // DB 데이터의 날짜.
				Date Real_date = dateList.get(k); // 실제 있어야 하는 날짜.
	System.out.println("DB 날짜 : " + DB_date);
	System.out.println("실제 날짜 : " + Real_date);

				if (!DB_date.equals(Real_date)) {
					saleList.add(0); // 날짜가 없으면 (같지 않으면) 0 더하기.
					k++;
				} else {
					saleList.add(ddrs.get(j).getSales()); // 날짜가 있으면
					System.out.println("일치한 데이터베이스 날짜 : "+DB_date);
					System.out.println("일치한 실제날짜리스 날짜 : "+Real_date);
					System.out.println("일치한 날짜의 매출액 : " + ddrs.get(j).getSales());
					j++;
					k++;
				}
    		}catch (IndexOutOfBoundsException e) {
    			saleList.add(0);
    			j++;
    			k++;
			} // outofbound 일때 끝.

//        	calendar.setTime(dateList.get(j));
//            calendar.add(Calendar.DAY_OF_YEAR, -1);
//            Date yesterday = new Date(calendar.getTimeInMillis());
//        	
//            if(yesterday.equals(dateList.get(j-1)));

			
		}//while 끝

        request.setAttribute("dailySale", saleList);
		request.setAttribute("dailyDate", dateListStr);

	}
	
	
	// ------------------- function -------------------
	
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
	

	
	
	

}
