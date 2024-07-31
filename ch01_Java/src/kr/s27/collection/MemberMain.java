package kr.s27.collection;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class MemberMain {
	/*
	 * [실습]
	 * 메뉴 : 1.회원정보 입력,2.회원정보 출력,3.종료
	 * 메서드명 : 메뉴 callMenu()
	 *         회원정보 입력 register()
	 *         회원정보 출력 printUserInfo()
	 * 입력시 조건 체크 : 나이는 1살이상 입력 가능
	 */
	ArrayList<Member>list;//생성자에서 초기화하지 않고 여기서 한번에 초기화해도 되지만 멤버변수는 반드시 생성자 밖에서 선언해야한다.
	BufferedReader br;

	//생성자
	public MemberMain() {
		list = new ArrayList<Member>();
		try {
			br = new BufferedReader(new InputStreamReader(System.in));//입력받을 수 있게 객체생성
			callMenu();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(br!=null)try {br.close();}catch(IOException e) {}
		}
	}
	//메뉴
	public void callMenu()throws IOException{
		while(true) {
			System.out.print("1.회원정보 입력,2.회원정보 출력,3.종료>");
			try {
				//String -> int
				int num = Integer.parseInt(br.readLine());
				if(num == 1) {//회원정보 입력
					register();
				}else if(num == 2) {//회원정보 출력
					printUserInfo();
				}else if(num == 3) {//종료
					System.out.println("프로그램 종료");
					break;
				}else {
					System.out.println("잘못 입력했습니다.");
				}
			}catch(NumberFormatException e){
				System.out.println("숫자만 허용");
			}
		}
	}

	//회원정보 입력
	public void register()throws IOException{
		Member mb = new Member();
		System.out.print("이름:");
		mb.setName(br.readLine());
		
		mb.setAge(parseInputData("나이:"));
		
		System.out.print("직업:");
		mb.setJob(br.readLine());
		
		System.out.print("주소:");
		mb.setAddress(br.readLine());
		
		System.out.print("전화번호:");
		mb.setPhone(br.readLine());
		
		//Member를 ArrayList에 저장
		list.add(mb);
		System.out.println("회원 정보 1건이 추가되었습니다.");
	}
	
	//나이 조건
	public int parseInputData(String item)throws IOException{
		while(true) {
			System.out.print(item);
			try {
				int age = Integer.parseInt(br.readLine());
				if(age<=0) {
					System.out.println("나이는 1살이상 입력 가능");
					continue;
				}
				return age;
			}catch(NumberFormatException e) {
				System.out.println("[숫자만 입력 가능]");
			}
		}
	}

	//회원정보 출력
	public void printUserInfo() {
		System.out.println("회원목록 : 총회원수("+list.size()+")");
		System.out.println("----------------------------");
		System.out.println("이름\t나이\t직업\t주소\t전화번호");
		System.out.println("----------------------------");
		for(Member m : list) {
			System.out.printf("%s\t", m.getName());
			System.out.printf("%d\t", m.getAge());
			System.out.printf("%s\t", m.getJob());
			System.out.printf("%s\t", m.getAddress());
			System.out.printf("%s%n", m.getPhone());
		}
	}

	public static void main(String[] args) {
		new MemberMain();//MemberMain 객체 생성
	}
}
