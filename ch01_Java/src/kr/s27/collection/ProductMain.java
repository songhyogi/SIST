package kr.s27.collection;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class ProductMain {
	ArrayList<Product>list;
	BufferedReader br;
	
	public ProductMain() {
		list = new ArrayList<Product>();//ArrayList객체 생성
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			callMenu();
		}catch(Exception e) {
			e.printStackTrace();//예외가 발생한 라인을 알려준다
		}finally {//자원정리
			if(br!=null)try {br.close();}catch(IOException e) {}
		}
	}
	//메뉴 호출
	public void callMenu()throws IOException{
		while(true) {
			System.out.print("1.상품 입력,2.상품목록 보기,3.종료>");
			try{
				           //String -> int
				int num = Integer.parseInt(br.readLine());
				if(num == 1) {//상품 입력
					input();
				}else if(num == 2) {//상품목록 보기
					output();
				}else if(num == 3) {//종료
					System.out.println("프로그램 종료");
					break;
				}else {
					System.out.println("잘못 입력했습니다.");
				}
			}catch(NumberFormatException e) {
				System.out.println("숫자만 허용");
			}
		}
	}
	//상품 정보 입력
	public void input()throws IOException{
		Product p = new Product();
		System.out.print("상품명:");
		p.setName(br.readLine());//br.readLine은 무조건 String으로 반환한다.
		System.out.print("상품번호:");
		p.setNum(br.readLine());
		System.out.print("가격:");
		            //String -> int
		p.setPrice(Integer.parseInt(br.readLine()));
		System.out.print("제조사:");
		p.setMaker(br.readLine());
		System.out.print("재고:");
		p.setStock(Integer.parseInt(br.readLine()));
		
		//Product를 ArrayList에 저장
		list.add(p);
		System.out.println("상품 정보 1건이 추가되었습니다.");
	}
	//상품 정보 출력
	public void output() {
		System.out.println("상품리스트 : 총상품수("+list.size()+")");
		System.out.println("상품명\t상품번호\t가격\t제조사\t재고수");
		System.out.println("-----------------------------");
		//반복문을 이용한 요소의 출력
		/*
		for(int i=0;i<list.size();i++) {
			Product pt = list.get(i);
			System.out.printf("%s\t", pt.getName());
			System.out.printf("%s\t", pt.getNum());
			System.out.printf("%,d원\t", pt.getPrice());
			System.out.printf("%s\t", pt.getMaker());
			System.out.printf("%,d%n", pt.getStock());
		}*/
		//확장for문을 이용한 요소의 출력
		for(Product pt : list) {
			System.out.printf("%s\t", pt.getName());
			System.out.printf("%s\t", pt.getNum());
			System.out.printf("%,d원\t", pt.getPrice());
			System.out.printf("%s\t", pt.getMaker());
			System.out.printf("%,d%n", pt.getStock());
		}
	}
	
	public static void main(String[] args) { 
		new ProductMain();
	}
}
