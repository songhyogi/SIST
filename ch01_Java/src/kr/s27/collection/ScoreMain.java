package kr.s27.collection;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ScoreMain {
	/*
	 * [실습]
	 * 메뉴 : 1.성적입력,2.성적출력,3.종료
	 * 메서드명 : 메뉴 callMenu(),
	 *         성적입력 inputScore(),
	 *         성적출력 printScore()
	 * 입력시 조건 체크 : 0부터 100까지만 입력 가능
	 */
	ArrayList<Score>list;
	BufferedReader br;

	public ScoreMain() {
		list = new ArrayList<Score>();
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			callMenu();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(br!=null)try {br.close();}catch(IOException e) {}//close쓰려면 입출력시 오류를 방지하기 위해 의무적으로 IOException을 사용해야 한다.
		}
	}
	//메뉴
	public void callMenu()throws IOException{
		while(true) {
			System.out.print("1.성적 입력,2.성적 출력,3.종료>");
			try {
				//String -> int
				int num = Integer.parseInt(br.readLine());
				if(num == 1) {//성적입력
					inputScore();
				}else if(num == 2) {//성적출력
					printScore();
				}else if(num == 3) {//종료
					System.out.println("프로그램 종료");
					break;
				}else {
					System.out.println("잘못 입력했습니다.");
				}
			}catch(NumberFormatException e) {
				System.out.println("숫자만 입력하세요!");
			}
		}
	}
	//성적입력
	public void inputScore()throws IOException{
		Score s = new Score();
		System.out.print("이름:");
		s.setName(br.readLine());
		s.setKorean(parseInputData("국어:"));
		s.setEnglish(parseInputData("영어:"));
		s.setMath(parseInputData("수학:"));

		list.add(s);
		System.out.println("성적을 등록했습니다.");

	}

	//성적입력 조건 체크
	public int parseInputData(String course)throws IOException{
		while(true) {
			System.out.print(course);//과목 표시
			try {
				int num = Integer.parseInt(br.readLine());
				//성적 유효 범위(0~100) 체크
				if(num<0 || num>100) {
					throw new ScoreValueException("0부터 100까지만 입력 가능");
				}
				return num;//정상 값 반환
			}catch(NumberFormatException e) {
				System.out.println("[숫자만 입력 가능]");
			}catch(ScoreValueException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	//성적출력
	public void printScore()throws IOException{
		System.out.println("성적목록 : 학생 수("+list.size()+")");
		System.out.println("----------------------------");
		System.out.println("이름\t국어점수\t영어점수\t수학점수\t평균\t등급");
		System.out.println("----------------------------");
		for(Score s : list) {
			System.out.printf("%s\t", s.getName());
			System.out.printf("%d\t", s.getKorean());
			System.out.printf("%d\t", s.getEnglish());
			System.out.printf("%d\t", s.getMath());
			System.out.printf("%d\t", s.makeSum());
			System.out.printf("%.2f\t", s.makeAvg());
			System.out.printf("%s\n", s.makeGrade());
		}
	}
	public static void main(String[] args) {
		new ScoreMain();
	}
}
