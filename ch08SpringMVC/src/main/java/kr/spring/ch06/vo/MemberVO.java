package kr.spring.ch06.vo;

public class MemberVO {
	private String id;
	private String name;
	private String zipcode;
	private String address1;
	private String address2;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	
	@Override
	public String toString() {//프로퍼티에 제대로 담겨있는지 toString을 통해 확인하고 로그도 toString을 통해 확인하기 때문에 기본적으로 toString을 넣어준다.
		return "MemberVO [id=" + id + ", name=" + name + ", zipcode=" + zipcode + ", address1=" + address1
				+ ", address2=" + address2 + "]";
	}
}
