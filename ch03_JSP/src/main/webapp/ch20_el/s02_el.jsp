<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//전송된 데이터 인코딩 타입 지정
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>표현언어의 사용 예제</title>
</head>
<body>
<h3>표현언어의 - 파라미터 값 처리</h3>
<form action="s02_el.jsp" method="post"> <!-- 데이터를 자기 자신에게 보냄 -->
	이름 : <input type="text" name="name"><br>
	<input type="submit" value="확인"/>
	<br/>
	이름은 <%= request.getParameter("name") %><br>
	이름은 ${param.name}<br><!-- 객체 안에 멤버변수 호출하는 것처럼 명시 -->
	이름은 ${param["name"]} 입니다.
</form>
</body>
</html>