package kr.s17.object.overriding;

//부모클래스
class Mother{
	public String getLunch() {
		return "밥";
	}
}

//자식클래스
class Son extends Mother{
	
}
class Daughter extends Mother{
	//메서드 오버라이딩(메서드 재정의)
	@Override
	public String getLunch() {
		return "빵";
	}
}

public class OverridingMain02 {
	public static void main(String[] args) {
		Son s = new Son();
		System.out.println("아들은 " + s.getLunch() + "을 먹습니다.");
		
		Daughter d = new Daughter();
		System.out.println("딸은 " + d.getLunch() + "을 먹습니다.");
	}
}
