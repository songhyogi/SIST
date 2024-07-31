package kr.s02.operator;

public class OperatorMain02 {
	public static void main(String[] args) {
		System.out.println("===산술연산자===");
		
		System.out.println(1 + 1);
		System.out.println(10 * 3);
		System.out.println(10 / 3);//몫 구하기, 
		                          //(중요)정수를 정수로 나누면 정수가 됨 실수로 나오지 않음 
		                              //->자료형이 일치해야 되기 때문 
		                              //(10과 3은 정수니까 실수로 나오고 싶으면 아래처럼)
		System.out.println(10 % 3);//나머지 구하기
		System.out.println("-------------");
		               //double  int -> double 자동적으로 형변환
		System.out.println(10.0 / 3);
		               //double  int -> double 자동적으로 형변환
		System.out.println(2.4 * 4);
		
		
	}

}
