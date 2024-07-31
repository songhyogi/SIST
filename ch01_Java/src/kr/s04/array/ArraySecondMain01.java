package kr.s04.array;

public class ArraySecondMain01 {
	public static void main(String[] args) {
		//2차원 배열 선언
		int[][] test;
		//2차원 배열 생성(2행3열)
		test = new int[2][3];
		
		//2차원 배열 초기화
		 //행번호 열번호
		test[0][0] = 100;
		test[0][1] = 200;
		test[0][2] = 300;
		
		test[1][0] = 400;
		test[1][1] = 500;
		test[1][2] = 600;
		
		//배열의 요소 출력
		System.out.println(test[0][0]);
		System.out.println(test[0][1]);
		System.out.println(test[0][2]);
		
		System.out.println(test[1][0]);
		System.out.println(test[1][1]);
		System.out.println(test[1][2]);
		System.out.println("------------");
		
		//반복문을 이용한 배열의 요소 출력
		            //행의 길이
		for(int i=0;test.length<2;i++) {
			           //열의 길이
			for(int j=0;j<test[i].length;j++) {
				System.out.println("test["+i+"]["+j+"]" + test[i][j]);
			}
		}
		System.out.println("------------");
		
		//3행3열의 2차원 배열 선언 및 생성
		int[][] test2 = new int[3][3];
		
		//2행3열의 2차원 배열 선언, 생성(명시적 배열 생성), 초기화
		int[][] test3 = new int[][] {
			                         {10,20,30},
			                         {40,50,60}//여기에도 쉼표를 붙이면 안됨
			                        };
		
		//2행3열의 2차원 배열 선언, 생성(암시적 배열 생성), 초기화
		int[][] test4 = {{100,200,300},{400,500,600}};
		
	}
}
