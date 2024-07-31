package kr.s11.object.constructor;

class Car2{//같은 패키지에 동일한 명의 클래스를 만드려고 하면 충돌난다
	String color;
	String gearType;
	int door;
	
	//생성자 오버로딩
	//생성자를 여러개 정의하는데 인자의 타입과 개수, 배치 순서를 기준으로 생성자를 구분
	public Car2() {}//기본생성자(원래는 자동으로 생성되지만 아래에 인자가 있는 생성자를 만들어 놨기 때문에 자동으로 생성이 안됨)
	
	public Car2(String c, String g, int d) {
		color = c;
		gearType = g;
		door = d;
	}
}

public class CarMain02 {
	public static void main(String[] args) {
		//객체 선언 및 생성
		Car2 car = new Car2();
		car.color = "골드";
		car.gearType = "auto";
		car.door = 5;
		
		System.out.println(car.color + "," + car.gearType + "," + car.door);
		
		Car2 car2 = new Car2("검정색","manual",4);
		System.out.println(car2.color + "," + car2.gearType + "," + car2.door);
	}
}
