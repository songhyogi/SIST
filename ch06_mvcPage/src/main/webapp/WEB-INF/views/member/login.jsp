<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
	<c:when test="${auth == 1}"> <%--정지회원의 경우 아래 25번까지의 내용이 보여짐 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 정보</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
</head>
<body>
	<div class="page-main">
		<jsp:include page="/WEB-INF/views/common/header.jsp"/>
		<div class="content-main">
			<h2>회원 정보</h2>
			<div class="result-display">
				정지된 회원 ID입니다.<br>
				<input type="button" value="홈으로" onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
			</div>
		</div>
	</div>
</body>
</html>
	</c:when>
	<%-- auto가 1이 아닌 경우 --%> 
	<c:otherwise>
	<script type="text/javascript">
		alert('아이디 또는 비밀번호가 불일치합니다.');<%-- 탈퇴회원의 경우에도 여기로 들어오지만 탈퇴회원입니다라고 알려주는 것은 정보를 유출하는 것이다. --%>
		history.go(-1);
	</script>
	</c:otherwise>
</c:choose>