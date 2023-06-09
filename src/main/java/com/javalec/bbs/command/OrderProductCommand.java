package com.javalec.bbs.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javalec.bbs.dao.MDao;

public class OrderProductCommand implements MCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		request.setCharacterEncoding("utf-8");
		
		String[] bid = (String[]) session.getAttribute("bid");
		String cid = (String)session.getAttribute("cid");
		String[] pid = request.getParameterValues("pid[]");
		String[] qty = request.getParameterValues("qty[]");
		String[] price = request.getParameterValues("price[]");
		String postnum = request.getParameter("cpostnum");
		String address1 = request.getParameter("caddress1");
		String address2 = request.getParameter("caddress2");
		
		MDao dao = new MDao();
		boolean result = dao.order(bid, cid, pid, qty, price, postnum, address1, address2);
		
		System.out.println(result);
		
		
	}

}
