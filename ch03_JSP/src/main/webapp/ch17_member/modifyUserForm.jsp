<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="kr.member.dao.MemberDAO" %>
<%@ page import="kr.member.vo.MemberVO" %>
<%
	//회원제 서비스이기 때문에 로그인이 되어있는지 안 되어 있는지 체크
	Integer user_num = (Integer)session.getAttribute("user_num");
	if(user_num==null){//로그인이 되지 않은 경우
		response.sendRedirect("loginForm.jsp");//로그인 하고 다시 오도록 로그인 폼으로 보낸다.
	}else{//로그인 된 경우
%>
<!-- 로그인이 되지 않은 경우 loginForm.jsp가 보여지게 하고 로그인이 된 경우에는 html이 보여지도록 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보수정</title>
<link rel="stylesheet" href=" <%= request.getContextPath() %>/css/style.css" type="text/css">
<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-3.7.1.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$('#modify_form').submit(function(){
		const items = document.querySelectorAll('.input-check');
		for(let i=0;i<items.length;i++){
			if(items[i].value.trim()==''){
				const label = document.querySelector('label[for="'+items[i].id+'"]');
				alert(label.textContent + ' 항목은 필수 입력');
				items[i].value='';
				items[i].focus();
				return false;
			}
		}
	});
});
</script>
</head>
<body>
<%
	MemberDAO dao = MemberDAO.getInstance(); //MemberDAO로 한 건의 데이터를 읽어오려고 한다
	MemberVO member = dao.getMember(user_num);
	if(member.getPhone()==null){//phone이 null이면 빈문자열로
		member.setPhone("");
	}
%>
<div class="page-main">
	<h1>회원정보수정</h1>
	<form action="modifyUser.jsp" method="post" id="modify_form">
		<ul><!-- 아이디는 변경할 수 없으므로 아이디는 넣지 않는다 -->
			<li>
				<label for="name">이름</label> <%-- value="<%= member.getName() %>"를 통해 미리보기 --%>
				<input type="text" name="name" id="name" value="<%= member.getName() %>" class="input-check" maxlength="10">
			</li>
			<li>
				<label for="passwd">비밀번호</label>
				<input type="password" name="passwd" id="passwd" class="input-check" maxlength="12">
			</li>
			<li>
				<label for="email">이메일</label>
				<input type="email" name="email" id="email" value="<%= member.getEmail() %>" class="input-check" maxlength="50">
			</li>
			<li>
				<label for="phone">전화번호</label>
				<input type="text" name="phone" id="phone" value="<%= member.getPhone() %>"maxlength="15">
			</li>
		</ul>
		<div class="align-center">
			<input type="submit" value="수정">
			<input type="button" value="홈으로" onclick="location.href='main.jsp'">
		</div>
	</form>
</div>
</body>
</html>
<%
	}
%>
