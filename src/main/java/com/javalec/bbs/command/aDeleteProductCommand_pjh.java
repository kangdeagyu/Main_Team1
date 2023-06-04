package com.javalec.bbs.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalec.bbs.dao.Admin_Product_Dao;

public class aDeleteProductCommand_pjh implements MCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
			
			Admin_Product_Dao dao = new Admin_Product_Dao();
			String[] selectedItems = request.getParameterValues("selectedItems");
			dao.delete(selectedItems);
		}

}
