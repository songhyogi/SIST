<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="kr.story.dao.StoryDAO" %>
<%@ page import="kr.story.vo.StoryVO" %>
<%@ page import="java.util.List" %>
<%@ page import="kr.util.PagingUtil" %>
<%
	//선택한 페이지 번호
	String pageNum = request.getParameter("pageNum");//선택한 페이지를 get방식으로 보낸다
	if(pageNum == null){
		//최초에 list.jsp를 호출하면 pageNum을 전달할 수 없기 때문에 
		//null이 되고 연산할 대 연산이 되지 않는 문제가 있어서 최초 호출시 무조건 1 페이지로 설정
		pageNum = "1";
	}
	int rowCount = 10;
	int pageCount = 10;
	int currentPage = Integer.parseInt(pageNum);
	
	StoryDAO dao = StoryDAO.getInstance();
	int count = dao.getCount();
													//순서가 정해져 있으니 순서대로
	PagingUtil util = new PagingUtil(currentPage,count,rowCount,pageCount,"list.jsp");//각각의 페이지에 맞게 연산해준다
	
	//List를 통해 반환된 데이터를 받음
	List<StoryVO> list = null;
	if(count>0){//count가 0이면 데이터가 없기 때문에 getList를 호출할 필요가 없다
		list = dao.getList(util.getStartRow(),util.getEndRow());
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 목록</title>
<link rel="stylesheet" href=" <%= request.getContextPath() %>/css/style.css" type="text/css">
</head>
<body>
<div class="page-main">
	<h1>게시판 목록</h1>
	<div class="align-right">
<%
	Integer user_num = (Integer)session.getAttribute("user_num");
	if(user_num!=null){
%>
	<input type="button" value="글쓰기" onclick="location.href='writeForm.jsp'">
<%
	}
%>
		<input type="button" value="홈으로" onclick="location.href='main.jsp'">
	</div>
<%
	if(count == 0){
%>
	<div class="result-display">저장된 글이 없습니다.</div>
<%
	}else{//데이터가 있을 경우 table이 보여지고 데이터가 없을 경우 위 div가 보여짐
%>
	<table>
		<tr>
			<th>글번호</th>
			<th>제목</th>
			<th>아이디</th>
			<th>작성일</th>
		</tr>
<%
	for(StoryVO storyVO : list){
%>
	<tr>
		<td><%= storyVO.getSnum() %></td>
		<td><a href="detail.jsp?snum=<%= storyVO.getSnum() %>"><%= storyVO.getTitle() %></a></td>
		<td><%= storyVO.getId() %></td>
		<td><%= storyVO.getReg_date() %></td>
	</tr>
<%
	}
%>
	</table>
	<!-- 목록 출력 끝 -->
	<!-- 페이지 표시 시작 -->
	<div class="align-center">
		<%= util.getPage() %>
	</div>
	<!-- 페이지 표시 끝 -->
<%
	}
%>
</div>
</body>
</html>