package kr.s23.object.inter;

//인터페이스
interface A1{
	public static final int W = 10;//원형
	int X = 20;//변수가 아니고 상수다 public static final이 생략된 형태
	static int Y = 30;//public final이 생략된 형태
	final int Z = 40;//public static이 생략된 형태
}

public class InterfaceMain01 {
	public static void main(String[] args) {
		//interface는 객체 생성 불가능
		//A1 ap = new A1();
		
		//상수라서 변경 불가능
		//A1.X = 50;

		System.out.println("W : " + A1.W);
		System.out.println("X : " + A1.X);
		System.out.println("Y : " + A1.Y);
		System.out.println("Z : " + A1.Z);
	}
}
