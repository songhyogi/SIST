package kr.s20.object.lang;
import java.util.Arrays;

public class MathMain04 {
	public static void main(String[] args) {
		/*
		 * [실습]
		 * 로또 프로그램 제작
		 * 길이가 6인 int형 배열을 생성하고 1~45 숫자 범위로 난수를 구함. 
		 * 중복되지 않은 6개의 숫자를 생성해서 배열에 저장하고 출력하시오.
		 * 난수 생성 : Math.random() 또는 Random 클래스의 nextInt() 메서드 사용
		 */
		
		int[] lotto = new int[6];
		
		for(int i=0;i<lotto.length;i++) {
			lotto[i] = (int)(Math.random()*45) + 1; //1~45
			
			//중복된 숫자가 있는지 검증
			for(int j=0;j<i;j++) {
				if(lotto[i] == lotto[j]) {
					i--;//중복되면 생성한 난수를 불허
					    //i의 값을 1 줄여서 중복된 값을 제거하고 새로운 값을 저장함
					break;
				}
			}
		}
		Arrays.sort(lotto);//오름차순으로 정렬
		for(int num : lotto) {
			System.out.print(num + "\t");
		}
	}
}
