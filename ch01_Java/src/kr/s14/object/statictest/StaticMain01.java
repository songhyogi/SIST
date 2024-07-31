package kr.s14.object.statictest;

public class StaticMain01 {
	public static void main(String[] args) {
		StaticCount sc1 = new StaticCount();
		System.out.println("c : " + sc1.c + ",count: " + StaticCount.count);//static 호출은 앞에 클래스명을 붙인다.
		
		StaticCount sc2 = new StaticCount();
		System.out.println("c : " + sc2.c + ",count: " + StaticCount.count);
		
		StaticCount sc3 = new StaticCount();
		System.out.println("c : " + sc3.c + ",count: " + StaticCount.count);
		
	}
}
