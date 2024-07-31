package kr.s08.object.method;

import java.util.Scanner;

class Account{
	//멤버 변수
	String account_num;//계좌번호 //String앞에 java.lang.을 붙이지 않는다 java.lang이 기본패키지에 String이 기본클래스이기 때문에 자동으로 인식한다.
	String name;//예금주
	int balance;//잔고
	//멤버 메서드
	//예금하기
	public void deposite(int money) {
		if(money <= 0) {
			System.out.println("입금액은 0보다 크게 입력하세요!");
		}else {
			balance += money;//누적
			System.out.println("입금이 완료되었습니다.");
		}
	}
	//출금하기
	public void withdraw(int money) {
		if(money <= 0) {
			System.out.println("출금액은 0보다 크게 입력하세요!");
		}else if(balance < money) {
			System.out.println("잔액이 부족합니다.");
		}else {
			balance -= money;//차감
			System.out.println("출금이 완료되었습니다.");
		}	
	}
	//계좌정보
	public void printAccount() {
		System.out.println("계좌번호:" + account_num);
		System.out.println("예금주: " + name);
		System.out.printf("잔고 : %,d원%n", balance);
	}
}

public class BankMain {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		//계좌 생성
		Account account = new Account();
		//계좌 기본 정보 입력
		System.out.println("계좌 기본 정보를 입력해서 계좌를 생성합니다.");
		System.out.print("계좌번호:");
		account.account_num = input.nextLine();
		System.out.print("예금주:");
		account.name = input.nextLine();
		System.out.print("잔고:");
		account.balance = input.nextInt();
		
		System.out.println(account.name + "님의 계좌가 개설되었습니다.");
		
		while(true) {
			System.out.println("------------------------");
			System.out.println("1.예금,2.출금,3.잔고 확인,4.종료");
			System.out.println("------------------------");
			System.out.print("메뉴 선택>");
			int num = input.nextInt();
			if(num == 1) {//예금
				System.out.print("예금액>");
				account.deposite(input.nextInt());
			}else if(num == 2) {//출금
				System.out.print("출금액>");
				account.withdraw(input.nextInt());
			}else if(num == 3) {//잔고 확인
				account.printAccount();
			}else if(num == 4) {//종료
				System.out.println("프로그램 종료");
				break;
			}else {
				System.out.println("잘못 입력했습니다.");
			}
			
		}
		
		input.close();
	}
}//프로그램이 종료되면 객체는 소멸된다. 프로그램이 작동하고 있을 때만 사용 가능
