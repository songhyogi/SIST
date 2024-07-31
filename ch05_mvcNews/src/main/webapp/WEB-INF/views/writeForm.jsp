<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>뉴스 등록</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
<script type="text/javascript">
window.onload=function(){
	const myForm = document.getElementById('write_form');
	//이벤트 연결
	myForm.onsubmit=function(){
		const items = document.querySelectorAll('.input-check');
		for(let i=0;i<items.length;i++){
			if(items[i].value.trim()==''){
				const label = document.querySelector('label[for="'+items[i].id+'"]');
				alert(label.textContent + ' 항목은 필수 입력');
				items[i].value = '';
				items[i].focus();
				return false;
			}
		}
	};
};
</script>
</head>
<body>
<div class="page-main">
	<h2>뉴스 등록</h2>
	<form id="write_form" action="write.do" method="post" enctype="multipart/form-data"><!-- 파일 등록이 있기 때문에 enctype 명시 -->
		<ul>
			<li>
				<label for="title">제목</label>
				<input type="text" name="title" id="title" size="30" maxlength="50" class="input-check"><!-- 유효성체크할 때 쓰려고 클래스를 넣었다. -->
			</li>
			<li>
				<label for="writer">작성자</label>
				<input type="text" name="writer" id="writer" size="10" maxlength="10" class="input-check">
			</li>
			<li>
				<label for="passwd">비밀번호</label>
				<input type="password" name="passwd" id="passwd" size="12" maxlength="12" class="input-check">
			</li>
			<li>
				<label for="email">이메일</label>
				<input type="email" name="email" id="email" size="30" maxlength="50" class="input-check">
			</li>
			<li>
				<label for="article">내용</label>
				<textarea rows="5" cols="40" name="article" id="article" class="input-check"></textarea>
			</li>
			<li>
				<label for="filename">사진</label>
				<input type="file" name="filename" id="filename" accept="image/gif,image/png,image/jpeg" class="input-check">
			</li>
		</ul>
		<div class="align-center">
			<input type="submit" value="등록">
			<input type="button" value="목록" onclick="location.href='list.do'">
		</div>
	</form>
</div>
</body>
</html>