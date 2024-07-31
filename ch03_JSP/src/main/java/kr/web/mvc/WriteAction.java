package kr.web.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WriteAction implements Action{

	@Override
					//writeAction이 호출되면 execute가 동작
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//request에 데이터 저장
		request.setAttribute("insert", "글 등록 완료");//속성명이 insert
		//JSP 경로 반환 (모델클래스와 JSP 1:1 대응)
		return "/views/write.jsp";
	}

}
