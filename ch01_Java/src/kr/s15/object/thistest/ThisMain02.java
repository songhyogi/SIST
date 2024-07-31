package kr.s15.object.thistest;

class ThisTest{
	//은닉화
	private int a;//멤버 변수
	//캡슐화
	public void setA(int a) {
		//멤버 변수 = 지역변수 (메서드를 만들 때 멤버변수와 지역변수명을 일치시키는 것이 일반적인 규칙이다)
		this.a = a;
	}
	public int getA() {
		return a;
	}
}

public class ThisMain02 {
	public static void main(String[] args) {
		ThisTest tt = new ThisTest();
		tt.setA(10);
		System.out.println(tt.getA());
	}
}
