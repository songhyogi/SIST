package kr.spring.ch08;

public class WorkController {
	//프로퍼티
	private long periodTime;
	private EmailSender email;
	
	//[Source] - [Generate Getters and Setters] - [Select Setters] - [Generate]
	//set메서드만 생성
	public void setPeriodTime(long periodTime) {
		this.periodTime = periodTime;
	}
	public void setEmail(EmailSender email) {
		this.email = email;
	}
	
	//object가 제공하는 toString을 재정의 : [Source] - [Generate toString()] - 아무것도 선택하지 않고 [Generate]
	@Override
	public String toString() {
		return "WorkController [periodTime=" + periodTime + ", email=" + email + "]";
	}
	
}
