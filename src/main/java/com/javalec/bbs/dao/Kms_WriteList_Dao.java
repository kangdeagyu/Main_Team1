package com.javalec.bbs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.javalec.bbs.dto.Kms_WriteList_Dto;

public class Kms_WriteList_Dao {
	
	DataSource dataSource;
	
	public Kms_WriteList_Dao() {
		// TODO Auto-generated constructor stub
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/littlep");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Kms_WriteList_Dto> list(){
		ArrayList<Kms_WriteList_Dto> dtos = new ArrayList<Kms_WriteList_Dto>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = dataSource.getConnection();
			String query = "select * from forum where ftype = 1 order by fref,freforder";
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				int fid = resultSet.getInt(1);
				String f_cid = resultSet.getString(2);
				String f_aid = resultSet.getString(3);
				int f_pid = resultSet.getInt(4);
				int ftype = resultSet.getInt(5);
				int fref = resultSet.getInt(6);
				int freforder = resultSet.getInt(7);
				int fstep = resultSet.getInt(8);
				String ftitle = resultSet.getString(9);
				String fcontent = resultSet.getString(10);
				Timestamp finsertdate = resultSet.getTimestamp(11);
				Timestamp fdeletedate = resultSet.getTimestamp(12);
				int fmotherid = resultSet.getInt(13);
				int fanswernum = resultSet.getInt(14);
				
				
				
				Kms_WriteList_Dto dto = new Kms_WriteList_Dto(fid, f_cid, f_aid, f_pid, ftype, 
				fref, freforder, fstep, ftitle, fcontent, finsertdate, fdeletedate, fmotherid, fanswernum);
				dtos.add(dto);
				
			
				}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(resultSet != null) resultSet.close();
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return dtos;
		
	} // list
	
	public void forumAction(String f_cid, int f_pid,String ftitle, String fcontent) {
		Connection connection = null;
		PreparedStatement preparedStatement1 = null;
		
		try {
			connection = dataSource.getConnection();
			String query2 = "insert into forum (f_cid, f_aid, f_pid, ftype, fref, freforder, fstep, ftitle, fcontent, finsertdate, fmotherid, fanswernum)";
			String query3 = " select ?,'admin', ?, 1 , max(fref) + 1, 0, 0,?,?,now(),0,0";
			preparedStatement1 = connection.prepareStatement(query2 + query3);
			preparedStatement1.setString(1, f_cid);
			preparedStatement1.setInt(2, f_pid);
			preparedStatement1.setString(3, ftitle);
			preparedStatement1.setString(4, fcontent);
			
			
			preparedStatement1.executeUpdate();
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(preparedStatement1 != null) preparedStatement1.close();
				if(connection != null) connection.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	} // 답글 
	
	public void replyAction(int fid, String f_cid, int f_pid, int fref, int fstep, int freforder,
			String ftitle, String fcontent, int fmotherid, int fanswernum) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		PreparedStatement preparedStatement1 = null;
		PreparedStatement preparedStatement2 = null;
		int a = freforder + fanswernum;
		try {
			connection = dataSource.getConnection();
			String query = "update forum set freforder = freforder + 1 where freforder >=" + freforder + " + " + fanswernum + " + 1";
			String query1 = " and fref = " + fref;
			preparedStatement = connection.prepareStatement(query + query1);
			preparedStatement.executeUpdate();
			
			String query2 = "insert into forum (f_cid, f_aid, f_pid, ftype, fref, freforder, fstep, ftitle, fcontent, finsertdate, fmotherid, fanswernum)";
			String query3 = " values (?,'admin', ?, 1 ,?, ? + 1, ? + 1,? ,? ,now(), 0, 0)";
			preparedStatement1 = connection.prepareStatement(query2 + query3);
			preparedStatement1.setString(1, f_cid);
			preparedStatement1.setInt(2, f_pid);
			preparedStatement1.setInt(3, fref);
			preparedStatement1.setInt(4, a);
			preparedStatement1.setInt(5, fstep);
			preparedStatement1.setString(6, ftitle);
			preparedStatement1.setString(7, fcontent);
			
			
			
			preparedStatement1.executeUpdate();
				
			String query4 = "update forum set fanswernum = fanswernum + 1 where fid = " + fid;
			preparedStatement2 = connection.prepareStatement(query4);
			preparedStatement2.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(preparedStatement != null) preparedStatement.close();
				if(preparedStatement1 != null) preparedStatement1.close();
				if(preparedStatement2 != null) preparedStatement2.close();
				if(connection != null) connection.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	} // 답글 
}
