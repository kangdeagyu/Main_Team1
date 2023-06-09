package com.javalec.bbs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.javalec.bbs.dto.RDto;

public class RDao {
	DataSource dataSource;
	
	public RDao() {
		// TODO Auto-generated constructor stub
	
	try {
		Context context = new InitialContext();
		dataSource = (DataSource) context.lookup("java:comp/env/jdbc/littlep");
	}catch (Exception e) {
		e.printStackTrace();
	}
	
	}

//나의 리뷰 가져오기(잠깐 대기)
	public ArrayList<RDto> list(){
		ArrayList<RDto> dtos = new ArrayList<RDto>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

	try {
		connection = dataSource.getConnection(); // sql 연결
		String query = "select f_cid, ftype, ftitle, fcontent from forum";
	
		preparedStatement = connection.prepareStatement(query);
		resultSet = preparedStatement.executeQuery();
	
		while(resultSet.next()) {
			String f_cid = resultSet.getString("f_cid");
			int ftype = resultSet.getInt("ftype");
			String ftitle = resultSet.getString("ftitle");
			String fcontent = resultSet.getString("fcontent");
		
			
		RDto dto = new RDto(f_cid, ftype, ftitle, fcontent);
			dtos.add(dto);
			
		}
	}catch (Exception e) {
		e.printStackTrace();
	}finally {
		try {
			if(resultSet != null) resultSet.close();
			if(preparedStatement != null) preparedStatement.close();
			if(connection != null) connection.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	return dtos;
}
	

	
	//상품 상세 페이지
public ArrayList<RDto> DetailedProduct(int ppid){
	ArrayList<RDto> dtos = new ArrayList<RDto>();
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;

try {
	connection = dataSource.getConnection(); // sql 연결
	String query = "select pid, pname, pfilename, pcontent, pprice, pstock from product where pid = ?";
	preparedStatement = connection.prepareStatement(query);
	preparedStatement.setInt(1, ppid);
	resultSet = preparedStatement.executeQuery();

	while(resultSet.next()) {
		int pid = resultSet.getInt("pid");
		String pname = resultSet.getString("pname");
		String filename = resultSet.getString("pfilename");
		String pcontent = resultSet.getString("pcontent");
		int pprice = resultSet.getInt("pprice");
		int pstock = resultSet.getInt("pstock");
		
		String pfilename = "image/" + filename; 	
		RDto dto = new RDto(pid, pname, pfilename, pcontent, pprice, pstock);
		dtos.add(dto);
		
	}
}catch (Exception e) {
	e.printStackTrace();
}finally {
	try {
		if(resultSet != null) resultSet.close();
		if(preparedStatement != null) preparedStatement.close();
		if(connection != null) connection.close();
	}catch (Exception e) {
		e.printStackTrace();
	}
}

return dtos;



}

public ArrayList<RDto> productordre(int ppid){
	ArrayList<RDto> dtos = new ArrayList<RDto>();
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;

try {
	connection = dataSource.getConnection(); // sql 연결
	String query = "select pid, pname, pfilename, pcontent, pprice, pstock from product where pid = ?";
	preparedStatement = connection.prepareStatement(query);
	preparedStatement.setInt(1, ppid);
	resultSet = preparedStatement.executeQuery();

	while(resultSet.next()) {
		int pid = resultSet.getInt("pid");
		String pname = resultSet.getString("pname");
		String filename = resultSet.getString("pfilename");
		String pcontent = resultSet.getString("pcontent");
		int pprice = resultSet.getInt("pprice");
		int pstock = resultSet.getInt("pstock");
		
		String pfilename = "image/" + filename; 	
		RDto dto = new RDto(pid, pname, pfilename, pcontent, pprice, pstock);
		dtos.add(dto);
		
	}
}catch (Exception e) {
	e.printStackTrace();
}finally {
	try {
		if(resultSet != null) resultSet.close();
		if(preparedStatement != null) preparedStatement.close();
		if(connection != null) connection.close();
	}catch (Exception e) {
		e.printStackTrace();
	}
}

return dtos;



}
//데이터 베이스 장바구니(basket) 입력
public boolean InsertCart(String cid, int pid, int qty) {
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	boolean result = false;
	
	try {
		connection = dataSource.getConnection(); // sql 연결
		String query = "INSERT INTO basket (b_cid, b_pid, bqty) VALUES (?, ?, ?)";
				       		
		preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, cid);
		preparedStatement.setInt(2, pid);
		preparedStatement.setInt(3, qty);
		
		preparedStatement.executeUpdate();
	
		result = true;

	
	}catch (Exception e) {
		e.printStackTrace();
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

// write
//상품별 리뷰 잠깐 대기
//public ArrayList<RDto> ProductReview(int ppid){
//	ArrayList<RDto> dtos = new ArrayList<RDto>();
//	Connection connection = null;
//	PreparedStatement preparedStatement = null;
//	ResultSet resultSet = null;
//
//try {
//	connection = dataSource.getConnection(); // sql 연결
//	String query = "select pid, pname, pfilename, pcontent, pprice, pstock from product where pid = ?";
//	preparedStatement = connection.prepareStatement(query);
//	preparedStatement.setInt(1, ppid);
//	resultSet = preparedStatement.executeQuery();
//
//	while(resultSet.next()) {
//		int pid = resultSet.getInt("pid");
//		String pname = resultSet.getString("pname");
//		String filename = resultSet.getString("pfilename");
//		String pcontent = resultSet.getString("pcontent");
//		int pprice = resultSet.getInt("pprice");
//		int pstock = resultSet.getInt("pstock");
//		
//		String pfilename = "image/" + filename; 	
//		RDto dto = new RDto(pid, pname, pfilename, pcontent, pprice, pstock);
//		dtos.add(dto);
//		
//	}
//}catch (Exception e) {
//	e.printStackTrace();
//}finally {
//	try {
//		if(resultSet != null) resultSet.close();
//		if(preparedStatement != null) preparedStatement.close();
//		if(connection != null) connection.close();
//	}catch (Exception e) {
//		e.printStackTrace();
//	}
//}
//
//return dtos;
//
//
//
//}

}


