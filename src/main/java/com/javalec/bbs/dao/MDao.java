package com.javalec.bbs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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
	
	// 로그인 체크
	public String authenticate(String username, String password) {
		String result = null;
		int count = 0;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			connection = dataSource.getConnection(); // sql 연결
			String query = "select count(cname), cname from customer where cid = ? and cpassword = ?";
			ps = connection.prepareStatement(query);
		      ps.setString(1, username);
		      ps.setString(2, password);
		      rs = ps.executeQuery();
				if(rs.next()) {
					count = rs.getInt(1);
				}
				if(count > 0) {
					result =  rs.getString(2);	// 등록된 회원
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
	
// 이메일 체크
	public String emailcheak(String email) {
		String result = "email";
		int count = 0;
		
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			connection = dataSource.getConnection(); // sql 연결
			String query = "select count(cid) from customer where cid = ?";
			ps = connection.prepareStatement(query);
		      ps.setString(1, email);
		      rs = ps.executeQuery();
		      
				if(rs.next()) {
					count = rs.getInt(1);

				}
				if(count > 0) {
					result =  "duplicate";	// 등록된 회원
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
	
	// 회원가입
	public int join(String cid, String cpassword, String cname, String cphone, String cbirthdate, int cgender, String cpostnum,
			String caddress1, String caddress2) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		int result;
		try {
			connection = dataSource.getConnection(); // sql 연결
			String query = "insert into customer (cid, cpassword, cname, cphone, cbirthdate, cgender, cpostnum, caddress1, caddress2, cinsertdate) values (?,?,?,?,?,?,?,?,?,now())";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, cid);
			preparedStatement.setString(2, cpassword);
			preparedStatement.setString(3, cname);
			preparedStatement.setString(4, cphone);
			preparedStatement.setString(5, cbirthdate);
			preparedStatement.setInt(6, cgender);
			preparedStatement.setString(7, cpostnum);
			preparedStatement.setString(8, caddress1);
			preparedStatement.setString(9, caddress2);
			
			preparedStatement.executeUpdate();
			result = 1;

		}catch (Exception e) {
			result = 0;
		}finally {
			try {
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	// Id찾기
	public String Id(String cname, String cphone) {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String result = null;
		
		try {
			connection = dataSource.getConnection(); // sql 연결
			String query = "select cid from customer where cname = ? and cphone = ?";
			ps = connection.prepareStatement(query);
		      ps.setString(1, cname);
		      ps.setString(2, cphone);
		      rs = ps.executeQuery();
		      
				if(rs.next()) {
					result =  rs.getString(1);	// 등록된 회원	
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
	
	// 비밀번호 찾기
	public String Pw(String cid, String cname) {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String result = null;
		
		try {
			connection = dataSource.getConnection(); // sql 연결
			String query = "select cpassword from customer where cid = ? and cname = ?";
			ps = connection.prepareStatement(query);
		      ps.setString(1, cid);
		      ps.setString(2, cname);
		      rs = ps.executeQuery();
		      
				if(rs.next()) {
					result =  rs.getString(1);	// 등록된 회원	
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
