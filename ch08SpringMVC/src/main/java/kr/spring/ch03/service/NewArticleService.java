package kr.spring.ch03.service;

import kr.spring.ch03.vo.NewArticleVO;

public class NewArticleService {
	public void writeArticle(NewArticleVO vo) {
		System.out.println("신규 게시글 등록 : " + vo); //vo가 여기서 출력되게. 컨트롤러에서 주입받아야 한다. 
	}
}
