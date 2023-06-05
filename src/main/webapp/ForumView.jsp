<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리뷰 상세 보기</title>
</head>
<body>
 <table>
        <tr>
            <td>제목</td>
            <td>${forumView.ftitle }</td>
        </tr>
        <tr>
            <td>작성자</td>
            <td>${forumView.f_cid} </td>
        </tr>
        <tr>
            <td>상품코드</td>
            <td>${forumView.f_pid }</td>
        </tr>
        <tr>
            <td colspan="2">내용</td>
            <td>${forumView.fcontent }</td>
        </tr>
    </table>
    <br/>
    <table>
        <tr>
            <th>작성자</th>
            <th>제목</th>
            <th>작성일</th>
        </tr>
        <c:forEach items="${CList}" var="cdto">
            <tr>
                <td>${cdto.fid}</a></td>
                <td>${cdto.ftitle}</td>
                <td>${cdto.finsertdate}</td>
            </tr>
        </c:forEach>
</table>
    
</body>
</html>