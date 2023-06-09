<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<style>



    td {
        text-align: left;
        padding: 10px;
    }

    td:first-child {
        text-align: left;
    }


</style>


<meta charset="UTF-8">
<title>구매내역</title>
</head>
<body>
	

		<!-- 장바구니 리스트 사이즈  -->
		<c:set var="listSize" value="${fn:length(orderList)}" />
<div>
	<h3>주문 상품 정보</h3>
	<table>
		<c:forEach items="${orderList}" var="dto" varStatus="status">
			  <tr>
			   	 <td>
			    	<img src="${dto.pfilename}" style="width: 100px; height: 100px; margin-bottom: 10px;" alt="..." />
			    </td>
			    <td>${dto.pname}<br/>
			    	${dto.pcontent} - ${dto.bqty}개<br/>
			    	<fmt:formatNumber value="${dto.price * dto.bqty}" pattern="#,##0원" />
			    </td>
			  </tr>
		</c:forEach> 	
    </table><br/>
</div>
	

<br/>

	<h3>주문자 정보</h3>
	<form action="orderProduct.do" method="get">
	<c:forEach items="${list}" var="dto" >
		<c:set var="name" value="${dto.cname }"></c:set>
		<c:set var="phone" value="${dto.cphone }"></c:set>
		<c:set var="id" value="${dto.cid }"></c:set>
		<c:set var="postnum" value="${dto.cpostnum  }"></c:set>
		<c:set var="address1" value="${dto.caddress1 }"></c:set>
		<c:set var="address2" value="${dto.caddress2}"></c:set>
		<input type="checkbox" id="sameInfoCheckbox" name="user"> 주문자 정보와 동일<br/>
		<div id="additionalInfo">
		    <input type="text" name="cname" id="cname" size="20" value="${dto.cname }" placeholder="이름"><br/>
		    <input type="text" name="cphone" id="cphone" size="20" value="${dto.cphone }" placeholder="전화번호"><br/>
		    <input type="email" name="cid" id="cid" size="42" value="${dto.cid }" placeholder="아이디(이메일)"><br/>
		    <input type="text"  name="cpostnum" id="sample6_postcode" placeholder="우편번호" value="${dto.cpostnum }" readonly>
			<input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기"><br/>

			<input type="text"  name="caddress1" id="sample6_address" placeholder="주소" value="${dto.caddress1 }" readonly><br>
			<input type="text"  name="caddress2" id="sample6_detailAddress" placeholder="상세주소" value="${dto.caddress2 }"><br/>
			<input type="hidden" id="sample6_extraAddress" placeholder="참고항목">
		</div>
		<br><br>
		
		배송메모<br>
		<select>
			<option>배송메모를 선택해주세요.</option>		
			<option>배송 전에 미리 연락 바랍니다.</option>		
			<option>부재시 경비실에 맡겨주세요.</option>		
			<option>부재시 전화나 문자를 남겨주세요.</option>		
			<option>직접입력</option>		
		</select>
	
	</c:forEach>
	
<br/><br/>

	<h3>주문 요약</h3>
	<c:set var="totalprice" value="0"></c:set>
	<c:forEach items="${orderList}" var="dto" varStatus="status">
		<input type="hidden" name="pid[]" value="${dto.pid }">
		<input type="hidden" name="qty[]" value="${dto.bqty }">
		<input type="hidden" name="price[]" value="${dto.price }">
	  	<c:set var="subtotal" value="${dto.price * dto.bqty}"></c:set>
	  	<c:set var="totalprice" value="${totalprice + subtotal}"></c:set>
	</c:forEach>
	
	상품가격 ${totalprice }<br/>
	배송비 ${totalprice >= 150000 ? 0 : 3000  }			<br/>

	총 주문 금액 ${(totalprice) + (totalprice >= 150000 ? 0 : 3000)}		<br/>

<br/><br/>

	<h3>결제수단</h3>
	<input type="radio" name="purchase" checked="checked">신용카드
	<input type="radio" name="purchase" >가상계좌<br/>
	<input type="radio" name="purchase" >무통장입금
	<input type="radio" name="purchase" >카카오페이<br/>
	<input type="radio" name="purchase" >삼성페이
	<input type="radio" name="purchase" >휴대폰<br/>
	
	
	
	<input type="submit" value="구매하기">
	</form>



<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
    function sample6_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    // 조합된 참고항목을 해당 필드에 넣는다.
                    document.getElementById("sample6_extraAddress").value = extraAddr;
                
                } else {
                    document.getElementById("sample6_extraAddress").value = '';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('sample6_postcode').value = data.zonecode;
                document.getElementById("sample6_address").value = addr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("sample6_detailAddress").focus();
            }
        }).open();
    }
    
    
    
 // 체크박스 요소를 가져옵니다.
    const checkbox = document.getElementById('sameInfoCheckbox');

    // 텍스트 필드 요소들을 가져옵니다.
    const cnameField = document.getElementById('cname');
    const cphoneField = document.getElementById('cphone');
    const cidField = document.getElementById('cid');
    const cpostnumField = document.getElementById('sample6_postcode');
    const caddress1Field = document.getElementById('sample6_address');
    const caddress2Field = document.getElementById('sample6_detailAddress');

    // 체크박스의 상태가 변경될 때마다 실행되는 함수를 정의합니다.
    function toggleAdditionalInfo() {
        // 체크박스가 체크되어 있는지 확인합니다.
        if (checkbox.checked) {
            // 체크박스가 체크되어 있다면 데이터를 채웁니다.
            cnameField.value = "${name }";
            cphoneField.value = "${phone }";
            cidField.value = "${cid }";
            cpostnumField.value = "${postnum }";
            caddress1Field.value = "${address1 }";
            caddress2Field.value = "${address2 }";
        } else {
            // 체크박스가 체크되어 있지 않다면 데이터를 비웁니다.
            cnameField.value = "";
            cphoneField.value = "";
            cidField.value = "";
            cpostnumField.value = "";
            caddress1Field.value = "";
            caddress2Field.value = "";
        }
    }

    // 체크박스의 상태가 변경될 때마다 toggleAdditionalInfo 함수를 호출합니다.
    checkbox.addEventListener('change', toggleAdditionalInfo);

    // 페이지 로드 시 체크박스의 초기 상태에 따라 toggleAdditionalInfo 함수를 호출하여 데이터를 채울지 비울지 결정합니다.
    toggleAdditionalInfo();



</script>




	<!-- footer -->
	<%@ include file="bottom.jsp" %>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>