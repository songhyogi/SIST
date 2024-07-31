package kr.s07.object.method;

public class StudentMain {
	//멤버 필드(속성)
	String name;
	int korean;
	int english;
	int math;
	
	//멤버 메서드(동작)
	//총점 구하기
	public int makeSum() {
		return korean + english + math;
	}
	//평균 구하기
	public int makeAverage() {
		return makeSum() / 3; 
	}
	//등급 구하기
	public String makeGrade() {
		String grade;
		switch(makeAverage()/10) {
		case 10:
		case 9:
			grade = "A"; break;
		case 8:
			grade = "B"; break;
		case 7:
			grade = "C"; break;
		case 6:
			grade = "D"; break;
			default:
				grade = "F";
		}
		return grade;
	}
	
	public void printScore() {
		System.out.println("이름 : " + name);
		System.out.println("국어 : " + korean);
		System.out.println("영어 : " + english);
		System.out.println("수학 : " + math);
		System.out.println("총점 : " + makeSum());
		System.out.println("평균 : " + makeAverage());
		System.out.println("등급 : " + makeGrade());
	}
	
	public static void main(String[] args) {//main은 메소드를 호출하기 위한 것이지 main은 객체에 포함되어 있지 않다. 같은 객체에 있는 것은 직접 호출 가능
		StudentMain student = new StudentMain();
		student.name = "홍길동";
		student.korean = 98;
		student.english = 97;
		student.math = 96;
		student.printScore();
		
		System.out.println("----------------");
		
		StudentMain student2 = new StudentMain();
		student2.name = "김영희";
		student2.korean = 91;
		student2.english = 99;
		student2.math = 79;
		student2.printScore();
		
	}
}
