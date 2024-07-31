package kr.s28.iostream;

import java.io.IOException;

public class InputStreamMain01 {
	public static void main(String[] args) {
		System.out.print("영문자 1개 입력:");
		try{
			//문자 하나를 입력 받아서 아스키코드로 반환
			int a = System.in.read();//영문자 1개씩 입력 받는다.
			System.out.println(a + ", " + (char)a);
			
			System.in.read(); //enter 처리 \r 13
			System.in.read(); //enter 처리 \n 10
			
			System.out.print("숫자 1개 입력:");
			int b = System.in.read()-48;
			System.out.println(b);
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
