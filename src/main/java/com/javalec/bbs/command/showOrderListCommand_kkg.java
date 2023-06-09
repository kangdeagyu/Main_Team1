package com.javalec.bbs.command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalec.bbs.dao.Admin_Dao_kkg;
import com.javalec.bbs.dto.AdminOrdering_Dto_kkg;

public class showOrderListCommand_kkg implements MCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub


		
		String cid = request.getParameter("cid");
		
		
		ArrayList<AdminOrdering_Dto_kkg> dtos = new ArrayList<AdminOrdering_Dto_kkg>();  
		
		Admin_Dao_kkg dao = new Admin_Dao_kkg();
		
		
		
		
		
		
		
		
	} // end of execute

}//endGame
