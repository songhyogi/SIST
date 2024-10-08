package kr.order.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.order.dao.OrderDAO;
import kr.order.vo.OrderVO;

public class UserModifyFormAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num==null) {
			return "redirect:/member/loginForm.do";
		}
		
		int order_num = Integer.parseInt(request.getParameter("order_num"));
		OrderDAO dao = OrderDAO.getInstance();
		//주문정보 호출
		OrderVO order = dao.getOrder(order_num);//한건의 레코드를 구함
		if(order.getMem_num() != user_num) {//get방식으로 호출하면 구매자와 로그인한한 사람이 달라도 들어올 수 있기 때문에 조건체크해줘야 한다.
			//구매자 회원번호와 로그인한 회원번호가 불일치할 경우
			return "/WEB-INF/views/common/notice.jsp";
		}
		
		request.setAttribute("order", order);
		
		return "/WEB-INF/views/order/user_modifyForm.jsp";
	}

}
