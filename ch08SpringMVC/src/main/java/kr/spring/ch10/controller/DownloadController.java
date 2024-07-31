package kr.spring.ch10.controller;

import java.io.File;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DownloadController {
	@RequestMapping("/file.do")
	public ModelAndView download(HttpSession session) {//HttpSession을 인자로 넘기면 사용할 수 있다.
		//file.txt의 컨텍스트 경로상의 절대 경로를 구하기
		String path = session.getServletContext().getRealPath("/WEB-INF/file.txt");
		File downloadFile = new File(path);//경로를 구한 다음에 파일 객체를 만들어서 보냄
										// 	  뷰 이름			속성명			   속성값
		return new ModelAndView("download","downloadFile",downloadFile);
	}
}
