<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 화면</title>
<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

    <style>
      .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        user-select: none;
      }

      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }

      .b-example-divider {
        height: 3rem;
        background-color: rgba(0, 0, 0, .1);
        border: solid rgba(0, 0, 0, .15);
        border-width: 1px 0;
        box-shadow: inset 0 .5em 1.5em rgba(0, 0, 0, .1), inset 0 .125em .5em rgba(0, 0, 0, .15);
      }

      .b-example-vr {
        flex-shrink: 0;
        width: 1.5rem;
        height: 100vh;
      }

      .bi {
        vertical-align: -.125em;
        fill: currentColor;
      }

      .nav-scroller {
        position: relative;
        z-index: 2;
        height: 2.75rem;
        overflow-y: hidden;
      }

      .nav-scroller .nav {
        display: flex;
        flex-wrap: nowrap;
        padding-bottom: 1rem;
        margin-top: -1px;
        overflow-x: auto;
        text-align: center;
        white-space: nowrap;
        -webkit-overflow-scrolling: touch;
      }
    </style>

<!--e94ea7cf7a4161d305da7590513621dc  -->

<link href="login.css" rel="stylesheet">
</head>
<body>
<!-- 카카오 스크립트 -->
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
<script>
	Kakao.init('e94ea7cf7a4161d305da7590513621dc'); //발급받은 키 중 javascript키를 사용해준다.
	console.log(Kakao.isInitialized()); // sdk초기화여부판단

//카카오로그인
function kakaoLogin() {
    Kakao.Auth.login({
      success: function (response) {
        Kakao.API.request({
          url: '/v2/user/me',
          success: function (response) {
        	  console.log(response)
          },
          fail: function (error) {
            console.log(error)
          },
        })
      },
      fail: function (error) {
        console.log(error)
      },
    })
  }
 
  
</script>

 


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
			    <a class="nav-link active" aria-current="page" href="home.jsp"><p class="text-dark">HOME</p></a>
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


<!-- <main>
	<div id="app">
		<form @submit.prevent="login" method="post">
			  <label>Username:</label>
			  <input type="text" v-model="username" required>
			  <br>
			  <label>Password:</label>
			  <input type="password" v-model="password" required>
			  <br>
			  <button type="submit">Login</button>
		</form>
	    <p v-if="errorMessage">{{ errorMessage }}</p>
	 </div>

</main> -->
<div class="container">
	<main class="form-signin w-100 m-auto">
		<div class="container">
		<figure class="text-center">
			<h3 class="text-dark">Little and Precious</h3>
		</figure>
		<hr/>
		</div>
	<div id="app">	
	  <form @submit.prevent="login" method="post">
	    <h6 class="h6 mb-3 fw-normal">*가입하신 이메일 주소로 로그인 해주세요</h6>
	
	    <div class="form-floating">
	      <input type="email" class="form-control" id="floatingInput" placeholder="name@example.com" v-model="username" required>
	      <label for="floatingInput">이메일 (아이디)</label>
	    </div>
	    
	    <div class="form-floating">
	      <input type="password" class="form-control" id="floatingPassword" placeholder="Password" v-model="password" required>
	      <label for="floatingPassword">비밀번호</label>
	    </div>
	
	    <div class="checkbox mb-3">
	      <label>
	        <input type="checkbox" value="remember-me"> Remember me
	      </label>
	    </div>
	    <button class="w-100 btn btn-lg btn-primary" type="submit">로그인</button>
	  </form>
<hr/>
	<div class="d-flex justify-content-between">
	  <div>회원가입</div>
	  <div>아이디/비밀번호 찾기</div>
	</div>
<hr/>
	<a href="javascript:void(0)" onclick="kakaoLogin();">
	   <img src="image/so8.png" alt="" class="w-100 btn btn-lg">
	</a>

</div>
</main>
<hr/>

</div>
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
</div>

  <script>
    new Vue({
      el: '#app',
      data: {
        username: '',
        password: '',
        errorMessage: ''
      },
      methods: {
    	  login() {
    		  const username = this.username;
    		  const password = this.password;

    		  axios.post('login.go', null, {
    		    params: {
    		      username: username,
    		      password: password
    		    }
    		  })
    		  .then(response => {
    		    // 로그인 성공 시 처리
    		    console.log(response.data);
    		  })
    		  .catch(error => {
    		    // 로그인 실패 시 처리
    		    this.errorMessage = error.response.data.message;
    		  });
    		}
        
      }
    });
  </script>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>