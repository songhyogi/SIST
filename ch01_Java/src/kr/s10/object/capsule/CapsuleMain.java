package kr.s10.object.capsule;

class Capsule{
	//private은 같은 클래스내에서만 접근이 가능함
	//은닉화
	private int a;
	
	//캡슐화 (캡슐화의 장점: 데이터를 넣을 때 메서드를 통해서 검증할 수 있다.)
	public void setA(int n) {
		if(n>=0) {
			a = n;
		}else {
			System.out.println("음수는 허용되지 않습니다.");
		}
	}
	public int getA() {
		return a;
	}
}

public class CapsuleMain {
	public static void main(String[] args) {
		Capsule cap = new Capsule();
		//변수 a의 접근 지정자(제한자)가 private이기 때문에 같은 클래스에서는 호출이 가능하지만 다른 클래스에서는 호출이 불가능하다.
		//System.out.println(cap.a);
		cap.setA(-10); //데이터 저장
		System.out.println(cap.getA());//데이터 호출
	}
}

