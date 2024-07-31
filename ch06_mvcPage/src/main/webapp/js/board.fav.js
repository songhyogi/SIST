$(function(){
	/*======================
	 좋아요 선택 여부와 선택한 총 개수 읽기
	========================*/
	function selectFav(){
		//서버와 통신
		$.ajax({
			url:'getFav.do',
			type:'post',
			data:{board_num:$('#output_fav').attr('data-num')},//detail.jsp img태그에 커스텀 속성으로 data-num을 만들어놨다. img id로 읽어옴
			dataType:'json',
			success:function(param){
				displayFav(param);
			},
			error:function(){
				alert('네트워크 오류 발생');
			}
		});
	}
	
	/*======================
	 좋아요 등록(및 삭제) 이벤트 연결
	========================*/
	$('#output_fav').click(function(){
		//서버와 통신
		$.ajax({
			url:'writeFav.do',
			type:'post',
			data:{board_num:$(this).attr('data-num')},//this가 이벤트가 발생한 태그를 의미. this로 부터 data-num을 읽어옴
			dataType:'json',
			success:function(param){
				if(param.result == 'logout'){
					alert('로그인 후 좋아요를 눌러주세요!');
				}else if(param.result == 'success'){
					displayFav(param);
				}else{
					alert('좋아요 등록/삭제 오류 발생');
				}
			},
			error:function(){
				alert('네트워크 오류 발생');	
			}
		});
	});
	
	/*======================
	 좋아요 표시 함수
	========================*/
	function displayFav(param){
		let output;
		if(param.status == 'yesFav'){//좋아요 선택
			output = '../images/fav02.gif';//.js 파일이기 때문에 el로 pageContext.request.contextPath를 사용할 수 없다. 컨텍스트 경로를 직접 명시하면 컨텍스트 명이 바뀔 위험이 있기 때문에 ..으로 상위폴더를 명시했다.
		}else{//좋아요 미선택
			output = '../images/fav01.gif';
		}
		//문서 객체에 설정
		$('#output_fav').attr('src',output);
		$('#output_fcount').text(param.count);
	}
	/*======================
	 초기 데이터 호출
	========================*/
	selectFav();
});