package kr.spring.ch17;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class SystemMonitor2 {
	//프로퍼티
	@Autowired
	//@Qualifier를 이용한 자동 설정 제한
	@Qualifier("main")
	private Recorder recorder;

	public Recorder getRecorder() {
		return recorder;
	}

	public void setRecorder(Recorder recorder) {
		this.recorder = recorder;
	}
	
}
