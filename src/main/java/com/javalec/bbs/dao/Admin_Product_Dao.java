package com.javalec.bbs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.javalec.bbs.dto.Admin_Product_Dto;

public class Admin_Product_Dao {

	
	// Field
	DataSource datasource;
	
	
	// Constructor
	public Admin_Product_Dao() {
		try {
			Context context = new InitialContext();
			datasource = (DataSource) context.lookup("java:comp/env/jdbc/littlep");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	// Method
	public ArrayList<Admin_Product_Dto> list() {
		ArrayList<Admin_Product_Dto> dtos = new ArrayList<Admin_Product_Dto>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = datasource.getConnection();
			String query = "select pfilename, pname, pprice, pid";
			String query1 = " from product";
			preparedStatement = connection.prepareStatement(query+query1);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				String pfilename=resultSet.getString(1);
			    String pname = resultSet.getString(2);
			    int pprice = resultSet.getInt(3);
			    int pid = resultSet.getInt(4);
			    Admin_Product_Dto dto = new Admin_Product_Dto(pfilename, pname, pprice, pid);
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
	
	
	public ArrayList<Admin_Product_Dto> search1(String list, String query){
		ArrayList<Admin_Product_Dto> dtos = new ArrayList<Admin_Product_Dto>();
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			connection = datasource.getConnection();
			String query1 = "select pfilename, pid, pprice, pname from product";
			String Where2 = " where " + list + " like ?";
			ps = connection.prepareStatement(query1 + Where2);
			ps.setString(1, "%" + query + "%");
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String pfilename = rs.getString(1);
				int pid = rs.getInt(2);
				int pprice = rs.getInt(3);
				String pname = rs.getString(4);

				
				
				Admin_Product_Dto dto = new Admin_Product_Dto(pfilename, pname, pprice, pid);
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
	
	
	public int saveProduct(String pname, String pprice, String pstock, String pcontent, String pcategory, String filename, String fileRealName, String filepath) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try{
			connection = datasource.getConnection();
			String query = "INSERT INTO product (pname, pprice, pstock, pfilename, pcategory, pcontent, pinsertdate) VALUES (?, ?, ?, ?, ?, ?, now())";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, pname);
			preparedStatement.setString(2, pprice);
			preparedStatement.setString(3, pstock);
			preparedStatement.setString(4, filename);
			preparedStatement.setString(5, pcategory);
			preparedStatement.setString(6, pcontent);
			return preparedStatement.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		 } finally {
		        // 리소스 해제 코드
		        if (preparedStatement != null) {
		            try {
		                preparedStatement.close();
		            } catch (Exception e) {
		                e.printStackTrace();
		            }
		        }
		        if (connection != null) {
		            try {
		                connection.close();
		            } catch (Exception e) {
		                e.printStackTrace();
		            }
		        }
		    }
		    return -1;
		}
	
	public void delete(String[] pid) {
	    Connection connection = null;
	    PreparedStatement deleteInboundStatement = null;
	    PreparedStatement preparedStatement = null;
	    try {
	        connection = datasource.getConnection();
	        
	        // Delete from inbound table first
	        String deleteInboundQuery = "delete from inbound where i_pid = ?";
	        deleteInboundStatement = connection.prepareStatement(deleteInboundQuery);
	        for (int i = 0; i < pid.length; i++) {
	            deleteInboundStatement.setInt(1, Integer.parseInt(pid[i]));
	            deleteInboundStatement.executeUpdate();
	        }
	        
	        // Delete from product table
	        String deleteProductQuery = "delete from product where pid = ?";
	        preparedStatement = connection.prepareStatement(deleteProductQuery);
	        for (int i = 0; i < pid.length; i++) {
	            preparedStatement.setInt(1, Integer.parseInt(pid[i]));
	            preparedStatement.executeUpdate();
	        }
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
	
	public void modify(int pid, String pname, String pfilename, String pcontent, int pstock, int pprice) {
		
	}
	
	
	public int getTotalProductCount() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int totalCount = 0;

		try {
			connection = datasource.getConnection();
			String query = "SELECT COUNT(*) FROM product";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.executeUpdate();

			if (resultSet.next()) {
				totalCount = resultSet.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} try {
			if(resultSet !=null) resultSet.close();
			if(preparedStatement !=null) preparedStatement.close();
			if(connection !=null) connection.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return totalCount;
	}
	
	
	
	
	
	
	
	
}
