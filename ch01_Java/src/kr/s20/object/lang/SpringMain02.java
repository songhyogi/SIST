package kr.s20.object.lang;

public class SpringMain02 {
	public static void main(String[] args) {
		String s1 = "Kwon Sun Ae";
		           //012345678910  , 길이 = 11
		
		int index = s1.indexOf('n');//문자가 위치해있는 인덱스를 반환해준다.
		System.out.println("맨 처음 문자 n의 위치 : " + index);
		
		index = s1.indexOf("Sun");//문자열을 넣었을 경우에는 가장 앞에 있는 값을 반환해준다.
		System.out.println("문자 Sun의 위치 : " + index);
		
		index = s1.lastIndexOf('n');//indexOf는 앞에서 찾는 거고 lastIndexOf는 마지막에서 찾는다
		System.out.println("마지막 문자 n의 위치 : " + index);
		
		char c = s1.charAt(index);//charAt은 인덱스를 넣어서 문자를 추출하는 것이다
		System.out.println("지정한 인덱스의 문자 추출 : " + c);
		
		index = s1.indexOf('S');
		String str = s1.substring(index);//substring은 문자열을 추출하는 것이다.
		System.out.println("대문자 S부터 끝까지 잘라내기 : " + str);
		
		str = s1.substring(index,index+3);//5,8
		System.out.println("인덱스5부터 인덱스 8전까지 문자열 추출 : " + str);//5부터 7까지 추출한다
		
		int length = s1.length();
		System.out.println("문자열의 길이 : " + length);
		
		//구분자를 이용해서 문자열 잘라내기
		String[] array = s1.split(" ");
		for(int i=0;i<array.length;i++) {
			System.out.println("array["+i+"]:" + array[i]);
		}
	}
	
	
	
}
