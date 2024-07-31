<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="kr.board.dao.BoardDAO" %>
<%@ page import="kr.board.vo.BoardVO" %>
<%
	int num = Integer.parseInt(request.getParameter("num"));
	BoardDAO dao = BoardDAO.getInstance();
	BoardVO boardVO = dao.getBoard(num);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 글 상세</title>
<link rel="stylesheet" href=" <%= request.getContextPath() %>/css/style.css" type="text/css">
</head>
<body>
<div class="page-main">
	<h1>게시판 글 상세</h1>
	<ul>
		<li>글번호 : <%= boardVO.getNum() %></li>
		<li>제목 : <%= boardVO.getTitle() %></li>
		<li>작성자 : <%= boardVO.getName() %></li>
	</ul>
	<hr size="1" width="100%" noshade="noshade">
	<p>
		<%= boardVO.getContent() %>
	</p>
	<div class="align-right">
		작성일 : <%= boardVO.getReg_date() %>
																		<!-- 작은따옴표는 자바스크립트, 큰따옴표는 html -->
		<input type="button" value="수정" onclick="location.href='updateForm.jsp?num=<%= boardVO.getNum() %>'"> <!-- get방식으로 pk를 넘겨줘야 어떤 글을 수정하는지 알 수 있다. -->
		<input type="button" value="삭제" onclick="location.href='deleteForm.jsp?num=<%= boardVO.getNum() %>'"> 
		<input type="button" value="목록" onclick="location.href='list.jsp'"> 
	</div>
</div>
</body>
</html>