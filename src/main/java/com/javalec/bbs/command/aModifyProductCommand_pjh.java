package com.javalec.bbs.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalec.bbs.dao.Admin_Product_Dao;


public class aModifyProductCommand_pjh implements MCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String pname=request.getParameter("pname");
		int pstock=Integer.parseInt(request.getParameter("pstock"));
		String pfilename=request.getParameter("pfilename");
		String pcontent=request.getParameter("pcontent");
		int pprice=Integer.parseInt(request.getParameter("pprice"));
		int pid=Integer.parseInt(request.getParameter("pid"));
		
		
		Admin_Product_Dao dao = new Admin_Product_Dao();
        dao.modify(pid, pname, pfilename, pcontent, pstock, pprice);
	}

}
