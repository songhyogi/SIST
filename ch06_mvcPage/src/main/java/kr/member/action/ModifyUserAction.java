package kr.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.member.dao.MemberDAO;
import kr.member.vo.MemberVO;

public class ModifyUserAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num == null) {//로그인이 안 된 경우
			return "redirect:/member/loginForm.do";
		}
		//로그인 된 경우
		//전송된 데이터 인코딩 타입 지정
		request.setCharacterEncoding("utf-8");
		
		//자바빈에 담아서 전달
		MemberVO member = new MemberVO();//자바빈 객체 생성
		member.setMem_num(user_num);//회원번호 (세션에서 뽑아옴)
		member.setName(request.getParameter("name"));
		member.setPhone(request.getParameter("phone"));
		member.setEmail(request.getParameter("email"));
		member.setZipcode(request.getParameter("zipcode"));
		member.setAddress1(request.getParameter("address1"));
		member.setAddress2(request.getParameter("address2"));
		
		MemberDAO dao = MemberDAO.getInstance();
		dao.updateMember(member);
		
		//result_view.jsp처럼 결과페이지를 만들어도 되고 자바스크립트로 결과페이지를 만들 수 있는데 이번에는 자바스크립트로 alert창을 띄울 예정
		//아래에 자바스크립트에서 필요한 것을 저장
		request.setAttribute("notice_msg", "회원정보 수정 완료");//alert창을 띄우기 위해
		request.setAttribute("notice_url", request.getContextPath()+"/member/myPage.do");//어떤 상태든 호출하기 위해서 uri로 하는 것이 좋다.
		
		//JSP 경로 반환
		return "/WEB-INF/views/common/alert_view.jsp";
		
	}

}
