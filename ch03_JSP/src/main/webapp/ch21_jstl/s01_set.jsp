<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="kr.web.member.Member" %>
<%-- 태그 디렉티브를 명시하면 core 라이브러리를 사용할 수 있다. --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>set 태그</title>
</head>
<body>
<%--         속성명          속성값            영역--%>
<c:set var="msg" value="봄" scope="page"/>
<c:set var="msg2" value="여름" scope="request"/>
<c:set var="msg3" value="가을" scope="session"/>
<c:set var="msg4" value="겨울" scope="application"/>
${pageScope.msg} , ${msg}<br>
${requestScope.msg2} , ${msg2}<br>
${sessionScope.msg3} , ${msg3}<br>
${applicationScope.msg4} , ${msg4}<br>

<%
	Member member = new Member();
%>
<%-- page 영역에 Member 객체 저장 --%>
<c:set var="member" value="<%= member %>"/>
<%-- Member 객체의 name 멤버변수에 데이터 저장 --%>
<%--
target : 값을 설정하고자 하는 객체를 명시
property : 값이 저장되는 멤버변수
 --%>
<c:set target="${member}" property="name" value="홍길동"/>
<%-- 페이지 영역의 멤버 객체에 접근한 다음에 하위의 name에 접근 --%>
회원 이름 : ${member.name}<br><%-- 내부적으로 get메서드를 호출해서 프로퍼티에 접근 --%>
<%-- 버전이 높아지면서 메서드명을 통해 불러와도 호출은 가능하지만 바람직하지 않다 --%>
회원 이름 : ${member.getName()}
</body>
</html>