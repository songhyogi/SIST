<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC패턴 연습</title>
</head>
<body>
결과 : <%= request.getAttribute("result") %><br><!-- result가 속성명인데 속성명을 넣어주면 속성값을 가져온다. -->
결과 : ${result}<!-- el을 사용하는 것이 코드를 줄일 수 있다. -->
</body>
</html>