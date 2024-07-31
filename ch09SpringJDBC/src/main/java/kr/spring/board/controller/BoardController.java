package kr.spring.board.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.board.service.BoardService;
import kr.spring.board.vo.BoardVO;
import kr.spring.util.PagingUtil;

@Controller
public class BoardController {
	
	//인터페이스에서 정의한 메서드만 사용할 수 있게
	@Autowired
	private BoardService boardService;
	
	//로그 처리 (로그 대상 지정)
	private static final Logger log = LoggerFactory.getLogger(BoardController.class);
	/*
	 * 로그 레벨
	 * FATAL : 가장 심각한 오류
	 * ERROR : 일반적인 오류
	 * WARN : 주의를 요하는 경우
	 * INFO : 런타임시 관심있는 경우
	 * DEBUG : 시스템 흐름과 관련된 상세 정보
	 * TRACE : 가장 상세한 정보
	 */
	
	//유효성 체크를 위한 폼 초기화
	@ModelAttribute
	public BoardVO initCommand() {
		return new BoardVO();
	}
	
	//폼 호출
	@GetMapping("/insert.do")
	public String form() {
		return "insertForm";
	}
	//전송된 데이터 처리
	@PostMapping("insert.do")
	public String submit(@Valid BoardVO vo, BindingResult result) {//어노테이션을 이용한 유효성체크를 위해서 @Valid 명시
		log.debug("<<BoardVO>> : " + vo);
		
		//유효성 체크 결과 오류가 있으면 폼을 호출 (유효성 체크를 @Valid BoardVO가 자동적으로 다 함)
		if(result.hasErrors()) {
			return form();
		}
		//글 등록
		boardService.insertBoard(vo);
		
		return "redirect:/list.do";
	}
	
	@RequestMapping("/list.do")
	public ModelAndView process(@RequestParam(value="pageNum", defaultValue="1") int currentPage) {//겉으로는 pageNum이지만 내부적으로는 currenPage를 사용할거라서 value를 적어준 것이다.
		
		int count = boardService.getBoardCount();
		log.debug("<<count>> : " + count);
		
		//페이지 처리
		PagingUtil page = new PagingUtil(currentPage,count,20,10,"list.do");
		
		//목록 호출
		List<BoardVO> list = null;
		if(count > 0) {
			list = boardService.getBoardList(page.getStartRow(), page.getEndRow());
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("selectList");
		mav.addObject("count", count);
		mav.addObject("list", list);
		mav.addObject("page", page.getPage());
		
		return mav;
	}
	//글 상세
	@RequestMapping("/detail.do")
	public ModelAndView detail(int num) {
		log.debug("<<num>> : " + num);
		
		BoardVO board = boardService.getBoard(num);
										//      뷰 이름       속성명    속성값
		return new ModelAndView("selectDetail","board",board);
	}
	//수정 폼
	@GetMapping("/update.do")
	public String formUpdate(int num,Model model) {//request에 데이터 저장 용도로 Model 사용.
		
		model.addAttribute("boardVO", boardService.getBoard(num));//한 건의 데이터를 읽어와서 세팅. 모델에 있는 데이터가 request에 전달된다.
		return "updateForm";
	}
	@PostMapping("/update.do")
	//전송된 데이터 처리
	public String submitUpdate(@Valid BoardVO vo,BindingResult result) {
		log.debug("<<BoardVO>> : " + vo);
		
		//유효성 체크 결과 오류가 있으면 폼을 호출
		if(result.hasErrors()) {
			return "updateForm";//폼을 호출하거나 메서드를 호출할 수 있는데 num값과 model값을 같이 넘겨줘야 하는데 복잡하므로 updateForm이라고 명시했다.
		}
		
		//DB에 저장된 비밀번호 구하기
		BoardVO db_board = boardService.getBoard(vo.getNum());
		//비밀번호 일치 여부 체크
		if(!db_board.getPasswd().equals(vo.getPasswd())) {
									// 필드				에러코드
			result.rejectValue("passwd", "invalidPassword");//필드가 있기 때문에 rejectValue를 사용했다.
			return "updateForm";
		}
		
		//글 수정
		boardService.updateBoard(vo);//비밀번호가 일치하면 글 수정
		
		return "redirect:/list.do";//코드를 절약하기 위해 뷰를 만들지 않았다.
	}
	//삭제 폼 호출
	@GetMapping("/delete.do")
	public String formDelete(BoardVO vo) {//유효성체크를 위해 자바빈에 담아서 처리. request에 boardVO로 저장된다. num값이 전송되어 num값이 저장된다.
		return "deleteForm";
	}
	//전송된 데이터 처리
	@PostMapping("/delete.do")
	public String submitDelete(@Valid BoardVO vo, BindingResult result) {
		log.debug("<<BoardVO>> : " + vo);
		//비밀번호만 체크해야되는데 BoardVO를 보면 writer,title,content도 있다. 이게 전달이 안 되면 null이기 때문에 에러가 발생한다. writer,title,content는 체크하지 않고 passwd만 체크하기 위해 hasFieldErrors 메서드를 사용
		//비밀번호만 유효성 체크 결과 오류가 있으면 폼 호출
		if(result.hasFieldErrors("passwd")) {//비밀번호만 오류가 있는지 체크
			return "deleteForm";
		}
		//DB에 저장된 비밀번호 구하기
		BoardVO db_board = boardService.getBoard(vo.getNum());
		//비밀번호 일치 여부 체크
		if(!db_board.getPasswd().equals(vo.getPasswd())) {
			result.rejectValue("passwd", "invalidPassword");
			return "deleteForm";
		}
		
		boardService.deleteBoard(vo.getNum());//비밀번호가 일치하면 글 삭제
		
		return "redirect:/list.do";//뷰를 만들지 않고 정상적으로 처리되면 목록으로 리다이렉트
	}
}
