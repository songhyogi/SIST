package kr.spring.main.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kr.spring.member.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class CommonController {
	@PostMapping("/common/imageUploader")
	@ResponseBody
	public Map<String, Object> uploadImage(MultipartFile upload,HttpSession session,
											HttpServletRequest request,HttpServletResponse response)
													throws Exception{
		//업로드할 폴더 경로
		String realFolder = session.getServletContext().getRealPath("/image_upload");//절대경로 구하기
		
		//업로드할 파일 이름 (원본이 아닌 변경해서 저장)
		String org_filename = upload.getOriginalFilename();
		String str_filename = System.currentTimeMillis() + "_" + org_filename;//연월일시분초를 붙여서 유니크하게(연월일시분초를 롱타입으로 반환한다.)
		
		log.debug("<<원본 파일명>> : " + org_filename);
		log.debug("<<저장할 파일명>> : " + str_filename);
		
		//로그인한 회원번호의 파일을 만들어서 그곳에 저장하려고 한다.
		//로그인이 풀렸을 경우 제너럴에 보관하려고 한다.
		String sub_path;//중간 경로
		MemberVO user = (MemberVO)session.getAttribute("user");
		if(user == null) {
			sub_path = "general";
		}else {
			sub_path = String.valueOf(user.getMem_num());//해당 회원이 올린 이미지는 같은 곳에 보관
		}
		String file_path = realFolder + "/" + sub_path + "/" + str_filename;
		
		log.debug("<<파일 경로>> : " + file_path);
		
		File f = new File(file_path);
		if(!f.exists()) {//폴더가 존재하지 않은 경우 폴더를 만든 후 업로드하려고 한다.
			//상위 경로를 생성해야 하기 때문에 mkdirs() 사용할 것 (mkdirs를 사용하면 상위경로를 생성한다.)
			//mkdir()를 사용하면 상위 경로를 생성하지 못 함
			f.mkdirs();
		}
		upload.transferTo(f);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("uploaded", true);//CKEditor에서 이미 지정된 사항. 반드시 이렇게 보내야 인식한다.
		map.put("url", request.getContextPath()+"/image_upload/" + sub_path + "/" + str_filename);
		
		return map;
	}
}
