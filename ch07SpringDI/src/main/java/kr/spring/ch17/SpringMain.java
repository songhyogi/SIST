package kr.spring.ch17;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringMain {
	public static void main(String[] args) {
		//스프링 컨테이너를 생성
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("applicationContextAnnot.xml");
		
		//DI - @Autowired 어노테이션을 이용한 자동 설정
		SystemMonitor2 monitor = (SystemMonitor2)context.getBean("systemMonitor");
		System.out.println(monitor.getRecorder());//메서드를 만들어 놨으니까 getRecorder
		
		context.close();
	}
}
