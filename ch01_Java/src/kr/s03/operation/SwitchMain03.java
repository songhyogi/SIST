package kr.s03.operation;

public class SwitchMain03 {
	public static void main(String[] args) {
		java.util.Scanner input = new java.util.Scanner(System.in);
		int score;
		char grade;
		
		System.out.print("성적 입력:");
		score = input.nextInt();
		
		if(score<0 || score>100) {
			System.out.println("성적은 0 ~ 100만 입력 가능");
			//프로그램 종료
			System.exit(0);
		}
		
		/*
		0~100
		A : 100    -> 10
		A : 90~99  -> 9
		B : 80~89  -> 8
		C : 70~79  -> 7
		D : 60~69  -> 6
		F : 0~59
		
		case를 줄이기 위해서 연산을 해준다 
		그래서 10으로 나눔
		정수를 정수로 나누면 정수가 된다
		*/
		
		switch(score/10) {
		case 10://동일한 수행문은 지움
		case 9:
			grade = 'A'; break;
		case 8:
			grade = 'B'; break;
		case 7:
			grade = 'C'; break;
		case 6:
			grade = 'D'; break;
		default:
			grade = 'F';
		}

		System.out.println();//단순 줄바꿈
		System.out.printf("성적 : %d%n", score);
		System.out.printf("등급 : %c%n", grade);

		input.close();
	}
}