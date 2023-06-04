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
import com.javalec.bbs.dto.Admin_QnA_Dto;

public class Admin_QnA_Dao {
	
		// Field
		DataSource datasource;
		
		
		// Constructor
		public Admin_QnA_Dao() {
			try {
				Context context = new InitialContext();
				datasource = (DataSource) context.lookup("java:comp/env/jdbc/littlep");
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		// Method
		public ArrayList<Admin_QnA_Dto> list() {
			ArrayList<Admin_QnA_Dto> dtos = new ArrayList<Admin_QnA_Dto>();
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			try {
				connection = datasource.getConnection();
				String query = "SELECT f.fid, f.ftitle, f.f_aid, f.f_cid, f.finsertdate, t.t_name ";
				String query1 = " FROM forum f JOIN type t ON (f.ftype-1) = t.t_num where f.type=1";
				preparedStatement = connection.prepareStatement(query+query1);
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					int fid=resultSet.getInt(1);
				    String ftitle = resultSet.getString(2);
				    String f_aid = resultSet.getString(3);
				    String f_cid = resultSet.getString(4);
				    Timestamp finsertdate = resultSet.getTimestamp(5);
				    String t_name=resultSet.getString(6);
				    Admin_QnA_Dto dto = new Admin_QnA_Dto(fid, ftitle, f_aid, f_cid, finsertdate, t_name);
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
		
		
		
		public int saveQnA(String pname, String pprice, String pstock, String pcontent, String pcategory, String filename, String fileRealName, String filepath) {
			Connection connection = null;
			PreparedStatement preparedStatement = null;

			try{
				connection = datasource.getConnection();
				String query = "INSERT INTO forum (pname, pprice, pstock, pfilename, pcategory, pcontent, pinsertdate) VALUES (?, ?, ?, ?, ?, ?, now())";
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
		
		
		
		
}
