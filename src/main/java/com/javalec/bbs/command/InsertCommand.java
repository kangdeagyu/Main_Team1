package com.javalec.bbs.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalec.bbs.dao.RDao;

public class InsertCommand implements MCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String f_cid = request.getParameter("f_cid");
		int f_pid = Integer.parseInt(request.getParameter("f_pid"));
		String bqty = request.getParameter("bqty");
		
		RDao dao = new RDao();
		dao.InsertCart(f_pid, f_pid, bqty);
		
		
	}

}
