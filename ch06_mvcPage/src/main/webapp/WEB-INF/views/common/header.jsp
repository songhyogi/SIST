<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- header 시작 -->
<div id="main_logo">
	<h1 class="align-center">
		<a href="${pageContext.request.contextPath}/main/main.do">회원제 게시판</a>
	</h1>
</div>
<div id="main_nav">
	<ul>
		<li><!-- 로그인 하지 않아도 누구나 볼 수 있음 -->
			<a href="${pageContext.request.contextPath}/item/itemList.do">상품리스트</a>
		</li>
		<li>
			<a href="${pageContext.request.contextPath}/board/list.do">게시판</a>
		</li>
		<c:if test="${!empty user_num && user_auth == 9}"><!-- 로그인이 되어 있고 user_auth가 9일 경우 보여짐 -->
		<li>
			<a href="${pageContext.request.contextPath}/member/adminList.do">회원관리</a>
		</li>
		<li>
			<a href="${pageContext.request.contextPath}/item/adminList.do">상품관리</a>
		</li>
		<li>
			<a href="${pageContext.request.contextPath}/order/adminList.do">주문관리</a>
		</li>
		</c:if>
		<c:if test="${!empty user_num}"> <!-- 비어있지 않을 경우 여기로 진입 (로그인이 되어 있을 경우) -->
		<li>
			<a href="${pageContext.request.contextPath}/cart/list.do">장바구니</a>
		</li>
		<li>
			<a href="${pageContext.request.contextPath}/member/myPage.do">MY페이지</a>
		</li>
		</c:if>
		<c:if test="${!empty user_num && !empty user_photo}"><!-- 로그인이 되어 있고 프로필 사진이 있으면 프로필 사진이 보이게 -->
		<li class="menu-profile">
			<img src="${pageContext.request.contextPath}/upload/${user_photo}" width="25" height="25" class="my-photo">
		</li>
		</c:if>
		<c:if test="${!empty user_num && empty user_photo}"><!-- 로그인이 되어 있고 프로필 사진이 없으면 기본이미지가 보이게-->
		<li class="menu-profile">
			<img src="${pageContext.request.contextPath}/images/face.png" width="25" height="25" class="my-photo">
		</li>
		</c:if>
		<c:if test="${!empty user_num}"><!-- 로그인이 되어 있으면 로그아웃이 보이게 -->
		<li class="menu-logout">
			[<span>${user_id}</span>]
			<a href="${pageContext.request.contextPath}/member/logout.do">로그아웃</a>
		</li>
		</c:if>
		<c:if test="${empty user_num}"><!-- 로그인이 안 되어 있으면 회원가입과 로그인이 보이게 -->
		<li>
			<a href="${pageContext.request.contextPath}/member/registerUserForm.do">회원가입</a>
		</li>
		<li>
			<a href="${pageContext.request.contextPath}/member/loginForm.do">로그인</a>
		</li>
		</c:if>
	</ul>
</div>
<!-- header 끝 -->    