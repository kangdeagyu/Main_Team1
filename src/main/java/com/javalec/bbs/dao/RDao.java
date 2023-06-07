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

//나의 리뷰 가져오기
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
			String ftype = resultSet.getString("ftype");
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
public ArrayList<RDto> DetailedProduct(){
	ArrayList<RDto> dtos = new ArrayList<RDto>();
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;

try {
	connection = dataSource.getConnection(); // sql 연결
	String query = "select pid, pname, pfilename, pcontent, pprice from product";
	preparedStatement = connection.prepareStatement(query);
	resultSet = preparedStatement.executeQuery();

	while(resultSet.next()) {
		int pid = resultSet.getInt("pid");
		String pname = resultSet.getString("pname");
		String pfilename = resultSet.getString("pfilename");
		String pcontent = resultSet.getString("pcontent");
		int pprice = resultSet.getInt("pprice");
	
		RDto dto = new RDto(pid, pname, pfilename, pcontent, pprice);
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


}


