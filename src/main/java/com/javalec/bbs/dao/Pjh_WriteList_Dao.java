package com.javalec.bbs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.javalec.bbs.dto.Admin_Product_Dto;
import com.javalec.bbs.dto.Admin_WriteList_Dto;

public class Pjh_WriteList_Dao {

	
	
	// Field
	DataSource dataSource;
	
	
	
	// Constructor
	public Pjh_WriteList_Dao() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/littlep");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	//Method
	
	public void commentAction(int f_pid, String ftitle, int fid) {
		Connection connection = null;
		PreparedStatement preparedStatement1 = null;
		
		try {
			connection = dataSource.getConnection();
			String query2 = "insert into forum (f_cid, f_aid, f_pid, ftype, fref, freforder, fstep, ftitle, fcontent, finsertdate, fmotherid, fanswernum)";
			String query3 = " select 'dummy','admin', ?, 3 , max(fref) + 1, 0, 0, ?, ?, now(),?,0 from forum";
			preparedStatement1 = connection.prepareStatement(query2 + query3);
			preparedStatement1.setInt(1, f_pid);
			preparedStatement1.setString(2, ftitle);
			preparedStatement1.setString(3, ftitle);
			preparedStatement1.setInt(4, fid);
			
			
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
			String query = "UPDATE forum SET freforder = freforder + 1 WHERE freforder >= ? + ? + 1 AND fref = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, freforder);
			preparedStatement.setInt(2, fanswernum);
			preparedStatement.setInt(3, fref);
			preparedStatement.executeUpdate();
			
			String query2 = "INSERT INTO forum (f_cid, f_aid, f_pid, ftype, fref, freforder, fstep, ftitle, fcontent, finsertdate, fmotherid, fanswernum)";
			String query3 = " VALUES ('dummy','admin', ?, 3, ?, ? + 1, ?, ? ,? ,NOW(), ?, 0)";
			preparedStatement1 = connection.prepareStatement(query2 + query3);
			preparedStatement1.setInt(1, f_pid);
			preparedStatement1.setInt(2, fref);
			preparedStatement1.setInt(3, a);
			preparedStatement1.setInt(4, fstep + 1); // fstep 값을 1 증가시킴
			preparedStatement1.setString(5, ftitle);
			preparedStatement1.setString(6, fcontent);
			preparedStatement1.setInt(7, fid);
			preparedStatement1.executeUpdate();
				
			String query4 = "UPDATE forum SET fanswernum = fanswernum + 1 WHERE fid = ?";
			preparedStatement2 = connection.prepareStatement(query4);
			preparedStatement2.setInt(1, fid);
			preparedStatement2.executeUpdate();
			
			String query5 = "UPDATE forum SET fanswernum = fanswernum + 1 WHERE fref = ? AND freforder < ? AND fanswernum >= 1";
			preparedStatement3 = connection.prepareStatement(query5);
			preparedStatement3.setInt(1, fref);
			preparedStatement3.setInt(2, freforder);
			preparedStatement3.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null) preparedStatement.close();
				if (preparedStatement1 != null) preparedStatement1.close();
				if (preparedStatement2 != null) preparedStatement2.close();
				if (preparedStatement3 != null) preparedStatement3.close();
				if (connection != null) connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
}
	
	
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
			String query3 = " values ('dummy','admin', ?, 3 ,?, ? + 1, ? + 1,? ,? ,now(), ?, 0)";
			preparedStatement1 = connection.prepareStatement(query2 + query3);
//			preparedStatement1.setString(1, f_cid);
			preparedStatement1.setInt(1, f_pid);
			preparedStatement1.setInt(2, fref);
			preparedStatement1.setInt(3, a);
			preparedStatement1.setInt(4, fstep);
			preparedStatement1.setString(5, ftitle);
			preparedStatement1.setString(6, ftitle);
			preparedStatement1.setInt(7, fmotherid); // 대댓글 입력하기
			
			
			
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
	
	public Admin_WriteList_Dto noticeView(int nid) {
		Admin_WriteList_Dto dto= null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		 ResultSet resultSet = null;
		try {
			connection = dataSource.getConnection();
			String query = "SELECT n_aid, ntitle, ncontent, ninsertdate FROM notice WHERE nid = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, nid);
			resultSet = preparedStatement.executeQuery();
	        
	        if (resultSet.next()) {
	            String n_aid = resultSet.getString("n_aid");
	            String ntitle = resultSet.getString("ntitle");
	            String ncontent = resultSet.getString("ncontent");
	            Timestamp ninsertdate = resultSet.getTimestamp("ninsertdate");
	            
	            dto = new Admin_WriteList_Dto(n_aid, ntitle, ncontent, ninsertdate);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	    	try {
				if(resultSet != null) resultSet.close();
				if(preparedStatement != null) preparedStatement.close();
				if(connection != null) connection.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
	    }
		return dto;
	}
	
	public void modify(String ntitle, String ncontent, int nid) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = dataSource.getConnection();
			String query = "update notice set ntitle=?, ncontent=?, ninsertdate=now() where nid= ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, ntitle);
			preparedStatement.setString(2, ncontent);
			preparedStatement.setInt(3, nid);
			preparedStatement.executeUpdate();
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                if (preparedStatement != null)
	                    preparedStatement.close();
	                if (connection != null)
	                    connection.close();
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	    }
	
	public void delete(String nid) {
	    if (nid == null) {
	        // pid 배열이 null인 경우 처리
	        System.out.println("No products selected for deletion.");
	        return;
	    }
	    Connection connection = null;
	    PreparedStatement deleteInboundStatement = null;
	    PreparedStatement preparedStatement = null;
	    try {
	        connection = dataSource.getConnection();
	        // Delete from product table
	        String deletedateInsert= "update notice set ndeletedate= now() where nid = ?";
	        preparedStatement = connection.prepareStatement(deletedateInsert);
	            preparedStatement.setInt(1, Integer.parseInt(nid));
	            preparedStatement.executeUpdate();
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (deleteInboundStatement != null)
	                deleteInboundStatement.close();
	            if (preparedStatement != null)
	                preparedStatement.close();
	            if (connection != null)
	                connection.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	}
	
	public void checkcommentAction(int f_pid, String ftitle, int fid) {
		Connection connection = null;
		PreparedStatement preparedStatement1 = null;
		
		try {
			connection = dataSource.getConnection();
			String query2 = "insert into forum (f_cid, f_aid, f_pid, ftype, fref, freforder, fstep, ftitle, fcontent, finsertdate, fmotherid, fanswernum)";
			String query3 = " select 'dummy','admin', ?, 3 , max(fref) + 1, 0, 0, ?, ?, now(),?,0 from forum";
			preparedStatement1 = connection.prepareStatement(query2 + query3);
			preparedStatement1.setInt(1, f_pid);
			preparedStatement1.setString(2, ftitle);
			preparedStatement1.setString(3, ftitle);
			preparedStatement1.setInt(4, fid);
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
	
	
	
	
	
}
