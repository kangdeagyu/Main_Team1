<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html;" charset="UTF-8">
    <title>공지사항 작성</title>
    <link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
    <script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
    <script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script>

    <link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.css" rel="stylesheet">
    <script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.js"></script> 
</head>
<body>
<h3>공지사항</h3>
<form action="AQnA.do" method="post" enctype="multipart/form-data">
    <table>
    	<tr>
    	<td>카테고리</td>
    		<td><select name="list" class="form-select">
        <option value="pname" selected="selected">서비스</option>
        <option value="pcategory">공지사항</option>
    			</select>
    		</td>
    	</tr>
    
        <tr>
            <td>이름</td>
            <td>${dto.id}</td>
        </tr>
        <tr>
            <td>공개여부</td>
            <td><input type="radio" name="openornot" value="공개" checked="checked">공개
            <input type="radio" name="openornot" value="비공개">비공개
            </td>
        </tr>
       <!--  <tr>
            <td>전화번호</td>
            <td><input type="text" name="phone"></td>
        </tr>
        <tr>
            <td>E-mail</td>
            <td><input type="text" name="E-mail"></td>
        </tr> -->
        <tr>
            <td>제목</td>
            <td><input type="text" name="title"></td>
        </tr>
        <tr>
            <td>내용</td>
   <td><textarea id="content" name="content"></textarea>
    <script type="text/javascript">
$(document).ready(function() {
    $('#content').summernote({
        width: 800,
    	height: 500,
        minHeight : null,
        maxHeight : null,
        tabsize : 2,
        lang : "ko-KR",
        toolbar : [
        	['fontsize',['fontsize']],
        	['style', ['fontname']],
        	['color', ['forecolor', 'backcolor']],
        	['para', ['ul','ol','paragraph']],
        	['height', ['height']],
        	['table',['table']],
        	['insert',['link','video']],
        	['view',['fullscreen','codeview','help']],
        ],
        fontNames : ['맑은 고딕', '궁서', '굴림체', '굴림', '돋움체', '바탕체'],
        fontSizes : ['8','9','10','11','12','14','16','18','20','22','24','28','30','36'],
   		
        callbacks: {
        	onImageUpload : function(files, editor, welEditable){
        		sendFile(files[0], this);
        	}
        }
    });
});
</script></td>
            
        </tr>
        <tr>
            <td>첨부파일</td>
            <td><input type="file" name="file"></td>
        </tr>
    </table>
            <input type="submit" name=action value="업로드">
            <input type="submit" name=action value="재작성">
    
		</form>
		<form action="Admin_QnA_List.do" method="post">
            <input type="submit" value="목록">
        </form>
</body>
</html>