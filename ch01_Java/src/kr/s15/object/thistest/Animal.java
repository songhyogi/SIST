package kr.s15.object.thistest;

public class Animal {
	//멤버 변수
	private String name; //이름
	private int age; //나이
	private boolean fly; //비행여부
	
	//생성자
	public Animal() {}
		
	public Animal(String name,int age, boolean fly) {
		this.name = name;
		this.age = age;
		this.fly = fly;
		}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public boolean isFly() {
		return fly;
	}

	public void setFly(boolean fly) {
		this.fly = fly;
	}
	
}
