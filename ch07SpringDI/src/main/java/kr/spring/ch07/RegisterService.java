package kr.spring.ch07;

public class RegisterService {
	private RegisterDAO registerDAO; //프로퍼티
	
	//set메서드 생성
	public void setRegisterDAO(RegisterDAO registerDAO) {
		this.registerDAO = registerDAO;
	}
	
	public void write() {
		System.out.println("RegisterService의 write() 메서드 실행");
		registerDAO.insert();
	}
}
