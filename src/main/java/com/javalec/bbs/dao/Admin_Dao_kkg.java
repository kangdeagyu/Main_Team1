package com.javalec.bbs.dao;

import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

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
			
	} 
	
	
	
	
	
	
	public ArrayList<AdminOrdering_Dto_kkg> orderList(){
		
		ArrayList<AdminOrdering_Dto_kkg> dtos = new ArrayList<AdminOrdering_Dto_kkg>();
		
		
		
		
		
		
		return dtos;
		
	} //orderList 끝 
	
	
	
	
	
	
	
	
	

}//end Game
