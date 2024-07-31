package kr.s13.object.array;

public class Score {
	//은닉화
	private String name;
	private int korean;
	private int english;
	private int math;
	
	//생성자
	public Score(String name, int korean, int enlish, int math) {
		//멤버 변수 = 지역 변수
		this.name = name;
		this.korean = korean;
		this.english = enlish;
		this.math = math;
	}
	
	//총점 구하기
	public int makeSum() {
		return korean + english + math;
	}
	//평균 구하기
	public int makeAvg() {
		return makeSum()/3;
	}
	
	//등급 구하기
	public String makeGrade() {
		String grade;//변수
		switch(makeAvg()/10) {
		case 10:
		case 9:
			grade = "A"; break; //return "A"; 위에 있는 변수(String grade;)를 만들지 않을 경우 이렇게 해도 상관 없다.
		case 8:
			grade = "B"; break;//return "B";
		case 7:
			grade = "C"; break;//return "C";
		case 6:
			grade = "D"; break;//return "D";
			default : 
				grade = "F";//return "F";
		}
		return grade;
	}
	
	//private에 접근을 못하니까 public한 메서드를 만들어준다
	public String getName() {
		return name;
	}
	public int getKorean() {
		return korean;
	}
	public int getEnglish() {
		return english;
	}
	public int getMath() {
		return math;
	}
}
