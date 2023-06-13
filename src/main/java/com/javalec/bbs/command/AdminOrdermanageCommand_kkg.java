package com.javalec.bbs.command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalec.bbs.dao.Admin_Dao_kkg;
import com.javalec.bbs.dto.Admin_Order_Dto_kkg;

public class AdminOrdermanageCommand_kkg implements MCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		
		String cid ;
		
		Admin_Dao_kkg dao = new Admin_Dao_kkg();
		
		
//		try {
//			
//			cid = request.getParameter("customerId");
//		}catch (Exception e) {
//			
//			cid = "1";
//		}
		
		cid = "1";
		
		
		ArrayList<Admin_Order_Dto_kkg> dtos = dao.getOrderlist(cid);
		
		
		
		request.setAttribute("orderList", dtos);
		
		
	}

}
