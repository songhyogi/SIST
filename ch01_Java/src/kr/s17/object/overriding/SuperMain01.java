package kr.s17.object.overriding;

//부모클래스
class Parent{
	int a = 100;
	public void play() {
		System.out.println("Parent의 play 메서드");
	}
}
//자식클래스
class Child extends Parent{
	int a = 200;
	
	//생성자
	public Child() {
		System.out.println(super.a);
		super.play();
		System.out.println("---------");
	}
	@Override
	public void play() {
		System.out.println("Child의 play 메서드");
	}
}

public class SuperMain01 {
	public static void main(String[] args) {
		Child ch = new Child();
		System.out.println(ch.a);
		ch.play();
	}
}
