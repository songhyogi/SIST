<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="kr.employee.dao.EmployeeDAO" %>
<%@ page import="kr.employee.vo.EmployeeVO" %>
<%
	//회원제 서비스라 로그인이 안 되어있는데 이 페이지로 오면 안되니까 로그인여부 확인
	//user_num과 user_id 모두 session에 들어 있기 때문에 이번에는 user_id를 사용할거라 user_id로 로그인 여부 체크
	String user_id = (String)session.getAttribute("user_id");
	if(user_id==null){//로그인이 되지 않은 경우
		response.sendRedirect("loginForm.jsp");
	}else{//로그인 된 경우
	//전송된 데이터 인코딩 타입 지정
	request.setCharacterEncoding("utf-8");
	//전송된 데이터 반환
	String id = request.getParameter("id");
	String passwd = request.getParameter("passwd");
	//cpasswd(비밀번호확인)의 경우 데이터를 전송받지 않는다. 애초에 cpasswd에 name을 넣지 않았다.
	
	//id, 비밀번호 일치 여부 체크
	EmployeeDAO dao = EmployeeDAO.getInstance();
	EmployeeVO employee = dao.checkEmployee(id);
	boolean check = false;
	
	//아이디가 등록되어 있고 로그인한 아이디와 일치할 경우
	if(employee!=null && user_id.equals(id)){
		//비밀번호 일치 여부 체크
		check = employee.isCheckedPassword(passwd);
	}
	if(check){//인증 성공
		//회원 정보 삭제
		dao.deleteEmployee(employee.getNum());
		//로그아웃 - 회원탈퇴했기 때문에 계속 로그인되어있으면 안 된다.
		session.invalidate();
%>
<!-- 성공적으로 회원탈퇴했을 경우 이 페이지를 보여준다. -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사원정보삭제</title>
<link rel="stylesheet" href=" <%= request.getContextPath() %>/css/style.css" type="text/css">
</head>
<body>
<div class="page-main">
	<h1>사원정보삭제 완료</h1>
	<div class="result-display">
		사원정보삭제가 완료되었습니다. <p>
		<button onclick="location.href='main.jsp'">홈으로</button>
	</div>
</div>
</body>
</html>
<%
		}else{//아이디 또는 비밀번호가 불일치
%>
	<script type="text/javascript">
		alert('아이디 또는 비밀번호가 불일치합니다.');
		history.go(-1);
	</script>
<%			
		}
	}
%>
