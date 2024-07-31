package kr.s03.operation;

public class SwitchMain04 {
	public static void main(String[] args) {
		/*
		 * [실습]
		 * 두 개의 정수와 연산자를 입력한 후 연산의 결과를 출력하시오.
		 * 
		 * [입력 예시]
		 * 첫번째 수:10
		 * 연산자:+
		 * 두번째 수:20
		 * 
		 * [출력 예시]
		 * 10 + 20 = 30
		 */

		java.util.Scanner input = new java.util.Scanner(System.in);
		int result=0;//연산의 결과값을 저장
		             //초기화를 하지 않는 경우 값이 없어서 결과를 도출해내지 못 할 수 있기 때문에 값을 명시
		             //이 때 값은 꼭 0일 필요는 없으나, 무색무취의 값을 넣어두기 위해 통상적으로 0값을 넣는다.
		System.out.print("첫번째 수:");
		int first = input.nextInt();
		System.out.print("연산자:");
		String operator = input.next();
		System.out.print("두번째 수:");
		int second = input.nextInt();

		switch(operator) {
		case "+":
			result = first + second; break;
		case "-":
			result = first - second; break;
		case "*":
			result = first * second; break;
		case "/":
			if(second!=0) {
				result = first / second; break;
			}else {
				System.out.println("0으로 나눌 수 없습니다.");
				//프로그램 종료
				System.exit(0);
			}//break;를 여기에 붙이지 않는다
		case "%":
			if(second!=0) {
				result = first % second; break;
			}else {
				System.out.println("0으로 나눌 수 없습니다.");
				//프로그램 종료
				System.exit(0);
			}
		default:
			System.out.println("잘못된 연산자 입력");
			System.exit(0);//프로그램 종료
		}

		System.out.println();//단순 줄바꿈

		System.out.printf("%d %s %d = %d", first, operator, second, result);
												//int result = 0; 값이 없으면 여기에 오류 발생 

		input.close();

	}
}
