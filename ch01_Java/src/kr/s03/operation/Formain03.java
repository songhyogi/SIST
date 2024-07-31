package kr.s03.operation;

public class Formain03 {
	public static void main(String[] args) {
		int sum = 0;
		for(int i=1;i<=100;i++) {
			//누적
			sum += i;
		}
		System.out.println("1부터 100까지의 합 : " + sum);
		System.out.println("------------------");
		
		//1부터 100까지의 범위에서 짝수의 합 구하기
		int total = 0;
		for(int i=1;i<=100;i++) {
			if(i%2==0) {//짝수의 합을 구함
				total += i;
			}
		}
		System.out.println("1부터 100까지 짝수의 합 : " + total);
		
	}
}
