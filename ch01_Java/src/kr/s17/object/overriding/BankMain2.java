package kr.s17.object.overriding;

import java.util.Scanner;

public class BankMain2 {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		//마이너스 계좌 생성
		MinusAccount ma = new MinusAccount("456-123","1234","이상화",10000L,100000L);
		ma.printAccount();
		
		//입금하기
		System.out.print("입금:");
		long money = input.nextLong();
		ma.deposit(money);
		ma.printAccount();
		
		//출금하기
		System.out.print("출금");
		money = input.nextLong();
		ma.withdraw(money);
		ma.printAccount();
		input.close();
	}
}
