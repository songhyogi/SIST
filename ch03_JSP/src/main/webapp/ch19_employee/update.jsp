<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="kr.story.dao.StoryDAO" %>
<%@ page import="kr.story.vo.StoryVO" %>
<%
	//전송된 데이터 인코딩 타입 지정
	request.setCharacterEncoding("utf-8");
%>
<jsp:useBean id="storyVO" class="kr.story.vo.StoryVO"/>
<jsp:setProperty property="*" name="storyVO"/>

<%
	StoryDAO dao = StoryDAO.getInstance();
	storyVO.setIp(request.getRemoteAddr());
	dao.update(storyVO);
%>

	<script>
		alert('글 수정을 완료했습니다.');
		location.href='detail.jsp?snum=<%= storyVO.getSnum() %>'; 
		<%-- num값이 없으면 NumberFormatException이 발생하므로 num값을 반드시 넘겨줘야 한다. --%>
	</script>
