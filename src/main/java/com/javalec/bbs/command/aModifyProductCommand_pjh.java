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

public class aModifyProductCommand_pjh implements MCommand {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        // TODO Auto-generated method stub
    	String directory = "/Volumes/Data/jihwan/Main_Team1/src/main/webapp/image/";
        int maxSize = 1024 * 1024 * 100;
        String encoding = "UTF-8";
        request.setCharacterEncoding("utf-8");
        // MultipartRequest 객체 생성
        MultipartRequest multipartRequest = new MultipartRequest(request, directory, maxSize, encoding,
                new DefaultFileRenamePolicy());

        // 새로운 파일 이름 변수 초기화
        String fileName = null;

        // 업로드된 파일 가져오기
        File file = multipartRequest.getFile("pfilename");

        String newFileName;
        if (file != null) {
            // 파일이 선택된 경우에만 처리
            String originalFileName = file.getName();
            String extension = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
            String pid = multipartRequest.getParameter("pid");

            // 새로운 파일 이름 생성
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
            String timeStamp = now.format(formatter);

            newFileName = pid + "_" + timeStamp + "." + extension;

            File newFile = new File(directory, newFileName);
            file.renameTo(newFile);

            // 원본 이미지 파일 삭제
            file.delete();
        } else {
            newFileName = "";
        }

        fileName = newFileName;

        // 폼 데이터 가져오기
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

        // DAO 객체 생성 및 수정 메소드 호출
        Admin_Product_Dao dao = new Admin_Product_Dao();
        dao.modify(pid, pname, fileName, pcontent, pstock, pprice);
    }
}