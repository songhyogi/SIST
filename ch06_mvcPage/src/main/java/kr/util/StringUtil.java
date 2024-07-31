package kr.util;

public class StringUtil {
	//HTML 태그를 허용하면서 줄바꿈
	public static String useBrHTML(String str) {
		if(str == null) return null;
		
		return str.replaceAll("\r\n", "<br>")
					.replaceAll("\r", "<br>")
					.replaceAll("\n", "<br>");
	}
	
	//HTML 태그를 허용하지 않으면서 줄바꿈
	public static String useBrNoHTML(String str) {
		if(str == null) return null;
		
		return str.replaceAll("<", "&lt;")
					.replaceAll(">", "&gt;")
					.replaceAll("\r\n", "<br>")
					.replaceAll("\r", "<br>")
					.replaceAll("\n", "<br>");
	}
	
	//HTML를 허용하지 않음
	public static String useNoHTML(String str) {
		if(str == null) return null;
		
		return str.replaceAll("<", "&lt;").replaceAll(">", "&gt;");//꺽쇠를 무조건 엔티티 참조형태로 변환
	}
	
	//큰 따옴표 처리
	public static String parseQuot(String str) {
		if(str == null) return null;
		
		return str.replaceAll("\"", "&quot;");//큰따옴표를 &quot로 변환
	}
}
