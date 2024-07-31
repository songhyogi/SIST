<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- 비밀번호 변경 시작 -->
<div class="page-main">
	<h2>비밀번호 변경</h2>
	<form:form action="changePassword" id="member_change" modelAttribute="memberVO"><!-- 자바빈 초기화한 것을 받음 -->
	<form:errors element="div" cssClass="error-color"/><!-- field가 없는 에러메세지 (아이디, 비밀번호가 불일치할 경우)-->
	<ul>
		<li>
			<form:label path="now_passwd">현재 비밀번호</form:label>
			<form:password path="now_passwd"/>
			<form:errors path="now_passwd" cssClass="error-color"/><!-- 스프링에서 사용하는 에러메세지 -->
		</li>
		<li>
			<form:label path="passwd">새비밀번호</form:label>
			<form:password path="passwd"/>
			<form:errors path="passwd" cssClass="error-color"/>
		</li>
		<li><!-- 전송할 게 아니라 자바스크립트로 비밀번호와 새비밀번호 확인이 일치하는지 확인하는 거라 자바빈에 없어서 커스텀태그를 사용X -->
			<label for="confirm_passwd">새비밀번호 확인</label>
			<input type="password" id="confirm_passwd"><!-- 전송되는 걸 원치 않기 때문에 name을 뺐다. -->
			<span id="message_password"></span>
		</li>
		<li>
			<div id="captcha_div">
				<img src="getCaptcha" id="captcha_img" width="200" height="90">
			</div>
			<input type="button" value="새로고침" id="reload_btn">
			<script>
				$(function(){
					$('#reload_btn').click(function(){
						$.ajax({
							url:'getCaptcha',<%--같은 경로이기 때문에 getCaptcha만 작성해도 된다. --%>
							type:'get',
							success:function(){
								<%-- JS는 특정영역만 리로드하는 기능이 있다. --%>
								$('#captcha_div').load(location.href+' #captcha_div');
							},
							error:function(){
								alert('네트워크 오류 발생');
							}
						});
					});
				});
			</script>
		</li>
		<li>
			<form:label path="captcha_chars">캡챠 문자 확인</form:label>
			<form:input path="captcha_chars"/>
			<form:errors path="captcha_chars" cssClass="error-color"/>
		</li>
	</ul>
	<div class="align-center">
		<form:button>전송</form:button>
		<input type="button" value="MY페이지" onclick="location.href='myPage'">
	</div>
	</form:form>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/member.password.js"></script>
</div>
<!-- 비밀번호 변경 끝 -->