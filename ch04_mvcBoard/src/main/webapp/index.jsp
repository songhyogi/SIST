<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	response.sendRedirect(request.getContextPath()+"/list.do");//컨텍스트 경로의 명칭은 바뀔 수 있기 때문에 request.getContextPath()라고 작성해서 컨텍스트 경로를 가져오는 것이 좋다.
%>