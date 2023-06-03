package com.javalec.bbs.homecontroller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javalec.bbs.command.MCommand;
import com.javalec.bbs.command.aHomeCommand_kkg;


/**
 * Servlet implementation class MController
 */
@WebServlet("*.do")
public class MController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		actionDo(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		actionDo(request, response);
	}
	
	
	private void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession(true);
		
		String viewPage = null;
		MCommand command = null;
		
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		
		String com = uri.substring(conPath.length());
	
		
		switch (com) {
		
		/* ***** PART I 시작. 킹갓더제너럴 5STAR 강대규팀장님 part 입니다. 일동 기립. 경례. 쉬어.***** */
		
		
		
		
		case("/login.go"):
			session.invalidate();
			viewPage = "login.jsp";
			break;
			
		case("/home.do"):
			//command = new LMCommand();
			//command.execute(request, response);
			String username = (String)session.getAttribute("cid");
			System.out.println(username);
			viewPage = "home.jsp";
			break;
			
		case("/logout.do"):
			session.invalidate();
			viewPage = "home.jsp";
			break;
			
		case("/join.do"):
			viewPage = "join.jsp";
			break;
		
			
			/* PART I 종료. 킹갓더제너럴 강대규팀장님 part 입니다. 일동 기립. 경례. 쉬어.*/
		    //*************************************************************//
			
			
			/* PART II 시작. 큐티보이 김종진뀨 part 입니다. 뀨  일동 say 뀨~. 쉬어.*/
				
				
			/* PART II 종료. 큐티보이 김종진뀨 part 입니다. 뀨  일동 say 뀨~. 쉬어.*/
		    //*************************************************************//


			
			
			/* PART III 시작. 스윗남자 박지환 서윗한 part 입니다. 일용할 스윗함에 고마움을 :).*/
			
			
			/* PART III 종. 스윗남자 박지환 서윗한 part 입니다. 일동 .일용할 스윗함에 고마움을 :)*/
		    //*************************************************************//

			
			/* PART IV 시작. 세상Cool 남자 김민성군의 Part 입니다. 평균연령 낮춰줘서 고맙다 민성아.*/
			
			/* PART IV 종료. 세상Cool 남자 김민성군의 Part 입니다. 평균연령 낮춰줘서 고맙다 민성아.*/
		    //*************************************************************//

			
			
			
			
			/* PART V 시작. 나 강경구 파트다. 돈트 터치 디스 에어리어. 디스 이즈 사유지. ㅋㅋㅋㅋㅋㅋㅋ */
		
		case("/adminHome.do"):
			System.out.println("adminHome.do 로 들어옴");

			command = new aHomeCommand_kkg();
			command.execute(request, response);
			System.out.println("adminCommand_kkg 실행완료");
			
			viewPage = "adminHome.jsp";
			System.out.println("viewPage 입력되었음");
			
		break;
		
			/* PART V 종료. 나 강경구 파트다. 돈트 터치 디스 에어리어. 디스 이즈 사유지. ㅋㅋㅋㅋㅋㅋㅋ */
		    //*************************************************************//
	
		}//switch 구문 끝 
		

		
		
		
		
		
		
		
		
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}

}