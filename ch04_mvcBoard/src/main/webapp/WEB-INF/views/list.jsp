<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>목록</title>
						<!-- 모델2에서는 스크립트릿과 표현식을 사용하지 않기 때문에 el을 사용 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
</head>
<body>
<div class="page-main">
	<h2>게시판 목록</h2>
	<div class="align-right">
		<input type="button" value="글쓰기" onclick="location.href='writeForm.do'">
	</div>
	<c:if test="${count == 0}"><!-- 데이터가 없는 경우 -->
		<div class="result-display">
			표시할 게시물이 없습니다.
		</div>
	</c:if>
	<c:if test="${count > 0}">
		<table>
			<tr>
				<th>글번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
			</tr>
			<c:forEach var="board" items="${list}"> <%-- ${list}는 ArrayList를 의미하는데 ArrayList에 접근해서 루프를 돌며 자바빈을 뽑아내서 board에 담긴다. --%>
				<tr>
					<td>${board.num}</td><%-- 객체에 접근한 후에 프로퍼티명을 명시 getNum이라고 명시하는 것보다 코드를 줄일 수 있음 --%>
					<td><a href="detail.do?num=${board.num}">${board.title}</a></td> <%-- num이 Primarykey이기 때문에 num을 넘겨서 링크 --%>
					<td>${board.name}</td>
					<td>${board.reg_date}</td>
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