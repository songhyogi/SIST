<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="kr.util.DBUtil" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.Date" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 상세 정보 보기</title>
<link rel="stylesheet" href="../css/style.css" type="text/css">
</head>
<body>
<div class="page-main">
	<h2>상품 상세 정보</h2>
<%
	int num = Integer.parseInt(request.getParameter("num"));
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = null;
	
	try{
		//Connection 객체 반환
		conn = DBUtil.getConnection();
		//SQL문 작성
		sql = "SELECT * FROM product WHERE num=?";
		//JDBC 수행 3단계 : PreparedStatement 객체 생성
		pstmt = conn.prepareStatement(sql);
		//?에 데이터 바인딩
		pstmt.setInt(1, num);
		//JDBC 수행 4단계 : SQL문을 테이블에 반영하고 결과행을 ResultSet에 담아서 반환
		rs = pstmt.executeQuery();
		
		if(rs.next()){//행이 있는 경우
			String name = rs.getString("name");
			int price = rs.getInt("price");
			int stock = rs.getInt("stock");
			String origin = rs.getString("origin");
			String reg_date = rs.getString("reg_date");
%>
	<ul>
		<li>상품번호 : <%= num %></li>
		<li>상품명 : <%= name %></li>
		<li>가격 : <%= String.format("%,d원", price) %></li>
		<li>재고 : <%= stock %></li>
		<li>원산지 : <%= origin %></li>
		<li>등록일 : <%= reg_date %></li>
	</ul>
	<p>
	<hr width="100%" size="1" noshade="noshade">
	<div class="align-right">
		<input type="button" value="수정" onclick = "location.href = 'updateTestForm.jsp?num=<%= num %>'">
		<input type="button" value="삭제" onclick = "location.href = 'deleteTestForm.jsp?num=<%= num %>'">
		<input type="button" value="목록" onclick = "location.href = 'selectTest.jsp'">
	</div>
<%
		}else{//행이 없는 경우
%>
	<div class="result-display">
		<div class="align-center">
			상품 상세 정보가 없습니다.<p>
			<input type="button" value="목록" onclick="location.href='selectTest.jsp'">
		</div>
	</div>
<%			
		}
	}catch(Exception e){
%>
	<div class="result-display">
		<div class="align-center">
			오류 발생! 상품 상세 정보 호출 실패!<p>
			<input type="button" value="목록" onclick="location.href='selectTest.jsp'">
		</div>
	</div>
<%
		e.printStackTrace();
	}finally{
		//자원 정리
		DBUtil.executeClose(rs, pstmt, conn);
	}
%>
</div>
</body>
</html>