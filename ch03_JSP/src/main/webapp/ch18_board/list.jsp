<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="kr.board.dao.BoardDAO" %>
<%@ page import="kr.board.vo.BoardVO" %>
<%@ page import="java.util.List" %>
<%@ page import="kr.util.PagingUtil" %>
<%
	//선택한 페이지 번호
	String pageNum = request.getParameter("pageNum"); //선택한 페이지를 get방식으로 보낸다
	if(pageNum == null){
		//최초에 list.jsp를 호출하면 pageNum를 전달할 수 없기 때문에 
		//null이 되고 연산할 때 연산이 되지 않는 문제가 있어서 최초 호출시 무조건 1페이지로 설정
		pageNum = "1"; 
	}
	
	//한 화면에 몇 개의 글(행,레코드)를 보여줄 지 지정
	int rowCount = 10;
	//한 화면에 몇 개의 페이지 수를 보여줄 지 지정
	int pageCount = 10;
	//현재 선택한 페이지(String -> int)
	int currentPage = Integer.parseInt(pageNum);//9번째 줄을 보면 String인데 연산을 하려면 int로 변환
	
	BoardDAO dao = BoardDAO.getInstance();//BoardDAO에 접근
	int count = dao.getCount();
	
	PagingUtil util = new PagingUtil(currentPage,count,rowCount,pageCount,"list.jsp"); //PagingUtil이 각각의 페이지에 맞게 연산해준다
	
	//List를 통해 반환된 데이터를 받음
	List<BoardVO> list = null;
	if(count>0){//count가 0이면 데이터가 없기 때문에 getList를 호출할 필요가 없다.
		list = dao.getList(util.getStartRow(), util.getEndRow());//PagingUtil이 getStartRow와 getEndRow를 만들어 보내준다.
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
		<input type="button" value="글 쓰기" onclick="location.href='writeForm.jsp'">
	</div>
<%
	if(count == 0){
%>
	<div class="result-display">저장된 글이 없습니다.</div>
<%
	}else{//데이터가 있을 경우(count>0) table이 보여지고 데이터가 없을 경우(count==0) 위 div가 보여짐
%>
	<!-- 목록 출력 시작 -->
	<table>
		<tr>
			<th>글번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
		</tr>
<%
	for(BoardVO boardVO : list){
%>
		<tr>
			<td><%= boardVO.getNum() %></td>
			<td><a href="detail.jsp?num=<%= boardVO.getNum() %>"><%= boardVO.getTitle() %></a></td> <!-- get방식, 파라미터와 밸류의 쌍으로 -->
			<td><%= boardVO.getName() %></td>
			<td><%= boardVO.getReg_date() %></td>
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
	<!--  페이지 표시 끝 -->
<%
	}
%>
</div>
</body>
</html>