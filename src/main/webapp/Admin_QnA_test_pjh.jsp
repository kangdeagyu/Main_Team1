<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
		<head>
		<meta charset="EUC-KR">
		<title>Insert title here</title>
		</head>
	<body>
	
	<form action="QnAQuery.do" method="post">
     <select name="list" class="form-select">
    </select>
    <input type="text" name="query">
    <input type="submit" name="action" value="�˻�">

</form>
	
	
	<form action="write.do" method="post">
    <input type="submit" name="action" value="�۾���">
    </form>
    <table border=1>
        <tr>
            <th>��ȣ</th>
            <th>����</th>
            <th>�ۼ���</th>
            <th>�����</th>
        </tr>
        <c:forEach items="${list}" var="dto">
            <tr>
                <td>${dto.fid}</td>
                <td>${dto.ftitle}</td>
                <td>${dto.f_cid}</td>
                <td>${dto.finsertdate}</td>
            </tr>
        </c:forEach>
    </table>
	
	
	</body>
</html>