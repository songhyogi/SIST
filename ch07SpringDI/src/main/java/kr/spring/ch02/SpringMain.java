package kr.spring.ch02;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringMain {
	public static void main(String[] args) {
		//applicationContext.xml 설정파일을 읽어들여 스프링 컨테이너를 생성
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		//객체를 컨테이너로부터 읽어옴
		StudentBean studentBean = (StudentBean)context.getBean("studentBean"); //getBean이 객체를 Object로 반환하기 때문에 다운캐스팅
		studentBean.study("국어");
		
		//어플리케이션 종료시 컨테이너에 존재하는 모든 빈(객체)를 종료 -> 자원정리하는 역할
		context.close();
	}
}
