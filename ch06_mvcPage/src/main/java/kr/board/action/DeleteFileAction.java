package kr.board.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.board.dao.BoardDAO;
import kr.board.vo.BoardVO;
import kr.controller.Action;
import kr.util.FileUtil;
  
public class DeleteFileAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//맵 객체 생성
		Map<String,String> mapAjax = new HashMap<String,String>();
		//회원제 서비스이기 때문에 로그인 되어 있는지(로그인이 되어 있는 상태에서 삭제가 되어야 하기 때문)
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num==null) {//로그인이 되지 않은 경우
			mapAjax.put("result", "logout");
		}else {//로그인 된 경우
			//전송된 데이터 인코딩 타입 지정
			request.setCharacterEncoding("utf-8");//post 방식이면 한글이 없다하더라도 반드시 utf-8로 인코딩 타입 지정 필요
			int board_num = Integer.parseInt(request.getParameter("board_num"));
			BoardDAO dao = BoardDAO.getInstance();
			BoardVO db_board = dao.getBoard(board_num);
			//작성하지 않은 사람이 작성한 것처럼 삭제하려고 시도할 수 있기 때문에 
			//로그인한 회원번호와 작성자 회원번호 일치 여부 체크
			if(user_num!=db_board.getMem_num()) {
				mapAjax.put("result", "wrongAccess");
			}else {
				dao.deleteFile(board_num);
				//파일 삭제
				FileUtil.removeFile(request, db_board.getFilename());
				
				mapAjax.put("result", "success");
			}
		}
		
		//JSON 데이터 생성
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);
		
		request.setAttribute("ajaxData", ajaxData);
		
		return "/WEB-INF/views/common/ajax_view.jsp";
	}

}
