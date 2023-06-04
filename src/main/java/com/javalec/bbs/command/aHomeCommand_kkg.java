package com.javalec.bbs.command;

import java.sql.Date;
import java.sql.Timestamp;
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

        // 날짜 별로 판매금액 가져오는 중. / 판매 없는 날은 0넣어야 함.
        Calendar calendar = Calendar.getInstance();
        saleList.add(ddrs.get(0).getSales());
        int j = 1;
        while (!dateList.get(j).equals(endday)) {
        	// DB에서 가져온 목록에서 오늘 날짜의 날이 있는지 없는지 검증.
        	
        	Date DB_date = ddrs.get(j).getDate();  //DB 데이터의 날짜.
        	Date Real_date = dateList.get(j);		//실제 있어야 하는 날짜.
        	
        	if(!DB_date.equals(Real_date)) { 
        		saleList.add(0);  // 날짜가 없으면 (같지 않으면) 0 더하기.
        	}else{
        		saleList.add(ddrs.get(j).getSales()); // 날짜가 있으면
        		j++;
        	}
        		
   

        	
//        	calendar.setTime(dateList.get(j));
//            calendar.add(Calendar.DAY_OF_YEAR, -1);
//            Date yesterday = new Date(calendar.getTimeInMillis());
//        	
//            if(yesterday.equals(dateList.get(j-1)));
        	
        	
        }

        request.setAttribute("dailySale", saleList);
		request.setAttribute("dailyDate", dateList);

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
