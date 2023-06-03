package com.javalec.bbs.command;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

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
        
        
        
		request.setAttribute("dailyData", ddrs);

	}

}
