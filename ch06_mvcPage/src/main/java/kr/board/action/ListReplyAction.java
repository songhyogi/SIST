package kr.board.action;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.board.dao.BoardDAO;
import kr.board.vo.BoardReplyVO;
import kr.controller.Action;
import kr.util.PagingUtil;

public class ListReplyAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//전송된 데이터 인코딩 타입 지정
		request.setCharacterEncoding("utf-8");
		
		String pageNum = request.getParameter("pageNum");
		if(pageNum==null) {//pageNum이 없을 경우 1페이지로 
			pageNum = "1";
		}
		
		String rowCount = request.getParameter("rowCount");
		if(rowCount==null) {//rowCount가 없을 경우 10으로 간주
			rowCount = "10";
		}
		
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		BoardDAO dao = BoardDAO.getInstance();
		int count = dao.getReplyBoardCount(board_num);
		
		/*
		 * ajax 방식으로 목록을 표시하기 때문에 PagingUtil은 페이지수 표시가 목적이 아니라 
		 * 목록 데이터의 페이지 처리를 위해 rownum 번호를 구하는 것이 목적임
		 */
		
		PagingUtil page = new PagingUtil(Integer.parseInt(pageNum),count,Integer.parseInt(rowCount));
		List<BoardReplyVO> list = null;
		if(count > 0) {
			list = dao.getListReplyBoard(page.getStartRow(), page.getEndRow(), board_num);
		}else {//위에 list 기본값을 null로 해놨기 때문에 else를 작성하지 않으면 null로 보낸다. else를 만들어서 비어있는 list로 보내려고 한다.
			//list 객체는 있는데 비어있는 list를 잭슨 라이브러리가 비어있는 배열로 만들어준다.
			list = Collections.emptyList();
		}
		
		//로그인한 사람이 작성자라면 수정 삭제가 보여지게 하기 위해서 user_num을 구함
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		
		Map<String,Object> mapAjax = new HashMap<String,Object>();
		mapAjax.put("count", count);
		mapAjax.put("list", list);
		//로그인한 사람이 작성자인지 체크하기 위해서 로그인한 회원번호 전송
		mapAjax.put("user_num", user_num);
		
		//JSON 문자열로 반환
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);
		
		request.setAttribute("ajaxData", ajaxData);
		
		return "/WEB-INF/views/common/ajax_view.jsp";
	}

}
