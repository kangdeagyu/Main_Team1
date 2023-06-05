<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"   %>
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
        <tbody>
       <c:forEach items="${Clist}" var="cdto">
            <tr>
                <td>${cdto.f_cid}</td>
                <td>${cdto.ftitle}</td>
                <td>${cdto.finsertdate}</td>
                <td>${cdto.fmotherid}</td>
                <td><form action="BigCommentWrite.do" method="post">
                <input type="text" name="ftitle">
                <input type="hidden" name="f_cid" value="JHWoo1990" >
                <input type="hidden" name="fid" value="${cdto.fid}">
                <input type="hidden" name="f_pid" value="${cdto.f_pid}">
                <input type="hidden" name="fref" value="${cdto.fref}">
                <input type="hidden" name="fstep" value="${cdto.fstep}">
                <input type="hidden" name="freforder" value="${cdto.freforder}">
                <input type="hidden" name="fmotherid" value="${cdto.fmotherid}">
                <input type="hidden" name="fanswernum" value="${cdto.fanswernum}">
                <input type="submit" value="댓글">
                </form></td>
            </tr>
        </c:forEach>
        </tbody>
</table>

   <form action="commentwrite.do" method="post">
   <input type="hidden" name="fid" value=${forumView.fid }>
   <input type="hidden" name="f_cid" value="IULee1993">
   <input type="hidden" name="f_pid" value=${forumView.f_pid }>
            댓글달기 <input type="text" name="ftitle" >
      
    <input type="submit" value="리뷰작성하기">
</form>
   
</body>
</html>