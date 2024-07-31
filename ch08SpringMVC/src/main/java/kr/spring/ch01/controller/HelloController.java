package kr.spring.ch01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller //이게 없으면 모델클래스로 인식하지 않는다.
public class HelloController {
	//요청 URL과 실행 메서드 연결
	@RequestMapping("/hello.do")
	public ModelAndView hello() {//반환 타입을 ModelAndView로 함
		ModelAndView mav = new ModelAndView();
		//뷰 이름 지정
		mav.setViewName("hello");// /WEB-INF/views/hello.jsp
											//hello라고만 명시해야 한다. hello.jsp라고 명시하면 안 된다. viewResolver에 hello가 어디있는지 물어본다. 그러면 prefix와 suffix를 조합해서 전체 경로를 만들어서 제공해준다.
		//뷰에서 사용할 데이터 셋팅
		mav.addObject("greeting", "안녕하세요!");//request에 저장해준다. request를 직접 호출하지 않아도 addObject로 속성명과 속성값으로 저장된다.
		
		return mav;
	}
}
