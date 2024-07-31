<%@ page language="java" contentType="text/plain; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ page import="kr.util.DBUtil" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%
	//전송된 데이터 인코딩 타입 지정
	request.setCharacterEncoding("utf-8");
	//전송된 데이터 반환
	String id = request.getParameter("id");//id 중복체크하는 것이기 때문에 id를 받음
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = null;
	try{//호출 성공
		//Connection 객체 반환
		conn = DBUtil.getConnection();
		//sql문 작성
		sql = "SELECT id FROM people WHERE id=?";
		//JDBC 수행 3단계 : PreparedStatement 객체 생성
		pstmt = conn.prepareStatement(sql);
		//?에 데이터 바인딩
		pstmt.setString(1,id);
		//JDBC 수행 4단계 
		rs = pstmt.executeQuery();
		if(rs.next()){//ID 중복 (행이 있기 때문에) 
%>
		{"result":"idDuplicated"}
<%
		}else{//ID 미중복
%>
		{"result":"idNotFound"}
<%		
		}
	}catch(Exception e){//호출 실패
%>
		{"result":"failure"}
<%
		e.printStackTrace();
	}finally{
		//자원 정리
		DBUtil.executeClose(rs, pstmt, conn);
	}
%>
