package kr.s05.object.field;

public class Student01 {
	//멤버 필드(속성)
	String name;
	int age;
	
	public static void main(String[] args) {
		//객체 선언
		Student01 student;//student에 객체의 주소를 넣어준다, Student01은 자료형
		//객체 생성
		student = new Student01();//Student01()이 생성자
		
		//객체의 멤버 변수에 값 할당
		student.name = "홍길동";//student에 객체의 주소가 들어있으니까 name에 접근
		student.age = 21;
		
		//객체의 멤버 변수에 저장된 값을 출력
		System.out.println("학생의 이름 : " + student.name);
		System.out.println("학생의 나이 : " + student.age);
		
	}
}









