<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html;" charset="UTF-8">
    <title>상품 추가하기</title>
    <link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
    <script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
    <script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script>

    <link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.css" rel="stylesheet">
    <script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.js"></script> 
</head>
<body>
<h3>상품 추가하기</h3>
<form action="AProductInsert.do" method="post" enctype="multipart/form-data">
    <table>
        <tr>
            <th>상품 설정</th>
            <th>상세 설명</th>
        </tr>
        <tr>
            <td>이름</td>
        </tr>
        <tr>
            <td><input type="text" name="pname"></td>
        </tr>
        <tr>
            <td>카테고리</td>
        </tr>
        <tr>
            <td><input type="text" name="pcategory"></td>
        </tr>
        <tr>
        <tr>
            <td>가격</td>
        </tr>
        <tr>
            <td><input type="text" name="pprice"></td>
        </tr>
        <tr>
            <td>대표 이미지</td>
        </tr>
        <tr>
            <td><input type="file" name="file"></td>
        </tr>
        <tr>
            <td>수량</td>
        </tr>
        <tr>
            <td><input type="text" name="pstock"></td>
        </tr>
    </table>
   <textarea id="content" name="pcontent"></textarea>
    <script type="text/javascript">
$(document).ready(function() {
    $('#content').summernote({
        width: 1200,
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
</script>
            <input type="submit" value="업로드">
    
    
    
</form>
</body>
</html>