package kr.spring.view;

import java.io.OutputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

@Component //자동스캔 대상 지정
public class ImageView extends AbstractView{

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {//Map<String, Object> model : 컨트롤러에서 전달된 모델 속성들이 담긴 맵
		byte[] file = (byte[])model.get("imageFile"); //모델에서 이미지 파일의 배열을 가져옴
		String filename = (String)model.get("filename"); //모델에서 파일 이름을 가져옴
		
		String ext = filename.substring(filename.lastIndexOf("."));//.을 기준으로 확장자를 추출, 확장자에 따라서 contentType이 달라지기 때문
		if(ext.equalsIgnoreCase(".gif")) {
			ext = "image/gif";
		}else if(ext.equalsIgnoreCase(".png")) {
			ext = "image/png";
		}else {
			ext = "image/jpeg";
		}
		
		response.setContentType(ext);
		response.setContentLength(file.length);
		
		String file_name = new String(filename.getBytes("utf-8"),"iso-8859-1");
		
		response.setHeader("Content-Disposition", "attachment; filename=\""+ file_name + "\";");//파일 다운로드와 파일 이름을 지정
		response.setHeader("Content-Transfer-Encoding", "binary");
		
		OutputStream out = response.getOutputStream();//응답의 출력 스트림을 가져옴
		InputStream input = null;
		try {
			 input = new ByteArrayInputStream(file);//이미지 바이트 배열로부터 입력 스트림을 생성
			 IOUtils.copy(input, out); //입력 스트림의 데이터를 출력 스트림으로 복사
			 out.flush(); //출력 스트림을 플러시하여 모든 데이터가 쓰이도록 함
		}finally {//에러가 발생하면 throws로 가서 에러가 발생하게 catch를 만들지 않았다.
			if(out!=null)out.close();
			if(input!=null)input.close();
		}
	}
	
}
