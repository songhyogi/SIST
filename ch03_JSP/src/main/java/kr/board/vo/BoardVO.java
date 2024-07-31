package kr.board.vo;

import java.sql.Date;

public class BoardVO {
	//프로퍼티
	private int num; //시퀀스를 통해 알아냄
	private String title; //실제 입력하는 것을 title,name,passwd,content 이렇게 네가지
	private String name;
	private String passwd;
	private String content;
	private String ip; //메서드를 통해 알아냄
	private Date reg_date; //sysdate로 default
	
	//비밀번호 체크
	public boolean isCheckedPassword(String userPasswd) {
		if(passwd.equals(userPasswd)) { //인증 성공
			return true;
		}
		return false; //인증 실패
	}
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
}
