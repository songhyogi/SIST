package kr.spring.ch03.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.spring.ch03.service.NewArticleService;
import kr.spring.ch03.vo.NewArticleVO;

@Controller
public class NewArticleController {//같은 컨트롤러 안에 동일한 URL을 넣으면 안 된다. 하지만 똑같이한 이유는 폼을 요청할 때는 get방식으로 요청할 거고 폼에서 보낸 데이터는 post로 처리할거다.
	@Autowired												
	private NewArticleService newArticleService;//멤버변수를 만들어서 타입 체크
																//@Autowired를 필드에 넣어주면 @Autowired가 set메서드를 자동으로 만들어주기 때문에 따로 굳이 set메서드를 만들지 않아도 된다.
									
	//폼을 호출하는 메서드
	//GET 요청이 들어올 때 호출
	@GetMapping("/article/newArticle.do")//요청 URL
	public String form() {//호출하는 메서드
						//뷰 이름
		return "article/newArticleForm";//문자열로 view이름을 전송 DispatcherServlet이 문자열을 받으면 뷰 이름이구나라고 인식한다.
	}
	
	/*
	 * @ModelAttribute 어노테이션을 이용해서 전송된 데이터를 자바빈에 담기
	 * [기능]
	 * 1. 자바빈(VO) 생성
	 * 2. 전송된 데이터를 자바빈에 저장
	 * 3. View에서 사용할 수 있도록 request에 자바빈(VO)를 저장 
	 * [형식]
	 * 1. @ModelAttribute(속성명) NewArticleVO vo
	 * 		지정한 속성명으로 JSP에서 request에 접근해서 자바빈(VO) 호출 가능.
	 * 		예) ${속성명.title}
	 * 2. @ModelAttribute를 명시할 때 속성명을 생략할 수 있음
	 * 		속성명을 생략하면 클래스명의 첫 글자를 소문자로 속성명을 자동 생성
	 * 		예) ModelAttribute NewArticleVO vo
	 * 			${newArticleVO.title}
	 * 3. @ModelAttirbute 생략
	 * 		호출 메서드에 인자명만 명시
	 * 		예)NewArticleVO vo와 같이 인자명만 명시.
	 * 		request에 저장되는 속성명은 newArticleVO로 저장됨
	 */
	
	//POST 요청이 들어올 때 호출
	@PostMapping("/article/newArticle.do")
	public String submit(NewArticleVO vo) {
		
		newArticleService.writeArticle(vo);
		
		return "article/newArticleSumitted";
	}
}
