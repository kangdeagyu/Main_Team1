<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!doctype html>
<html lang="ko">
  <head>

    <title>메인 홈페이지 입니다</title>

    <!-- Bootstrap core CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

	<style>
	  .image {
	    width: 100%;
	    height: 500px;
	    object-fit: fill;
	  }
	</style>
    
 </head>
 <body>
    
   <!-- 네버바 --> 
  <div class="container">
 <div class="row my-2"></div>
 <div class="row">
 	<div class="col-3">
		<ul class="nav">
		  <li class="nav-item">
		    <a class="nav-link active" href="#">
		      <!-- <img src="image/so7.png" alt="" width="80" height="40"> -->
		      <h3 class="text-dark">Little and Precious</h3>
		    </a>
		  </li>
		 </ul>
	 </div>
	 <div class="col-6">
		<ul class="nav justify-content-center">
			  <li class="nav-item">
			    <a class="nav-link active" aria-current="page" href="#"><p class="text-dark">HOME</p></a>
			  </li>
			  <li class="nav-item">
			    <a class="nav-link" href="#"><p class="text-dark">LIFESTYLE</p></a>
			  </li>
			  <li class="nav-item">
			    <a class="nav-link" href="#"><p class="text-dark">FURNITURE</p></a>
			  </li>
			  <li class="nav-item">
			    <a class="nav-link" href="#"><p class="text-dark">STATIONERTY</p></a>
			  </li>
			  <li class="nav-item">
			    <a class="nav-link" href="#"><p class="text-dark">Q&A</p></a>
			  </li>
		
		</ul>
		
	</div>
	<div class="col-3">
		<ul class="nav justify-content-end">
			<li class="nav-item">
			    <a class="nav-link" href="#">
			   	 <img src="image/so5.png" alt="" width="30" height="24">
			    </a>
		    </li>
			<li class="nav-item">
			    <a class="nav-link" href="#">
			   	 <img src="image/so6.png" alt="" width="30" height="24">
			    </a>
		    </li>
		    <li class="nav-item">
			    <a class="nav-link" href="#">
			   	 <img src="image/so4.png" alt="" width="30" height="24">
			    </a>
		    </li>
		</ul>
	</div>
</div>
<hr/>
<main>

<!-- 배너부분  -->

  <section class="text-center container">
	<div id="carouselExampleIndicators" class="carousel slide" data-bs-ride="carousel">
	  <div class="carousel-indicators">
	    <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
	    <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1" aria-label="Slide 2"></button>
	    <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2" aria-label="Slide 3"></button>
	  </div>
	  <div class="carousel-inner">
	    <div class="carousel-item active" >	    	
	      <img src="image/so1.jpg" class="image" alt="...">
	    </div>
	  
	    <div class="carousel-item">
	      <img src="image/so2.jpg" class="image" alt="...">
	    </div>
	    <div class="carousel-item">
	      <img src="image/so3.jpg" class="image" alt="...">   
	    </div>
	  </div>
	  <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
	    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
	    <span class="visually-hidden">Previous</span>
	  </button>
	  <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
	    <span class="carousel-control-next-icon" aria-hidden="true"></span>
	    <span class="visually-hidden">Next</span>
	  </button>
	</div>
  </section>

<hr/>
<!-- 상품을 관리 -->

 <div class="container">
   	<div class="row my-2">
  	  <div class="col-md-3">
  	  	<a class="nav-link" href="#">
		  	<div class="card" style="width: 100%">
			<img src="https://www.yoox.com/images/items/58/58026081tc_14_f.jpg?width=210&height=272&impolicy=crop&gravity=Center" class="card-img-top" alt="...">
		
		  <div class="card-body">
		   	  <p class="text-dark">상품설명</p>
		  </div>
		</div>
		</a>
      </div>
 	</div>
</div>
</div>

  


        

</main>
<!-- 하단  -->
<footer class="text-muted py-5">
  <div class="container">
    <p class="float-end mb-1">
      <a href="#">Back to top</a>
    </p>
    <p class="mb-2">안녕하세요</p>
    <p class="mb-1">반갑습니다</p>
    <p class="mb-0">감사합니다</p>

  </div>
</footer>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
      
</body>
</html>
