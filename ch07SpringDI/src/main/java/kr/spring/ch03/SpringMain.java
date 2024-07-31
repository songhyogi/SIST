package kr.spring.ch03;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringMain {
	public static void main(String[] args) {
		//applicationContext.xml 설정파일을 읽어들여 스프링 컨테이너를 생성
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		//객체를 컨테이너로부터 읽어옴
		OperatorBean bean = (OperatorBean)context.getBean("operatorBean");//읽어온 빈 주소를 bean에 저장
		
		int result = bean.add(10, 20); //bean객체 안에 add라는 메서드가 있음
		
		System.out.println("결과 : " + result);
		
		//어플리케이션 종료시 컨테이너에 존재하는 모든 빈(객체)를 종료
		context.close();
	}
}
