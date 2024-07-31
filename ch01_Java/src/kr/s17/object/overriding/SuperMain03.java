package kr.s17.object.overriding;

//부모클래스
class People2{
	int a;
	public People2(int a) {//인자가 있는 생성자
		this.a = a;
	}
}
//자식클래스
class Student2 extends People2{
	//생성자
	public Student2() {
		//부모클래스의 자료형이 int인 인자를 가지고 있는 생성자 호출
		super(700);
	}
}

public class SuperMain03 {
	public static void main(String[] args) {
		Student2 st = new Student2();
		System.out.println(st.a);
	}
}
