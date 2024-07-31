package kr.s38.jdbc.score;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class ScoreMain {
	private BufferedReader br;
	private ScoreDAO dao;
	//생성자
	public ScoreMain() {
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			dao = new ScoreDAO();
			callMenu();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//자원정리
			if(br!=null)try {br.close();}catch(IOException e) {}
		}
	}
	//메뉴
	public void callMenu()throws IOException{
		while(true) {
			System.out.print("1.입력,2.목록,3.상세정보,4.수정,5.삭제,6.종료>");
			try {
				int no = Integer.parseInt(br.readLine());
				if(no == 1) {//입력
					System.out.print("이름:");
					String name = br.readLine();
					int korean = parseInputData("국어:");
					int english = parseInputData("영어:");
					int math = parseInputData("수학:");
					
					int sum = makeSum(korean, english, math);
					int avg = makeAvg(sum);
					String grade = makeGrade(avg);
					
					dao.insertScore(name, korean, english, math, sum, avg, grade);
				}else if(no == 2) {//목록
					dao.selectScore();
				}else if(no == 3) {//상세정보
					dao.selectScore();
					System.out.print("성적 번호:");
					int num = Integer.parseInt(br.readLine());
					System.out.println("----------------");
					dao.selectDetailScore(num);
				}else if(no == 4) {//수정
					dao.selectScore();
					System.out.print("수정할 성적 번호:");
					int num = Integer.parseInt(br.readLine());
					dao.selectDetailScore(num);
					
					System.out.print("이름:");
					String name = br.readLine();
					System.out.print("국어:");
					int korean = Integer.parseInt(br.readLine());
					System.out.print("영어:");
					int english = Integer.parseInt(br.readLine());
					System.out.print("수학:");
					int math = Integer.parseInt(br.readLine());
					
					//총점 구하기
					int sum = makeSum(korean, english, math);
					//평균 구하기
					int avg = makeAvg(sum);
					//등급 구하기
					String grade = makeGrade(avg);
					
					dao.updateScore(num, name, korean, english, math, sum, avg, grade);
				}else if(no == 5) {//삭제
					dao.selectScore();
					System.out.print("삭제할 성적 번호:");
					int num = Integer.parseInt(br.readLine());
					
					dao.deleteScore(num);
				}else if(no == 6) {//종료
					System.out.println("프로그램 종료");
					break;
				}else {
					System.out.println("잘못 입력했습니다.");
				}
			}catch(NumberFormatException e) {
				System.out.println("숫자만 입력 가능");
			}
		}
	}
	//총점 구하기
	public int makeSum(int korean,int english,int math) {
		return korean + english + math;
	}
	//평균 구하기
	public int makeAvg(int sum) {
		return sum / 3;
	}
	//등급 구하기
	public String makeGrade(int avg) {
		String grade;
		switch(avg/10) {
		case 10:
		case 9: grade = "A"; break;
		case 8: grade = "B"; break;
		case 7: grade = "C"; break;
		case 6: grade = "D"; break;
		default : grade = "F"; 
		}
		return grade;
	}
	//성적 범위 체크(0~100)
	public int parseInputData(String course)throws IOException{
		while(true) {
			System.out.print(course);
			try {
				int num = Integer.parseInt(br.readLine());
				if(num<0 || num>100) {
					throw new ScoreValueException("0부터 100사이만 입력 가능");
				}
				return num;
			}catch(NumberFormatException e) {
				System.out.println("숫자만 입력 가능");
			}catch(ScoreValueException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	public static void main(String[] args) {
		new ScoreMain();
	}
}
