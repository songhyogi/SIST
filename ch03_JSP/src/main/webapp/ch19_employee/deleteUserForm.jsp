<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//회원제 서비스이기때문에 로그인 여부 체크
	Integer user_num = (Integer)session.getAttribute("user_num");
	if(user_num==null){//로그인이 되지 않은 경우
		response.sendRedirect("loginForm.jsp");
	}else{//로그인 된 경우
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사원정보삭제</title>
<link rel="stylesheet" href=" <%= request.getContextPath() %>/css/style.css" type="text/css">
<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-3.7.1.min.js"></script>
<script type="text/javascript">
$(function(){//$(document).ready 축약형
	//이벤트 연결
	$('#delete_form').submit(function(){
		if($('#id').val().trim()==''){
			alert('아이디를 입력하세요');
			$('#id').val('').focus();
			return false;
		}
		if($('#passwd').val().trim()==''){
			alert('비밀번호를 입력하세요');
			$('#passwd').val('').focus();
			return false;
		}
		if($('#cpasswd').val().trim()==''){
			alert('비밀번호 확인을 입력하세요');
			$('#cpasswd').val('').focus();
			return false;
		}
		//비밀번호와 비밀번호 확인 일치 여부 체크
		if($('#passwd').val() != $('#cpasswd').val()){
			alert('비밀번호와 비밀번호 확인이 불일치합니다.');
			$('#cpasswd').val('').focus();
			return false;
		}
	});
});
</script>
</head>
<body>
<div class="page-main">
	<h1>사원정보삭제</h1>
	<form id="delete_form" action="deleteUser.jsp" method="post">
		<ul>
			<li>
				<label for="id">아이디</label>
				<input type="text" name="id" id="id" maxlength="12">
			</li>
			<li>
				<label for="passwd">비밀번호</label>
				<input type="password" name="passwd" id="passwd" maxlength="12">
			</li>
			<li>
				<label for="passwd">비밀번호 확인</label>
				<input type="password" id="cpasswd" maxlength="12"> <!-- name을 명시하지 않으면 전송되지 않는다 -->
			</li>
		</ul>
		<div class="align-center">
			<input type="submit" value="사원정보삭제">
			<input type="button" value="홈으로" onclick="location.href='main.jsp'">
		</div>
	</form>
</div>
</body>
</html>
<%
	}
%>
