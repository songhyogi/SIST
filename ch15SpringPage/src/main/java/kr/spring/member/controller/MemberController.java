package kr.spring.member.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import kr.spring.member.service.MemberService;
import kr.spring.member.vo.MemberVO;
import kr.spring.util.AuthCheckException;
import kr.spring.util.CaptchaUtil;
import kr.spring.util.FileUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MemberController {
	@Autowired
	private MemberService memberService;
	
	/*=========================
	 * 회원가입
	 * ========================*/
	//자바빈(VO) 초기화
	@ModelAttribute
	public MemberVO initCommand() {
		return new MemberVO();
	}
	//회원가입 폼 호출
	@GetMapping("/member/registerUser")
	public String form() {
		return "memberRegister";//Tiles 설정명
	}
	//전송된 데이터 처리
	@PostMapping("/member/registerUser")
	public String submit(@Valid MemberVO memberVO, BindingResult result, Model model, HttpServletRequest request) {
		log.debug("<<회원가입>> : " + memberVO);
		
		//유효성 체크 결과 오류가 있으면 폼 호출
		if(result.hasErrors()) {
			return form();
		}
		//회원가입 
		memberService.insertMember(memberVO);
		
		//UI 메시지 처리
		model.addAttribute("accessTitle", "회원가입"); //이렇게 하면 request에 저장
		model.addAttribute("accessMsg", "회원 가입이 완료되었습니다.");
		model.addAttribute("accessBtn", "홈으로");
		model.addAttribute("accessUrl", request.getContextPath()+"/main/main");
		
		return "common/resultView";//타일스의 식별자로 등록되어있다면 타일스를 호출하지만 타일스의 식별자로 등록되어있지 않다면 resultView.jsp를 찾는다.
	}
	/*=========================
	 * 회원로그인
	 * ========================*/
	//로그인 폼 호출
	@GetMapping("/member/login")
	public String formLogin() {
		return "memberLogin"; //Tiles 설정명
	}
	//로그인 폼에서 전송된 데이터 처리
	@PostMapping("/member/login")
	public String submitLogin(@Valid MemberVO memberVO, BindingResult result, 
											HttpSession session, HttpServletResponse response) {
		log.debug("<<회원로그인>> : " + memberVO);
		
		//유효성 체크 결과 오류가 있으면 폼 호출
		//id와 passwd 필드만 체크
		if(result.hasFieldErrors("id") || result.hasFieldErrors("passwd")) {
			return formLogin();
		}
		
		//로그인 체크(id, 비밀번호 일치 여부 체크)
		MemberVO member = null;
		try {
			member = memberService.selectCheckMember(memberVO.getId());
			boolean check = false;
			if(member!=null) {
				//비밀번호 일치 여부 체크
				check = member.ischeckedPassword(memberVO.getPasswd());
			}
			if(check) {//인증 성공
				//===자동로그인 체크 시작===//
				boolean autoLogin = memberVO.getAuto()!=null && memberVO.getAuto().equals("on");
				if(autoLogin) {//getAuto가 null이 아니고 on이 전달되었을 경우 autoLogin이 true
					//자동로그인을 체크한 경우
					String au_id = member.getAu_id();
					if(au_id==null) {
						//자동로그인 체크 식별값 생성
						au_id = UUID.randomUUID().toString();//기존에 au_id가 있으면 그걸 사용하고 없으면 새로 랜덤으로 만들어서 사용
						log.debug("<<au_id>> : " + au_id);
						member.setAu_id(au_id);
						memberService.updateAu_id(member.getAu_id(), member.getMem_num());
					}
					Cookie auto_cookie = new Cookie("au-log",au_id);
					auto_cookie.setMaxAge(60*60*24*7);//쿠키의 유효기간은 일주일
					auto_cookie.setPath("/");
					
					response.addCookie(auto_cookie);
				}
				//===자동로그인 체크 끝===//
				
				//인증 성공, 로그인 처리
				session.setAttribute("user", member);
				log.debug("<<인증 성공>>");
				log.debug("<<id>> : " + member.getId());
				log.debug("<<auth>> : " + member.getAuth());
				log.debug("<<au_id>> : " + member.getAu_id());
				
				if(member.getAuth() == 9) {//관리자
					return "redirect:/main/admin";//관리자일 경우 main/admin으로 리다이렉트
				}else {
					return "redirect:/main/main";//일반회원일 경우 main/main으로 리다이렉트
				}
			}
			//인증 실패
			throw new AuthCheckException();//예외를 던짐. catch로 들어간다.
		}catch(AuthCheckException e) {//사용자정의클래스인 AuthCheckException를 타입으로
			//인증 실패로 로그인 폼 호출
			if(member!=null && member.getAuth()==1) {//정지회원 메시지 표시
				result.reject("noAuthority");//필드가 없는 경우 reject
			}else {
				result.reject("invalidIdOrPassword");
			}
			log.debug("<<인증 실패>>");
			
			return formLogin();
		}
	}
	/*=========================
	 * 로그아웃
	 * ========================*/
	@GetMapping("/member/logout")
	public String processLogout(HttpSession session,HttpServletResponse response) {
		//로그아웃
		session.invalidate();
		
		//===자동로그인 시작===//
		//클라이언트 쿠키 처리
		Cookie auto_cookie = new Cookie("au-log","");//로그아웃 할 경우 쿠키에 있는 것을 지움
		auto_cookie.setMaxAge(0);//쿠키 삭제
		auto_cookie.setPath("/");
		
		response.addCookie(auto_cookie);
		//===자동로그인 끝===//
		
		return "redirect:/main/main";
	}
	/*=========================
	 * MY페이지
	 * ========================*/
	@GetMapping("/member/myPage")
	public String process(HttpSession session,Model model) {
		MemberVO user = (MemberVO)session.getAttribute("user");//세션에는 일부정보만 넣었다.
		//회원정보
		MemberVO member = memberService.selectMember(user.getMem_num());//세션엔 일부정보만 넣었기 때문에 자바빈에서 전체 정보를 읽어온다.
		log.debug("<<MY페이지>> : " + member);
		
		model.addAttribute("member", member);
		return "myPage";//Tiles 설정명
	}
	/*=========================
	 * 회원정보 수정
	 * ========================*/
	//수정 폼 호출
	@GetMapping("/member/update")
	public String formUpdate(HttpSession session,Model model) {
		MemberVO user = (MemberVO)session.getAttribute("user");
		MemberVO memberVO = memberService.selectMember(user.getMem_num());
		model.addAttribute("memberVO", memberVO);
				
		return "memberModify";
	}
	//수정폼에서 전송된 데이터 처리
	@PostMapping("/member/update")
	public String submitUpdate(@Valid MemberVO memberVO, BindingResult result, HttpSession session) {//mem_num을 받기 위해서는 세션이 필요하다.
		log.debug("<<회원정보 수정>> : " + memberVO);
		
		//유효성 체크 결과 오류가 있으면 폼 호출
		if(result.hasErrors()) {
			return "memberModify";
		}
		
		//세션에서 자바빈을 꺼내고 그 중에서 mem_num을 꺼낸다.
		MemberVO user = (MemberVO)session.getAttribute("user");
		memberVO.setMem_num(user.getMem_num());
		
		//회원정보 수정
		memberService.updateMember(memberVO);
		
		//세션에 저장된 정보 변경 (세션에 있는 정보를 최신 정보로 변경해줘야 한다.)
		user.setNick_name(memberVO.getNick_name());
		user.setEmail(memberVO.getEmail());
		
		return "redirect:/member/myPage";
	}
	
	/*=========================
	 * 프로필 사진 출력
	 * ========================*/
	//프로필 사진 출력(로그인 전용)
	@GetMapping("/member/photoView")
	public String getProfile(HttpSession session, HttpServletRequest request, Model model) {
		MemberVO user = (MemberVO)session.getAttribute("user");
		log.debug("<<프로필 사진 출력>> : " + user);
		if(user==null) {//로그인이 되지 않은 경우
			getBasicProfileImage(request, model);
		}else {//로그인이 된 경우
			MemberVO memberVO = memberService.selectMember(user.getMem_num());//DB에 저장된 이미지를 읽어옴
			viewProfile(memberVO, request, model);
		}
		
		return "imageView"; //타일스 설정이 아니라 스트림처리하는 클래스
	}
	
	//프로필 사진 출력(회원번호 지정)
	@GetMapping("/member/viewProfile")
	public String getProfileByMem_num(long mem_num,HttpServletRequest request,Model model) {
		MemberVO memberVO = memberService.selectMember(mem_num);
		
		viewProfile(memberVO,request,model);
		
		return "imageView";
	}
	
	//프로필 사진 처리를 위한 공통 코드
	public void viewProfile(MemberVO memberVO, HttpServletRequest request, Model model) {
		if(memberVO==null || memberVO.getPhoto_name()==null) {//DB에 저장된 프로필 이미지가 없을 경우
			//DB에 저장된 프로필 이미지가 없기 때문에 기본 이미지 호출
			getBasicProfileImage(request, model);
		}else {//이미지가 있는 경우
			//업로드한 프로필 이미지 읽기
			model.addAttribute("imageFile", memberVO.getPhoto());
			model.addAttribute("filename", memberVO.getPhoto_name());//저장된 이름으로 하거나 고정된 이름으로 해도 된다. 이렇게 저장해두면 바이트 배열을 가져다가 스트림으로 처리하면 된다.
		}
	}
	
	//기본 이미지 읽기
	public void getBasicProfileImage(HttpServletRequest request, Model model) {//HttpServletRequest : 컨텍스트 경로상의 절대경로를 읽어오기 위함
																										  //Model : 데이터를 저장하려면 Model이 필요
		byte[] readbyte = FileUtil.getBytes(request.getServletContext().getRealPath("/image_bundle/face.png"));
		model.addAttribute("imageFile", readbyte);
		model.addAttribute("filename", "face.png");//byte배열형태로 전달되기 때문에 파일명이 필요하다면 파일명을 세팅
	}
	
	/*=========================
	 * 비밀번호 변경
	 * ========================*/
	//비밀번호 변경 폼 호출
	@GetMapping("/member/changePassword")
	public String formChangePassword() {
		return "memberChangePassword";
	}
	//비밀번호 변경 폼에서 전송된 데이터 처리
	@PostMapping("/member/changePassword")
	public String submitChangePassword(@Valid MemberVO memberVO,BindingResult result,
										HttpSession session,Model model,HttpServletRequest request) {
		log.debug("<<비밀번호 변경 처리>> : " + memberVO);
		
		//유효성 체크 결과 오류가 있으면 폼 호출
		if(result.hasFieldErrors("now_passwd") || result.hasFieldErrors("passwd") || result.hasFieldErrors("captcha_chars")) {
			return formChangePassword();
		}
		
		//======캡챠 문자 체크 시작 =====//
		String code = "1";//키 발급 0, 캡챠 이미지 비교시 1로 세팅
		//캡챠 키 발급시 받은 키값
		String key = (String)session.getAttribute("captcha_key");//캡챠 키 발급 시 받은 키값을 세션에 보관해놨었다.
		//사용자가 입력한 캡챠 이미지 글자값
		String value = memberVO.getCaptcha_chars();
		String apiURL = "https://openapi.naver.com/v1/captcha/nkey?code=" + code + "&key=" + key + "&value=" + value;
		
		
		Map<String,String> requestHeaders = new HashMap<String,String>();
		requestHeaders.put("X-Naver-Client-Id", "EuX9QQQpFMO8RTHkOZfL");
		requestHeaders.put("X-Naver-Client-Secret", "HU7RG1Jpeg");
		String responseBody = CaptchaUtil.get(apiURL, requestHeaders);
		log.debug("<<캡챠 결과>> : " + responseBody);
		
		//JSON문자열로 되어 있기 때문에 JSON객체로 만들어서 result값만 뽑아내서 true이면 일치 false이면 불일치
		JSONObject jObject = new JSONObject(responseBody);//JSON 객체로 변환작업
		boolean captcha_result = jObject.getBoolean("result");
		if(!captcha_result) {
			result.rejectValue("captcha_chars", "invalidCaptcha");
			return formChangePassword();
		}
		//======캡챠 문자 체크 끝 =====//
		MemberVO user = (MemberVO)session.getAttribute("user");
		memberVO.setMem_num(user.getMem_num());
		
		MemberVO db_member = memberService.selectMember(memberVO.getMem_num());
		//폼에서 전송한 현재 비밀번호와 DB에서 읽어온 비밀번호 일치 여부 체크
		if(!db_member.getPasswd().equals(memberVO.getNow_passwd())) {
			result.rejectValue("now_passwd", "invalidPassword");
			return formChangePassword();//불일치하니까 폼 다시 호출
		}
		
		//비밀번호 수정
		memberService.updatePassword(memberVO);
		
		//설정되어 있는 자동로그인 기능 해제(모든 브라우저에 설정된 자동로그인 해제)
		memberService.deleteAu_id(memberVO.getMem_num());
		
		//View에 표시할 메시지
		model.addAttribute("message", "비밀번호 변경 완료(*재접속시 설정되어 있는 자동로그인 기능 해제*");
		model.addAttribute("url", request.getContextPath() + "/member/myPage");
		
		return "common/resultAlert";
	}
	
	/*=========================
	 * 네이버 캡챠 API 사용
	 * ========================*/
	//캡챠 이미지 호출
	@GetMapping("/member/getCaptcha")
	public String getCaptcha(Model model, HttpSession session) {
		
		String code = "0"; //키 발급시 0, 캡챠 이미지 비교시 1로 세팅
		String key_apiURL = "https://openapi.naver.com/v1/captcha/nkey?code=" + code;
		
		Map<String,String> requestHeaders = new HashMap<String,String>();
		requestHeaders.put("X-Naver-Client-Id", "EuX9QQQpFMO8RTHkOZfL");
		requestHeaders.put("X-Naver-Client-Secret", "HU7RG1Jpeg");
		String responseBody = CaptchaUtil.get(key_apiURL, requestHeaders);
		log.debug("<<responseBody>> : " + responseBody);
		
		JSONObject jObject = new JSONObject(responseBody);
		try {
			//https://openapi.naver.com/v1/captcha/nkey 호출로 받은 키값
			String key = jObject.getString("key");//이걸 읽어와서 이미지 정보를 얻음
			session.setAttribute("captcha_key", key);//캡챠 이미지와 동일하게 입력하기 위해서 세션에 보관
			String apiURL = "https://openapi.naver.com/v1/captcha/ncaptcha.bin?key=" + key;
			
			byte[] response_byte = CaptchaUtil.getCaptchaImage(apiURL, requestHeaders);
			model.addAttribute("imageFile", response_byte);
			model.addAttribute("filename", "captcha.jpg");
		}catch(Exception e) {
			log.error(e.toString());
		}
		
		return "imageView";
	}
	/*=========================
	 * 임시비밀번호 발송 이메일 처리
	 * ========================*/
	@GetMapping("/member/sendPassword")
	public String sendPasswordform() {
		return "memberFindPassword";//Tiles
	}
}
