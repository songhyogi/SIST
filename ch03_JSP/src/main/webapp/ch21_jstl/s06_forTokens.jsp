<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>forTokens 태그</title>
</head>
<body>
<h4>쉼표와 점을 구분자로 사용</h4>
<c:forTokens items="빨간색,주황색.노란색.초록색,파란색,남색.보라색" var="token" delims=",."><%-- 쉼표와 점을 구분자로 --%>
	${token}<br><%-- 위 var="token"의 token을 의미 --%>
</c:forTokens>
<h4>날짜를 연월일시분초로 구분해서 출력</h4>
<c:forTokens items="2024-05-07 14:09:20" var="now" delims="- :"> <%-- -와 공백과 :를 구분자로 --%>
	${now}<br>
</c:forTokens>
</body>
</html>