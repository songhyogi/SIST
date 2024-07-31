<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>[실습]회원가입</title>
<script type="text/javascript">
	window.onload=function(){
		const myForm = document.getElementById('myForm');
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
						/* id가 id인것만 체크하기 위해 논리연산자 사용 */
				if(items[i].id == 'id' && !/^[A-Za-z0-9]{4,12}$/.test(items[i].value)){
					alert('영문 또는 숫자 사용, 최소 4자 ~ 최대 12자 사용');
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
<%--
이름(name),아이디(id),비밀번호(password),전화번호(phone),
취미(hobby) - 영화보기,음악감상,등산,낚시,춤
자기소개(content)

s06_register.jsp로 전송, 전송 방식 post

출력예시
이름 : 홍길동        * 필수 입력
아이디 : dragon    * 필수 입력
비밀번호 : 1234     * 필수 입력
전화번호 : 010-1234-5678
자기소개 : 서울에서 태어나서 계속 서울 거주
 --%>
<form action="s06_register.jsp" method="post" id="myForm">
	<ul>
		<li>
			<label for="name">이름</label>
			<input type="text" name="name" id="name" class="input-check">
		</li>
		<li>
			<label for="id">아이디</label>
			<input type="text" name="id" id="id" class="input-check">
		</li>
		<li>
			<label for="password">비밀번호</label>
			<input type="password" name="password" id="password" class="input-check">
		</li>
		<li>
			<label for="phone">전화번호</label>
			<input type="text" name="phone">
		</li>
		<li>
			<label>취미</label>
			<input type="checkbox" name="hobby" value="영화보기">영화보기
			<input type="checkbox" name="hobby" value="음악감상">음악감상
			<input type="checkbox" name="hobby" value="등산">등산
			<input type="checkbox" name="hobby" value="낚시">낚시
			<input type="checkbox" name="hobby" value="춤">춤
		</li>
		<li>
			<label for="content">자기소개</label>
			<textarea rows="5" cols="60" name="content"></textarea>
		</li>
	</ul>
	<input type="submit" value="전송">
</form>
</body>
</html>