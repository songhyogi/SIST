package kr.s12.object.constructor;

public class AccountMain {
	String accountNo; //계좌번호
	String ownerName;//예금주
	int balance;//잔고

	//생성자
	public AccountMain(String a, String o, int b) {
		accountNo = a;
		ownerName = o;
		balance = b;
		System.out.println(ownerName + "님 계좌가 개설되었습니다.");
	}

	//예금하기
	public void deposit(int amount) {
		if(amount <= 0) {
			System.out.println("0보다 크게 입력해야 합니다.");
			//void형 메서드에서 특정 조건일 때 메서드를 빠져나감(메서드가 종료된다는 뜻), else를 만들지 않아도 돼서 코드를 절약할 수 있음
			return;//void형일 경우에만 다중if문을 사용할 건지 특정조건일 때 return을 사용해서 메서드를 빠져나갈건지 선택
		}
		balance += amount;
		System.out.println("입금이 완료되었습니다.");
	}

	//출금하기
	public void withdraw(int amount) {
		if(amount <= 0) {
			System.out.println("0보다 크게 입력해야 합니다.");
			return;
		}
		if(balance < amount) {
			System.out.println("잔고가 부족합니다.");
			return;
		}
		balance -= amount;
		System.out.println("출금이 완료되었습니다.");
	}

	public static void main(String[] args) {
		//계좌 생성
		AccountMain account1 = new AccountMain("123-456", "홍길동", 10000);
		System.out.println("계좌번호 : " + account1.accountNo);
		System.out.println("예금주 : " + account1.ownerName);
		System.out.printf("잔고 : %,d원%n", account1.balance);
		
		System.out.println("-----------------");
		
		//예금하기
		account1.deposit(5000);
		//출금하기
		account1.withdraw(10000);
		
		System.out.println("계좌번호 : " + account1.accountNo);
		System.out.println("예금주 : " + account1.ownerName);
		System.out.printf("잔고 : %,d원%n", account1.balance);
		
	}
}


