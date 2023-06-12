<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="css/admin_kkg.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="aQnA_style.css">
<title>공지사항 내용</title>
</head>
<body>
<div class="sidebar">
			<jsp:include page="admin_01_sidebar.jsp" />
	 	</div>
	 	<div class="wrapper">
<h3>${noticeview.ntitle}</h3>
${noticeview.n_aid}<br/>
${noticeview.ninsertdate}<br/>
${noticeview.ncontent}<br/>
<form action="AnoticeModifyPage.do" method="post">
<input type="hidden" name="ftype" value="${ftype}">
<input type="hidden" name="nid" value="${nid}">
<input type="hidden" name="n_aid" value="${noticeview.n_aid}">
<input type="hidden" name="ntitle" value="${noticeview.ntitle}">
<input type="hidden" name="ninsertdate" value="${noticeview.ninsertdate}">
<input type="hidden" name="ncontent" value="${noticeview.ncontent}">
<input type="submit" value="수정">
</form>
<form action="AdeleteNotice.do?nid=${nid}&ftype=${ftype}" method="post">
<input type="submit" value="삭제">
</form>
<form action="Awritelist.do?ftype=${ftype}" method="post">
<input type="submit" value="목록">
</form>
</div>
</body>
</html>