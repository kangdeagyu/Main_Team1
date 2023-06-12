package com.javalec.bbs.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.javalec.bbs.dto.AdminExtra_Dto_kkg;
import com.javalec.bbs.dto.AdminOrdering_Dto_kkg;

public class Admin_Dao_kkg {

	DataSource dataSource;

	
	
	public Admin_Dao_kkg() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/littlep");
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	} //연결 끝
	
	
	
	
	
	
	
	
	
	

}//end Game
