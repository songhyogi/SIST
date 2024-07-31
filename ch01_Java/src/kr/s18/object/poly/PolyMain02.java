package kr.s18.object.poly;

//부모클래스
class A{
	public void make() {
		System.out.println("make 메서드");
	}
}
class B extends A{
	public void play() {
		System.out.println("play 메서드");
	}
}

public class PolyMain02 {
	public static void main(String[] args) {
		B bp = new B();
		bp.make();
		bp.play();
		
		A ap = bp;//자식클래스타입->부모클래스타입 형변환
		          //업캐스팅, 자동적으로 형변환
		ap.make();
		//ap.play(); 호출범위를 벗어나서 호출불가능
		
		B bp2 = (B)ap;//부모클래스타입->자식클래스타입 형변환
	                  //다운캐스팅, 명시적으로 형변환(캐스트연산자를 사용해야한다)
		bp2.make();
		bp2.play();
	}	
}

