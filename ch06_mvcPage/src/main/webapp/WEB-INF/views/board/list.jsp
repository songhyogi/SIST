<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 목록</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
<script type="text/javascript">
window.onload=function(){
	const myForm = document.getElementById('search_form');
	//이벤트 연결
	myForm.onsubmit=function(){
		const keyword = document.getElementById('keyword');
		if(keyword.value.trim()==''){
			alert('검색어를 입력하세요');
			keyword.value = '';
			keyword.focus();
			return false;
		}
	};
};
</script>
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="content-main">
		<h2>게시판 목록</h2>
		<form id="search_form" action="list.do" method="get">
			<ul class="search">
				<li>
					<select name="keyfield">
						<option value="1" <c:if test="${param.keyfield == 1}">selected</c:if>>제목</option>
						<option value="2" <c:if test="${param.keyfield == 2}">selected</c:if>>작성자</option>
						<option value="3" <c:if test="${param.keyfield == 3}">selected</c:if>>내용</option>
					</select>
				</li>
				<li>
					<input type="search" size="16" name="keyword" id="keyword" value="${param.keyword}"><!-- 어떤 것을 검색했는지 검색어가 남아있게 -->
				</li>
				<li>
					<input type="submit" value="검색">
				</li>
			</ul>
		</form>
		<div class="list-space align-right">
			<input type="button" value="글쓰기" onclick="location.href='writeForm.do'" 
						<c:if test="${empty user_num}">disabled="disabled"</c:if>><!-- 로그인이 되지 않으면 user_num이 비어있음. 로그인이 안 되어있을 경우 버튼 비활성화 -->
			<input type="button" value="목록" onclick="location.href='list.do'">
			<input type="button" value="홈으로" onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
		</div>
		<c:if test="${count == 0}"><!-- 게시물이 없음 -->
			<div class="result-display">
				표시할 게시물이 없습니다.
			</div>
		</c:if>
		<c:if test="${count > 0}"><!-- 게시물이 있음 -->
			<table>
				<tr>
					<th>글번호</th>
					<th>제목</th>
					<th>작성자</th>
					<th>작성일</th>
					<th>조회</th>
				</tr>
				<c:forEach var="board" items="${list}">
					<tr>
						<td>${board.board_num}</td>
						<td><a href="detail.do?board_num=${board.board_num}">${board.title}</a></td>
						<td>${board.id}</td>
						<td>${board.reg_date}</td>
						<td>${board.hit}</td>
					</tr>
				</c:forEach>
			</table>
			<div class="align-center">${page}</div>
		</c:if>
	</div>
</div>
</body>
</html>