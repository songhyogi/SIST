<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>뉴스 삭제</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
<script type="text/javascript"><%--유효성 체크--%>
window.onload=function(){
	const myForm = document.getElementById('delete_form');
	//이벤트 연결
	myForm.onsubmit=function(){
		const passwd = document.getElementById('passwd');
		if(passwd.value.trim()==''){//공백을 입력했거나 아예 입력을 하지 않고 submit 버튼을 눌렀을 경우
			alert('비밀번호를 입력하세요');
			passwd.value='';
			passwd.focus();
			return false;
		}
	};
};
</script>
</head>
<body>
<div class="page-main">
	<h2>글 삭제</h2>
	<form id="delete_form" action="delete.do" method="post">
		<input type="hidden" name="num" value="${num}"><!-- 글 번호를 바꾸면 안되기 때문에 hidden으로 처리해야한다. -->
		<ul>
			<li>
				<label for="passwd">비밀번호</label>
				<input type="password" name="passwd" id="passwd" size="12" maxlength="12">
			</li>
		</ul>
		<div class="align-center">
			<input type="submit" value="글 삭제">
			<input type="button" value="목록" onclick="location.href='list.do'">
		</div>
	</form>
</div>
</body>
</html>