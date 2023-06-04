package com.javalec.bbs.command;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalec.bbs.dao.Admin_Product_Dao;
import com.javalec.bbs.dao.Admin_QnA_Dao;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class aQnAuploadCommand_pjh implements MCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		String directory = "C:\\Users\\xdrag\\OneDrive\\Documents\\ji hwan\\Main_Team1\\src\\main\\webapp\\image\\";
        int maxSize = 1024 * 1024 * 100;
        String encoding = "UTF-8";
		MultipartRequest multipartRequest = new MultipartRequest(request, directory, maxSize, encoding, new DefaultFileRenamePolicy());
		String f_aid= request.getParameter("aid");
		String list = request.getParameter("list");
		String[] openornot = request.getParameterValues("openornot");
		String phone= request.getParameter("phone");
		String Email= request.getParameter("E-mail");
		String title= request.getParameter("title");
		String content=request.getParameter("pcontent");
		String fileName = MultipartRequest.getOriginalFileName("file");
		content = content.replaceAll("<p>", "").replaceAll("</p>", "");
		
		 if (!fileName.endsWith(".png") && !fileName.endsWith(".jpg") && !fileName.endsWith(".jpeg")) {
	            File file = new File(directory + fileName);
	            file.delete();
	            request.setAttribute("uploadError", "업로드 할 수 없는 확장자입니다.");
	        } else {
	            String filePath = directory + fileName; // 파일 경로 생성
	            int result = new Admin_QnA_Dao().saveQnA(title, content, fileName);
	            if (result == -1) {
	                System.out.println("업로드에 실패했습니다.");
	            } else {
	                request.setAttribute("fileName", fileName);
	                request.setAttribute("fileDirectory", filePath);
	            }
	        }
		
		
		
	}

}