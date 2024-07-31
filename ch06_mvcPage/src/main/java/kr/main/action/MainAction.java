package kr.main.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.item.dao.ItemDAO;
import kr.item.vo.ItemVO;

public class MainAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//상품 목록
		ItemDAO itemDAO = ItemDAO.getInstance();
		List<ItemVO> itemList = itemDAO.getListItem(1,5,null,null, 1);//표시상품만 반환(제일 뒤 status가 0이 아니라 1이니까)
		
		request.setAttribute("itemList", itemList);
		
		//JSP 경로 반환
		return "/WEB-INF/views/main/main.jsp";
	}

}
