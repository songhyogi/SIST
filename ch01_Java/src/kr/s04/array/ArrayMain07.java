package kr.s04.array;

public class ArrayMain07 {
	public static void main(String[] args) {
		java.util.Scanner input = new java.util.Scanner(System.in);
		String[] course = {"국어","영어","수학"};
		int[] score = new int[course.length];//0:국어점수,1:영어점수,2:수학점수
		int sum = 0;
		float avg = 0.0f;
		
		//반복문을 이용해서 입력 처리
		for(int i=0;i<score.length;i++) {
			//입력값을 0~100만 가능하게 조건 체크
			do {
				System.out.print(course[i] + "=");
				score[i] = input.nextInt();
			}while(score[i] < 0 || score[i] > 100);
			
			//총점 구하기
			sum += score[i];
		}//end of for
		
		//평균 구하기
		avg = sum / (float)course.length;
		
		System.out.println();
		
		//과목의 점수 출력
		for(int i=0;i<score.length;i++) {
			                               //과목명      점수
			System.out.printf("%s = %d%n", course[i], score[i]);
		}
		
		System.out.printf("총점 = %d%n", sum);
		System.out.printf("평균 = %.2f", avg);
		
		input.close();
	}
}
