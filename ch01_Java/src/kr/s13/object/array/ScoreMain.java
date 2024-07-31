package kr.s13.object.array;

public class ScoreMain {
	public static void main(String[] args) {
		Score[] scoreArray = new Score[3];//score을 저장할 수 있는 배열 생성
		
		scoreArray[0] = new Score("홍길순",99,98,97);
		scoreArray[1] = new Score("박문수",89,91,92);
		scoreArray[2] = new Score("장영실",79,81,85);
		
		//반복문을 이용한 배열의 요소 출력
		System.out.println("이 름 국어 영어 수학 총 점 평균 등급");
		System.out.println("-------------------------");
		for(int i=0;i<scoreArray.length;i++) {
			System.out.printf("%s ", scoreArray[i].getName());
			System.out.printf("%d ", scoreArray[i].getKorean());
			System.out.printf("%d ", scoreArray[i].getEnglish());
			System.out.printf("%d ", scoreArray[i].getMath());
			System.out.printf("%d ", scoreArray[i].makeSum());
			System.out.printf("%d ", scoreArray[i].makeAvg());
			System.out.printf("%s%n", scoreArray[i].makeGrade());
		}
		System.out.println("-------------------------");
		
		//확장for문을 이용한 배열의 요소 출력
		for(Score s : scoreArray) {//scoreArray 내부에서 ....한 후 s에 주소가 담긴다
			System.out.printf("%s ", s.getName());
			System.out.printf("%d ", s.getKorean());
			System.out.printf("%d ", s.getEnglish());
			System.out.printf("%d ", s.getMath());
			System.out.printf("%d ", s.makeSum());
			System.out.printf("%d ", s.makeAvg());
			System.out.printf("%s%n", s.makeGrade());
		}
	}
}
