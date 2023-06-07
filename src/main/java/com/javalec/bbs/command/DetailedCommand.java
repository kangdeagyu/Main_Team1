package com.javalec.bbs.command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javalec.bbs.dao.RDao;
import com.javalec.bbs.dto.RDto;

public class DetailedCommand implements MCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession(true);
		
		int pid = (int)session.getAttribute("pid");
				
		RDao dao = new RDao();
		ArrayList<RDto> dtos = dao.DetailedProduct();
		System.out.println(dtos);
		request.setAttribute("DetailedProduct", dtos);
	}

}
