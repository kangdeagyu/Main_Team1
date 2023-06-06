<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<title>Little</title>
</head>
<body>
<div class="container">

 <div class="row">
 	<div class="col-3">
		<ul class="nav">
		  <li class="nav-item">
		    <a class="nav-link active" href="home.do">
		      <!-- <img src="image/so7.png" alt="" width="80" height="40"> -->
		      <h3 class="text-dark">Little and Precious</h3>
		    </a>
		  </li>
		 </ul>
	 </div>
	 <div class="col-6">
		<ul class="nav justify-content-center">
			  <li class="nav-item">
			    <a class="nav-link active" aria-current="page" href="home.do"><p class="text-dark">HOME</p></a>
			  </li>
			  <li class="nav-item">
			    <a class="nav-link" href="CategoryView.do?num=0"><p class="text-dark">LAMP</p></a>
			  </li>
			  <li class="nav-item">
			    <a class="nav-link" href="CategoryView.do?num=1"><p class="text-dark">MINIATURE</p></a>
			  </li>
			  <li class="nav-item">
			    <a class="nav-link" href="CategoryView.do?num=2"><p class="text-dark">CHAIR</p></a>
			  </li>
			  <li class="nav-item">
			    <a class="nav-link" href="#"><p class="text-dark">Q&A</p></a>
			  </li>
		
		</ul>
		
	</div>
	<div class="col-3">
		<ul class="nav justify-content-end">
			<c:if test="${cid != null }">
				<li class="nav-item">
				   	<a class="nav-link" href="mypageview.do"><p class="text-dark" >${name }님</p></a>
				<li class="nav-item">
				   	 <a class="nav-link" href="logout.do"><p class="text-dark">로그아웃</p></a>
			    </li>
			</c:if>
			<c:if test="${cid == null }">
				<li class="nav-item">
				   	 <a class="nav-link" href="login.jsp"><p class="text-dark">로그인</p></a>
			    </li>
			</c:if>
			<li class="nav-item">	<!-- 마이 페이지 이동 -->
			    <a class="nav-link" href="mypageview.do" onclick="return userCheck()">
			   	 <img src="image/so5.png" alt="" width="30" height="24">
			    </a>
		    </li>
			<li class="nav-item">	<!-- 장바구니 이동 -->
			    <a class="nav-link" href="#">
			   	 <img src="image/so6.png" alt="" width="30" height="24">
			    </a>
		    </li>
		</ul>
	</div>
</div>

<hr/>

<script type="text/javascript">

function userCheck() {
	if(${cid == null }){
	    alert("로그인을 해주세요.")
	    return false
	}else{
		return true
	}
	
}

</script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>