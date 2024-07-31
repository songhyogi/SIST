<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="kr.web.member.MemberVO" %>
<%
   //전송된 데이터 인코딩 타입 지정
   request.setCharacterEncoding("utf-8");
   //자바빈 객체 생성
   MemberVO member = new MemberVO();
   //set메서드를 활용하여 MemberVO안에 데이터를 담아둠
   member.setId(request.getParameter("id"));//전송된 데이터를 넣어줌
   member.setPassword(request.getParameter("password"));
   member.setName(request.getParameter("name"));
   member.setEmail(request.getParameter("email"));
   member.setAddress(request.getParameter("address"));
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>
   <!-- 자바빈에 넣어둔 데이터를 get메서드를 활용하여 읽어옴 -->
   아이디 : <%= member.getId() %><br>
   비밀번호 : <%= member.getPassword() %><br>
   이름 : <%= member.getName() %><br>
   이메일 : <%= member.getEmail() %><br>
   주소 : <%= member.getAddress() %>
</body>
</html>