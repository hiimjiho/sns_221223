<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>    

<div class="d-flex">
	<div>
		<img src="https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_960_720.png" alt="프로필" width=100 height=100 class="profileImage">
	</div>
	<div class="profileLoginId">
			<span class="font-weight-bold"></span>
	</div>
	<div class="profileCorrectionBtn">
		<a href="#" class="btn btn-success">프로필 수정</a>
	</div>
</div>

<div class="d-flex">
	<c:forEach items="${postList}" var="post">
	<div class="postList">
		<img src="${post.imagePath}" width=200 height=300>
	</div>
	</c:forEach>
</div>