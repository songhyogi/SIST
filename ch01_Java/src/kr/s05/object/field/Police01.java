package kr.s05.object.field;

public class Police01 {
	//멤버 필드(속성) ->main 함수에 만드는 것이 아니라 클래스 영역에 만든다.
	String name;
	int age;
	
	//main 함수가 없으면 객체를 만들 수 없다.
	public static void main(String[] args) {
		//객체 선언 및 생성
		Police01 police = new Police01();
		//객체의 멤버 변수에 값 할당
		police.name = "김유신";
		police.age = 45;
		
		//객체의 멤버 변수에 저장된 값 읽기
		System.out.println("경찰의 이름 : " + police.name);
		System.out.println("경찰의 나이 : " + police.age);
	}
}
