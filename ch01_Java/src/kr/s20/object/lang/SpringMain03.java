package kr.s20.object.lang;

public class SpringMain03 {
	public static void main(String[] args) {
		String s1 = " aBa ";
		String s2 = "abc";
		int a = 100;
		String msg = null;
		
		msg = s1.toUpperCase();//toUpperCase는 대문자로 만드는 것
		System.out.println("msg:" + msg);//대문자 처리
		
		msg = s1.toLowerCase();//toLowerCase는 소문자로 만드는 것
		System.out.println("msg:" + msg);//소문자 처리
		
		msg = s1.replace("aB", "b");//old 문자를 new 문자로 대체
		System.out.println("msg:" + msg);
		
		msg = s1.trim();//앞뒤에 공백 제거 (중간에 있는 공백은 제거할 수 없다)
		System.out.println("msg:" + msg);
		
		//문자열 중에 메서드의 인자로 전달된 문자열이 포함되어 있는지 검증
		boolean f = s1.contains("aB");//aB가 있으면 true
		System.out.println("f = " + f);
		
		//메서드의 인자로 전달된 문자열로 시작하는지 검증
		f = s2.startsWith("ab");//ab로 시작하면 true
		System.out.println("f = " + f);
		
		//메서드의 인자로 전달된 문자열로 끝나는지 검증
		f = s2.endsWith("bc");//bc로 끝나면 true
		System.out.println("f = " + f);
		
		//int -> String
		msg = String.valueOf(a);//메서드를 이용한 방법
		msg = a + ""; //메서드를 이용하지 않고 빈문자열을 이용하는 방법
	}
}
