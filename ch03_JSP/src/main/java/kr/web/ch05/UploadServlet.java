package kr.web.ch05;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/*
 * maxFileSize : 개별 파일의 전송 가능한 최대 크기
 * maxRequestSize : 전송되는 모든 파일의 용량을 합친 최대 크기
 */

@MultipartConfig(
		maxFileSize = 1024 * 1024 * 5,
		maxRequestSize = 1024 * 1024 * 50
		)

@WebServlet("/fileUpload")
public class UploadServlet extends HttpServlet{
	@Override
	public void doPost(HttpServletRequest request,HttpServletResponse response)
							throws ServletException,IOException{
		//컨텍스트 경로상의 파일 업로드 절대 경로 구하기(상대경로를 넣어서 절대경로를 구함)
		String realFolder = request.getServletContext().getRealPath("/upload");
		
		//문서 타입 및 캐릭터셋 지정
		response.setContentType("text/html;charset=utf-8");
		
		//POST 방식으로 전송된 데이터 인코딩 타입 지정
		request.setCharacterEncoding("utf-8");
		
		//HTML 출력을 위한 출력 스트림을 생성
		PrintWriter out = response.getWriter();
		try {
			out.println("작성자 : " + request.getParameter("user") + "<br>");
			out.println("제목 : " + request.getParameter("title") + "<br>");
			out.println("-----------------------------------<br>");
			//절대 경로 위치 구하기
			out.println("파일 업로드 절대 경로 : " + realFolder + "<br>");
			//파일 업로드 처리 (Part : 실질적으로 업로드 처리하는 객체)
			Part part = request.getPart("uploadFile");
			String fileName = part.getSubmittedFileName();
			if(!fileName.isEmpty()) {
				//지정한 경로에 파일을 저장
				part.write(realFolder+"/"+fileName);
				out.println("파라미터명 : " + part.getName() + "<br>");
				out.println("컨텐트 타입 : " + part.getContentType() + "<br>");
				out.println("파일명 : " + fileName + "<br>");
				out.println("파일 크기 : " + part.getSize() + "bytes<br>");
				out.println("-----------------------------------<br>");
			}
			
		}catch(IllegalStateException e) {
			out.println("용량 초과 : " + e.toString());
		}
	}
}
