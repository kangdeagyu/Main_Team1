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
	        String query = "SELECT p.pfilename, p.pname, p.pprice, p.pid, pstock, pcontent, c.c_name ";
	        String query1 = "FROM product p JOIN category c ON p.pcategory = c.c_num WHERE p.pdeletedate IS NULL";
	        preparedStatement = connection.prepareStatement(query + query1);
	        resultSet = preparedStatement.executeQuery();
	        while (resultSet.next()) {
	            String pfilename = resultSet.getString(1);
	            String pname = resultSet.getString(2);
	            int pprice = resultSet.getInt(3);
	            int pid = resultSet.getInt(4);
	            int pstock = resultSet.getInt(5);
	            String pcontent = resultSet.getString(6);
	            String c_name = resultSet.getString(7);
	            Admin_Product_Dto dto = new Admin_Product_Dto(pfilename, pname, pprice, pid, pstock, pcontent, c_name);
	            dtos.add(dto);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (resultSet != null) resultSet.close();
	            if (preparedStatement != null) preparedStatement.close();
	            if (connection != null) connection.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    return dtos;
	}
	
	
	public ArrayList<Admin_Product_Dto> search1(String list, String query){
		ArrayList<Admin_Product_Dto> dtos = new ArrayList<Admin_Product_Dto>();
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			connection = datasource.getConnection();
			String query1 = "select pfilename, pid, pprice, pname, pcategory from product";
			String Where2 = " where " + list + " like ?";
			ps = connection.prepareStatement(query1 + Where2);
			ps.setString(1, "%" + query + "%");
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String pfilename = rs.getString(1);
				int pid = rs.getInt(2);
				int pprice = rs.getInt(3);
				String pname = rs.getString(4);
				String pcategory = Integer.toString(rs.getInt(5));

				
				
				Admin_Product_Dto dto = new Admin_Product_Dto(pfilename, pname, pprice, pid, pcategory);
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

	    try {
	        connection = datasource.getConnection();
	        String query = "INSERT INTO product (pname, pprice, pstock, pfilename, pcategory, pcontent, pinsertdate) VALUES (?, ?, ?, ?, ?, ?, now())";
	        String query2 = "INSERT INTO make (product_pid, admin_aid, mdate) VALUES (?, 'admin', now())";

	        preparedStatement = connection.prepareStatement(query);
	        preparedStatement.setString(1, pname);
	        preparedStatement.setString(2, pprice);
	        preparedStatement.setString(3, pstock);
	        preparedStatement.setString(4, filename);
	        preparedStatement.setString(5, pcategory);
	        preparedStatement.setString(6, pcontent);
	        preparedStatement.executeUpdate();

	        int pid = getProductId(pname); // 상품명으로 pid 조회

	        // make 테이블에 데이터 추가
	        preparedStatement = connection.prepareStatement(query2);
	        preparedStatement.setInt(1, pid); // 조회한 pid 사용
//	        preparedStatement.setInt(2, admin_aid); // 관리자 ID 사용
	        return preparedStatement.executeUpdate();
	    } catch (Exception e) {
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
	
	 private int getProductId(String pname) {
	        int pid = 0;
	        Connection connection = null;
	        PreparedStatement preparedStatement = null;
	        ResultSet resultSet = null;

	        try {
	            connection = datasource.getConnection();
	            String query = "SELECT pid FROM product WHERE pname = ?";
	            preparedStatement = connection.prepareStatement(query);
	            preparedStatement.setString(1, pname);
	            resultSet = preparedStatement.executeQuery();

	            if (resultSet.next()) {
	                pid = resultSet.getInt("pid");
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                if (resultSet != null) resultSet.close();
	                if (preparedStatement != null) preparedStatement.close();
	                if (connection != null) connection.close();
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }

	        return pid;
	    }
	
	
	
	
	public void delete(String[] pid) {
	    if (pid == null) {
	        // pid 배열이 null인 경우 처리
	        System.out.println("No products selected for deletion.");
	        return;
	    }
	    Connection connection = null;
	    PreparedStatement deleteInboundStatement = null;
	    PreparedStatement preparedStatement = null;
	    try {
	        connection = datasource.getConnection();
	        // Delete from product table
	        String deleteProductQuery = "delete from product where pid = ?";
	        String deletedateInsert= "update product set pdeletedate= now() where pid = ?";
	        preparedStatement = connection.prepareStatement(deletedateInsert);
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
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int existingPstock = getProductStock(pid);
		try {
			connection = datasource.getConnection();
			String query = "update product set pname=?, pfilename=?, pcontent=?, pstock=?, pprice=? where pid= ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, pname);
			preparedStatement.setString(2, pfilename);
			preparedStatement.setString(3, pcontent);
			preparedStatement.setInt(4, pstock);
			preparedStatement.setInt(5, pprice);
			preparedStatement.setInt(6, pid);
			preparedStatement.executeUpdate();
			 if (pstock != existingPstock) {
	                // inbound 정보 저장
	                int modifiedQty = pstock - existingPstock; // 변경된 양 계산

	                // inbound 정보를 저장하기 위한 SQL 쿼리
	                String inboundQuery = "INSERT INTO inbound (i_aid, i_pid, iqty, idate) VALUES ('admin', ?, ?, now())";
	                PreparedStatement inboundStatement = connection.prepareStatement(inboundQuery);
	                inboundStatement.setInt(1, pid);
	                inboundStatement.setInt(2, modifiedQty);
	                inboundStatement.executeUpdate();
	                inboundStatement.close();
	            }
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
	
	 private int getProductStock(int pid) {
	        int stock = 0;
	        Connection connection = null;
	        PreparedStatement preparedStatement = null;
	        ResultSet resultSet = null;

	        try {
	            connection = datasource.getConnection();
	            String query = "SELECT pstock FROM product WHERE pid = ?";
	            preparedStatement = connection.prepareStatement(query);
	            preparedStatement.setInt(1, pid);
	            resultSet = preparedStatement.executeQuery();

	            if (resultSet.next()) {
	                stock = resultSet.getInt("pstock");
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                if (resultSet != null) resultSet.close();
	                if (preparedStatement != null) preparedStatement.close();
	                if (connection != null) connection.close();
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }

	        return stock;
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
	
	
	public ArrayList<Admin_Product_Dto> informationlist() {
		ArrayList<Admin_Product_Dto> dtos = new ArrayList<Admin_Product_Dto>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = datasource.getConnection();
			String query = "SELECT p.pfilename, p.pname, p.pprice, p.pid, c.c_name ";
			String query1 = " FROM product p JOIN category c ON p.pcategory = c.c_num";
			preparedStatement = connection.prepareStatement(query+query1);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				String pfilename=resultSet.getString(1);
			    String pname = resultSet.getString(2);
			    int pprice = resultSet.getInt(3);
			    int pid = resultSet.getInt(4);
			    String c_name=resultSet.getString(5);
			    Admin_Product_Dto dto = new Admin_Product_Dto(pfilename, pname, pprice, pid, c_name);
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
	
	
	public Admin_Product_Dto getProductInfo(int f_pid) {
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    Admin_Product_Dto productInfo = null;
	    
	    try {
	        connection = datasource.getConnection();
	        String query = "SELECT pid, pname, pprice, pcontent FROM product WHERE pid = ?";
	        preparedStatement = connection.prepareStatement(query);
	        preparedStatement.setInt(1, f_pid);
	        resultSet = preparedStatement.executeQuery();
	        
	        if (resultSet.next()) {
	            int pid = resultSet.getInt("pid");
	            String pname = resultSet.getString("pname");
	            int pprice = resultSet.getInt("pprice");
	            String pcontent = resultSet.getString("pcontent");
	            
	            productInfo = new Admin_Product_Dto(pid, pname, pprice, pcontent);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        // 리소스 해제
	    }
	    
	    return productInfo;
	}
	
	
	
	
	
	
	
}
