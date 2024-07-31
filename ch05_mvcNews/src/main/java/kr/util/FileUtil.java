package kr.util;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

public class FileUtil {
	//업로드 상대경로
	private static final String UPLOAD_PATH = "/upload";
	//파일 생성																//param은 parameter네임을 말함(input태그에 명시되어 있는 파라미터 네임)
	public static String createFile(HttpServletRequest request,String param) 
											throws IllegalStateException,IOException,ServletException{
		//업로드 절대경로
		String upload = request.getServletContext().getRealPath(UPLOAD_PATH);
		//파일 정보 얻기
		Part part = request.getPart(param);
		//파일명 구하기
		String filename = part.getSubmittedFileName(); //part.getSubmittedFileName()은 파일이 없으면 빈문자열을 반환한다.
		if(!filename.isEmpty()) {
			//파일 중복 방지를 위해 임의의 값_원래 파일명 형식으로 변경
			filename = UUID.randomUUID()+"_"+filename;//UUID.randomUUID라고 하면 유니크하게 만들어 준다.
			part.write(upload+"/"+filename);
		}
		return filename;
	}
	//파일 삭제
	public static void removeFile(HttpServletRequest request,String filename) {
		if(filename!=null) {//파일명이 있을 경우에만 삭제 작업 수행
			//업로드 절대경로
			String upload = request.getServletContext().getRealPath(UPLOAD_PATH); //컨텍스트 경로상의 절대경로를 알려준다.
			//파일 객체 생성
			File file = new File(upload+"/"+filename);
			if(file.exists()) file.delete();//filename은 있는데 그 경로에 들어가서 보니까 file이 없으면 파일 삭제를 안 하고 file이 존재하면 파일 삭제
			
		}
	}
}
