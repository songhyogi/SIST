package kr.s02.operator;

public class OperatorMain01 {
	public static void main(String[] args) {
		System.out.println("===증감연산자===");
		//증가연산자
		int i = 5;
		//증가연산자를 변수명 앞에 명시하면 변수값을 1 증가시킨 후 증가된 값을 읽어옴
		System.out.println(++i);//6
		System.out.println("------------");
		
		//증가연산자를 변수명 뒤에 명시하면 변수값을 우선 읽어온 후 변수에 값을 1 증가시킴
		System.out.println(i++);//6, 변수값을 6으로 먼저 읽어왔지만 메모리에는 7로 되어 있음 
		System.out.println(i);//7, 한번 더 호출 필요
		
		System.out.println("------------");
		
		//감소연산자
		int j = 10;
		
		//감소연산자를 변수명 앞에 명시하면 변수값을 1 감소시킨 후 감소된 값을 읽어옴
		System.out.println(--j);//9
		
		System.out.println("------------");
		//감소연산자를 변수명 뒤에 명시하면 변수값을 우선 읽어온 후 변수의 값을 1 감소시킴
		System.out.println(j--);//9
		System.out.println(j);//8
	}
}
