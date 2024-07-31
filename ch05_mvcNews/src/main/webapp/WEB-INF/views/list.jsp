<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>뉴스 목록</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
</head>
<body>
<div class="page-main">
	<h2>뉴스 목록</h2>
	<div class="align-right">
		<input type="button" value="등록" onclick="location.href='writeForm.do'">
	</div>
	<c:if test="${count == 0}"><!-- 데이터가 없는 경우 -->
		<div class="result-display">
			표시할 뉴스가 없습니다.
		</div>
	</c:if>
	<c:if test="${count > 0}">
		<table>
			<tr>
				<th>뉴스 번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
			</tr>		<!-- 자바빈을 가르킨다 -->
			<c:forEach var="news" items="${list}"> <!-- ${list}는 ArrayList를 의미 -->
				<tr>
					<td>${news.num}</td><!-- 객체에 접근한 후에 프로퍼티명을 명시  -->
					<td><a href="detail.do?num=${news.num}">${news.title}</a></td>
					<td>${news.writer}</td>
					<td>${news.reg_date}</td>
				</tr>
			</c:forEach>
		</table>
		<div class="align-center">
			${page}
		</div>
	</c:if>
</div>
</body>
</html>