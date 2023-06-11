package com.javalec.bbs.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javalec.bbs.dao.RDao;

public class OrderCommand implements MCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		HttpSession session = request.getSession(true);
//		String pidParameter = request.getParameter("pid");
//		int ppid = 0;
//		String ccid = (String)session.getAttribute("cid");
//		String oid = request.getParameter("oid");
//		String oqty = request.getParameter("oqty");
//		String oprice = request.getParameter("oprice");
//		String opostnum = request.getParameter("opostnum");
//		String oaddress1 = request.getParameter("oaddress1");
//		String oaddress2 = request.getParameter("oaddress2");
//		String odate = request.getParameter("odate");
//		String view;
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession(true);
		
		int pid = Integer.parseInt(request.getParameter("pid"));
		String cid = (String)session.getAttribute("cid");
		int oqty = Integer.parseInt(request.getParameter("oqty"));
		int oprice = Integer.parseInt(request.getParameter("oprice"));
		String opostnum = request.getParameter("opostnum");
		String oaddress1 = request.getParameter("oaddress1");
		String oaddress2 = request.getParameter("oaddress2");
		
		RDao dao = new RDao();
		String view;
		
		boolean result = dao.PurchaseOrder(cid, pid, oqty, oprice, opostnum, oaddress1, oaddress2 );
		if(result == true) {
			view = "Orderview.do";
			request.setAttribute("view",  view);
		}else {
			view = "detailedpage.do";
			request.setAttribute("view",  view);
		}
		
	
//		RDao dao = new RDao();
//		boolean result = dao.Ordering(ccid, ppid, oid, oqty, oprice, opostnum, oaddress1, oaddress2, odate);
//		
//		if(result == true) {
//			view = "OrderView.do";
//		}else {
//			view = "detailedpage.jsp";
//		}
//		
//		try {
//		    ppid = (pidParameter != null) ? Integer.parseInt(pidParameter) : 0;
//		} catch (NumberFormatException e) {
//		    ppid = 0; 
//	}

	}
}
