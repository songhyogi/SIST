package kr.news.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.news.dao.NewsDAO;
import kr.news.vo.NewsVO;
import kr.util.FileUtil;

public class ModifyAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//전송된 데이터 인코딩 타입 지정
		request.setCharacterEncoding("utf-8");
		
		int num = Integer.parseInt(request.getParameter("num"));
		String passwd = request.getParameter("passwd");
		
		NewsDAO dao = NewsDAO.getInstance();
		//비밀번호 인증을 위해 한 건의 레코드를 자바빈(VO)에 담아서 반환
		NewsVO db_news = dao.getNews(num);
		boolean check = false;
		if(db_news!=null) {//null이 아니다라고 하면 한 건의 레코드 비밀번호가 존재
			//비밀번호 일치 여부 체크 
			check = db_news.isCheckedPassword(passwd);
		}
		if(check) {//비밀번호 일치 (check가 true라면)
			NewsVO vo = new NewsVO();
			vo.setNum(num);
			vo.setTitle(request.getParameter("title"));
			vo.setWriter(request.getParameter("writer"));
			vo.setEmail(request.getParameter("email"));
			vo.setArticle(request.getParameter("article"));
			vo.setFilename(FileUtil.createFile(request, "filename"));
			
			dao.updateNews(vo);
			
			if(vo.getFilename()!=null &&  !vo.getFilename().isEmpty()) {//vo.setFilename에 파일명이 존재하고 비어있지 않으면 이전 것을 삭제하고 새로 업로드
				//새 파일로 교체할 때 원래 파일 제거
				FileUtil.removeFile(request, db_news.getFilename());
			}
			//상세페이지로 이동하기 위해 글 번호 저장
			request.setAttribute("num", vo.getNum());
		}
		//UI 처리를 위해서 check 저장(비밀번호가 일치하건 불일치하건 UI처리를 위해서)
		request.setAttribute("check", check); //비밀번호 불일치라서 check가 false라면 원래 폼으로 돌아가야 되기 때문에 check값을 저장
		return "/WEB-INF/views/modify.jsp";

	}

}
