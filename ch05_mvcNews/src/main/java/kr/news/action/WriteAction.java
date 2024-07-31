package kr.news.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.news.dao.NewsDAO;
import kr.news.vo.NewsVO;
import kr.util.FileUtil;

public class WriteAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//전송된 데이터 인코딩 타입 지정
		request.setCharacterEncoding("utf-8");
		
		//자바빈(VO) 생성
		NewsVO vo = new NewsVO();
		vo.setTitle(request.getParameter("title"));
		vo.setWriter(request.getParameter("writer"));
		vo.setPasswd(request.getParameter("passwd"));
		vo.setEmail(request.getParameter("email"));
		vo.setArticle(request.getParameter("article"));
																//파라미터명 (파일명을 명시하는 것이 아니다.)
		vo.setFilename(FileUtil.createFile(request, "filename"));//이미 utility클래스를 만들었기 때문에 호출한 후에 데이터를 넘기기만 하면 된다.
																				//이걸 호출하는 순간에 업로드 경로에 사진이 올라간다.
		
		NewsDAO dao = NewsDAO.getInstance();
		dao.registerNews(vo);
		
		//JSP 경로 반환
		return "/WEB-INF/views/write.jsp";
	}

}
