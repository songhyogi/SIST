package kr.s02.operator;

public class OperatorMain10 {
	public static void main(String[] args) {
		System.out.println("===조건(삼항)연산자===");
		
		int a=5, b=10;
		int max; //최대값을 저장할 변수 선언
		int min; //최소값을 저장할 변수 선언
		
		//최대값 구하기
		     //조건   참값  거짓값
		max = a > b ? a : b;
		System.out.println("max = " + max);
		
		//최소값 구하기
		min = a < b ? a : b;
		System.out.println("min = " + min);
		
		
	}

}
