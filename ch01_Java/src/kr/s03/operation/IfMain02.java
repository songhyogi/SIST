package kr.s03.operation;

public class IfMain02 {
	public static void main(String[] args) {
		java.util.Scanner input = new java.util.Scanner(System.in);//시작

		System.out.print("정수형 데이터 한 개 입력:");
		int a = input.nextInt();

		//if~else
		//조건이 true이면 if블럭의 수행문을 실행, 조건이 false이면 else블럭의 수행문을 실행
		if(a%2==1) {
			System.out.println("홀수이다.");
		}else {
			System.out.println("짝수이다.");
		}

		input.close();//끝
	}

}
