package kr.s21.object.util;

import java.util.Calendar;

public class CalendarMain01 {
	public static void main(String[] args) {
		Calendar today = Calendar.getInstance();
		System.out.println(today);
		
		int year = today.get(Calendar.YEAR);//연도 //static한 상수는 앞에 클래스명을 붙인다.
		int month = today.get(Calendar.MONTH)+1;//월 0부터 11로 반환하기 때문에 +1을 해줘야 한다.
		int date = today.get(Calendar.DATE);//일 DAYOFMONTH라고 해도 된다
		
		System.out.printf("%d년%d월%d일 ", year,month,date);
		
		int day = today.get(Calendar.DAY_OF_WEEK);//요일 1부터 7로 반환한다.
		String[] days = {"일","월","화","수","목","금","토"};
		
		System.out.print(days[day-1]+"요일");//요일은 1~7까지 반환하기때문에 -1을 해줘야 한다.
		
		int amPm = today.get(Calendar.AM_PM);//오전 0,오후 1
		String str= amPm == Calendar.AM ? "오전" : "오후";
		
		int hour =  today.get(Calendar.HOUR);//시 HOUR_OF_DAY(24시 표시)
		int min = today.get(Calendar.MINUTE);//분
		int sec = today.get(Calendar.SECOND);//초
		
		System.out.printf(" %s%d시%d분%d초", str,hour,min,sec);
	}
}
