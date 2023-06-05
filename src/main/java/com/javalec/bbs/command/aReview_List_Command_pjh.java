package com.javalec.bbs.command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalec.bbs.dao.Admin_Review_Dao;
import com.javalec.bbs.dto.Admin_Review_Dto;

public class aReview_List_Command_pjh implements MCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
int total = 0;
		
		
		Admin_Review_Dao dao = new Admin_Review_Dao();
		ArrayList<Admin_Review_Dto>dtos = dao.list();
		
		String uploadPath = "image/";
		for (Admin_Review_Dto dto : dtos) {
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
