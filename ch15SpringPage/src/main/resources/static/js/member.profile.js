$(function(){
	/*-----------------------------
	* MY페이지 프로필 사진 등록 및 수정
	------------------------------*/
	//수정 버튼 이벤트 처리
	$('#photo_btn').click(function(){
		$('#photo_choice').show();
		$(this).hide();
	});
	
	//처음 화면에 보여지는 이미지 읽기
	let photo_path = $('.my-photo').attr('src');
	let my_photo; //업로드하고자 선택한 이미지 저장
	//파일 선택 이벤트 연결
	$('#upload').change(function(){
		my_photo = this.files[0];//선택한 이미지 저장
		if(!my_photo){
			$('.my-photo').attr('src',photo_path);
			return;
		}
		
		if(my_photo.size > 1024*1024){
			alert(Math.round(my_photo.size/1024)+'kbytes(1024kybtes까지만 업로드 가능)');
			$('.my-photo').attr('src',photo_path);//1024kbytes를 넘었다면 원상복귀
			$(this).val('');
			return;
		}
		
		//이미지 미리보기 처리
		const reader = new FileReader();//자바스크립트에서는 객체가 생성될 때 객체의 주소를(객체의 정보를) 보관하게 되는데 주소를 못 바꾸게 하기 위해서 const를 선호한다.
		reader.readAsDataURL(my_photo);
		
		reader.onload=function(){
			$('.my-photo').attr('src',reader.result);
		};
	});//end of change
	
	//파일 업로드 처리
	$('#photo_submit').click(function(){
		if($('#upload').val==''){
			alert('파일을 선택하세요!');
			$('#upload').focus();
			return;
		}
		//서버에 전송할 파일 선택
		const form_data = new FormData();//파일을 Ajax 방식으로 전송하기 때문에 FormData을 생성해야 한다.
		form_data.append('upload',my_photo);//반드시 파일업로드 파라미터네임은 반드시 upload로 지정해야 한다. upload라고 하지 않으면 자바빈에서 set메서드가 동작하지 않는다.
		
		//서버와 통신
		$.ajax({
			url:'../member/updateMyPhoto',
			type:'post',
			data:form_data,
			dataType:'json',
			contentType:false,
			processData:false,
			success:function(param){
				if(param.result == 'logout'){
					alert('로그인 후 사용하세요');
				}else if(param.result == 'success'){
					alert('프로필 사진이 수정되었습니다.');
					//교체된 이미지 저장
					photo_path = $('.my-photo').attr('src');//다시 작업할 수 있기 때문에 현재 교체된 이미지 저장
					$('#upload').val('');
					$('#photo_choice').hide();
					$('#photo_btn').show();
				}else{
					alert('파일 전송 오류 발생');
				}
			},
			error:function(){
				alert('네트워크 오류 발생');
			}
		});
	});//end of click - 파일 전송
	//취소 버튼 처리
	$('#photo_reset').click(function(){//초기화
		$('.my-photo').attr('src',photo_path);
		$('#upload').val('');
		$('#photo_choice').hide();
		$('#photo_btn').show();
	});//end of click - 취소 버튼 처리
});