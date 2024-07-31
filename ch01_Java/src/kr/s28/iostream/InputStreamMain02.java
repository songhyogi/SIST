package kr.s28.iostream;

import java.io.IOException;

public class InputStreamMain02 {
	public static void main(String[] args) {
		int input = 0;//입력하는 데이터를 input에 넣어주려고 한다
		try {
			//명시적으로 -1을 만드려면 ctrl + z
			while((input = System.in.read())!=-1) {
				System.out.println(input + ", " + (char)input);
			}
			System.out.println("프로그램 종료!!");
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
