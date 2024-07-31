package kr.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.board.dao.BoardDAO;
import kr.board.vo.BoardVO;
import kr.controller.Action;
import kr.util.StringUtil;

public class UpdateFormAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(); 
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num == null) {//로그인이 되지 않은 경우
			return "redirect:/member/loginForm.do";
		}
		//로그인 된 경우
		int board_num = Integer.parseInt(request.getParameter("board_num"));//한건의 레코드를 읽어오기 위해 board_num을 넘김
		BoardDAO dao = BoardDAO.getInstance();
		BoardVO board = dao.getBoard(board_num);
		//로그인한 아이디와 작성하는 아이디가 일치해야 한다.
		if(user_num!=board.getMem_num()) {
			return "/WEB-INF/views/common/notice.jsp";//문구를 지정하지 않아도 잘못된 접속이라고 나옴(문구 지정 가능)
		}
		
		//큰 따옴표 처리
		//수정폼의 input 태그에서 큰 따옴표 오류 보정
		board.setTitle(StringUtil.parseQuot(board.getTitle()));
		
		//로그인이 되어 있고 로그인한 외원번호와 작성자 회원번호 일치
		request.setAttribute("board", board);
		return "/WEB-INF/views/board/updateForm.jsp";
	}

}
