<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<h3>회원 가입</h3>

<form id="signUpForm" method="post" action="/user/sign_up">
<div class="form-group d-flex">
	<input type="text" class="form-control col-5" id="loginId" placeholder="아이디를 입력하세요.">
	<button type="button" class="btn btn-info ml-2" id="duplicationBtn">중복확인</button>
</div>
	<div>
		<small id="duplicatedId" class="text-danger d-none">아이디가 중복되었습니다.</small>
		<small id="lengthCheckId" class="text-danger d-none">아이디가 4자 미만입니다.</small>
		<small id="successId" class="text-success d-none">사용 가능한 아이디입니다.</small>
	</div>

<div class="form-group">
	<input type="password" class="form-control col-5" id="password" placeholder="비밀번호를 입력하세요.">
</div>

<div class="form-group">
	<input type="password" class="form-control col-5" id="confirmPassword" placeholder="비밀번호를 입력하세요.">
</div>

<div class="form-group">
	<input type="text" class="form-control col-5" id="name" placeholder="이름을 입력하세요.">
</div>

<div class="form-group">
	<input type="text" class="form-control col-5" id="email" placeholder="이메일을 입력하세요.">
</div>
<button type="submit" class="btn btn-info" id="signInBtn">회원가입</button>
</form>

<script>
	$(document).ready(function(){
		$("#duplicationBtn").on("click", function(){
			let loginId = $("#loginId").val().trim();
			
			$("#duplicatedId").addClass("d-none");
			$("#lengthCheckId").addClass("d-none");
			$("#successId").addClass("d-none");
			
			if(loginId.length < 4){
				$("#lengthCheckId").removeClass("d-none");
				return;
			}
			
			$.ajax({
				// request
				url:"/user/is_duplicated_id"
				, data:{"loginId":loginId}
			
				// response
				, success:function(data){
					if(data.result){
						$("#duplicatedId").removeClass("d-none");
					}else{
						$("#successId").removeClass("d-none");
					}
				}
			});
			
		});
		
		$("#signUpForm").on("submit", function(e){
			e.preventDefault();	// submit 기능 중단
			
			// validation
			let loginId = $("#loginId").val().trim();
			let password = $("#password").val();
			let confirmPassword = $("#confirmPassword").val();
			let name = $("#name").val().trim();
			let email = $("#email").val().trim();
			
			if(!loginId){
				alert("아이디를 입력하세요.");
				return false;
			}
			
			if(!password || !confirmPassword){
				alert("비밀번호를 입력해주세요");
				return false;
			}
			
			if(password != confirmPassword){
				alert("비밀번호가 일치하지 않습니다.");
				return false;
			}
			
			if(!name){
				alert("이름을 입력해주세요.");
				return false;
			}
			
			if(!email){
				alert("이메일을 입력해주세요.");
				return false;
			}
			
			// 아이디 중복확인 완료됐는지 확인 -> idCheckOk에 d-none이 있으면 얼럿을 띄워야 한다.
			if($("#successId").hasClass("d-none")){
				alert("아이디 중복확인을 다시 해주세요.");
				return false;
			}
			
			// 서버로 보내는 방법
			// 1) 서브밋
			//$(this)[0].submit();		일반 컨트롤러(화면이동)
		
			// 2) ajax		// restController
			let url = $(this).attr("action");
			console.log(url);
			let params = $(this).serialize();	// form 태그에 있는 name 속성 값들로 파라미터 구성
			console.log(params);
			
			$.post(url, params)	//request
			.done(function(data) {
				// response
				if (data.code == 1) {	// 성공
					alert("가입을 환영합니다! 로그인을 해주세요.");
					location.href = "/user/sign_in_view";
				} else {	// 실패
					alert(data.errorMessage);
				}
			});
		});
	});
</script>