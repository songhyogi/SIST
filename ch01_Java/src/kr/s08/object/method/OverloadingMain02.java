package kr.s08.object.method;

public class OverloadingMain02 {
	public void getLength(int n) {
		String s = String.valueOf(n); //int -> String
		getLength(s);//아래에 있는(String s)가 호출되기 때문에 String s의 수행문을 수행하게 된다.
		             //메서드 내에서 또 다른 메서드를 호출할 수 있다.
	}
	
	public void getLength(float n) {
		String s = String.valueOf(n);//float -> String
		getLength(s);
	}
	
	public void getLength(String s) {
		System.out.println(s + "의 길이 : " + s.length());
	}
	
	public static void main(String[] args) {
		OverloadingMain02 om = new OverloadingMain02();
		om.getLength(500000);//500000 -> "500000"
		om.getLength(3.14f);//3.14f -> "3.14"
		om.getLength("Hello");//Hello가 s에 전달
	}
}
