package kr.spring.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j //로그 처리를 위해 작성, Lombok을 사용해서 이용 가능
@Controller
public class MainController {
	@GetMapping("/")
	public String init() {
		return "redirect:/main/main"; //최근에는 확장자가 없는 형태가 유행
	}
	@GetMapping("/main/main")
	public String main() {
		return "main";//Tiles의 설정명 (definition의 name을 말함)
	}
}
