package kr.s36.jdbc.note;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class NoteMain {
	private BufferedReader br;
	private NoteDAO note;
	//생성자
	public NoteMain() {
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			note = new NoteDAO(); //NoteDAO 객체 생성
			//메뉴 호출
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
			System.out.print("1.글쓰기,2.목록 보기,3.상세글 보기,4.글수정,5.글삭제,6>종료");
			try {
				int no = Integer.parseInt(br.readLine());
				if(no == 1) {//글쓰기
					System.out.print("이름:");
					String name = br.readLine();
					System.out.print("비밀번호:");
					String passwd = br.readLine();
					System.out.print("제목:");
					String subject = br.readLine();
					System.out.print("내용:");
					String content = br.readLine();
					System.out.print("이메일:");
					String email = br.readLine();
					
					//NoteDAO의 insertInfo 메서드를 호출해서 입력받은 데이터 전달
					note.insertInfo(name, passwd, subject, content, email);
				}else if(no == 2) {//목록 보기
					note.selectInfo();
				}else if(no == 3) {//상세글 보기
					note.selectInfo();//먼저 목록을 보고 글번호를 확인하게 만든 후 번호를 선택하게 한다.
					
					System.out.println("선택한 글의 번호:");
					int num = Integer.parseInt(br.readLine());
					System.out.println("--------------");
					
					note.selectDetailInfo(num);
				}else if(no == 4) {//글수정
					//먼저 목록을 보고 글 번호 확인 후 그 번호의 상세글을 보고 그 데이터에서 변경할 것과 변경하지 않을 것을 결정하게 한다.
					note.selectInfo();
					
					System.out.print("수정할 글의 번호:");
					int num = Integer.parseInt(br.readLine());
					note.selectDetailInfo(num);
					
					System.out.println("------------");
					
					System.out.print("이름:");
					String name = br.readLine();
					System.out.print("비밀번호:");
					String passwd = br.readLine();
					System.out.print("제목:");
					String subject = br.readLine();
					System.out.print("내용:");
					String content = br.readLine();
					System.out.print("이메일:");
					String email = br.readLine();
					
					note.updateInfo(num, name, passwd, subject, content, email);
					
				}else if(no == 5) {//글삭제
					note.selectInfo();
					
					System.out.print("삭제할 글의 번호:");
					int num = Integer.parseInt(br.readLine());
					
					note.deleteInfo(num);
				}else if(no == 6) {
					System.out.println("프로그램을 종료합니다.");
					break;
				}else {
					System.out.println("잘못 입력했습니다.");
				}
			}catch(NumberFormatException e) {
				System.out.println("숫자만 입력 가능!!");
			}
		}
	}
	public static void main(String[] args) {
		//DAO쪽에 메서드가 있기 때문에 main에서 객체생성만 해주면 된다.
		new NoteMain();
	}
	
}
