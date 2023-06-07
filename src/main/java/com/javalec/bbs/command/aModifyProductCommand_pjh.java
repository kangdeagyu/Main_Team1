package com.javalec.bbs.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalec.bbs.dao.Admin_Product_Dao;

public class aModifyProductCommand_pjh implements MCommand {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        // TODO Auto-generated method stub
    	  String pname = request.getParameter("pname");
    	    int pstock = 0;
    	    String pstockStr = request.getParameter("pstock");
    	    if (pstockStr != null && !pstockStr.isEmpty()) {
    	        pstock = Integer.parseInt(pstockStr);
    	    }
    	    String pfilename = request.getParameter("pfilename");
    	    String pcontent = request.getParameter("pcontent");
    	    int pprice = 0;
    	    String ppriceStr = request.getParameter("pprice");
    	    if (ppriceStr != null) {
    	        pprice = Integer.parseInt(ppriceStr);
    	    }
    	    int pid = 0;
    	    String pidStr = request.getParameter("pid");
    	    if (pidStr != null) {
    	        pid = Integer.parseInt(pidStr);
    	    }

    	    Admin_Product_Dao dao = new Admin_Product_Dao();
    	    dao.modify(pid, pname, pfilename, pcontent, pstock, pprice);
    	}
}