package kr.s07.object.method;

public class MethodMain02 {
	/*
	 * [실습]
	 * 입력한 int형 정수값이 음수이면 -1을, 0이면 0을, 양수이면 1을 반환하는 signOf 메서드를 작성하시오
	 */
	public int signOf(int n) {
		int sign = 0;
		if(n>0) {//양수
			sign = 1;
		}else if(n<0) {//음수
			sign = -1;
		}
		/*else {
					sign = 0;
				}sign을 0으로 초기화 해놨으니까 생략 가능*/
		return sign;
	}



	public static void main(String[] args) {
		java.util.Scanner input = new java.util.Scanner(System.in);
		System.out.print("정수 x:");
		int x = input.nextInt();
		
		MethodMain02 me = new MethodMain02();
		int result = me.signOf(x);
		
		System.out.println("signOf(x)는 " + result + "입니다.");
		
		input. close();
	}
}
