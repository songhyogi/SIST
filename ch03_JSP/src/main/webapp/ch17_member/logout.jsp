<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//로그아웃
	session.invalidate();//속성을 다 지워버림
	response.sendRedirect("main.jsp");
%>