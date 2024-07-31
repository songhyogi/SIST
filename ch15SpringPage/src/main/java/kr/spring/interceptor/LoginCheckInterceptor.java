package kr.spring.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{//Object handler : 호출하는 컨트롤러 객체를 얻어낼 수 있다.
		log.debug("<<LoginCheckInterceptor 진입>>");//동작을 하는 지 안 하는 지 확인 할 수 있다.
		
		HttpSession session = request.getSession();
		//로그인 여부 검사
		if(session.getAttribute("user")==null) {
			log.debug("<<LoginCheckInterceptor 로그아웃 상태>>");
			//로그인이 되지 않은 상태
			response.sendRedirect(request.getContextPath()+"/member/login");
			return false; //요청한 URL을 호출하지 않을 경우 false
		}   
		log.debug("<<LoginCheckInterceptor 로그인 상태>>");
		return true; //요청한 URL을 호출할 경우 true -> 로그인이 되어 있는 경우
	}
}
