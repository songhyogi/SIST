package kr.s20.object.lang;

public class MathMain {
	public static void main(String[] args) {
		int a = Math.abs(-10);
		System.out.println("절대값 : " + a);
		
		double b = Math.ceil(3.3);//ceil이나 floor은 double타입으로 반환된다.
		System.out.println("올림 : " + b);
		
		double c = Math.floor(3.7);
		System.out.println("버림 : " + c);
	
		int d = Math.round(3.7f);//인자는 float타입으로 
		System.out.println("반올림 : " + d);//정수타입으로 반환한다.
		
		int e = Math.max(3, 5);
		System.out.println("최대값 : " + e);
		
		int f = Math.min(4, 7);
		System.out.println("최소값 : " + f);
	}
}
