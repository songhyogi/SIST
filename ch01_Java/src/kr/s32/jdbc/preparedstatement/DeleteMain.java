package kr.s32.jdbc.preparedstatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import kr.util.DBUtil;

public class DeleteMain {
	public static void main(String[] args) {
		//DB를 사용하다보면 예외 상황이 발생할 수 있기 때문에 반드시 try catch 작업을 해줘야 한다
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			//JDBC 수행 1,2단계
			conn = DBUtil.getConnection();
			
			//SQL문 작성
			sql = "DELETE FROM test2 WHERE id=?";
			
			//JDBC 수행 3단계 : PreparedStatement 객체 생성
			 //sql문을 실어나를 수 있는 
			pstmt = conn.prepareStatement(sql); //PreparedStatement가 sql문장을 갖게 된다
			//?에 데이터 바인딩
			pstmt.setString(1, "star"); //1번 ?에 star를 넣어라
			
			//JDBC 수행 4단계 : SQL문을 테이블에 반영해서 행을 삭제
			int count = pstmt.executeUpdate();
			System.out.println(count + "개 행을 삭제했습니다.");
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//자원정리
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
}
