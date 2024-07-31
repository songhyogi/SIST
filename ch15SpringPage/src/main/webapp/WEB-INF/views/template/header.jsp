<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 상단 시작 -->
<h2 class="align-center">SpringPage</h2>
<div class="align-right">
	<a href="${pageContext.request.contextPath}/board/list">게시판</a>
	<c:if test="${!empty user}">
	<a href="${pageContext.request.contextPath}/member/myPage">MY페이지</a>
	<img src="${pageContext.request.contextPath}/member/photoView" width="25" height="25" class="my-photo">
	<a href="${pageContext.request.contextPath}/member/logout">로그아웃</a>
	</c:if>
	<c:if test="${empty user}"><!-- 필요한 데이터를 자바빈에 넣어서 통째로 세션에 넘기려고 한다. -->
	<a href="${pageContext.request.contextPath}/member/registerUser">회원가입</a>
	<a href="${pageContext.request.contextPath}/member/login">로그인</a>
	</c:if>
	<a href="${pageContext.request.contextPath}/main/main">홈으로</a>
</div>
<!-- 상단 끝 -->