package kr.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.member.dao.MemberDAO;
import kr.member.vo.MemberVO;
   
public class LoginAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//전송된 데이터 인코딩 타입 지정
		request.setCharacterEncoding("utf-8");
		//전송된 데이터 반환
		String id = request.getParameter("id");
		String passwd = request.getParameter("passwd");
		
		MemberDAO dao = MemberDAO.getInstance();
		MemberVO member = dao.checkMember(id);
		boolean check = false;
		
		if(member!=null) {//동일한 id 존재
			//비밀번호 일치 여부 체크
			check = member.isCheckedPassword(passwd);
			//정지회원의 경우 상태 표시
			request.setAttribute("auth", member.getAuth());//check가 true건 false건 정지회원의 경우 상태를 표시하기 위해 저장
		}
		if(check) {//인증 성공
			//로그인 처리
			HttpSession session = request.getSession();//jsp에서는 session이라고만 하면 구해지는데 여기서는 jsp가 아니라 request가 갖고 있는 getSession메서드 사용
			session.setAttribute("user_num", member.getMem_num());
			session.setAttribute("user_id", member.getId());
			session.setAttribute("user_auth", member.getAuth());
			session.setAttribute("user_photo", member.getPhoto());
			
			//메인으로 리다이렉트
			return "redirect:/main/main.do";//DispatcherServlet에서 앞에 redirect:이 있으면 리다이렉트하기로 정해놨다 redirect:이 식별자인데 있으면 리다이렉트, 없으면 포워드
		}
		//인증 실패
		return "/WEB-INF/views/member/login.jsp";
	}

}
