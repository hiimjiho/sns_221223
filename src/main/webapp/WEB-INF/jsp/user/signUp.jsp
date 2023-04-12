<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<h2>회원 가입</h2>
<span>ID</span>
<div class="form-group d-flex">
	<input type="text" class="form-control col-5" id="loginId">
	<button type="submit" class="btn btn-info ml-3" id="duplicationBtn">중복확인</button>
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
			alert("ㅎㅇ");
		});
	});
</script>