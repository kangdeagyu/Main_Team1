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

import com.javalec.bbs.dto.AdminExtra_Dto_kkg;


public class ChartDao_kkg {
	
	DataSource dataSource;

	
	
	public ChartDao_kkg() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/littlep");
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	
	
	
	
	public ArrayList<AdminExtra_Dto_kkg> dailyGraph(Timestamp startday, Timestamp endday){
		ArrayList<AdminExtra_Dto_kkg> dtos= new ArrayList<AdminExtra_Dto_kkg>();
				
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
				AdminExtra_Dto_kkg dto = new AdminExtra_Dto_kkg (date, dailySales, dailyOrders);
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
	
	
	
	
	
	public ArrayList<AdminExtra_Dto_kkg> monthlyGraph(Timestamp startday, Timestamp endday){
		
		ArrayList<AdminExtra_Dto_kkg> dtos= new ArrayList<AdminExtra_Dto_kkg>();

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
						AdminExtra_Dto_kkg dto = new AdminExtra_Dto_kkg (month, monthlySales,monthlyOrders);
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
	
	public ArrayList<AdminExtra_Dto_kkg> dailyNSGraph(Timestamp startday, Timestamp endday){
		ArrayList<AdminExtra_Dto_kkg> dtos= new ArrayList<AdminExtra_Dto_kkg>();
				
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ResultSet resultSet2 = null;
		
		Date startdate = new Date(startday.getTime()); 
			System.out.println("시작 날짜 : "+startdate);
		Date enddate = new Date(endday.getTime()); 
			System.out.println("마지막 날짜 : " + enddate);
		
		
		//가져오는 값들 int bid,  int sale, 
		try {
			connection =dataSource.getConnection();
			String sql_select = "select ROW_NUMBER() OVER (ORDER BY Date(cinsertdate)) AS No, count(*) as NScount,  cinsertdate as date ";
			String sql_from =	" from customer";
			String sql_where = 	" where cinsertdate >= ? ";
			String sql_group =  " group by date";
			String sql = sql_select + sql_from + sql_where+sql_group;
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setTimestamp(1, startday);
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

			while(resultSet.next()) {
			
					//System.out.println("i값 : "+ i);
					//System.out.println("더하는 날짜 : "+oneday/(60*60*24*1000) );
				int dailyNS = resultSet.getInt("NScount");
				
//				customerint = customerint + dailyNS;
				
				
				Date date = resultSet.getDate("date");
					//System.out.println("날짜 : " +date);
					//System.out.println("매출 : "+ dailySales);
				AdminExtra_Dto_kkg dto = new AdminExtra_Dto_kkg (date, dailyNS);  //	dto 에는 오른쪽 명칭임. (Date date, int sales, int ordercount)
				dtos.add(dto);
				
			System.out.println("완료된 date : "+date);
			System.out.println("완료된 신규 가입자 : "+dailyNS);
//			System.out.println("완료된 날의 회원수 : "+ customerint);
		
			

			}
			connection.close();
		} catch (Exception e) {
				// TODO: handle exception
			System.out.println("실패");

				e.printStackTrace();
			}
		
		
		return dtos;
	} //dailyNSGraph 
	
	
	
	//adminuserlist.jsp 화면 하단의 유저 목록을 보여주기 위한 데이터 가져오기
	
	public ArrayList<AdminExtra_Dto_kkg> getUserList() {
		ArrayList<AdminExtra_Dto_kkg> userList = new ArrayList<>() ;

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = dataSource.getConnection();
			String sql_select = "select  *, ROW_NUMBER() OVER (ORDER BY Date(cinsertdate)) AS No from customer;";

			String sql = sql_select ;
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				int seq = resultSet.getInt("No");
				String cid = resultSet.getString("cid");
				String cname = resultSet.getString("cid");
				String cphone = resultSet.getString("cid");
				String cbirthdate = resultSet.getString("cid");
				String cgender = null;
				
				switch(resultSet.getInt("cgender")){
					case 0 :
						cgender = "여성";
					break;		
					
					case 1 :
						cgender = "남성";
					break;		
				}
				
				String caddress = "("+resultSet.getString("cpostnum")+") " + resultSet.getString("caddress1")+ resultSet.getString("caddress2");
				Date cinsertdate  = new Date(resultSet.getTimestamp("cinsertate").getTime());
				AdminExtra_Dto_kkg dto = new AdminExtra_Dto_kkg(seq, cid, cname, cphone, cbirthdate, cgender, caddress, cinsertdate);
				userList.add(dto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
		
		
		
		return userList;
	}//getUserList 끝
	
	
	
	
	
	

}
