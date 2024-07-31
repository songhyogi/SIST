package kr.spring.ch07.service;

import kr.spring.ch07.vo.LoginVO;

public class LoginService {
	public void CheckLogin(LoginVO vo)throws LoginCheckException {
		//테스트용으로 userId와 password가 일치하면 로그인 처리
		if(!vo.getUserId().equals(vo.getPassword())) {//일치하지 않으면 여기로 진입
			System.out.println("인증 에러 - " + vo.getUserId());
			throw new LoginCheckException(); //메서드이기 때문에 try~catch하지 않고 throw를 사용 CheckLogin이 호출되는 곳에서 try~catch를 사용하려고 한다.
		}
	}
}
