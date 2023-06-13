<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="css/admin_kkg.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="aQnA_style.css">
<title>공지사항 내용</title>
<!-- 여기서부터 복사하시면 됩니다~~~~~~~~~!!!! -->

<jsp:include page="admin_01_header.jsp" />


</head>

<body>

	<div class="container-fluid">
		<div class="row">
			<div class="col-md-2">
				<jsp:include page="admin_01_sidebar.jsp" />
			</div>

			<div class="col-md-10" style="margin-left: 15%;">
				<main class="ms-sm-auto px-md-4">

					<!-- 요기서부터 본문 내용 입력하면 됩니다아~~!!!!!  하단에  </div> 및 </main> 자리 맞춰서 넣는거만 기억하면 됩니다.-->

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
</main>
</div>
</div>
</body>
</html>