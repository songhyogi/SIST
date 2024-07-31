<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="kr.story.dao.StoryDAO" %>
<%@ page import="kr.story.vo.StoryVO" %>
<%
	int snum = Integer.parseInt(request.getParameter("snum"));
	StoryDAO dao = StoryDAO.getInstance();
	StoryVO story = dao.getStory(snum);/* num을 받아서 vo에 넣어 한 건의 레코드를 저장 */
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 수정</title>
<link rel="stylesheet" href=" <%= request.getContextPath() %>/css/style.css" type="text/css">
<script type="text/javascript">//jquery를 넣지 않았기 때문에 순수자바스크립트로 진행
window.onload=function(){
	const myForm = document.getElementById('update_form');
	//이벤트 연결
	myForm.onsubmit=function(){
		const items = document.querySelectorAll('input[type="text"],input[type="password"],textarea');//input태그로 접근할 경우 submit까지 접근되기 때문에 속성선택자로 접근
		for(let i=0;i<items.length;i++){
			if(items[i].value.trim()==''){
				const label = document.querySelector('label[for="'+items[i].id+'"]');
				alert(label.textContent + ' 항목은 필수 입력');//label에 접근했기때문에 textContent를 알아낼 수 있음
				items[i].value='';
				items[i].focus();
				return false;
			}
		}
	};
}
</script>
</head>
<body>
<div class="page-main">
	<h1>글 수정</h1>
	<form id="update_form" action="update.jsp" method="post">
		<input type="hidden" name="snum" value="<%= snum %>"> <!-- 안 보여지기 때문에 변경할 수 없는 상태로 전달 -->
		<ul>
			<li>
				<label for="title">제목</label>
				<input type="text" name="title" id="title" value="<%= story.getTitle() %>" size="30" maxlength="50">
			</li>
			<li>
				<label for="id">작성자</label>
				<input type="text" name="id" id="name" value="<%= story.getId() %>" size="10" maxlength="10">
			</li>
			<li>
				<label for="content">내용</label>
				<textarea rows="5" cols="40" name="content" id="content" ><%= story.getContent() %></textarea>
			</li>
		</ul>
		<div class="align-center">
			<input type="submit" value="글 수정">
			<input type="button" value="목록" onclick="location.href='list.jsp'">
		</div>
	</form>
</div>
</body>
</html>