package kr.s19.object.finaltest;
class Me{
	int var = 100;
	//메서드에 final를 지정하면 자식클래스에서 메서드 오버라이딩이 불가능
	public final void setVar(int var) {//메서드에 final을 붙이는 건 내용을 바꾸지 말라는 뜻
		this.var = var;
	}
}
public class FinalMain02 extends Me{
	//부모클래스의 final 메서드는 재정의 불가
	/*@Override
	public void setVar(int var) {
		System.out.println(var);
	}
	*/
	public static void main(String[] args) {
		FinalMain02 fm = new FinalMain02();
		fm.setVar(500);
		System.out.println(fm.var);
	}
}
 