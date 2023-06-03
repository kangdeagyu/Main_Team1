package com.javalec.bbs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MDao {

	DataSource dataSource;
	public MDao() {
		// TODO Auto-generated constructor stub
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/littlep");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String authenticate(String username, String password) {
		String result = null;
		int count = 0;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			connection = dataSource.getConnection(); // sql 연결
			String query = "select count(cname), cname from Customer where cid = ? and cpassword = ?";
			ps = connection.prepareStatement(query);
		      ps.setString(1, username);
		      ps.setString(2, password);
		      rs = ps.executeQuery();
		      
				if(rs.next()) {
					count = rs.getInt(1);
					if(count > 0) {
						result =  rs.getString(2);	// 등록된 회원
					}
				}
				

		}catch (Exception e) {
			e.printStackTrace();
		
		}finally {
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
				if(connection != null) connection.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		
	  }
		return result;
	}
	
	
}// end
