<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="kr.member.dao.MemberDAO" %>
<%
	//회원제 서비스이기 때문에 로그인 여부 확인
	Integer user_num = (Integer)session.getAttribute("user_num");
	if(user_num==null){//로그인이 되지 않은 경우
		response.sendRedirect("loginForm.jsp");
	}else{//로그인 된 경우 
		//전송된 데이터 인코딩 타입 지정
		request.setCharacterEncoding("utf-8");
%>
<jsp:useBean id="member" class="kr.member.vo.MemberVO"/>
<jsp:setProperty property="*" name="member"/>
<%
	//num이 전송되지 않았기 때문에 session에 저장된 num을 사용
	member.setNum(user_num);

	MemberDAO dao = MemberDAO.getInstance();
	dao.updateMember(member);
%>
<!--  로그인이 된 경우 해당페이지가 보여지게  -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보수정 완료</title>
<link rel="stylesheet" href=" <%= request.getContextPath() %>/css/style.css" type="text/css">
</head>
<body>
<div class="page-main">
	<h1>회원정보수정 완료</h1>
	<div class="result-display">
		<div class="align-center">
			회원정보수정 완료!<p> <!-- 정상적으로 처리되었다면 이 화면이 보여지고 오류가 나면 에러페이지가 보인다. -->
			<button onclick="location.href='myPage.jsp'">MyPage</button> <!-- 같은 경로이기 때문에 myPage.jsp만 명시 -->
		</div>
	</div>
</div>
</body>
</html>		
<%
	}
%>
