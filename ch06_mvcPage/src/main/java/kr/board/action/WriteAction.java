package kr.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.board.dao.BoardDAO;
import kr.board.vo.BoardVO;
import kr.controller.Action;
import kr.util.FileUtil;

public class WriteAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(); 
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num == null) {//로그인이 되지 않은 경우
			return "redirect:/member/loginForm.do";
		}
		//로그인 된 경우
		//전송된 데이터 인코딩 타입 지정
		request.setCharacterEncoding("utf-8");
		//자바빈(VO)를 생성한 후 전송된 데이터를 저장
		BoardVO board = new BoardVO();
		board.setTitle(request.getParameter("title"));
		board.setContent(request.getParameter("content"));
		board.setIp(request.getRemoteAddr());
		board.setFilename(FileUtil.createFile(request, "filename"));//업로드할 경우 이름을 바꿔서 setFilename에 전송
																					//업로드를 하지 않으면 빈문자열을 대입하여 DB에서는 null로 인식
		board.setMem_num(user_num);//작성자 회원번호
		
		BoardDAO dao = BoardDAO.getInstance();
		dao.insertBoard(board);
		
		request.setAttribute("notice_msg", "글쓰기 완료");
		request.setAttribute("notice_url", request.getContextPath()+"/board/list.do");//같은 경로여도 uri방식으로 명시하는 것이 안전
		
		//JSP 경로 반환
		return "/WEB-INF/views/common/alert_view.jsp";
	}

}
