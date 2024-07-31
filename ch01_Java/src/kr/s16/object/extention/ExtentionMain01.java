package kr.s16.object.extention;

//부모클래스
class Parent{
	int a = 100;
}
//자식 클래스
class Child extends Parent{
	int b = 200;
}
public class ExtentionMain01 {
	public static void main(String[] args) {
		Child ch = new Child();//Child객체 생성 
		                       //상속관계이기 때문에 Child객체만 생성해도 Parent도 사용할 수 있다.
		System.out.println(ch.a);//Parent의 a
		System.out.println(ch.b);//Child의 b
	}
}
