<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 등록</title>
<link rel="stylesheet" href="../css/style.css" type="text/css">
<script type="text/javascript" src="script.js"></script>
</head>
<body>
<div class="page-main">
	<h2>상품 등록하기</h2>
	<form id="myForm" action="insertTest.jsp" method="post">
		<ul>
			<li>
				<label for="name">상품명</label>
				<input type="text" name="name" id="name" size="20" maxlength="10">
			</li>
			<li>
				<label for="price">가격</label>
				<input type="Number" name="price" id="price" size="30" maxlength="10">
			</li>
			<li>
				<label for="stock">재고</label>
				<input type="Number" name="stock" id="stock" size="20" maxlength="10">
			</li>
			<li>
				<label for="origin">원산지</label>
				<input type="text" name="origin" id="orgin" size="20" maxlength="10" >
			</li>
		</ul>
		<div class="align-center">
			<input type="submit" value="전송">
			<input type="button" value="목록" onclick="location.href='selectTest.jsp'">
		</div>
	</form>
</div>
</body>
</html>