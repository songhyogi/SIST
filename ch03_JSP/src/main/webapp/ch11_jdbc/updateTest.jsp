<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="kr.util.DBUtil" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 수정</title>
<link rel="stylesheet" href="../css/style.css" type="text/css">
</head>
<body>
<%
	//POST 방식으로 전송된 데이터 인코딩 타입 지정
	request.setCharacterEncoding("utf-8");

	//전송된 데이터 반환
	int num = Integer.parseInt(request.getParameter("num"));
	String name = request.getParameter("name");
	int price = Integer.parseInt(request.getParameter("price"));
	int stock = Integer.parseInt(request.getParameter("stock"));
	String origin = request.getParameter("origin");
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	String sql = null;
	try{
		
	}catch(Exception e){
		e.printStackTrace();
	}finally{
		//자원 정리
		DBUtil.executeClose(null, pstmt, conn);
	}
%>
</body>
</html>