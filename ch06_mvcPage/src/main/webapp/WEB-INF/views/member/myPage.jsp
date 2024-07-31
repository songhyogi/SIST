<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Page</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
<script type="text/javascript">
$(function(){
	$('#photo_btn').click(function(){
		$('#photo_choice').show();
		$(this).hide(); //수정 버튼 감추기
	});
	
	//이미지 미리 보기
	let photo_path = $('.my-photo').attr('src');//처음 화면에 보여지는 이미지 읽기	
	$('#photo').change(function(){
		let my_photo = this.files[0];//업로드한 파일 정보
		if(!my_photo){
			//선택을 취소하면 원래 처음 화면으로 되돌림
			$('.my-photo').attr('src',photo_path);
			return;
		}
		if(my_photo.size > 1024*1024){
			alert(Math.round(my_photo.size/1024) + 'kbytes(1024kbytes까지만 업로드 가능)');
			$('.my-photo').attr('src',photo_path);//사이즈를 초과하면 원래 사진으로 되돌림
			$(this).val('');//선택한 파일 정보 지우기
			return;
		}
		//화면에 이미지 미리 보기
		const reader = new FileReader();
		reader.readAsDataURL(my_photo);
		
		reader.onload=function(){
			$('.my-photo').attr('src',reader.result);	
		};//화면에 이미지 미리보기를 먼저 본 다음에 전송
	});//end of change
	
	//이미지 전송
	$('#photo_submit').click(function(){
		if($('#photo').val()==''){//선택하지 않았으면 파일이 비어있고 선택하면 파일이 있음
			alert('파일을 선택하세요');
			$('#photo').focus();
			return;
		}
		//파일 전송
		const form_data = new FormData();
		//업로드할 파일은 $('#photo').files[0]을 호출할 수 없음 
		//$('#photo')[0].files[0] 또는 
		//document.getElementById('photo').files[0]
		//형식으로 호출 가능
		form_data.append('photo',$('#photo')[0].files[0]);//FormData객체에 담아서 보내야 한다.
		
		$.ajax({
			url:'updateMyPhoto.do',
			type:'post',
			data:form_data,
			dataType:'json',
			contentType:false, //데이터 객체를 문자열로 바꿀지 설정. true이면 일반문자. true가 기본값 (false이면 파일이 섞여있다는 얘기)
			processData:false, //해당 타입을 true로 하면 일반 text로 구분. true가 기본값 (false이면 파일이 섞여있다는 얘기)
			success:function(param){
				if(param.result == 'logout'){
					alert('로그인 후 사용하세요');
				}else if(param.result == 'success'){
					alert('프로필 사진이 수정되었습니다.');
					//수정된 이미지 정보 저장
					photo_path = $('.my-photo').attr('src');
					$('#photo_choice').hide();
					$('#photo_btn').show();
				}else{//오타일 경우 여기로 옴
					alert('파일 전송 오류 발생');
				}
			},
			error:function(){
				alert('네트워크 오류 발생');
			}
		});
		
	});//end of click
	
	//이미지 미리보기 취소
	$('#photo_reset').click(function(){
		//초기 이미지 표시
		$('.my-photo').attr('src',photo_path);//이미지 미리보기 전 이미지로 되돌리기
		$('#photo').val('');
		$('#photo_choice').hide();
		$('#photo_btn').show();//수정 버튼 표시
	});
});
</script>
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<div class="content-main">
		<h2>회원정보</h2>
		<div class="mypage-div">
			<h3>프로필 사진</h3>
			<ul>
				<li>
					<c:if test="${empty member.photo}"><!-- memberdao에서 member.setPhoto(rs.getString("photo"))라고 photo를 저장해뒀는데 없다면 기본이미지를 보여줌  -->
						<img src="${pageContext.request.contextPath}/images/face.png" width="200" height="200" class="my-photo">
					</c:if>
					<c:if test="${!empty member.photo}"><!-- member.photo가 있는 경우 -->
						<img src="${pageContext.request.contextPath}/upload/${member.photo}" width="200" height="200" class="my-photo">
					</c:if>
				</li>
				<li>
					<div class="align-center">
						<input type="button" value="수정" id="photo_btn">
					</div>
					<div id="photo_choice" style="display:none;">
						<input type="file" id="photo" accept="image/gif,image/png,image/jpeg">
						<input type="button" value="전송" id="photo_submit"><!-- ajax통신으로 할 것이기 때문에 submit이 아니라 button으로 -->
						<input type="button" value="취소" id="photo_reset">
					</div>
				</li>
			</ul>
			<h3>연락처 <input type="button" value="연락처 수정" onclick="location.href='modifyUserForm.do'"></h3>
			<ul>
				<li>아이디 : ${member.id}</li>
				<li>이름 : ${member.name}</li>
				<li>전화번호 : ${member.phone}</li>
				<li>이메일 : ${member.email}</li>
				<li>우편번호 : ${member.zipcode}</li>
				<li>주소 : ${member.address1} ${member.address2}</li>
				<li>가입일 : ${member.reg_date}</li>
				<c:if test="${!empty member.modify_date}">
				<li>최근 정보 수정일 : ${member.modify_date}</li>
				</c:if>
			</ul>
			<h3>비밀번호 수정 <input type="button" value="비밀번호 수정" onclick="location.href='modifyPasswordForm.do'"></h3>
			<h3>회원탈퇴 <input type="button" value="회원탈퇴" onclick="location.href='deleteUserForm.do'"></h3>
		</div><!-- 왼쪽에 보여지는 영역 끝 -->
		<div class="mypage-div">
			<h3>관심 게시물 목록</h3>
			<table>
				<tr>
					<th>제목</th>
					<th>작성자</th>
					<th>등록일</th>
				</tr>
				<c:forEach var="board" items="${boardList}">
				<tr>																																	<!-- 새 탭을 열면서 띄움 -->
					<td><a href="${pageContext.request.contextPath}/board/detail.do?board_num=${board.board_num}" target="_blank">${fn:substring(board.title,0,12)}</a></td><!-- functions라이브러리를 사용해서 제목이 길 경우 줄여서 12자만 표시-->
					<td>${board.id}</td>
					<td>${board.reg_date}</td>
				</tr>
				</c:forEach>
			</table>
			<h3>상품구매목록 <input type="button" value="더보기" onclick="location.href='${pageContext.request.contextPath}/order/orderList.do'"></h3>
			<table>
				<tr>
					<th>주문번호</th>
					<th>상품명</th>
					<th>주문날짜</th>
					<th>주문상태</th>
				</tr>
				<c:forEach var="order" items="${orderList}">
				<tr>
					<td>${order.order_num}</td>
					<td><a href="${pageContext.request.contextPath}/order/orderDetail.do?order_num=${order.order_num}">${order.item_name}</a></td>
					<td>${order.reg_date}</td>
					<td>
						<c:if test="${order.status == 1}">배송대기</c:if>
						<c:if test="${order.status == 2}">배송준비중</c:if>
						<c:if test="${order.status == 3}">배송중</c:if>
						<c:if test="${order.status == 4}">배송완료</c:if>
						<c:if test="${order.status == 5}">주문취소</c:if>
					</td>
				</tr>
				</c:forEach>
			</table>
		</div>
		<div class="mypage-end"></div>
	</div>
</div>
</body>
</html>