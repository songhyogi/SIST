package kr.s27.collection;

import java.util.HashSet;//데이터 저장
import java.util.Iterator;//데이터 읽을 때

public class HashSetMain01 {
	public static void main(String[] args) {
		//HashSet : 중복값 불허
		String[] array = {"Java","JSP","Java","Oracle"};//대문자 소문자가 다르면 다른 걸로 인식한다.
		
		HashSet<String> hs = new HashSet<String>();
		for(String n : array) {
			hs.add(n);//중복 불허
		}
		//저장된 요소의 목록
		System.out.println(hs);
		System.out.println("-----------------");
		//저장된 요소의 출력
		Iterator<String> ir = hs.iterator(); //원래 데이터를 복사해서 iterator객체를 만든 다음 거기에서 뽑아낸다
		while(ir.hasNext()) {
			System.out.println(ir.next());
		}
		System.out.println("-----------------");
		for(String s : hs) {
			System.out.println(s);
		}
	}
}
