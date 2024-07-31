package kr.spring.ch06.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import kr.spring.ch06.vo.MemberVO;

public class MemberValidator implements Validator{
	//Validator가 검증할 수 있는 타입인지를 검사
	@Override
	public boolean supports(Class<?> clazz) {
		return MemberVO.class.isAssignableFrom(clazz);
	}
	
	//유효성 체크를 수행하는 메서드
	//target : 검증하는 자바빈(VO) 객체
	//errors : 에러 정보를 담는 객체
	@Override
	public void validate(Object target, Errors errors) {//target에 MemberVO가 전달됨, Errors에 BindingResult객체가 전달됨 Errors는 BindingResult의 부모
		MemberVO vo = (MemberVO)target;
		if(vo.getId() == null || vo.getId().trim().isEmpty()) {
							      //필드     에러코드
			errors.rejectValue("id", "required");
						//필드(자바빈의 프로퍼티(멤버변수)), 에러코드, 에러문구
			//errors.rejectValue("id", null, "아이디는 필수 항목");//errors.rejectValue() : 아이디가 비어있을 경우 errors 객체에 넣음. 에러 코드는 당장 안 쓸 것이기 때문에 null로
		}
		if(vo.getName() == null || vo.getName().trim().isEmpty()) {
			errors.rejectValue("name", "required");
			//errors.rejectValue("name", null, "이름은 필수 항목");
		}
		if(vo.getZipcode() == null || vo.getZipcode().trim().isEmpty()) {
			errors.rejectValue("zipcode", "required");
			//errors.rejectValue("zipcode", null, "우편번호는 필수 항목");
		}
		if(vo.getAddress1() == null || vo.getAddress1().trim().isEmpty()) {
			errors.rejectValue("address1", "required");
			//errors.rejectValue("address1", null, "주소는 필수 항목");
		}
		if(vo.getAddress2() == null || vo.getAddress2().trim().isEmpty()) {
			errors.rejectValue("address2", "required");
			//errors.rejectValue("address2", null, "상세주소는 필수 항목");
		}
	}
	
}
