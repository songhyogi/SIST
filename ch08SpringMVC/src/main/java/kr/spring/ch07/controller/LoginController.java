package kr.spring.ch07.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import kr.spring.ch07.service.LoginCheckException;
import kr.spring.ch07.service.LoginService;
import kr.spring.ch07.validator.LoginValidator;
import kr.spring.ch07.vo.LoginVO;

@Controller
public class LoginController {
	@Autowired
	private LoginService loginService;
	
	//유효성 체크를 위한 자바빈 초기화
	@ModelAttribute	//속성명을 명시하지 않을 경우 LoginVO를 소문자로 바꿔서 loginVO가 속성명이 되어 request에 저장한다.
	public LoginVO initCommand() {
		return new LoginVO();
	}
	
	//폼 호출
	@GetMapping("/login/login.do")
	public String form() {
		return "login/form";
	}
	//폼에서 전송된 데이터 처리
	@PostMapping("/login/login.do")
	public String submit(LoginVO loginVO,BindingResult result) {
		//유효성 체크
		new LoginValidator().validate(loginVO, result);
		//유효성 체크 결과 오류가 있으면 폼을 다시 호출
		if(result.hasErrors()) {
			return form();
		}
		
		//로그인 체크
		try {
			loginService.CheckLogin(loginVO);
			//로그인 성공한 경우
			return "redirect:/index.jsp";
		}catch(LoginCheckException e) {
			//로그인 실패한 경우
			//메시지 처리			에러코드
			result.reject("invalidIdOrPassword");//자바빈 안의 프로퍼티를 유효성체크하는데 필드가 없을 경우에는 reject라고 한다.
			return form();
		}
		
	}
}
