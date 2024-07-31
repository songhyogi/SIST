package kr.news.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.news.dao.NewsDAO;
import kr.news.vo.NewsVO;
import kr.controller.Action;

public class DeleteAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//deleteForm.jsp를 보면 post방식으로 넘기는 것을 볼 수 있다. post방식으로 보내기 때문에 인코딩타입을 지정해야 한다.
		//전송된 데이터 인코딩 타입 지정
		request.setCharacterEncoding("utf-8");
		
		int num = Integer.parseInt(request.getParameter("num"));
		String passwd = request.getParameter("passwd");
		
		NewsDAO dao = NewsDAO.getInstance();
		NewsVO db_news = dao.getNews(num);
		boolean check = false;
		if(db_news!=null) {
			//비밀번호 일치 여부 체크
			check = db_news.isCheckedPassword(passwd);
		}
		if(check) {//check가 true일 경우
			dao.deleteNews(num);//글 삭제
		}
		
		request.setAttribute("check", check); //check가 true건 false건 check는 저장되어야 한다. check가 false면 다시 폼으로 돌아간다.
		//JSP 경로 반환
		return "/WEB-INF/views/delete.jsp";
	}

}

