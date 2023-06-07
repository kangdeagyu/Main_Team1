package com.javalec.bbs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.javalec.bbs.dto.Admin_Product_Dto;
import com.javalec.bbs.dto.Admin_Review_Dto;

public class Admin_Review_Dao {
	
	
	// Field
	DataSource datasource;
	
	// Constructor
	public Admin_Review_Dao() {
		try {
			Context context = new InitialContext();
			datasource = (DataSource) context.lookup("java:comp/env/jdbc/littlep");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	// Method
	
	
	public ArrayList<Admin_Review_Dto> list() {
		ArrayList<Admin_Review_Dto> dtos = new ArrayList<Admin_Review_Dto>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = datasource.getConnection();
			String query = "SELECT f.fid, f.f_pid, f.finsertdate, c.cname, p.pname, p.pfilename, p.pprice, f.fcontent, f.ftitle, p.pcontent, p.pcategory"; 
			String query1 = " FROM forum f, product p, customer c, type t WHERE f.f_cid=c.cid AND f.f_pid=p.pid AND f.ftype=1;";
			preparedStatement = connection.prepareStatement(query+query1);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int fid=resultSet.getInt(1);
				int f_pid=resultSet.getInt(2);
			    Timestamp finsertdate = resultSet.getTimestamp(3);
			    String cname  = resultSet.getString(4);
			    String pname = resultSet.getString(5);
			    String pfilename=resultSet.getString(6);
			    int pprice = resultSet.getInt(7);
			    String fcontent = resultSet.getString(8);
			    String ftitle= resultSet.getString(9);
			    String pcontent =resultSet.getString(10);
			    int pcategory = resultSet.getInt(11);
			    Admin_Review_Dto dto = new Admin_Review_Dto(fid, f_pid, finsertdate, cname, pname, pfilename, pprice, fcontent, ftitle, pcontent, pcategory);
			    dtos.add(dto);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(resultSet !=null) resultSet.close();
				if(preparedStatement !=null) preparedStatement.close();
				if(connection !=null) connection.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		return dtos;
		
	} // list
}
