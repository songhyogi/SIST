package kr.spring.ch12;

import java.util.Properties;

public class BookClient {
	//프로퍼티
	private Properties prop; //프로퍼티즈 타입의 객체를 받음

	public void setProp(Properties prop) {
		this.prop = prop;
	}

	@Override
	public String toString() {
		return "BookClient [prop=" + prop + "]";
	}
	
}
