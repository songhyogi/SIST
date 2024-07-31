package kr.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.board.dao.BoardDAO;
import kr.board.vo.BoardVO;
import kr.controller.Action;

public class ModifyAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//get방식이 아니라 post방식으로 전송할 거라서
		//전송된 데이터 인코딩 타입 지정
		request.setCharacterEncoding("utf-8");
		//자바빈(VO) 생성
		BoardVO boardVO = new BoardVO();
		boardVO.setNum(Integer.parseInt(request.getParameter("num")));
		boardVO.setTitle(request.getParameter("title"));
		boardVO.setName(request.getParameter("name"));
		boardVO.setPasswd(request.getParameter("passwd")); //변경할 수 없지만 전송되어 왔기 때문에 담아준 것
		boardVO.setEmail(request.getParameter("email"));
		boardVO.setContent(request.getParameter("content"));
		boardVO.setIp(request.getRemoteAddr());
		
		BoardDAO dao = BoardDAO.getInstance();
		//비밀번호 인증을 위해 한 건의 레코드를 자바빈(VO)에 담아서 반환
		BoardVO db_board = dao.getBoard(boardVO.getNum());
		boolean check = false;
		if(db_board!=null) {//null이 아니다라고 하면 한 건의 레코드 비밀번호가 존재
			//비밀번호 일치 여부 체크 
			check = db_board.isCheckedPassword(boardVO.getPasswd());
		}
		if(check) {//비밀번호 일치 (check가 true라면)
			dao.update(boardVO);//글 수정
			//상세페이지로 이동하기 위해 글 번호 저장
			request.setAttribute("num", boardVO.getNum());
		}
		request.setAttribute("check", check); //비밀번호 불일치로 check가 false라면 원래 폼으로 돌아가야 되기 때문에 check값을 저장
		return "/WEB-INF/views/modify.jsp";
	}

}
