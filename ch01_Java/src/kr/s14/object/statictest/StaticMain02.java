package kr.s14.object.statictest;

class StaticMethod{
	//인스턴스 변수
	String s1 = "행복";
	//static(클래스) 변수
	static String s2 = "행운";
	
	//static(클래스) 메서드
	public static String getString() {
		return s2;//인스턴스 변수는 객체 생성전엔 메모리에 못 올라가기 때문에 s1으로 하면 에러난다
	}
}

public class StaticMain02 {
	public static void main(String[] args) {
		//static 메서드는 객체 생성 없이 호출할 수 있고 '클래스명.메서드명'의 형태로 호출한다
		System.out.println(StaticMethod.getString());
		//Static 변수는 '클래스명.변수명'의 형태로 호출
		System.out.println(StaticMethod.s2);
		
		//인스턴스 변수를 호출할 때는 먼저 객체 생성 후 '참조변수.변수명'의 형태로 호출한다
		StaticMethod sm = new StaticMethod();
		System.out.println(sm.s1);
	}
}
