package kr.s32.jdbc.preparedstatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import kr.util.DBUtil;

public class InsertMain {
	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			//JDBC 수행 1,2단계
			conn = DBUtil.getConnection();//static하게 만들었기 때문에 객체 생성없이 메서드를 동작할 수 있어서 작업이 편해진다.
			
			//SQL문 작성
			sql = "INSERT INTO test2 (id,name,age,reg_date) VALUES (?,?,?,SYSDATE)";//데이터를 넣지 않고 자리만 잡는다.
	
			//JDBC 수행 3단계 : PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터를 바인딩
			pstmt.setString(1, "dragon");//1번 ?에 데이터 전달
			pstmt.setString(2, "박문수");//2번 ?에 데이터 전달
			pstmt.setInt(3, 20);//3번 ?에 데이터 전달
			
			//JDBC 수행 4단계 : SQL문 실행해서 테이블에 행을 추가
			int count = pstmt.executeUpdate();//위에 prepareStatement에 이미 sql을 넣었기 때문에 여기에 sql을 넣으면 안된다. 
			System.out.println(count + "개 행을 추가했습니다.");
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//자원정리
			DBUtil.executeClose(null,pstmt,conn);//resultset이 없기 때문에 null로 한다.
		}
	}
}
