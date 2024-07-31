package kr.s27.collection;

public class CartItem {
	private String code;//상품코드
	private int num;//수량
	private int price;//단가
	
	public CartItem() {}//인자가 없는 생성자
	
	public CartItem(String code, int num, int price) {//인자가 있는 생성자
		this.code = code;
		this.num = num;
		this.price = price;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	
}
