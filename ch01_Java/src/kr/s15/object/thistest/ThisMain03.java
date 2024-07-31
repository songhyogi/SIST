package kr.s15.object.thistest;

public class ThisMain03 {
	//생성자
	public ThisMain03() {
		//생성자 내부에서 또 다른 생성자를 호출할 때 this()를 표시함
		//반복적인 코드를 제거하고 코드를 재사용하기 위해 또 다른 생성자를 호출해서 동작시킴
		//System.out.println("----"); this()전에 다른 코드를 실행하면 오류 발생
		this("여름");
		System.out.println("----");
	}
	
	public ThisMain03(String msg) {
		System.out.println(msg);//this는 이걸 가져온다
	}
	
	public ThisMain03(int a) {
		                  //int -> String
		                  //클래스명  static변수
		this(String.valueOf(a));
	}
	
	public static void main(String[] args) {
		ThisMain03 tm = new ThisMain03();
		ThisMain03 tm2 = new ThisMain03("겨울");
		ThisMain03 tm3 = new ThisMain03(50000);
	}
}
