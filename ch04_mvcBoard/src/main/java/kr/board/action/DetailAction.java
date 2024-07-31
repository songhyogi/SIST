package kr.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.board.dao.BoardDAO;
import kr.board.vo.BoardVO;
import kr.controller.Action;

public class DetailAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//get방식이라 자동적으로 처리되기 때문에 인코딩타입을 지정할 필요 없음
		//request에서 primarykey인 num을 뽑아냄
		int num = Integer.parseInt(request.getParameter("num"));
		BoardDAO dao = BoardDAO.getInstance();
		BoardVO boardVO = dao.getBoard(num);//한건의 레코드를 자바빈에 담아서 보낸다
		
		request.setAttribute("boardVO", boardVO);//JSP와 공유하기 위해서
		//JSP 경로 반환
		return "/WEB-INF/views/detail.jsp";
	}
	
}
