package com.javalec.bbs.command;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javalec.bbs.dao.MDao;

/**
 * Servlet implementation class LoginCommand
 */
@WebServlet("*.go")
public class LoginCommand extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginCommand() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		actionGo(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		actionGo(request, response);
	}
    
	private void actionGo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession(true);
		
	    String username = request.getParameter("username").trim();
	    String password = request.getParameter("password").trim();

	    // DAO를 사용하여 유저 정보 검증 등의 작업 수행
	    MDao dao = new MDao();
	    String isAuthenticated = dao.authenticate(username, password);
	    if (isAuthenticated != null) {
	    	if(isAuthenticated.equals("mdraw")) {
	    		response.setStatus(HttpServletResponse.SC_OK);
	    		response.getWriter().print("mdraw");	    		
	    	}else {
	    		// 로그인 성공
	    		response.setStatus(HttpServletResponse.SC_OK);
	    		response.getWriter().print(isAuthenticated);
	    		session.setAttribute("cid", username);
	    		session.setAttribute("name", isAuthenticated);	    		
	    	}
	      
	    } else {
	      // 로그인 실패
	      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	      response.getWriter().print("Login failed");
	    }
	    
	}	
	
	

}
