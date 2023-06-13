<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
 <meta http-equiv="Content-Type" content="text/html;" charset="UTF-8">
<title>공지사항 수정</title>
<link href="css/admin_kkg.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="aQnA_style.css">
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

			<form action=AnoticeModify.do method="post">
			<table border="1">
                    <tr>
                        <td>제목</td>
         				<td>
         				<input type="hidden" name="nid" value="${nid}">
         				<input type="hidden" name="ftype" value="${ftype}">
         				<input type="text" name="ntitle" value="${ntitle}"></td>
         			</tr>
         			<tr>
                        <td>작성자</td>
                        <td><input type="text" name="n_aid" value="${n_aid}" disabled/></td>
                    </tr>
                    <tr>
                        <td>입력날짜</td>
                        <td>
                            <input type="text" name="ninsertdate" value="${ninsertdate}" disabled/>
                        </td>
                    </tr>
                    <tr>
                        <td>내용</td>
                        <td><textarea name="ncontent">${ncontent}</textarea></td>
                    </tr>
                </table>
                <input type="submit" value="저장">
            </form>
            <form action="ANoticeView.do?nid=${nid}&ftype=${ftype}" method="post">
            <input type="submit" value="취소">
            </form>
            </div>
            </main>
            </div>
            </div>
</body>
</html>