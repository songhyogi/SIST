<%@ page language="java" contentType="text/plain; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ page import="kr.employee.dao.EmployeeDAO" %>
<%@ page import="kr.employee.vo.EmployeeVO" %>
<%
	//전송된 데이터 인코딩 타입 지정
	request.setCharacterEncoding("utf-8"); //POST방식이기 때문에 인코딩타입을 지정함
	//전송된 데이터 반환
	String id = request.getParameter("id");
	
	EmployeeDAO dao = EmployeeDAO.getInstance();
	EmployeeVO employee = dao.checkEmployee(id); //MemberVO가 null이면 미중복, MemberVO가 생성되는 거면 하나의 행이 있는 것이기 때문에 중복
	if(employee!=null){//아이디 증복
%>
	{"result":"idDuplicated"}
<%
	}else{//아이디 미중복
%>
	{"result":"idNotFound"}
<%
	}
%>
