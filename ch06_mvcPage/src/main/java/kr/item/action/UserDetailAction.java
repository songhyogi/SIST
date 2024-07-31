package kr.item.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.item.dao.ItemDAO;
import kr.item.vo.ItemVO;
import kr.util.StringUtil;

public class UserDetailAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//상품 번호 반환
		int item_num = Integer.parseInt(request.getParameter("item_num"));
		ItemDAO dao = ItemDAO.getInstance();
		ItemVO item = dao.getItem(item_num);//한 건의 데이터를 읽어와서 자바빈에 담아서 가져옴
		
		//상품설명 줄바꿈 처리(HTML 태그 허용)
		item.setDetail(StringUtil.useBrHTML(item.getDetail()));//HTML을 허용한 이유는 관리자만 상품을 등록하기 때문에 태그를 넣을 우려가 적기 때문
		
		request.setAttribute("item", item);
		
		return "/WEB-INF/views/item/user_detail.jsp";
	}

}
