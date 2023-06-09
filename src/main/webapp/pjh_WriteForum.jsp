<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리뷰작성하기</title>
<link href="css/admin_kkg.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="aQnA_style.css">
</head>
<script type="text/javascript">
    function validateForumWriteForm() {
        var title = document.getElementsByName("ftitle")[0].value;
        var content = document.getElementsByName("fcontent")[0].value;

        if (title.trim() === "") {
            alert("제목을 입력하세요.");
            return false;
        }

        if (content.trim() === "") {
            alert("내용을 입력하세요.");
            return false;
        }

        return true;
    }
</script>
<body>
<div class="sidebar">
			<jsp:include page="admin_01_sidebar.jsp" />
	 	</div>
	 	<div class="wrapper">
<h1>리뷰 작성</h1>
<form action="Aforumwrite.do" method="post" onsubmit="return validateForumWriteForm()">
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
    <input type="submit" value="리뷰작성하기">
</form>
</div>
</body>
</html>