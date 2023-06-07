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
		ArrayList<aExtraDto_kkg> dtos= new ArrayList<aExtraDto_kkg>();
				
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		Date startdate = new Date(startday.getTime()); 
			System.out.println("시작 날짜 : "+startdate);
		Date enddate = new Date(endday.getTime()); 
			System.out.println("마지막 날짜 : " + enddate);
		long oneday = 24*60*60*1000;
		
		
		//가져오는 값들 int bid,  int sale, 
		try {
			connection =dataSource.getConnection();
			String sql_select = "select ROW_NUMBER() OVER (ORDER BY Date(odate)) AS No, sum(oprice) as sale, count(*) as ocount, Date(odate) as date ";
			String sql_from =	" from ordering";
			String sql_where = 	" where odate >= ? AND odate < ? group by date(odate);";
			String sql_group = "";
			String sql = sql_select + sql_from + sql_where+sql_group;
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setTimestamp(1, startday);
			preparedStatement.setTimestamp(2, endday);
			
			System.out.println(sql);
			resultSet = preparedStatement.executeQuery();
			int i = 0;
			while(resultSet.next()) {
				
				long dayplus = oneday *i;
					//System.out.println("i값 : "+ i);
					//System.out.println("더하는 날짜 : "+oneday/(60*60*24*1000) );
				int dailySales = resultSet.getInt("sale");
				int dailyOrders = resultSet.getInt("ocount");
				Date date = resultSet.getDate("date");
					System.out.println("날짜 : " +date);
					System.out.println("매출 : "+ dailySales);
				aExtraDto_kkg dto = new aExtraDto_kkg (date, dailySales, dailyOrders);
				dtos.add(dto);
				
			System.out.println("완료된 date : "+date);
			System.out.println("완료된 날의 sale : "+ dailySales);
			i++;
			

			}
			connection.close();
		} catch (Exception e) {
				// TODO: handle exception
			System.out.println("실패");
			
				e.printStackTrace();
			}
		
		return dtos;
	}
	
	
	
	
	
	public ArrayList<aExtraDto_kkg> monthlyGraph(Timestamp startday, Timestamp endday){
		
		ArrayList<aExtraDto_kkg> dtos= new ArrayList<aExtraDto_kkg>();

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		

		Date startdate = new Date(startday.getTime()); 
			System.out.println("시작 날짜 : "+startdate);
		Date enddate = new Date(endday.getTime()); 
			System.out.println("마지막 날짜 : " + enddate);
		long oneday = 24*60*60*1000;
		
		
		
		
		//가져오는 값들 int bid,  int sale, 
				try {
					connection =dataSource.getConnection();
					String sql_select = "select ROW_NUMBER() OVER (ORDER BY Date_format(odate,'%Y-%m')) AS No, sum(oprice) as sale, count(*) as ocount, Date_format(odate,'%Y-%m') as month ";
					String sql_from =	" from ordering";
					String sql_where = 	" where odate >= ? AND odate < ? group by Date_format(odate,'%Y-%m')";
					String sql_group = "";
					String sql = sql_select + sql_from + sql_where+sql_group;
					preparedStatement = connection.prepareStatement(sql);
					preparedStatement.setTimestamp(1, startday);
					preparedStatement.setTimestamp(2, endday);
					
					System.out.println(sql);
					resultSet = preparedStatement.executeQuery();
					int i = 0;
					while(resultSet.next()) {
						
						long dayplus = oneday *i;
						int monthlySales = resultSet.getInt("sale");
						int monthlyOrders = resultSet.getInt("ocount");
						String month = resultSet.getString("month");
						
							//System.out.println("날짜 : " +date);
							//System.out.println("매출 : "+ dailySales);
						aExtraDto_kkg dto = new aExtraDto_kkg (month, monthlySales,monthlyOrders);
						dtos.add(dto);
						
					System.out.println("완료된 month : "+month);
					System.out.println("완료된 월의 sale : "+ monthlySales);
					i++;
					}
					connection.close();
				} catch (Exception e) {
						// TODO: handle exception
					System.out.println("실패");

						e.printStackTrace();
					}
		
		
		
		
		
		
		
		
		
		
		
		return dtos;
	}
	

	
	// 신규가입자 현황 (Daily)
	
	public ArrayList<aExtraDto_kkg> dailyNSGraph(Timestamp startday, Timestamp endday){
		ArrayList<aExtraDto_kkg> dtos= new ArrayList<aExtraDto_kkg>();
				
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ResultSet resultSet2 = null;
		
		Date startdate = new Date(startday.getTime()); 
			System.out.println("시작 날짜 : "+startdate);
		Date enddate = new Date(endday.getTime()); 
			System.out.println("마지막 날짜 : " + enddate);
		long oneday = 24*60*60*1000;
		
		
		//가져오는 값들 int bid,  int sale, 
		try {
			connection =dataSource.getConnection();
			String sql_select = "select ROW_NUMBER() OVER (ORDER BY Date(cinsertdate)) AS No, count(*) as NScount,  cinsertdate as date ";
			String sql_from =	" from customer";
			String sql_where = 	" where cinsertdate >= ? AND cdeletedate < ? ";
			String sql_group =  " group by date";
			String sql = sql_select + sql_from + sql_where+sql_group;
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setTimestamp(1, startday);
			preparedStatement.setTimestamp(2, endday);
			System.out.println(sql);
			resultSet = preparedStatement.executeQuery();
			
//			String sql_select2	= "select count(*) as NScount";
//			String sql_from2 	= " from custoemr";
//			String sql_where2	= " where cinsertdate < ?";
//			String sql_group2	= " ";
//			String sql2 = sql_select2 + sql_from2 + sql_where2+sql_group2;
//			preparedStatement = connection.prepareStatement(sql2);
//			preparedStatement.setTimestamp(1, startday);
//			
//			System.out.println(sql2);
//			resultSet2 = preparedStatement.executeQuery();
//			int customerint = resultSet2.getInt("NScount");			

			int i = 0;
			while(resultSet.next()) {
			
				long dayplus = oneday *i;
					//System.out.println("i값 : "+ i);
					//System.out.println("더하는 날짜 : "+oneday/(60*60*24*1000) );
				int dailyNS = resultSet.getInt("NScount");
				
//				customerint = customerint + dailyNS;
				
				
				Date date = resultSet.getDate("date");
					//System.out.println("날짜 : " +date);
					//System.out.println("매출 : "+ dailySales);
				aExtraDto_kkg dto = new aExtraDto_kkg (date, dailyNS);  //	dto 에는 오른쪽 명칭임. (Date date, int sales, int ordercount)
				dtos.add(dto);
				
			System.out.println("완료된 date : "+date);
			System.out.println("완료된 신규 가입자 : "+dailyNS);
//			System.out.println("완료된 날의 회원수 : "+ customerint);
			i++;
			

			}
			connection.close();
		} catch (Exception e) {
				// TODO: handle exception
			System.out.println("실패");

				e.printStackTrace();
			}
		
		
		return dtos;
	} //dailyNSGraph 
	
	
	
	
	
	
	
	
	
	

}
