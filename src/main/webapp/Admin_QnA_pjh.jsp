<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html;" charset="UTF-8">
    <title>공지사항 작성</title>
     
     <link href="css/admin_kkg.css" rel="stylesheet">

</head>
<body>

		<div class="sidebar">
			<jsp:include page="admin_01_sidebar.jsp" />
	 	</div>
	 	

	 	<div class="wrapper">
<h3>공지사항</h3>
<form action="ANoticeUpload.do" method="post" id="myForm">
    <table>    
        <tr>
            <td>이름: </td>
            <td>관리자</td>
        </tr>
       <!--  <tr>
            <td>공개여부</td>
            <td><input type="radio" name="openornot" value="공개" checked="checked">공개
            <input type="radio" name="openornot" value="비공개">비공개
            </td>
        </tr> -->
        <tr>
            <td>제목</td>
            <td><input type="text" name="title">
            	<input type="hidden" name="ftype" value="${ftype}">
            </td>
        </tr>
        <tr>
            <td>내용</td>
   <td><textarea name="content" style="width:500px;height:500px;"></textarea>
   </td>
            
        </tr>
        <!-- <tr>
            <td>첨부파일</td>
            <td><input type="file" name="file"></td>
        </tr> -->
    </table>
            <input type="submit" name=action value="업로드">
            <input type="button" value="재작성" onclick="resetForm()">
		</form>
		
		<form action="Awritelist.do?ftype=${ftype}" method="post">
            <input type="submit" value="목록">
        </form>
         </div>

	<script type="text/javascript">
		function resetForm() {
			document.getElementById("myForm").reset();
		}
	</script>


</body>
</html>