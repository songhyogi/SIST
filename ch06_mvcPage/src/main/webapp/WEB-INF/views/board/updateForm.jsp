<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 수정</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
<script type="text/javascript">
window.onload=function(){
	const myForm = document.getElementById('update_form');
	//이벤트 연결
	myForm.onsubmit=function(){
		const title = document.getElementById('title');
		if(title.value.trim()==''){
			alert('제목을 입력하세요');
			title.value = '';
			title.focus();
			return false;
		}
		const content = document.getElementById('content');
		if(content.value.trim()==''){
			alert('내용을 입력하세요');
			content.value = '';
			content.focus();
			return false;
		}
	};
};
</script>
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
	<div class="content-main">
		<h2>글 수정</h2>														<!-- 파일 업로드하는 부분이 있기 때문에 반드시 enctype명시 -->
		<form id="update_form" action="update.do" method="post" enctype="multipart/form-data">
			<!-- session에 board_num이 없기 때문에 hidden값으로 board_num을 넘겨줘야 한다. primarykey가 있어야 수정(업데이트)이 됨 -->
			<input type="hidden" name="board_num" value="${board.board_num}">
			<ul>
				<li>
					<label for="title">제목</label>
					<input type="text" name="title" id="title" value="${board.title}" maxlength="50"><!-- 미리 보기 위해서 value 추가 -->
				</li>
				<li>
					<label for="content">내용</label>
					<textarea rows="5" cols="40" name="content" id="content">${board.content}</textarea>
				</li>
				<li>
					<label for="filename">이미지</label><!-- 파일 업로드는 필수 입력사항은 아님 -->
					<input type="file" name="filename" id="filename" accept="image/gif,image/png,image/jpeg">
					<c:if test="${!empty board.filename}"><!-- 이미지가 없는 경우도 있기 때문에 조건체크 -->
					<div id="file_detail">
					<img src="${pageContext.request.contextPath}/upload/${board.filename}" width="100">
					<br>
					<input type="button" value="파일 삭제" id="file_del"><!-- 수정 시 파일만 삭제하고 싶을 경우 ajax로 처리하려고 함 -->
					</div>
					<!-- 위 input 파일 삭제 부분이 인식되는 상태에서 스크립트를 넣어야 해서 if블럭 안에 스크립트를 넣어야 한다. -->
					<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
					<script type="text/javascript">
						$(function(){
							$('#file_del').click(function(){
								let choice = confirm('삭제하시겠습니까?');
								if(choice){
									//서버와 통신
									$.ajax({
										url:'deleteFile.do',
										type:'post',         //el(jsp이기 때문에 el 사용 가능)
										data:{board_num:${board.board_num}},
										dataType:'json',
										success:function(param){
											if(param.result == 'logout'){
												alert('로그인 후 사용하세요');
											}else if(param.result == 'success'){//성공적으로 삭제가 된 거니까 이미지가 안 보이게 처리
												$('#file_detail').hide();
											}else if(param.result == 'wrongAccess'){//타인의 글을 삭제하려고 시도한 경우
												alert('잘못된 접속입니다.');
											}else{//오타가 생긴 경우
												alert('파일 삭제 오류 발생');
											}
										},
										error:function(){
											alert('네트워크 오류 발생');
										}
									});
								}
							});
						});
					</script>
					</c:if>
				</li>
			</ul>
			<div class="align-center">
				<input type="submit" value="글 수정">
				<input type="button" value="목록" onclick="location.href='list.do'"><!-- 같은 경로이기 때문에 파일명만 명시 -->
			</div>
		</form>
	</div>
</div>
</body>
</html>