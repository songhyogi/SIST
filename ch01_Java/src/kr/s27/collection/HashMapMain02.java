package kr.s27.collection;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class HashMapMain02 {
	public static void main(String[] args) {
		String[] msg = {"Berlin","Paris","Seoul","New York","London"};
		
		HashMap<Integer,String> map = new HashMap<Integer,String>();
		//HashMap에 key와 value 저장
		for(int i=0;i<msg.length;i++) {
			map.put(i, msg[i]);
		}
		//HashMap의 데이터 목록 호출
		System.out.println(map);
		System.out.println("--------------");
		
		//Set<Integer> s = map.keySet();       변수를 한번밖에 못 쓰는데 변수를 생성하는 것은 낭비하는 거다.
		//Iterator<Integer>keys = s.iterator();
		Iterator<Integer> keys = map.keySet().iterator();//keyset가 주소를 갖고 있다 한번에 메서드를 호출
		while(keys.hasNext()) {
			Integer key = keys.next();
			System.out.println(key + "," + map.get(key));
		}
	}
}
