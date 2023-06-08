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

	public ArrayList<Kms_WriteList_Dto> list(int Ftype){
		ArrayList<Kms_WriteList_Dto> dtos = new ArrayList<Kms_WriteList_Dto>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = dataSource.getConnection();
			String query = "select * from forum where ftype = ? order by fref desc,freforder";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, Ftype);
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
			String query3 = " select ?,'admin', ?, 1 , max(fref) + 1, 0, 0,?,?,now(),0,0 from forum";
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
		PreparedStatement preparedStatement3 = null;
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
			
			String query5 = "update forum set fanswernum = fanswernum + 1 where fref =" + fref + " and freforder <" + freforder + " and fanswernum >= 1";
			preparedStatement3 = connection.prepareStatement(query5);
			preparedStatement3.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(preparedStatement != null) preparedStatement.close();
				if(preparedStatement1 != null) preparedStatement1.close();
				if(preparedStatement2 != null) preparedStatement2.close();
				if(preparedStatement3 != null) preparedStatement3.close();
				if(connection != null) connection.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	} // 답글 
	
	public Kms_WriteList_Dto ForumView(int fid) {
		Kms_WriteList_Dto dto = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		
		try {
			connection = dataSource.getConnection();
			String query = "select * from forum where fid = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, fid);
			rs = preparedStatement.executeQuery();
			
			if(rs.next()) { // 한 줄 짜리니까 while 말고 if 쓰자
				int Fid = rs.getInt(1);
				String f_cid = rs.getString(2);
				String f_aid = rs.getString(3);
				int f_pid = rs.getInt(4);
				int ftype = rs.getInt(5);
				int fref = rs.getInt(6);
				int freforder = rs.getInt(7);
				int fstep = rs.getInt(8);
				String ftitle = rs.getString(9);
				String fcontent = rs.getString(10);
				Timestamp finsertdate = rs.getTimestamp(11);
				Timestamp fdeletedate = rs.getTimestamp(12);
				int fmotherid = rs.getInt(13);
				int fanswernum = rs.getInt(14);
				
				dto = new Kms_WriteList_Dto(Fid, f_cid, f_aid, f_pid, ftype, fref, freforder, fstep, ftitle, fcontent, finsertdate, fdeletedate, fmotherid, fanswernum);
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return dto;

	} // content view
	
	public ArrayList<Kms_WriteList_Dto> commentList(int fid){
		ArrayList<Kms_WriteList_Dto> dtos1 = new ArrayList<Kms_WriteList_Dto>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = dataSource.getConnection();
			String query = "select * from forum where ftype = 3 and fmotherid = ? order by fref desc,freforder";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, fid);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				int f_id = resultSet.getInt(1);
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
				
				
				
				Kms_WriteList_Dto dto = new Kms_WriteList_Dto(f_id, f_cid, f_aid, f_pid, ftype, fref, freforder, 
						fstep, ftitle, fcontent, finsertdate, fdeletedate, fmotherid, fanswernum);
				dtos1.add(dto);
				
			
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
		
		return dtos1;
		
	} // 댓글list
	
	public void commentAction(String f_cid, int f_pid, String ftitle, int fid) {
		Connection connection = null;
		PreparedStatement preparedStatement1 = null;
		
		try {
			connection = dataSource.getConnection();
			String query2 = "insert into forum (f_cid, f_aid, f_pid, ftype, fref, freforder, fstep, ftitle, fcontent, finsertdate, fmotherid, fanswernum)";
			String query3 = " select ?,'admin', ?, 3 , max(fref) + 1, 0, 0, ?, ?, now(),?,0 from forum";
			preparedStatement1 = connection.prepareStatement(query2 + query3);
			preparedStatement1.setString(1, f_cid);
			preparedStatement1.setInt(2, f_pid);
			preparedStatement1.setString(3, ftitle);
			preparedStatement1.setString(4, ftitle);
			preparedStatement1.setInt(5, fid);
			
			
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
		
	} // 댓글 
	
	public void bigCommentAction(int fid, String f_cid, int f_pid, int fref, int fstep, int freforder,
			String ftitle, String fcontent, int fmotherid, int fanswernum) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		PreparedStatement preparedStatement1 = null;
		PreparedStatement preparedStatement2 = null;
		PreparedStatement preparedStatement3 = null;
		int a = freforder + fanswernum;
		try {
			connection = dataSource.getConnection();
			String query = "update forum set freforder = freforder + 1 where freforder >=" + freforder + " + " + fanswernum + " + 1";
			String query1 = " and fref = " + fref;
			preparedStatement = connection.prepareStatement(query + query1);
			preparedStatement.executeUpdate(); // 자리 만들어주기
			
			String query2 = "insert into forum (f_cid, f_aid, f_pid, ftype, fref, freforder, fstep, ftitle, fcontent, finsertdate, fmotherid, fanswernum)";
			String query3 = " values (?,'admin', ?, 3 ,?, ? + 1, ? + 1,? ,? ,now(), ?, 0)";
			preparedStatement1 = connection.prepareStatement(query2 + query3);
			preparedStatement1.setString(1, f_cid);
			preparedStatement1.setInt(2, f_pid);
			preparedStatement1.setInt(3, fref);
			preparedStatement1.setInt(4, a);
			preparedStatement1.setInt(5, fstep);
			preparedStatement1.setString(6, ftitle);
			preparedStatement1.setString(7, ftitle);
			preparedStatement1.setInt(8, fmotherid); // 대댓글 입력하기
			
			
			
			preparedStatement1.executeUpdate();
				
			String query4 = "update forum set fanswernum = fanswernum + 1 where fid = " + fid;
			preparedStatement2 = connection.prepareStatement(query4);
			preparedStatement2.executeUpdate(); // 다음 대댓글을 위한 작업1
			
			String query5 = "update forum set fanswernum = fanswernum + 1 where fref =" + fref + " and freforder <" + freforder + " and fanswernum >= 1";
			preparedStatement3 = connection.prepareStatement(query5);
			preparedStatement3.executeUpdate(); // 다음 대댓글을 위한 작업2
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(preparedStatement != null) preparedStatement.close();
				if(preparedStatement1 != null) preparedStatement1.close();
				if(preparedStatement2 != null) preparedStatement2.close();
				if(preparedStatement3 != null) preparedStatement3.close();
				if(connection != null) connection.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	} // 대댓글 
	
	public void commentdelete(int fid) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = dataSource.getConnection();
			String query = "UPDATE forum SET fdeletedate = CURRENT_TIMESTAMP, ftitle = '삭제된 댓글입니다'";
			String query1 = " WHERE fid =" + fid;
			preparedStatement = connection.prepareStatement(query + query1);
			
			
			preparedStatement.executeUpdate();
				
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	} // 삭제
	
	public ArrayList<Kms_WriteList_Dto> forumsearch(int Ftype, String content){
		ArrayList<Kms_WriteList_Dto> dtos = new ArrayList<Kms_WriteList_Dto>();
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			connection = dataSource.getConnection();
			String query = "select * from forum";
			String Where2 = " where ftype =" + Ftype + " like ?";
			ps = connection.prepareStatement(query + Where2);
			ps.setString(1, "%" + content + "%");
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int f_id = rs.getInt(1);
				String f_cid = rs.getString(2);
				String f_aid = rs.getString(3);
				int f_pid = rs.getInt(4);
				int ftype = rs.getInt(5);
				int fref = rs.getInt(6);
				int freforder = rs.getInt(7);
				int fstep = rs.getInt(8);
				String ftitle = rs.getString(9);
				String fcontent = rs.getString(10);
				Timestamp finsertdate = rs.getTimestamp(11);
				Timestamp fdeletedate = rs.getTimestamp(12);
				int fmotherid = rs.getInt(13);
				int fanswernum = rs.getInt(14);

				
				
				Kms_WriteList_Dto dto = new Kms_WriteList_Dto(f_id, f_cid, f_aid, f_pid, ftype, fref, freforder, fstep,
						ftitle, fcontent, finsertdate, fdeletedate, fmotherid, fanswernum);
				dtos.add(dto);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
				if(connection != null) connection.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return dtos;
	} // list 출력
}
