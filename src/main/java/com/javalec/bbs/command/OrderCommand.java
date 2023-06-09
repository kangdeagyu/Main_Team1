package com.javalec.bbs.command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalec.bbs.dao.RDao;
import com.javalec.bbs.dto.RDto;

public class OrderCommand implements MCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		int pid = Integer.parseInt(request.getParameter("pid"));
		
		RDao dao = new RDao();
		ArrayList<RDto> dtos = dao.productordre(pid);
		System.out.println(dtos);
		request.setAttribute("productordre", dtos);
	}

}
