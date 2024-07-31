package kr.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.board.dao.BoardDAO;
import kr.board.vo.BoardVO;
import kr.controller.Action;
import kr.util.FileUtil;

public class DeleteAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(); 
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num == null) {//로그인이 되지 않은 경우
			return "redirect:/member/loginForm.do";
		}
		//로그인 된 경우
		int board_num = Integer.parseInt(request.getParameter("board_num"));//get방식으로 board_num을 넘김
		BoardDAO dao = BoardDAO.getInstance();
		BoardVO db_board = dao.getBoard(board_num);
		//로그인한 회원번호와 작성자 회원번호 일치 여부 체크
		if(user_num != db_board.getMem_num()) {
			return "/WEB-INF/views/common/notice.jsp";//forward방식으로 잘못된 접속임을 보여줌
		}
		//로그인한 회원번호와 작성자 회원번호 일치
		dao.deleteBoard(board_num);
		//파일 삭제 (정상적으로 삭제가 되었을 경우 파일이 존재하면 파일 삭제)
		FileUtil.removeFile(request, db_board.getFilename());//db_board.getFilename이 null이면 파일을 삭제하지 않고 null이 아니면 파일을 삭제함
		
		request.setAttribute("notice_msg", "글 삭제 완료");
		request.setAttribute("notice_url", request.getContextPath()+"/board/list.do");
		
		return "/WEB-INF/views/common/alert_view.jsp";
	}

}
