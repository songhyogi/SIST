package kr.s03.operation;

public class WhileMain03 {
	public static void main(String[] args) {
		java.util.Scanner input = new java.util.Scanner(System.in);
		
		System.out.print("단 입력:");
		int dan = input.nextInt();
		
		System.out.println(dan + "단");
		System.out.println("-------------");
		
		int i =1;//초기값 지정
		     //조건식
		while(i<=9) {
			System.out.println(dan + "*" + i + "=" + dan*i);
			//증감식
			i++;
		}
		
		input.close();
	}
}
