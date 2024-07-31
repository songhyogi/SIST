<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>뉴스 상세</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
</head>
<body>
<div class="page-main">
	<h2>뉴스 상세</h2>
	<ul>
		<li>뉴스번호 : ${newsVO.num}</li>
		<li>제목 : ${newsVO.title}</li>
		<li>작성자 : ${newsVO.writer}</li>
		<li>이메일 : ${newsVO.email}</li>
	</ul>
	<hr size="1" noshade="noshade" width="100%">
	<p>
		<img src="${pageContext.request.contextPath}/upload/${newsVO.filename}" width="200" height="200">
		${newsVO.article}
	</p>
	<hr size="1" noshade="noshade" width="100%">
	<div class="align-right">
		파일명 : ${newsVO.filename}
		<p>
		작성일 : ${newsVO.reg_date}
		<input type="button" value="수정" onclick="location.href='modifyForm.do?num=${newsVO.num}'">
		<input type="button" value="삭제" onclick="location.href='deleteForm.do?num=${newsVO.num}'">
		<input type="button" value="목록" onclick="location.href='list.do'">
	</div>
</div>
</body>
</html>