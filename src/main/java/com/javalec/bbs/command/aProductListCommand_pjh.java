package com.javalec.bbs.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalec.bbs.dao.Admin_Product_Dao;
import com.javalec.bbs.dto.Admin_Product_Dto;

public class aProductListCommand_pjh implements MCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		int total = 0;
		
		
		Admin_Product_Dao dao = new Admin_Product_Dao();
		ArrayList<Admin_Product_Dto>dtos = dao.list();
		request.setAttribute("list", dtos);
		
		
		for (Admin_Product_Dto dto : dtos) {
			int price = dto.getPprice();
			total +=price;
		}		
		request.setAttribute("total", total);
	}

	

}
