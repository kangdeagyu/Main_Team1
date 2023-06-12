package com.javalec.bbs.command;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalec.bbs.dao.Admin_Product_Dao;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class aProductAddCommand_pjh implements MCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
//		String directory = "/Volumes/Data/jihwan/Main_Team1/src/main/webapp/image/";
		String directory = request.getServletContext().getRealPath("/image/");
		System.out.println(directory);
        int maxSize = 1024 * 1024 * 100;
        String encoding = "UTF-8";
        request.setCharacterEncoding("utf-8");
        MultipartRequest multipartRequest = new MultipartRequest(request, directory, maxSize, encoding, new DefaultFileRenamePolicy());
        
        String fileName = multipartRequest.getOriginalFileName("file");
        String fileRealName = multipartRequest.getFilesystemName("file");
        String filepath1="../webapp/imag/";
        String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
        String pname = multipartRequest.getParameter("pname");
        String pcategory = multipartRequest.getParameter("pcategory");
        String pstock = multipartRequest.getParameter("pstock");
        String pprice = multipartRequest.getParameter("pprice");
        String pcontent = multipartRequest.getParameter("pcontent");
        pcontent = pcontent.replaceAll("<p>", "").replaceAll("</p>", "");
        System.out.println(pname);
        System.out.println(pstock);
        System.out.println(pprice);
        System.out.println(pcontent);

        if (!fileName.endsWith(".png") && !fileName.endsWith(".jpg") && !fileName.endsWith(".jpeg")) {
            File file = new File(directory + fileRealName);
            file.delete();
            request.setAttribute("uploadError", "업로드 할 수 없는 확장자입니다.");
        } else {
        	 LocalDateTime now = LocalDateTime.now();
             DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
             String timeStamp = now.format(formatter);

             String newFileName = timeStamp + "." + extension;

             String filePath = directory + newFileName; // 파일 경로 생성
             int result = new Admin_Product_Dao().saveProduct(pname, pprice, pstock, pcontent, pcategory, newFileName, fileRealName, filePath);
             if (result == -1) {
                 System.out.println("업로드에 실패했습니다.");
             } else {
                 request.setAttribute("fileName", fileName);
                 request.setAttribute("fileRealName", fileRealName);
                 request.setAttribute("fileDirectory", filePath);
                 request.setAttribute("filepath1", filepath1);
                 
                 String updatedFileName = new Admin_Product_Dao().getUpdatedFileName();

                 // 파일 이름 변경 실행
                 renameImageFile(directory, fileRealName, updatedFileName);
             }
         }
     }

     public void renameImageFile(String directory, String oldFileName, String newFileName) {
         File oldFile = new File(directory, oldFileName);
         File newFile = new File(directory, newFileName);
         boolean success = oldFile.renameTo(newFile);
         if (success) {
             System.out.println("이미지 파일 이름 변경 완료");
         } else {
             System.out.println("이미지 파일 이름 변경 실패");
         }
     }
}