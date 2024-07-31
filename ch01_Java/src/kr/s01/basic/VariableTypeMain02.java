package kr.s01.basic;

public class VariableTypeMain02 {
	public static void main(String[] args) {
		//확장 특수 출력 문자(escape sequence)
		char single = '\'';//특수문자를 일반문자로 바꾼 것
		System.out.println(single);
		
		String str = "오늘은 \"월요일\"입니다.";
		System.out.println(str);
		
		//문자열("")에 '를 표시하면 자동으로 일반문자로 변환됨
		String str2 = "오늘은 '서울'에 비가 와요!";
		System.out.println(str2);
		
		String str3 = "C:\\javaWork\\workspace";//\를 하나만 쓰면 특수문자로 인식돼서 두개 써줘야 함
		System.out.println(str3);
		
		String str4 = "오늘은 월요일\n내일은 화요일";//printf에도 \n사용할 수 있지만 %n는 printf에서만 사용가능
		System.out.println(str4);
		
		String str5 = "이름\t나이\t취미";
		System.out.println(str5);
		
		
	}

}
