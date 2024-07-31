package kr.s06.object.instance;

public class InstanceMain {
	//클래스의 기본 구조
	//멤버 필드(속성)
	int var1;//멤버 변수
	String var2;//멤버 변수
	
	//생성자, 생략 가능하며 생략하면 컴파일시 자동 생성
	public InstanceMain() {}
	
	//멤버 메서드(동작)
	               //a와 b에 정수형 데이터가 들어간다 ()가 입구
	public int sum(int a, int b) {
		return a + b; //return으로 밖으로 뽑아낸다 return이 출구
	}
	
	public static void main(String[] args) {
		//객체 선언 및 생성
		  //참조자료형                 생성자
		InstanceMain im = new InstanceMain();
		//객체의 멤버 변수에 값을 할당
		im.var1 = 100;
		im.var2 = "서울";
		//객체의 멤버 변수 값 호출
		System.out.println(im.var1 + ", " + im.var2);
		
		//객체의 멤버 메서드 호출
		int result = im.sum(10, 20);//a가 10을 받고 b가 20을 받는다 데이터가 가공된 후 return으로 밖으로 빠져나가서 result에 대입된다
		System.out.println("result = " + result);
	}
	
}
