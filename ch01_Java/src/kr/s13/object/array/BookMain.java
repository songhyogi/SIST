package kr.s13.object.array;

class Book{
	//은닉화
	private String category;
	private String name;
	private int price;
	private double discount;

	//생성자
	public Book(String c, String n, int p, double d) {
		category = c;
		name = n;
		price = p;
		discount = d;
	}
	//캡슐화
	//변수가 private이기 때문에 다른 클래스에서 쓰려면 public을 만들어줘서 활용한다.
	public String getCategory() {
		return category;
	}
	public String getName() {
		return name;
	}
	public int getPrice() {
		return price;
	}
	public double getDiscount() {
		return discount;
	}
}

public class BookMain {
	public static void main(String[] args) {
		Book[] bookArray = new Book[3];//Book 객체 생성, Book 객체가 들어가는 것이 아니라 객체의 주소가 들어간다
		//Book 객체를 3개 생성하여 배열에 저장
		bookArray[0] = new Book("IT","Java",50000,0.05);
		bookArray[1] = new Book("IT","Oracle",40000,0.03);
		bookArray[2] = new Book("미술","반 고흐",60000,0.06);

		//반복문을 이용해서 객체의 요소 출력
		for(int i=0;i<bookArray.length;i++) {
			System.out.printf("%s\t",bookArray[i].getCategory());
			System.out.printf("%s\t",bookArray[i].getName());
			System.out.printf("%,d원\t",bookArray[i].getPrice());
			System.out.printf("%.2f%n",bookArray[i].getDiscount());
		}
	}
}
