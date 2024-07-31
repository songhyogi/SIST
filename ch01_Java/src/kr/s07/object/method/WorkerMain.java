package kr.s07.object.method;

class Worker{
	/*
	 * [실습]
	 * Worker
	 * 1)멤버 변수 : 직원 이름(name),급여(money),계좌 잔고(balance)
	 * 2)멤버 메서드 : work(실행하면 money에 1000원 누적)
	 *             deposite(실행하면 money에 저장된 돈을 balance에 누적시키고 money는 0처리)
	 * WorkerMain의 main
	 * 1)Worker 객체 생성
	 * 2)직원의 이름 지정
	 * 3)10번 일하는데 번 돈이 3000원일 때마다 계좌에 저축
	 * 4)직원 이름,현재 계좌에 입금되지 않고 남아 있는 급여(money),계좌 잔고(balance)를 출력하시오.
	 */
	//멤버 변수
	String name;
	int money;
	int balance;
	
	//멤버 메서드
	public void work() {//누적이니까 반환하는 데이터가 없어서 void로 //일하는 메서드
		money+=1000;
	}
	public void deposite() {//저축하는 메서드
		balance += money;
		money = 0;
	}
}

public class WorkerMain {
	public static void main(String[] args) {
		Worker worker = new Worker();
		worker.name = "홍길동";
		
		//10번 일하는데 번 돈이 3000원이 되면 저축
		for(int i=1;i<=10;i++) {
			worker.work();
			if(worker.money>=3000) {//3000원마다 저축 worker.money%3000==0로 해도 됨
				worker.deposite();
			}
		}
		System.out.println("직원이름: " + worker.name);
		System.out.printf("현재 입금되지 않고 남아 있는 급여 : %,d원%n", worker.money);
		System.out.printf("계좌잔고: %,d원%n", worker.balance);
	}
}

