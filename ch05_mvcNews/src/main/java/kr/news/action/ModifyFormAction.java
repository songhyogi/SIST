package kr.news.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.news.dao.NewsDAO;
import kr.news.vo.NewsVO;

public class ModifyFormAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int num = Integer.parseInt(request.getParameter("num"));
		NewsDAO dao = NewsDAO.getInstance();
		NewsVO newsVO = dao.getNews(num);
		
		request.setAttribute("newsVO", newsVO);
		//JSP 경로 반환
		return "/WEB-INF/views/modifyForm.jsp";
	}

}
