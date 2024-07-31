package kr.s03.operation;

public class WhileMain07 {
	public static void main(String[] args) {
		//은행 프로그램
		java.util.Scanner input = new java.util.Scanner(System.in);
		long balance = 0L; //잔고
		
		while(true) {
			System.out.println("-------------------------------");
			System.out.println("1.예금 | 2.출금 | 3.잔고확인 | 4.종료");
			System.out.println("-------------------------------");
			
			System.out.print("메뉴선택>");
			int num = input.nextInt();
			
			if(num == 1) {
				System.out.print("예금액>");
				balance += input.nextLong();//누적
			}else if(num == 2) {
				System.out.print("출금액>");
				balance -= input.nextLong();//차감
			}else if(num == 3) {//잔고 확인
				System.out.printf("잔고 : %,d원%n", balance);
			}else if(num == 4) {
				System.out.println("프로그램 종료");
				break;
			}else {
				System.out.println("잘못 입력했습니다.");
			}
			
		}
		
		input.close();
	}
}
