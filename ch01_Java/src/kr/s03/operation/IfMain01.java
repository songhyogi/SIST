package kr.s03.operation;

public class IfMain01 {
	public static void main(String[] args) {
		System.out.println("===단일if문===");
		int n = 10;

		//단일if문 : 조건이 true이면 if블럭 안의 수행문을 실행 (false이면 실행되지 않음)
		 //조건(논리연산자,비교연산자가 들어감)
		if(n>5) {//if블럭 시작
			System.out.println("n은 5보다 크다");
		}//if블럭 끝
		System.out.println("------------");

		//if문 블럭내의 수행문이 한 줄이면 {}블럭을 생략할 수 있음 
		//(헷갈릴 수 있으니까 들여쓰기 해주기 (ctrl+a 후 ctrl+i누르기))
		if(n<5) 
			System.out.println("~~~~~~~~~~");

		System.out.println("프로그램 종료!");
	}

}
