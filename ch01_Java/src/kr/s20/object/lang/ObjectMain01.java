package kr.s20.object.lang;

public class ObjectMain01 {
	public static void main(String[] args) {
		ObjectMain01 ob = new ObjectMain01();
		System.out.println(ob.getClass());//클래스 정보를 가진 객체에 접근
		System.out.println(ob.getClass().getName());//ob.getClass()가 객체에 접근->getName()이 클래스명 반환
		System.out.println(ob.hashCode());//10진수의 유니크한 값 반환
		                   //10진수 -> 16진수
		System.out.println(Integer.toHexString(ob.hashCode()));
		System.out.println(ob.toString());//getClass().getName()과 Integer.toHexString의 조합
		System.out.println(ob);//참조값만 명시하여도 자동호출되기 때문에 위의 코드와 동일한 값 출력
	}
}
