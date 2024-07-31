package kr.s16.object.extention;

//부모클래스
class A{
	int x = 100;
	private int y = 200;
	
	public int getY() {
		return y;
	}
}

//자식클래스
class B extends A{
	int z = 300;
}

public class ExtentionMain03 {
	public static void main(String[] args) {
		B bp = new B();
		System.out.println(bp.x);
		System.out.println(bp.getY());
		System.out.println(bp.z);
	}
}
