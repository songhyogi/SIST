package kr.s19.object.finaltest;
class A{
	//멤버 필드
	final int NUM = 10; //상수
	public static final int NUMBER = 20; //static한 상수
}
public class FinalMain01 {
	public static void main(String[] args) {
		A ap = new A();
		//상수는 변경 불가능
		//ap.NUM = 20;
		System.out.println(ap.NUM);
		
		//static 상수 호출 (주로 사용함)
		System.out.println(A.NUMBER);//객체생성하지 않고 호출만 해도 실행된다.
		
		final int NO = 30; //지역적인 상수 (잘 사용하지 않음)
		System.out.println(NO);
	}
}
