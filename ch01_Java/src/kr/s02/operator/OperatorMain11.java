package kr.s02.operator;

public class OperatorMain11 {
	public static void main(String[] args) {
		char ch = 'b';
		String str;//대문자인지 판단한 결과를 저장할 문자열 변수 선언
		
		str = ch>=65 && ch<=90 ? "대문자임" : "대문자가 아님";
		
		System.out.println(ch + "=>" + str);
		
		char ch1 = 'M';
		String str1;//대문자인지 판단한 결과를 저장할 문자열 변수 선언
		
		str1 = ch1>=65 && ch1<=90 ? "대문자임" : "대문자가 아님";
		
		System.out.println(ch1 + "=>" + str1);
		
		str1 = ch1>='A' && ch1<='Z' ? "대문자임" : "대문자가 아님";
		
		System.out.println(ch1 + "=>" + str1);
				
	
	}

}
