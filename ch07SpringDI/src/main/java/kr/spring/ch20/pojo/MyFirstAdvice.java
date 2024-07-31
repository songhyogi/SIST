package kr.spring.ch20.pojo;

import org.aspectj.lang.ProceedingJoinPoint;

public class MyFirstAdvice {
	/*
	 * 구현 가능한 Advice(언제 공통 기능을 핵심 기능에 적용할 지를 정의) 종류
	 * 종류									설명 
	 * Before Advice						대상 객체의 메서드 호출 전에 공통 기능을 실행 
	 * After Returning Advice			대상 객체의 메서드가 예외 없이 실행한 이후에 공통 기능을 실행
	 * After Throwing Advice 			대상 객체의 메서드를 실행하는 도중 예외가 발생한 경우에 공통 기능을 실행
	 * After Advice						대상 객체의 메서드를 실행하는 도중 예외가 발생했는지의 여부와 상관없이 메서드 실행 후 공통 기능을 실행
	 * 											(try~catch~finally의 finally 블럭과 비슷)
	 * Around Advice						대상 객체의 메서드 실행 전, 후 또는 예외 발생 시점에 공통 기능을 실행
	 */
	
	public void before() {
		//메서드 시작 직전에 동작하는 어드바이스
		System.out.println("Hello Before! **메서드가 호출 되기 전에 나온다!");
	}
	public void afterReturning(String msg) {//핵심 기능이 동작한 후에 반환하는 데이터가 있으면 이를 받아서 출력할 수 있기 때문에 인자를 생성
		//메서드 호출이 예외를 내보내지 않고 종료했을 때 동작하는 어드바이스
		System.out.println("Hello AfterReturning! **메서드가 호출한 후에 나온다! 전달된 객체 : " + msg);//공통기능이 수행된 이후에 반환되는 데이터 받아서 표시
	}
	public void afterThrowning(Exception ex) {
		//메서드 호출이 예외를 던졌을 때 동작하는 어드바이스
		System.out.println("Hello AfterThronwing! **예외가 생기면 나온다! 예외 : " + ex);
	}
	public void after() {
		//메서드 종료 후에 동작하는 어드바이스(예외가 발생해도 실행됨)
		System.out.println("Hello After! **메서드가 호출된 후에 나온다!");
	}
	public String around(ProceedingJoinPoint joinPoint)throws Throwable {//Throwable은 exception의 부모
		//메서드 호출 전후에 동작하는 어드바이스
		System.out.println("Hello Around before! **메서드가 호출되기 전에 나온다");
		String s = null;
		//try~catch~finally 구조로 명시해야 예외가 발생해도 메서드 실행 후 공통 기능을 수행
		try {
			//핵심 기능이 수행된 후 데이터 반환
			s = (String)joinPoint.proceed(); //proceed() : 핵심 기능을 수행하는 메서드 실행 //s에 [상품 출시]가 담김 
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//공통 기능
			System.out.println("Hello Around after! **메서드가 호출된 후에 나온다! 반환된 객체 : " + s);
		}
		
		return s;
	}
}

