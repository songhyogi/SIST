package kr.s18.object.poly;
//부모클래스
class Product{
	int price;//제품가격
	int bonusPoint;//제품 구매시 제공하는 보너스 점수
	//생성자
	public Product(int price) {
		this.price = price;
		bonusPoint = price/10;
	}
	public String getName() {//부모에 만들어 놓고 자식클래스에 재정의를 해서 상품이름을 변경하면 된다.
		return "상품";
	}
}
class Tv extends Product{
	//생성자
	public Tv() {
		super(100);
	}
	@Override
	public String getName() {
		return "Tv";
	}
}
class Computer extends Product{
	//생성자
	public Computer() {
		super(200);
	}
	@Override
	public String getName() {
		return "컴퓨터";
	}
}
class Audio extends Product{
	public Audio() {
		super(300);
	}
	@Override
	public String getName() {
		return "오디오";
	}
}
//구매자
class Buyer{
	private int money = 1000; //구매자 보유 금액
	private int bonusPoint = 0; //제품 구매시 누적 포인트 점수
	//구매하기
	public void buy(Product p) {
		if(money < p.price) {
			System.out.println("잔액이 부족하여 물건을 구매할 수 없습니다.");
			return;
		}
		money -= p.price;
		bonusPoint += p.bonusPoint;
		
		System.out.println(p.getName() + "을/를 구입했습니다.");
		System.out.println("현재 남은 돈은 " + money + "만원입니다.");
		System.out.println("현재 보너스점수는 " + bonusPoint + "점입니다.");
	}
}
public class PolyMain04 {
	public static void main(String[] args) {
		//구매자 생성
		Buyer b = new Buyer();
		//제품 생성
		Tv tv = new Tv();
		Computer com = new Computer();
		Audio au = new Audio();
		//제품 구매
		b.buy(tv);//Tv->Product 업캐스팅 자동적으로 형변환
		b.buy(com);//Computer->Product 업캐스팅 자동적으로 형변환
		b.buy(au);//Audio->Product 업캐스팅 자동적으로 형변환
	}
}
