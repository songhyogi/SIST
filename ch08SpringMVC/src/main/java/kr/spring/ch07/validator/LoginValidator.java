package kr.spring.ch07.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import kr.spring.ch07.vo.LoginVO;

public class LoginValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {//유효성 체크 대상이 자바빈인지 검증
		return LoginVO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		LoginVO vo = (LoginVO)target; //LoginVO로 다운캐스팅
		if(vo.getUserId()==null || vo.getUserId().trim().isEmpty()) {
								     //필드		   에러코드
			errors.rejectValue("userId", "required");
		}
		if(vo.getPassword()==null || vo.getPassword().trim().isEmpty()) {
			errors.rejectValue("password", "required");
		}
	}

}
