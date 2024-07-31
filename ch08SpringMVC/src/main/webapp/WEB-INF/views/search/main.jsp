<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게임 검색 메인</title>
</head>
<body>
<form action="game.do" method="get"><!-- 검색방식은 주로 get방식을 사용. form태그가 있는데 method를 명시하지 않으면 get이 기본값. post방식은 post라고 명시해줘야 한다. -->
	<select name="type">
		<option value="1">전체</option>
		<option value="2">아이템</option>
		<option value="3">캐릭터</option>
	</select>	
	<input type="search" name="query">
	<input type="submit" value="검색">
</form>
</body>
</html>