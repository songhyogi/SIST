$(function(){
	/*-------------------
	 *		회원가입	
	 *-------------------*/
	//아이디 중복 여부 저장 변수 : 0은 아이디 중복 또는 중복 체크 미실행, 1은 아이디 미중복
	let checkId = 0;
	
	//아이디 중복 체크
	$('#confirmId').click(function(){
		if($('#id').val().trim()==''){
			$('#message_id').css('color','red').text('아이디를 입력하세요');
			$('#id').val('').focus();
			return;
		}
		
		$('#message_id').text('');//메세지 초기화
		
		//서버와 통신
		$.ajax({
			url:'confirmId',
			type:'get',
			data:{id:$('#id').val()},
			dataType:'json',
			success:function(param){
				if(param.result == 'idNotFound'){
					checkId = 1;
					$('#message_id').css('color','#000').text('등록 가능 ID');
				}else if(param.result == 'idDuplicated'){
					checkId = 0;
					$('#message_id').css('color','red').text('중복된 ID');
					$('#id').val('').focus();//다시 검색하게 유도
				}else if(param.result == 'notMatchPattern'){
					checkId = 0;
					$('#message_id').css('color','red').text('영문,숫자 4자~12자 입력');
					$('#id').val('').focus();
				}else{
					checkId = 0;
					alert('ID 중복 체크 오류');
				}
			},
			error:function(){
				checkId = 0;
				alert('네트워크 오류 발생');//이클립스 콘솔을 봐야 한다. 이클립스 콘솔에 오류문구가 없으면 브라우저의 네트워크에 오류문구가 있는지 확인
			}
		});
	});//end of click
	
	//아이디 중복 안내 메세지 초기화 및 아이디 중복 값 초기화
	$('#member_register #id').keydown(function(){
		checkId = 0;
		$('#message_id').text('');
	});//end of keydown
	
	//submit 이벤트 발생 시 아이디 중복 체크 여부 확인
	$('#member_register').submit(function(){
		if(checkId==0){//여기에 진입하면 submit이 안 되게 return false
			$('#message_id').css('color','red').text('아이디 중복 체크 필수!');
			if($('#id').val().trim()==''){//공백이 있을 경우 공백 지우고 포커스
				$('#id').val('').focus();
			}
			return false;
		}
	});//end of submit
	
});