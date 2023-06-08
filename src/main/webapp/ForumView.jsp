<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"   %>
<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리뷰 상세 보기</title>
</head>
<script type="text/javascript">
function confirmDelete(cid, form) {
	  var userCid = "JHWoo1990"; // 로그인 사용자의 f_cid 값
	  var commentCid = form.elements["f_cid"].value;

	  if (userCid !== commentCid) {
	    // 삭제 권한이 없는 경우 경고 메시지 출력
	    alert("댓글 삭제 권한이 없습니다.");
	    return false;
	  }

	  // 댓글 삭제 확인 메시지 표시
	  var confirmed = confirm("정말로 댓글을 삭제하시겠습니까?");
	  if (confirmed) {
	    // 사용자가 확인한 경우에만 댓글 삭제 로직 수행
	    // ...

	    // 삭제 성공 메시지 출력
	    alert("댓글이 성공적으로 삭제되었습니다.");

	    // 삭제된 댓글의 삭제와 댓글 버튼을 숨김
	 
	    return true;
	  } else {
	    return false;
	  }
	}
</script>
<script type="text/javascript">
function validateComment() {
    var comment = document.getElementsByName("ftitle")[0].value;
    if (comment.trim() === "") {
        alert("댓글을 입력하세요.");
        return false;
    }
    return true;
}

</script>
<script type="text/javascript">
    function validateCommentForm() {
        var comment = document.getElementsByName("ftitle")[0].value;
        if (comment.trim() === "") {
            alert("댓글을 입력하세요.");
            return false;
        }
        return true;
    }
</script>
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
                <td>
            <c:choose>
                <c:when test="${cdto.fstep eq 0}"> <!-- step이 0인 경우 -->
                    ${cdto.ftitle}
                </c:when>
                <c:otherwise> <!-- step이 0이 아닌 경우 (대댓글) -->
                    <span style="margin-left: ${cdto.fstep * 20}px">ㄴ${cdto.ftitle}</span>
                </c:otherwise>
            </c:choose>
        </td>
                <td>${cdto.finsertdate}</td>
                <td>${cdto.fmotherid}</td>
                <td><c:if test="${cdto.fdeletedate eq null}">
               <form action="BigCommentWrite.do" method="post" onsubmit="return validateCommentForm()">
                <input type="text" name="ftitle" placeholder="댓글을 입력하세요.">
                <input type="hidden" name="f_cid" value="JHWoo1990" >
                <input type="hidden" name="fid" value="${cdto.fid}">
                <input type="hidden" name="f_pid" value="${cdto.f_pid}">
                <input type="hidden" name="fref" value="${cdto.fref}">
                <input type="hidden" name="fstep" value="${cdto.fstep}">
                <input type="hidden" name="freforder" value="${cdto.freforder}">
                <input type="hidden" name="fmotherid" value="${cdto.fmotherid}">
                <input type="hidden" name="fanswernum" value="${cdto.fanswernum}">
                <input type="submit" id="replyButton_${cdto.f_cid}" value="댓글" >
                </form> </c:if></td>
               <td><c:if test="${cdto.fdeletedate eq null}">
  <form action="commentdelete.do" method="post" onsubmit="return confirmDelete('${cdto.f_cid}', this)">
    <input type="hidden" name="page" value="${forumView.fid}">
    <input type="hidden" name="f_cid" value="${cdto.f_cid}">
    <input type="hidden" name="fid" value="${cdto.fid}">
    <input type="submit" id="deleteButton_${cdto.f_cid}" value="삭제" >
  </form></c:if>
</td></tr>
        </c:forEach>
        </tbody>
</table>

   <form action="commentwrite.do" method="post" onsubmit="return validateComment()">
            댓글달기 <input type="text" name="ftitle" placeholder="댓글을 입력하세요.">
   <input type="hidden" name="fid" value=${forumView.fid }>
   <input type="hidden" name="f_cid" value="IULee1993">
   <input type="hidden" name="f_pid" value=${forumView.f_pid }>
    <input type="submit" value="댓글달기">
</form>
<form action="Kms_WriteReply.jsp" method="post" >
<input type="hidden" name="fid" value="${forumView.fid }">
<input type="hidden" name="fref" value="${forumView.fref }">
<input type="hidden" name="freforder" value="${forumView.freforder }">
<input type="hidden" name="fstep" value="${forumView.fstep }">
<input type="hidden" name="fmotherid" value="${forumView.fmotherid }">
<input type="hidden" name="fanswernum" value="${forumView.fanswernum }">
<input type="submit" value="답글달기">
</form> 
<%@ include file="bottom.jsp" %>
</body>
</html>