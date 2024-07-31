<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="kr.employee.dao.EmployeeDAO" %>
<%@ page import="kr.employee.vo.EmployeeVO" %>
<%
	Integer user_num = (Integer)session.getAttribute("user_num");
	if(user_num == null){
		response.sendRedirect("loginForm.jsp");
	}else{
%>	
	<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사원상세정보</title>
<link rel="stylesheet" href=" <%= request.getContextPath() %>/css/style.css" type="text/css">
</head>
<body>
<%
	EmployeeDAO dao = EmployeeDAO.getInstance();
	EmployeeVO employee = dao.getEmployee(user_num);
%>
<div class="page-main">
	<h1>사원상세정보</h1>
	<ul>
		<li>회원 번호 : <%= employee.getNum() %></li>
		<li>아이디 : <%= employee.getId() %></li>
		<li>이름 : <%= employee.getName() %>
		<li>비밀번호 : <%= employee.getPasswd() %></li>
		<li>급여 : <%= employee.getSalary() %></li>
		<li>업무 : <%= employee.getJob() %></li>
	</ul>
	<hr size="1" width="100%" noshade="noshade">
	<div class="align-right">
		작성일 : <%= employee.getReg_date() %>
		
		<input type="button" value="사원정보수정" onclick="location.href='modifyUserForm.jsp'">
		<input type="button" value="사원정보삭제" onclick="location.href='deleteUserForm.jsp'"> 
		<input type="button" value="홈으로" onclick="location.href='main.jsp'"> 
	</div>	
</div>
</body>
</html>
<%
	}
 %>
