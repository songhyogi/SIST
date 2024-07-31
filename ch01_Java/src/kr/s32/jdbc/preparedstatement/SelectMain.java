package kr.s32.jdbc.preparedstatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import kr.util.DBUtil;

public class SelectMain {
	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		try {
			//JDBC 수행 1,2단계
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "SELECT * FROM test2 ORDER BY reg_date DESC";
			//데이터를 전달할 것이 없어서 ?가 없다.
			
			//JDBC 수행 3단계
			pstmt = conn.prepareStatement(sql);
			//JDBC 수행 4단계 : SQL문을 실행해서 테이블에 반영하고 결과집합을 ResultSet에 담아서 반환
			rs = pstmt.executeQuery();//이미 수행3단계에서 sql을 넣었기 때문에 여기에 sql을 넣으면 안된다.
			
			System.out.println("ID\t이름\t나이\t등록일");
			while(rs.next()) {
				System.out.print(rs.getString("id"));
				System.out.print("\t");
				System.out.print(rs.getString("name"));
				System.out.print("\t");
				System.out.print(rs.getInt("age"));
				System.out.print("\t");
				//연-월-일
				//System.out.println(rs.getDate("reg_date"));
				//연-월-일 시:분:초
				System.out.println(rs.getString("reg_date"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//자원정리
			DBUtil.executeClose(rs, pstmt, conn);
		}
	}
}
