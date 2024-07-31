package kr.s03.operation;

public class CoffeeMain02 {
	public static void main(String[] args) {
		java.util.Scanner input = new java.util.Scanner(System.in);
		//커피 정보
		int price = 400;
		//커피 한 잔에 들어갈 양
		int content_coffee = 5;
		int content_cream = 3;
		int content_sugar = 1;

		//커피에 들어갈 재료의 현재 보유량
		int coffee = 10;
		int cream = 10;
		int sugar = 10;

		//투입한 금액 누적
		int amount = 0;
		//자판기 보유 동전
		int coin = 1000;
		while(true) {
			System.out.print("1:커피마시기,2:종료>");
			int num = input.nextInt();
			if(num==1) {
				System.out.print("동전을 넣으세요(커피값" + price + "원):");
				int payment = input.nextInt();
				//거스름돈 계산
				int change = payment - price;

				//커피 주문이 가능한지 조건 체크
				if(coffee < content_coffee) {
					System.out.println("커피가 부족합니다.");
					continue;
				}
				if(cream < content_cream) {
					System.out.println("프림이 부족합니다.");
					continue;
				}
				if(sugar < content_sugar) {
					System.out.println("설탕이 부족합니다.");
					continue;
				}
				if(payment < price) {
					System.out.println("투입한 동전 부족!!");
					continue;
				}
				if(coin < change) {
					System.out.println("거스름돈 부족!!");
					continue;
				}


				//커피 구매가 가능하기 때문에 연산
				coffee -= content_coffee; //커피 차감
				cream -= content_cream; //프림 차감
				sugar -= content_sugar; //설탕 차감
				coin -= change; //거스름돈 차감
				amount += payment; //투입한 금액 누적

				System.out.printf("거스름돈 : %,d원%n", change);
				System.out.println("맛 좋은 커피가 준비되었습니다.");

				System.out.println("====현재 자판기 정보====");
				System.out.printf("커피 : %d%n", coffee);
				System.out.printf("프림 : %d%n", cream);
				System.out.printf("설탕 : %d%n", sugar);
				System.out.printf("자판기 보유 동전 금액 : %,d원%n", coin);
				System.out.printf("투입한 동전 금액 : %,d원%n", amount);
				System.out.println("====================");

			}else if(num==2) {
				System.out.println("자판기 안녕~~~");
				break;
			}else {
				System.out.println("잘못 입력했습니다.");
			}
		}
	}
}

