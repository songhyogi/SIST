<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="kr.story.dao.StoryDAO" %>
<%
	//로그인이 된 상태인지 여부 체크
	Integer user_num = (Integer)session.getAttribute("user_num");
	if(user_num==null){
		response.sendRedirect("loginForm.jsp");
	}else{
		request.setCharacterEncoding("utf-8");
	}
%>
<jsp:useBean id="story" class="kr.story.vo.StoryVO"/>
<jsp:setProperty property="*" name="story"/>
<%
	//작성자
	story.setNum(user_num);
	//클라이언트의 ip 주소 저장
	story.setIp(request.getRemoteAddr());//ip주소를 알아내는 메서드
	
	StoryDAO dao = StoryDAO.getInstance();
	dao.insert(story);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 쓰기 완료</title>
<link rel="stylesheet" href=" <%= request.getContextPath() %>/css/style.css" type="text/css">
</head>
<body>
<div class="page-main"> <!-- 성공적으로 전송되었을 경우 이 페이지가 보여진다. -->
	<h1>글 쓰기 완료</h1>
	<div class="result-display">
		<div class="align-center">
			게시판에 글을 등록했습니다.<p>
			<button onclick="location.href='list.jsp'">목록</button>
		</div>
	</div>
</div>
</body>
</html>