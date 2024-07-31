<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="kr.member.dao.MemberDAO" %>
<%@ page import="kr.member.vo.MemberVO" %>
<%
	//회원제 서비스이기 때문에 회원만 보게 해야 한다.
	//만약 로그인 안 하고 들어오면 로그인 화면을 보이게 만든다.
	Integer user_num = (Integer)session.getAttribute("user_num");//user_num이 없으면 로그인이 되어 있지 않다는 의미
	if(user_num==null){//로그인이 되지 않은 경우
		response.sendRedirect("loginForm.jsp");
	}else{//로그인이 된 경우
%>
<!-- 로그인이 된 경우 html이 보이게 한다. -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원상세정보</title>
<link rel="stylesheet" href=" <%= request.getContextPath() %>/css/style.css" type="text/css">
</head>
<body>
<%
	MemberDAO dao = MemberDAO.getInstance();//MemberDAO에서 가져와야 하니까 MemberDAO 객체 생성
	MemberVO member = dao.getMember(user_num);
	if(member.getPhone()==null){
		member.setPhone("");
	}
%>
<div class="page-main">
	<h1>회원정보</h1>
	<ul>
		<li>아이디 : <%= member.getId() %></li>	
		<li>이름 : <%= member.getName() %></li>
		<li>이메일 : <%= member.getEmail() %></li>
		<li>전화번호 : <%= member.getPhone() %></li>
		<li>가입날짜 : <%= member.getReg_date() %></li>
	</ul>
	<hr size="1" width="100%" noshade="noshade">
	<div class="align-right">
		<input type="button" value="회원정보수정" onclick="location.href='modifyUserForm.jsp'">
		<input type="button" value="회원정보탈퇴" onclick="location.href='deleteUserForm.jsp'">
		<input type="button" value="홈으로" onclick="location.href='main.jsp'">
	</div> 
</div>
</body>
</html>	
<%
	}
%>
