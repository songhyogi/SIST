<%@ page language="java" contentType="text/plain; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ page import="kr.member.dao.MemberDAO" %>
<%@ page import="kr.member.vo.MemberVO" %>
<%
	//전송된 데이터 인코딩 타입 지정
	request.setCharacterEncoding("utf-8"); //POST방식이기 때문에 인코딩타입을 지정함
	//전송된 데이터 반환
	String id = request.getParameter("id");
	
	MemberDAO dao = MemberDAO.getInstance();
	MemberVO member = dao.checkMember(id); //MemberVO가 null이면 미중복, MemberVO가 생성되는 거면 하나의 행이 있는 것이기 때문에 중복
	if(member!=null){//아이디 증복
%>
	{"result":"idDuplicated"}
<%
	}else{//아이디 미중복
%>
	{"result":"idNotFound"}
<%
	}
%>
