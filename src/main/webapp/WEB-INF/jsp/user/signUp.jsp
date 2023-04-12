<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<h2>회원 가입</h2>
<span>ID</span>
<div class="form-group d-flex">
	<input type="text" class="form-control col-5" id="loginId">
	<button type="submit" class="btn btn-info ml-3" id="duplicationBtn">중복확인</button>
</div>
	<div>
		<small id="duplicatedId" class="text-danger d-none">아이디가 중복되었습니다.</small>
		<small id="lengthCheckId" class="text-danger d-none">아이디가 4자 미만입니다.</small>
		<small id="successId" class="text-success d-none">사용 가능한 아이디입니다.</small>
	</div>

<div class="form-group">
	<span>비밀번호</span>
	<input type="password" class="form-control col-5" id="password">
</div>

<div class="form-group">
	<span>비밀번호 확인</span>
	<input type="password" class="form-control col-5" id="password">
</div>

<div class="form-group">
	<span>이름</span>
	<input type="text" class="form-control col-5" id="name">
</div>

<div class="form-group">
	<span>이메일</span>
	<input type="text" class="form-control col-5" id="email">
</div>
<button type="submit" class="btn btn-info" id="signInBtn">회원가입</button>

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
	});
</script>