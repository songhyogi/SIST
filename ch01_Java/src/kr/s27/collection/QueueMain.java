package kr.s27.collection;

import java.util.LinkedList;

public class QueueMain {
	public static void main(String[] args) {
		//큐(Queue) : 선입선출 FIFO(First-In First-Out)
		String[] array = {"서울","부산","대구","광주","인천"};
		
		LinkedList<String> linked = new LinkedList<String>();
		for(int i=0;i<array.length;i++) {
			//LinkedList에 요소를 저장
			linked.offer(array[i]);
		}
		//저장된 요소의 목록
		System.out.println(linked);
		System.out.println("--------------------");
		      //큐에 저장된 첫번째 요소를 검색
		while(linked.peek() != null) {//첫번째요소가 없지 않다는 것이니까 있다는 뜻
			                  //큐에 저장된 첫번째 요소를 반환하고 제거
			System.out.print(linked.poll() + "\t");
		}
		System.out.println("\n--------------------");
		System.out.println(linked);
	}
}
