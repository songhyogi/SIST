package kr.s02.operator;

public class OperatorMain15 {
	public static void main(String[] args) {
		/*
		 * [실습]
		 * 3개의 정수를 입력 받아서 최대값, 최소값을 출력하는 프로그램을 작성하시오.
		 * 
		 * [입력 예시]
		 * 첫번째 정수:10
		 * 두번째 정수:5
		 * 세번째 정수:7
		 * 
		 * [출력 예시]
		 * 최대값:10
		 * 최소값:5
		 *
		 */
		java.util.Scanner input = new java.util.Scanner(System.in);
		
		int max;//최대값을 저장할 변수 선언
		int min;//최소값을 저장할 변수 선언
		System.out.print("첫번째 정수:");
		int first = input.nextInt(); 
		System.out.print("두번째 정수:");
		int second = input.nextInt();
		System.out.print("세번째 정수:");
		int third = input.nextInt();
		
		//최대값 구하기
		max = first > second ? first : second;
		max = max > third ? max : third;
		System.out.printf("최대값 : %d%n",max);
		
		//최소값 구하기
		min = first < second ? first : second;
		min = min < third ? min : third;
		System.out.printf("최소값 : %d%n", min);
		
		input.close();
		
	}

}
