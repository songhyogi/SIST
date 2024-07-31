package kr.s04.array;

public class ArrayMain03 {
	public static void main(String[] args) {
		//배열 선언 및 생성(암시적 배열 생성), 초기화
		int array[] = {10,20,30,40,50};
		
		//반복문을 이용한 배열의 요소 출력
		                //배열의 길이
		for(int i=0;i<array.length;i++) {//index를 사용해야 한다면 이 방법 사용
			System.out.print(array[i] + "\t");
		}
		System.out.println("\n-------------");
		
		//확장 for문을 이용한 배열의 요소 출력
		for(int num : array) {//배열명(array)으로 먼저 접근 후 내부적으로 데이터를 읽어오는 것 
			                  //index를 사용하지 않아도 된다면 이 방법 사용
			System.out.print(num + "\t");
		}
	}
}
