package kr.s07.object.method;
//주클래스에는 public을 붙일 수 있지만 부클래스에는 public을 붙일 수 없다.
class Tv{
	//멤버 필드(속성)
	boolean power; //전원상태(on/off)
	int channel; //채널

	//멤버 메서드(동작)
	public void isPower() {//TV를 켜거나 끄는 기능을 수행하는 메서드
		power = !power;
	}
	public void channerlUp() {//TV의 채널을 높이는 기능을 수행하는 메서드
		++channel;
	}
	public void channelDown() {//TV의 채널을 낮추는 기능을 수행하는 메서드
		--channel;
	}
}

public class TvMain {//주클래스
	public static void main(String[] args) {
		//객체 선언 및 초기화
		Tv t = new Tv();
		t.isPower();//power의 값이 false에서 true로 바뀜
		System.out.println("TV 실행 여부 :" + t.power);
		System.out.println("현재 채널 : " + t.channel);
		System.out.println("---------------");
		
		t.channel = 7;
		System.out.println("첫번째 변경된 채널 : " + t.channel);
		System.out.println("---------------");
		
		t.channelDown();
		System.out.println("두번째 변경된 채널 : " + t.channel);
		System.out.println("---------------");
		
		//TV 전원 끄기
		t.isPower();//true -> false
		System.out.println("TV 실행 여부 : " + t.power);
		
	}
}
