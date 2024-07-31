package kr.s16.object.extention;

//부모클래스
class People extends Object{//Object는 최상위클래스, Object는 가장 먼저 만들어졌다, Object는 생략가능
	public void eat() {
		System.out.println("식사하다");
	}
}
//자식클래스
class Student extends People{
	public void study() {
		System.out.println("공부하다");
	}
}

public class ExtentionMain02 {
	public static void main(String[] args) {
		Student s = new Student();
		s.eat();//People의 메서드를 상속 받아서 호출
		s.study();//Student의 메서드
		System.out.println(s.toString());//Object의 메서드를 상속 받아서 호출
	}
}
