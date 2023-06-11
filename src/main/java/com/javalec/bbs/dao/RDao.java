package com.javalec.bbs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.javalec.bbs.dto.MyreviewDto;
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
//공지사항 게시판
public ArrayList<RDto> Notice(){
	ArrayList<RDto> dtos = new ArrayList<RDto>();
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;

try {
	connection = dataSource.getConnection(); // sql 연결
	String query = "select nid, n_aid, ntitle, ncontent, ninsertdate from notice";
	preparedStatement = connection.prepareStatement(query);
	resultSet = preparedStatement.executeQuery();

	while(resultSet.next()) {
		int nid = resultSet.getInt("nid");
		String n_aid = resultSet.getString("n_aid");
		String ntitle = resultSet.getString("ntitle");
		String ncontent = resultSet.getString("ncontent");
		String ninsertdate = resultSet.getString("ninsertdate");
		
		RDto dto = new RDto(nid, n_aid, ntitle, ncontent, ninsertdate);
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
//데이터베이스 구매 목록입력
public boolean PurchaseOrder(String cid, int pid,  int oqty, int oprice, String opostnum, String oaddress1, String oaddress2) {
	Connection connection = null;
    PreparedStatement preparedStatement = null;
    boolean result = false;
    try {
        connection = dataSource.getConnection();
        String query = "INSERT INTO ordering (customer_cid, product_pid, oqty, oprice, opostnum, oaddress1, oaddress2, odelivery, odate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, now())";

        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, cid);
		preparedStatement.setInt(2, pid);
		preparedStatement.setInt(3, oqty);
		preparedStatement.setInt(4, oprice);
		preparedStatement.setString(5, opostnum);
		preparedStatement.setString(6, oaddress1);
		preparedStatement.setString(7, oaddress2);
		preparedStatement.setInt(8, 0);
		
		preparedStatement.executeUpdate();
		
		result = true;

	
	}catch (Exception e) {
		e.printStackTrace();
	} finally {
	    try {
	        if (preparedStatement != null) preparedStatement.close();
	        if (connection != null) connection.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	

    return result;
}
//구매하기 데이터 베이스에 저장하기 입력하기

//구매 상품 오더링에 담기
//public boolean Ordering(String ccid, int ppid, String oid, String oqty, String oprice, String opostnum, String oaddress1, String oaddress2, String odate){
//	Connection connection = null;
//	PreparedStatement preparedStatement = null;
//	boolean result = false;
//	
//	try {
//		connection = dataSource.getConnection(); // sql 연결
//		String query = "INSERT INTO oredering (oid, oqty, oprice, opostnum, oaddress1, oaddress2, odate) values (?, ?, ?, ?, ?, ?, ?, ?,?)";
//				       		
//		preparedStatement = connection.prepareStatement(query);
//		preparedStatement.setString(1, ccid);
//		preparedStatement.setInt(2, ppid);
//		preparedStatement.setString(3, oid);
//		preparedStatement.setString(4, oqty);
//		preparedStatement.setString(5, oprice);
//		preparedStatement.setString(6, opostnum);
//		preparedStatement.setString(7, oaddress1);
//		preparedStatement.setString(8, oaddress2);
//		preparedStatement.setString(9, odate);
//		
//		preparedStatement.executeUpdate();
//	
//		result = true;
//
//	
//	}catch (Exception e) {
//		e.printStackTrace();
//	}finally {
//		try {
//			if(preparedStatement != null) preparedStatement.close();
//			if(connection != null) connection.close();
//		}catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	return result;
//}

//마이 페이지 나의 리뷰 데이터 가져오기
public ArrayList<MyreviewDto> Myreview(String cid){
	ArrayList<MyreviewDto> dtos = new ArrayList<MyreviewDto>();
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	
	try {
		connection = dataSource.getConnection();
		String query = "select f.*, p.pfilename, p.pname, c.cname"
				+ " from forum f, product p, customer c";
		String query2 = " where p.pid = f.fid and c.cid = f.f_cid and ftype = 1 and f.f_cid = ? order by fref desc, freforder";
		
		preparedStatement = connection.prepareStatement(query + query2);
		preparedStatement.setString(1, cid);
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
			String pfilename = resultSet.getString(15);
			String pname = resultSet.getString(16);
			String cname = resultSet.getString(17);
				
			String ppfilename = "image/" + pfilename;
			MyreviewDto dto = new MyreviewDto(fid, f_cid, f_aid, f_pid, ftype, 
			fref, freforder, fstep, ftitle, fcontent, finsertdate, fdeletedate, fmotherid, fanswernum, ppfilename, pname, cname);
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
	
} // QnA Detail list

}




