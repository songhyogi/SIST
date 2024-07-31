package kr.spring.ch05.service;

import kr.spring.ch05.vo.SearchVO;

public class SearchService {
	public String search(SearchVO vo) {
		System.out.println(vo); //콘솔에 출력
		
		return "검색 완료!!";
	}
}
