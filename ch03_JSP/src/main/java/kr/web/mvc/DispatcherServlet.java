package kr.web.mvc;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DispatcherServlet extends HttpServlet{
	@Override
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		requestPro(request,response);
	}
	@Override
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		requestPro(request,response);
	}
	//메서드를 하나 만들어서 메서드가 공통적으로 호출하게끔 만들어주면 된다.
	private void requestPro(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		//모델클래스가 데이터를 만들어서 servlet에 JSP 경로를 준다.(모델클래스가 return해서 JSP경로를 반환해준다.)
		Action com = null;//여러 개의 자료형이 아니라 하나의 자료형을 사용하기 위해 인터페이스를 만들었다.
		String view = null;//view가 JSP 경로
		
		String command = request.getRequestURI();//request.getRequestURI()가 주소를 알아온다.
		System.out.println("1:" + command);
		if(command.indexOf(request.getContextPath())==0) {//컨텍스트 경로가 맨 앞에 있으면 true
			command = command.substring(request.getContextPath().length());
			System.out.println("2 : " + command);
		}
		
		if(command.equals("/list.do")) {//클라이언트가 /list.do라고 요청하면 ListAction()이 동작
			com = new ListAction();
		}else if(command.equals("/write.do")) {//클라이언트가 /write.do라고 요청하면 WriteAction()이 동작
			com = new WriteAction();
		}else if(command.equals("/detail.do")) {//클라이언트가 /detail.do라고 요청하면 DetailAction()이 동작
			com = new DetailAction();
		}else if(command.equals("/update.do")) {
			com = new UpdateAction();
		}else if(command.equals("/delete.do")) {
			com = new DeleteAction();
		}
		
		try {
			view = com.execute(request, response);//객체가 생성되었으니까 execute를 호출할 수 있다. execute 동작
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		//forward 방식으로 view(jsp) 호출
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);//모델클래스에 따라서 view가 바뀐다.
		dispatcher.forward(request, response);//forward가 되기 위해서는 request와 response를 공유해야 한다.
	}

}
