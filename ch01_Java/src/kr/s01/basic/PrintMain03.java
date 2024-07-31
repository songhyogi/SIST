package kr.s01.basic;

public class PrintMain03 {
	public static void main(String[] args) {
		//문자 : 한 문자
		System.out.println('A');
		System.out.println('강');
		//문자열에 ''를 사용할 수 없음
		//System.out.println('도시');
		
		//문자열 : 한 문자 이상의 문자들
		System.out.println("Z");
		System.out.println("city");
		System.out.println("한강");
		
		//숫자(정수)
		System.out.println(23);
		System.out.println("23");//문자열 (연산할 수 없음)
		
		//숫자(실수)
		System.out.println(3.14);
		System.out.println("3.14");//문자열
		
		//논리값(boolean)
		System.out.println(true);//참값
		System.out.println(false);//거짓값
		System.out.println("true");//문자열 (참이라는 의미가 없어짐)
		
	}
}
