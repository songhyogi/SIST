package kr.news.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.news.dao.NewsDAO;
import kr.news.vo.NewsVO;
import kr.util.PagingUtil;

public class ListAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//선택한 페이지 반환
		String pageNum = request.getParameter("pageNum");
		if(pageNum==null) pageNum = "1"; //pageNum이 null일 경우 1페이지로 간주
		
		NewsDAO dao = NewsDAO.getInstance();
		int count = dao.getCount(); //총 count를 읽어옴
		
		PagingUtil page = new PagingUtil(Integer.parseInt(pageNum),count,10,10,"list.do");
		
		List<NewsVO> list = null;
		if(count > 0) {
			list = dao.getList(page.getStartRow(), page.getEndRow());
		}
		//request에 데이터를 저장
		request.setAttribute("count", count);
		request.setAttribute("list", list);
		request.setAttribute("page", page.getPage());
		
		//JSP 경로 반환
		return "/WEB-INF/views/list.jsp";
	}
	
}
