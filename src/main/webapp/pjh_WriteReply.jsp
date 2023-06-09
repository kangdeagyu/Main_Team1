<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>답글 쓰기</title>
<link href="css/admin_kkg.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="aQnA_style.css">
</head>
<body>
<div class="sidebar">
			<jsp:include page="admin_01_sidebar.jsp" />
	 	</div>
	 	<div class="wrapper">
<h1>답글 작성</h1>
<form action="Areplywrite.do" method="post">
<input type="hidden" name="fid" value="3">
<input type="hidden" name="fref" value="3">
<input type="hidden" name="freforder" value="0">
<input type="hidden" name="fstep" value="0">
<input type="hidden" name="fmotherid" value="3">
<input type="hidden" name="fanswernum" value="0">
    <table>
        <tr>
            <td>제목</td>
            <td><input type="text" name="ftitle" ></td>
        </tr>
        <tr>
            <td>작성자</td>
            <td><input type="text" name="f_cid" value="admin" readonly="readonly"></td>
        </tr>
        <tr>
            <td>상품코드</td>
            <td><input type="text" name="f_pid" value="1" readonly="readonly"></td>
        </tr>
        <tr>
            <td colspan="2">내용</td>
        </tr>
    </table>
    <textarea name="fcontent" cols="35" rows="10"></textarea>
    <br/>
    <input type="submit" value="답글달기">
</form>
</div>
</body>
</html>