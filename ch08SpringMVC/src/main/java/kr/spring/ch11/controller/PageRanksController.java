package kr.spring.ch11.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.ch11.vo.PageRank;

@Controller
public class PageRanksController {
	//엑셀 다운로드
	@RequestMapping("/pageRanksExcel.do")
	public ModelAndView handel() {
		List<PageRank> pageRanks = new ArrayList<PageRank>();//데이터를 여러 개 생성하여 ArrayList에 보관
		pageRanks.add(new PageRank(1,"/board/list.do"));
		pageRanks.add(new PageRank(2,"/member/login.do"));
		pageRanks.add(new PageRank(3,"/cart/list.do"));
											// 뷰 이름			속성명			속성값
		return new ModelAndView("pageRanks","pageRanks",pageRanks);
	}
	//JSON 문자열 처리
	@RequestMapping("/pageJson.do")
	@ResponseBody //뷰를 자동적으로 만들어준다. 우리가 뷰를 지정할 필요가 없다.
	public List<PageRank> parseJson(){
		List<PageRank> pageRanks = new ArrayList<PageRank>();
		pageRanks.add(new PageRank(1,"/file.do"));
		pageRanks.add(new PageRank(2,"pageRanksExcel.do"));
		pageRanks.add(new PageRank(3,"/pageJson.do"));
		
		return pageRanks;//리스트를 반환 
	}
}
