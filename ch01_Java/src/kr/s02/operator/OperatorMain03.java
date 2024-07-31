package kr.s02.operator;

public class OperatorMain03 {
	public static void main(String[] args) {
		/*
		 * [실습]
		 * 변수 선언시 자료형은 모두 int로 지정하고
		 * 변수 korean,english,math를 선언하고 90,95,88로 초기화한다.
		 * 총점을 구해서 변수 sum에 저장, 평균을 구해서 변수 avg에 저장
		 * 
		 * [출력예시]
		 * 국어 : 90점
		 * 영어 : 95점
		 * 수학 : 88점
		 * 총점 :   점
		 * 평균 :   점
		 */
		
		int korean = 90, english = 95, math = 88;
		//총점 구하기
		int sum = korean + english + math;
		//평균 구하기
		int avg = sum / 3;
		
		System.out.printf("국어 : %d점%n", korean);
		System.out.printf("영어 : %d점%n", english);
		System.out.printf("수학 : %d점%n", math);
		System.out.printf("총점 : %d점%n", sum);
		System.out.printf("평균 : %d점%n", avg);
				
		
	}

}
