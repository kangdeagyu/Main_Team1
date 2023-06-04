package com.javalec.bbs.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalec.bbs.dao.Admin_Product_Dao;
import com.javalec.bbs.dto.Admin_Product_Dto;

public class aProductSearchCommand_pjh implements MCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String list = request.getParameter("list");
		String query = request.getParameter("query");
		Admin_Product_Dao dao = new Admin_Product_Dao();
		ArrayList<Admin_Product_Dto> dtos = dao.search1(list,query);
		request.setAttribute("list", dtos);
	}

}
