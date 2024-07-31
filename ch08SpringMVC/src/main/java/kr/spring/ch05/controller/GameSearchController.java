package kr.spring.ch05.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.ch05.service.SearchService;
import kr.spring.ch05.vo.SearchVO;

@Controller
public class GameSearchController {
	@Autowired
	private SearchService searchService; //SearchSevice 의존관계
	
	@RequestMapping("/search/main.do")//main에서 get방식으로 검색하려고 한다.
	public String main() {
					//뷰 이름 지정
		return "search/main";//view이름만 있을 경우에는 String으로 view 이름 지정하면 된다.
	}
	
	@RequestMapping("/search/game.do")
	//type과 query가 여기에 전달되는데 이걸 자바빈에 담으려고 한다.
	public ModelAndView search(@ModelAttribute("vo") SearchVO searchVO) {
		System.out.println("검색어 = " + searchVO.getQuery());
		
		String result = searchService.search(searchVO);
		
		ModelAndView mav = new ModelAndView();
		//뷰 이름 지정
		mav.setViewName("search/game");
		//뷰에서 사용할 데이터 저장
		mav.addObject("searchResult", result);//request에 저장된다.
		
		return mav;//데이터가 있어서 ModelAndView에 담았다. (데이터가 없으면 String으로)
	}
}
