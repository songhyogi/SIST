package kr.s08.object.method;

public class MethodArgMain01 {
	//인자 전달 방식 : 값 호출(call by value)
	//기본 자료형의 값을 인자로 전달하는 방식. 값을 복사하여 전달
	public int increase(int n) {//객체 생성을 하지 않고서는 메서드를 사용할 수 없다
		++n;
		return n;
	}
	
	public static void main(String[] args) {
		int var1 = 100;
		MethodArgMain01 ma = new MethodArgMain01();
		int var2 = ma.increase(var1);
		System.out.println("var1 : " + var1 + ", var2 : " + var2);
	}
}
