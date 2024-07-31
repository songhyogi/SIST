package kr.s08.object.method;

public class MethodArgMain03 {
	/*
	 * Variable Arguments
	 * 자료형이 일치할 때 전달하고자 하는 값의 개수를 다르게 지정할 수 있음
	 * 전달되는 데이터는 내부적으로 배열로 인식함 (그래서 자료형이 일치해야 함)
	 */
	
	                          //n이 배열을 가르키고 n은 배열명
	public void argTest(int ... n) {
		for(int i=0;i<n.length;i++) {
			System.out.println("n[" + i + "]:" + n[i]);
		}
		System.out.println("----------");
	}
	
	public static void main(String[] args) {
		MethodArgMain03 ma = new MethodArgMain03();
		ma.argTest();//빈 배열
		ma.argTest(1);
		ma.argTest(10,20,30);
	}
}
