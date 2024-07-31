package kr.s24.object.enumtest;

enum Gender{
	MALE,FEMALE; //세미콜론은 없어도 되고 있어도 되지만 메서드재정의할 때에는 세미콜론을 넣어줘야 한다.
	             //열거형 상수를 객체로 인식한다.
	//메서드 재정의
	@Override
	public String toString() {
		switch(this) {
		case MALE:
			return "남자";
		default :
			return "여자";
		}
	}
}

public class EnumMain03 {
	public static void main(String[] args) {
		System.out.println(Gender.MALE);
		System.out.println(Gender.FEMALE);
		System.out.println("-------------");
		
		System.out.println(Gender.MALE.toString());
		System.out.println(Gender.FEMALE.toString());
	}
}
