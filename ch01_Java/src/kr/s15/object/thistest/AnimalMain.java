package kr.s15.object.thistest;

public class AnimalMain {
	/*
	 * [실습]
	 * Animal
	 * 멤버 변수 : 이름(name), 나이(age), 비행 여부(fly) - private
	 * 멤버 메서드 : public set/get 메서드
	 * 생성자 : 인자가 있는 생성자, 인자가 없는 생성자
	 * 
	 * AnimalMain
	 * 인자가 있는 생성자를 이용해서 객체 생성
	 * 이름, 나이, 비행여부를 출력, 비행여부 true/false -> 가능/불가능
	 * 
	 * 인자가 없는 생성자를 이용해서 객체 생성
	 * 이름, 나이, 비행여부 설정
	 * 이름, 나이, 비행여부를 출력, 비행여부 true/false -> 가능/불가능
	 */
	
	public static void main(String[] args) {
		//인자가 있는 생성자를 이용해서 객체 생성
		Animal a1 = new Animal("기러기",3,true);
		System.out.println("이름 : " + a1.getName());
		System.out.println("나이 : " + a1.getAge());
		//System.out.println("비행여부 : " + (a1.isFly() ? "가능" : "불가능"));
		System.out.println("비행여부 : " + printFly(a1.isFly()));
		
		//인자가 없는 생성자를 이용해서 객체 생성
		Animal a2 = new Animal();
		a2.setName("기린");
		a2.setAge(12);
		a2.setFly(false);
		
		System.out.println("이름 : " + a2.getName());
		System.out.println("나이 : " + a2.getAge());
		//System.out.println("비행여부: " + (a2.isFly() ? "가능" : "불가능"));
		System.out.println("비행여부 : " + printFly(a2.isFly()));
	}
	
	//객체 생성없이 static한 메서드로 호출하는 방법
	private static String printFly(boolean fly) {
		return fly ? "가능" : "불가능"; //반환이 문자열이기 때문에 String으로
	}
}
