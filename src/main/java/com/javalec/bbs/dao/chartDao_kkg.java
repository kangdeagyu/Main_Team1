package com.javalec.bbs.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.javalec.bbs.dto.aExtraDto_kkg;


public class chartDao_kkg {
	
	DataSource dataSource;

	
	
	public chartDao_kkg() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/littlep");
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	
	
	
	
	public ArrayList<aExtraDto_kkg> dailyGraph(Timestamp startday, Timestamp endday){
		ArrayList<aExtraDto_kkg> dtos= null;
				
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		Date startdate = new Date(startday.getTime()); 
		Date enddate = new Date(endday.getTime()); 
		long oneday = 24*60*60*1000;
		
		Date enddatePlusOneday = new Date(enddate.getTime()+oneday);
		
		//가져오는 값들 int bid,  int sale, 
		try {
			connection =dataSource.getConnection();
			String sql_select = "Select ROW_NUMBER() OVER (ORDER BY Date(o.odate)) AS No, sum(oprice) as sale, Date(o.odate) as date ";
			String sql_from ="from ordering as o";
			String sql_where = "where odate >= ? AND odate < ? ";
			String sql_group = "group by date";
			String sql = sql_select + sql_from + sql_where+sql_group;
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setTimestamp(1, startday);
			preparedStatement.setTimestamp(2, endday);
			
			resultSet = preparedStatement.executeQuery();
			int i = 0;
			while(resultSet.next()) {
				
				long dayplus = oneday *i;
				int dailySales = resultSet.getInt("sale");
				Date date = new Date(enddate.getTime()+dayplus);
				
				aExtraDto_kkg dto = new aExtraDto_kkg (date, dailySales);
				dtos.add(dto);
				
			System.out.println("완료된 date : "+date);
			}
		} catch (Exception e) {
				// TODO: handle exception
			System.out.println("실패");

				e.printStackTrace();
			}
		
		
		return dtos;
	}

}
