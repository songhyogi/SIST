<%@ page language="java" contentType="text/plain; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ page import="kr.util.DBUtil" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%--목록은 여러 건이라 배열 데이터로 처리해야하므로 배열로 출발--%>
[<%
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = null;
	try{
		//Conncetion 객체 반환
		conn = DBUtil.getConnection();
		//SQL문 작성
		sql = "SELECT * FROM people ORDER BY reg_date DESC";
		//JDBC 수행 3단계 : PreparedStatement 객체 생성
		pstmt = conn.prepareStatement(sql);
		//JDBC 수행 4단계 : SQL문 실행
		rs = pstmt.executeQuery();
		while(rs.next()){
			String id = rs.getString("id");
			String name = rs.getString("name");
			String job = rs.getString("job");
			String address = rs.getString("address");
			String blood_type = rs.getString("blood_type");
			String reg_date = rs.getString("reg_date");
			
			if(rs.getRow()>1) out.println(","); //getRow메서드를 사용해서 첫번째 행을 알아내기
			%>
			{
					  <%-- 자바스크립트는 큰따옴표가 없으면 문자열로 인식하지 못한다. --%>
				"id":"<%= id %>",
				"name":"<%= name %>",
				"job":"<%= job %>",
				"address":"<%= address %>",
				"blood_type":"<%= blood_type %>",
				"reg_date":"<%= reg_date %>"
			}
			<%
			
		}
	}catch(Exception e){
		e.printStackTrace();
	}finally{
		//자원 정리
		DBUtil.executeClose(rs, pstmt, conn);
	}
%>]
