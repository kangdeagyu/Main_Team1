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
</head>
<body>
<div class="sidebar">
			<jsp:include page="admin_01_sidebar.jsp" />
	 	</div>
	 	<div class="wrapper">
			<form action=AnoticeModify.do method="post">
			<table border="1">
                    <tr>
                        <td>제목</td>
         				<td>
         				<input type="hidden" name="nid" value="${nid}">
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
            <form action="ANoticeView.do?nid=${nid}" method="post">
            <input type="submit" value="취소">
            </form>
            </div>
</body>
</html>