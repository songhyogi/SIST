package kr.s28.iostream;

import java.io.File;

public class FileMain01 {
	public static void main(String[] args) {
		String path = "C:\\";//\가 디렉토리 구분자인데 하나만 하면 특수문자로 인식하기 때문에 두개로 한다.
		
		File f = new File(path);
		
		if(!f.exists() || !f.isDirectory()) {//파일을 검증하려면 파일이 존재하면서 디렉토리여야 한다.
			System.out.println("유효하지 않은 디렉토리입니다.");
			System.exit(0);//프로그램 종료
		}
		
		//지정한 디렉토리의 하위 디렉토리와 파일 정보 반환
		File[] files = f.listFiles();
		for(int i=0;i<files.length;i++) {
			File f2 = files[i];
			if(f2.isDirectory()) {//디렉토리
				System.out.println("["+f2.getName()+"]");//[]들어간 건 디렉토리명
			}else {//파일
				System.out.print(f2.getName());
				System.out.printf("(%,dbytes)%n", f2.length());//디렉토리는 용량의 개념이 없지만 파일은 용량의 개념이 있다.
			}
		}
	}
}
