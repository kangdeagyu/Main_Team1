package com.javalec.bbs.command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalec.bbs.dao.Admin_Product_Dao;
import com.javalec.bbs.dto.Admin_Product_Dto;

public class aProductListCommand_pjh implements MCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		
		int total = 0;
		
		
		Admin_Product_Dao dao = new Admin_Product_Dao();
		ArrayList<Admin_Product_Dto>dtos = dao.list();
		
		String uploadPath = "image/";
		for (Admin_Product_Dto dto : dtos) {
			String fileName = dto.getPfilename();
			String imagePath = uploadPath + fileName;
			dto.setPfilename(imagePath);
			String pfilename = dto.getPfilename();
			int price = dto.getPprice();
			total += price;
		}		
		request.setAttribute("list", dtos);
		request.setAttribute("total", total);
	}
}