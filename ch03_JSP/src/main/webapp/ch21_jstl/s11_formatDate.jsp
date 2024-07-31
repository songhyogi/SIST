<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>formatDate 태그</title>
</head>
<body>
<c:set var="now" value="<%= new java.util.Date() %>"/> <%-- 객체 생성해서 var에 저장 --%>
${now}<br> <%-- 영문 Date로 출력된다. (요일 월 일 시:분:초 그리니치천문대기준시 연) --%>
<fmt:formatDate value="${now}" type="date" dateStyle="full"/><br> <%-- 연 월 일 요일 --%>
<fmt:formatDate value="${now}" type="date" dateStyle="short"/><br> <%-- 연.월.일 --%>
<fmt:formatDate value="${now}" type="time" timeStyle="full"/><br> <%-- 오전/오후 시 분 초 그리니치천문대기준시 --%>
<fmt:formatDate value="${now}" type="time" timeStyle="short"/><br> <%-- 오전/오후 시:분 --%>
<fmt:formatDate value="${now}" type="both" dateStyle="full" timeStyle="full"/><br> <%-- 연 월 일 요일 오전/오후 시 분 초 그리니치천문대기준시 --%>
<fmt:formatDate value="${now}" pattern="yyyy년MM월dd일 a HH:mm:ss"/> <%-- 패턴을 넣으면 원하는 형태로 출력할 수 있다. --%>
</body>
</html>