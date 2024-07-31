package kr.item.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.item.dao.ItemDAO;
import kr.item.vo.ItemVO;
import kr.util.FileUtil;

public class AdminModifyAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num == null) {//로그인 되지 않은 경우
			return "redirect:/member/loginForm.do";
		}
		
		Integer user_auth = (Integer)session.getAttribute("user_auth");
		if(user_auth!=9) {//관리자로 로그인하지 않은 경우
			return "/WEB-INF/views/common/notice.jsp";
		}
		
		//관리자로 로그인한 경우
		//전송된 데이터 인코딩 타입 지정
		request.setCharacterEncoding("utf-8");
		int item_num = Integer.parseInt(request.getParameter("item_num"));
		
		//DB에 저장된 정보를 읽어옴
		ItemDAO dao = ItemDAO.getInstance();
		ItemVO db_item = dao.getItem(item_num);//파일을 업로드했다하면 그 전에 있던 데이터를 제거하기 위해서 한 건의 레코드를 읽어옴
		
		//전송된 데이터를 자바빈(VO)에 저장
		ItemVO item = new ItemVO();
		item.setItem_num(item_num);
		item.setName(request.getParameter("name"));
		item.setPrice(Integer.parseInt(request.getParameter("price")));
		item.setQuantity(Integer.parseInt(request.getParameter("quantity")));
		item.setDetail(request.getParameter("detail"));
		item.setPhoto1(FileUtil.createFile(request, "photo1")); //null도 아니고 빈문자열이 아니어야지만 업로드되는데 파일이 없다면 빈문자열이 전달되므로 업로드 되지 않는다.
		item.setPhoto2(FileUtil.createFile(request, "photo2"));
		item.setStatus(Integer.parseInt(request.getParameter("status")));
		
		dao.updateItem(item);
		
		if(item.getPhoto1()!=null && !"".equals(item.getPhoto1())) {//사용하지 않는 파일은 삭제
			FileUtil.removeFile(request, db_item.getPhoto1());
		}
		if(item.getPhoto2()!=null && !"".equals(item.getPhoto2())) {
			FileUtil.removeFile(request, db_item.getPhoto2());
		}
		
		request.setAttribute("notice_msg", "정상적으로 수정되었습니다.");
		request.setAttribute("notice_url", request.getContextPath()+"/item/adminModifyForm.do?item_num="+item_num);
		
		return "/WEB-INF/views/common/alert_view.jsp";
	}

}
