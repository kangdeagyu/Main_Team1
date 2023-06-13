package com.javalec.bbs.command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalec.bbs.dao.MDao;
import com.javalec.bbs.dto.HomeDto;

public class searchCommdand implements MCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String search = request.getParameter("search");

		MDao dao = new MDao();
		ArrayList<HomeDto> list = dao.searchView(search);
		
		request.setAttribute("searchlist", list);
	}

}
