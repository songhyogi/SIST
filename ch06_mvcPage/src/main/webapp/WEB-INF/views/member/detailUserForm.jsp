<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보 수정(관리자 전용)</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="content-main">
		<h2>${member.id}의 회원정보(관리자 전용)</h2>
		<form action="adminUser.do" method="post" id="detail_form">
			<input type="hidden" name="mem_num" value="${member.mem_num}"><!-- 세션에 있는 것은 회원의 번호가 아니라 관리자의 번호기 때문에 세션에 있는 것을 사용하면 안된다. -->
			<ul>
				<li>
					<label>등급</label>
					<c:if test="${member.auth!=9}"><!-- auth가 9(관리자)가 아닌 경우에 등급 변경 -->
						<input type="radio" name="auth" value="1" id="auth1" <c:if test="${member.auth == 1}">checked</c:if> >정지
						<input type="radio" name="auth" value="2" id="auth2" <c:if test="${member.auth == 2}">checked</c:if> >일반
					</c:if>
					<c:if test="${member.auth==9}"><!-- auth가 9(관리자)인 경우 변경 못하게 -->
						<input type="radio" name="auth" value="9" id="auth3" checked>관리
					</c:if>
				</li>
			</ul>
			<div class="align-center">
				<c:if test="${member.auth!=9}"><!-- auth가 9(관리자)가 아닌 경우 submit버튼은 있지만 auth가 9인 경우 submit버튼 없음 -->
					<input type="submit" value="수정">
				</c:if>
				<input type="button" value="목록" onclick="location.href='adminList.do'">
			</div>
			<ul>
				<li>
					<label>이름</label>${member.name}<!-- input태그가 아니기 때문에 submit 못함 표시용도로만 사용 -->
				</li>
				<li>
					<label>전화번호</label>${member.phone}
				</li>
				<li>
					<label>이메일</label>${member.email}
				</li>
				<li>
					<label>우편번호</label>${member.zipcode}
				</li>
				<li>
					<label>주소</label>${member.address1} ${member.address2}
				</li>
			</ul>
		</form>
	</div>
</div>
</body>
</html>