<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%-- uri가 식별자 - 유니크하기 때문에 uri를 사용했다. --%>
<%
	//전송된 데이터 인코딩 타입 지정
	request.setCharacterEncoding("utf-8");//utf-8로 지정해줘야 한글이 안 깨진다.
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>표현 언어 - 파라미터 값 처리</title>
</head>
<body>
<h3>표현언어 - 파라미터 값 처리</h3>
<form action="s03_el.jsp" method="post">
	좋아하는 계절
	<input type="checkbox" name="season" value="봄">봄
	<input type="checkbox" name="season" value="여름">여름
	<input type="checkbox" name="season" value="가을">가을
	<input type="checkbox" name="season" value="겨울">겨울
	<input type="submit" value="확인">
</form>
<br>
${paramValues.season[0]}<br>
${paramValues.season[1]}<br>
${paramValues.season[2]}<br>
${paramValues.season[3]}<br>
---------------------------<br>
${paramValues["season"][0]}<br>
${paramValues["season"][1]}<br>
${paramValues["season"][2]}<br>
${paramValues["season"][3]}<br>
---------------------------<br>
<c:forEach var="i" items="${paramValues.season}"> <%-- ${paramValues.season}이 배열인데 루프를 돌아서 i에 넣어준다 --%>
	${i}<br>
</c:forEach>
</body>
</html>