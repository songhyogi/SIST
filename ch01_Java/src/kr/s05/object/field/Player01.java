package kr.s05.object.field;

public class Player01 {
	//멤버 필드(속성)
	String name;
	int age;
	
	public static void main(String[] args) {
		//객체 선언 및 생성
		//참조자료형         //생성자료형
		Player01 p = new Player01();
		
		//객체의 멤버 변수에 값 할당
		p.name = "홍길동";
		p.age = 30;
		
		System.out.println("연주자의 이름 : " + p.name);
		System.out.println("연주자의 나이 : " + p.age);
	}
}
