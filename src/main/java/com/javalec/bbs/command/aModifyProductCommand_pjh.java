package com.javalec.bbs.command;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalec.bbs.dao.Admin_Product_Dao;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class aModifyProductCommand_pjh implements MCommand {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        // TODO Auto-generated method stub
    	 String directory = "/Volumes/Data/jihwan/Main_Team1/src/main/webapp/image/";
         int maxSize = 1024 * 1024 * 100;
         String encoding = "UTF-8";

         MultipartRequest multipartRequest = new MultipartRequest(request, directory, maxSize, encoding, new DefaultFileRenamePolicy());
         String fileName = null;
         
         File file = multipartRequest.getFile("pfilename");
         if (file != null) {
             String originalFileName = file.getName();
             String extension = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
             String pid = multipartRequest.getParameter("pid");
             String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
             String newFileName = pid + "_" + timeStamp + "." + extension;
             
             File newFile = new File(directory, newFileName);
             file.renameTo(newFile);
             
             fileName = newFileName;
         }
         
         String pname = multipartRequest.getParameter("pname");
         int pstock = 0;
         String pstockStr = multipartRequest.getParameter("pstock");
         if (pstockStr != null && !pstockStr.isEmpty()) {
             pstock = Integer.parseInt(pstockStr);
         }
         String pcontent = multipartRequest.getParameter("pcontent");
         int pprice = 0;
         String ppriceStr = multipartRequest.getParameter("pprice");
         if (ppriceStr != null) {
             pprice = Integer.parseInt(ppriceStr);
         }
         int pid = 0;
         String pidStr = multipartRequest.getParameter("pid");
         if (pidStr != null) {
             pid = Integer.parseInt(pidStr);
         }

         Admin_Product_Dao dao = new Admin_Product_Dao();
         dao.modify(pid, pname, fileName, pcontent, pstock, pprice);
     }
 }