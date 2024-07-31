<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- 회원로그인 시작 -->
<div class="page-main">
	<h2>회원로그인</h2>
	<form:form action="login" id="member_login" modelAttribute="memberVO"><!-- 자바빈 초기화한 것을 받음 -->
	<form:errors element="div" cssClass="error-color"/><!-- field가 없는 에러메세지 (아이디, 비밀번호가 불일치할 경우)-->
	<ul>
		<li class="floating-label">
			<form:input path="id" placeholder="아이디" autocomplete="off" cssClass="form-input"/><!-- 자동완성 끔 -->
			<form:label path="id">아이디</form:label>
			<form:errors path="id" cssClass="error-color"/><!-- 스프링에서 사용하는 에러메세지 -->
		</li>
		<li class="floating-label">
			<form:password path="passwd" placeholder="비밀번호" cssClass="form-input"/>
			<form:label path="passwd">비밀번호</form:label>
			<form:errors path="passwd" cssClass="error-color"/>
		</li>
		<li>
			<label for="auto"><input type="checkbox" name="auto" id="auto">로그인상태유지</label><!-- 쿠키형태로 로그인 상태 유지 -->
		</li>
	</ul>
	<div>
		<form:button class="login-btn">로그인</form:button>
	</div>
	</form:form>
	<p class="align-center">
		<input type="button" value="홈으로" class="default-btn" onclick="location.href='${pageContext.request.contextPath}/main/main'">
		<input type="button" value="비밀번호찾기" onclick="location.href='sendPassword'"><!-- 이메일 전송을 하려면 이메일 서버가 필요한데 구글이메일 서버를 활용 -->
	</p>
</div>
<!-- 회원로그인 끝 -->