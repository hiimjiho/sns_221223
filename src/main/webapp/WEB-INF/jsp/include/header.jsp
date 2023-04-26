<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<div class="h-100 d-flex justify-content-between align-items-center">
	<%-- logo --%>
	<div>
		<h1 class="font-weight-bold"><a href="/timeline/timeline_view">Marondalgram</a></h1>
	</div>
	
	<%-- 로그인 정보 --%>
	<div>
		<c:if test="${not empty userId}">
			<span><a href="/user/profile_view?userId=${userName}">${userName}</a>님 안녕하세요</span>
			<a href="/user/sign_out" class="font-weight-bold">로그아웃</a>
		</c:if> 
		<c:if test="${empty userId}">
			<a href="/user/sign_in_view" class="font-weight-bold">로그인</a>
		</c:if>
	</div>
</div>

