package kr.s20.object.lang;

import java.util.Scanner;

public class SpringMain04 {
	public static void main(String[] args) {
		/*
		 * [실습]
		 * 입력 받은 문자열을 한 문자씩 읽어서 역순으로 표시
		 * [입력 예시]
		 * 문자열 : hello
		 * [출력 예시]
		 * olleh
		 */
		Scanner input = new Scanner(System.in);
		System.out.print("문자열:");
		String s = input.nextLine();

		for(int i=s.length()-1;i>=0;i--) {//마지막 인덱스 구하는 방법: 길이 -1
			System.out.print(s.charAt(i));

			input.close();
		}
	}
}