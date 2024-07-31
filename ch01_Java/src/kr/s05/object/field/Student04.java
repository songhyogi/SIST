package kr.s05.object.field;

public class Student04 {
	//멤버 필드(속성)
	String name;
	int age;
	
	public static void main(String[] args) {
		//객체 선언 및 생성
		Student04 s1 = new Student04();
		s1.name = "장영실";
		s1.age = 15;
		System.out.println(s1.name + "," + s1.age);
		
		//객체의 참조값 출력해보기 (실제 주소가 아닌 가공된 주소(참조값)를 보여준다, 참조값을 객체의 주소와 같은 거라고 보면 됨)
		System.out.println(s1);
		
		System.out.println("----------");
		
		Student04 s2 = new Student04();
		s2.name = "을지문덕";
		s2.age = 30;
		
		System.out.println(s2.name + "," + s2.age);
		//객체의 참조값 (참조값이 다른 걸 보면 다른 객체인 걸 알 수 있다.)
		System.out.println(s2);
		
	}
}
