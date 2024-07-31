package kr.spring.ch10.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

public class DownloadView extends AbstractView{
	
	//브라우저가 view역할을 하지 않게 컨텐트 타입 지정 
	public DownloadView() {
		setContentType("application/download;charset=utf-8");//넓은범위/좁은범위
	}
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//다운로드하는 파일의 경로 정보가 저장된 File 객체 반환
		File file = (File)model.get("downloadFile");//DownloadController.java에서 지정한 속성명
		
		//스트림을 발생시켜 클라이언트에 정보 전송
		//컨텐트 타입 지정
		response.setContentType(getContentType());
		//컨텐트의 용량 지정
		response.setContentLength((int)file.length());
		//파일명 구하기
		String fileName = new String(file.getName().getBytes("utf-8"),"iso-8859-1");//처음 구할 때는 utf-8 방식, 전체적으로 iso-8859-1로 지정
		
		//HTTP 응답 메세지 헤더 셋팅
		response.setHeader("Content-Disposition", "attachment;filename=\""+fileName+"\";");//역슬래시를 넣어서 "를 특수문자가 아닌 일반문자로 변경
		response.setHeader("Content-Transfer-Encoding", "binary");
		
		//파일 쓰기
		OutputStream out = response.getOutputStream();//전송하는 역할 (파일을 읽어오면 그걸 보내는 역할)
		FileInputStream fis = null;//파일을 읽어오는 역할
		try {
			fis = new FileInputStream(file);//파일 읽기
			//읽은 정보를 쓰기 정보로 변환
			FileCopyUtils.copy(fis, out);//FileCopyUtils : input에 있는 데이터를 output에 있는 데이터로 넘김
		}finally{//catch를 만들지 않고 위에 throws Exception이 있기 때문에 예외가 발생하면 throws Exception이 동작
			if(fis!=null)try {fis.close();}catch(IOException e) {}
		}
		out.flush();//파일 전송
	}

}
