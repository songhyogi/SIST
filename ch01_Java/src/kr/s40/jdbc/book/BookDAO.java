package kr.s40.jdbc.book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import kr.util.DBUtil;

public class BookDAO {
	//관리자 도서 등록
	public void insertBook(String bk_name,String bk_category) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql =null;
		try {
			//JDBC 수행 1,2단계
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "INSERT INTO sbook (bk_num,bk_name,bk_category) VALUES (sbook_seq.nextval,?,?)";
			//JDBC 수행 3단계
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setString(1, bk_name);
			pstmt.setString(2, bk_category);
			//JDBC 수행 4단계
			int count = pstmt.executeUpdate();
			System.out.println(count + "건의 도서가 등록되었습니다.");
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//자원정리
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	//관리자 도서 목록 보기
	public void selectListBook() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql =null;
		ResultSet rs = null;
		try {
			//JDBC 수행 1,2단계
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "SELECT * FROM sbook LEFT OUTER JOIN (SELECT * FROM reservation WHERE re_status=1) USING(bk_num) ORDER BY bk_num DESC";
			//JDBC 수행 3단계
			pstmt = conn.prepareStatement(sql);
			//JDBC 수행 4단계
			rs = pstmt.executeQuery();

			if(rs.next()) {
				System.out.println("----------------------------------------------");
				System.out.println("번호\t도서명\t\t분류\t등록일\t\t도서대출상태");
				do {
					System.out.print(rs.getInt("bk_num"));
					System.out.print("\t");
					System.out.print(rs.getString("bk_name"));
					System.out.print("\t");
					System.out.print(rs.getString("bk_category"));
					System.out.print("\t");
					System.out.print(rs.getDate("bk_regdate"));
					System.out.print("\t");
					
					int re_status = rs.getInt("re_status");
					if(re_status == 1) {
						System.out.println("대출중");
					}else {
						System.out.println("미대출");
					}
				}while(rs.next());
			}else {
				System.out.println("표시할 도서 정보가 없습니다.");
			}
			System.out.println("----------------------------------------------");
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//자원정리
			DBUtil.executeClose(rs, pstmt, conn);
		}
	}
	//관리자 회원 목록 보기
	public void selectListMember() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql =null;
		ResultSet rs = null;
		try {
			//JDBC 수행 1,2단계
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "SELECT * FROM member ORDER BY me_regdate DESC";
			//JDBC 수행 3단계
			pstmt = conn.prepareStatement(sql);
			//JDBC 수행 4단계
			rs = pstmt.executeQuery();
			System.out.println("----------------------------------------------");
			
			if(rs.next()) {
				System.out.println("회원 ID\t비밀번호\t회원명\t전화번호\t\t가입일");
				do {
					System.out.print(rs.getString("me_id"));
					System.out.print("\t");
					System.out.print(rs.getString("me_passwd"));
					System.out.print("\t");
					System.out.print(rs.getString("me_name"));
					System.out.print("\t");
					System.out.print(rs.getString("me_phone"));
					System.out.print("\t");
					System.out.println(rs.getDate("me_regdate"));
				}while(rs.next());
			}else {
				System.out.println("표시할 회원 정보가 없습니다.");
			}
			System.out.println("----------------------------------------------");
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//자원정리
			DBUtil.executeClose(rs, pstmt, conn);
		}
	}
	//관리자 대출 목록 보기(대출 및 반납의 모든 데이터 표시)
	public void selectListReservation() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		try {
			//JDBC 수행 1,2단계
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "SELECT re_num,bk_num,bk_category,bk_name,re_status,me_id,re_regdate,TO_CHAR(re_modifydate,'YYYY-MM-DD') re_modifydate "
					+ "FROM reservation JOIN sbook USING(bk_num) ORDER BY re_num DESC";
			//JDBC 수행 3단계
			pstmt = conn.prepareStatement(sql);
			//JDBC 수행 4단계
			rs = pstmt.executeQuery();
			System.out.println("----------------------------------------------");
			
			if(rs.next()) {
				System.out.println("도서대출번호\t도서번호\t카테고리\t도서명\t\t대출상태\t회원id\t대출날짜\t\t반납날짜");
				do {
				System.out.print(rs.getInt("re_num"));
				System.out.print("\t");
				System.out.print(rs.getInt("bk_num"));
				System.out.print("\t");
				System.out.print(rs.getString("bk_category"));
				System.out.print("\t");
				System.out.print(rs.getString("bk_name"));
				System.out.print("\t");
				int re_status = rs.getInt("re_status");
				if(re_status == 1) {
					System.out.print("대출중");
				}else {
					System.out.print("반납완료");
				}
				System.out.print("\t");
				System.out.print(rs.getString("me_id"));
				System.out.print("\t");
				System.out.print(rs.getDate("re_regdate"));
				System.out.print("\t");
				
				String re_modifydate = rs.getString("re_modifydate");
				if(re_modifydate == null) re_modifydate = "";
				System.out.println(re_modifydate);
				
				}while(rs.next());
			}else {
				System.out.println("표시할 데이터가 없습니다.");
			}
			System.out.println("----------------------------------------------");
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//자원정리
			DBUtil.executeClose(rs, pstmt, conn);
		}
	}
	//사용자 아이디 중복 체크(count가 0이면 미중복,count가 1이면 중복)
	public int checkId(String me_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int count = 0;
		try {
			//JDBC 수행 1,2단계
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "SELECT me_id FROM member WHERE me_id=?";
			//JDBC 수행 3단계
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setString(1, me_id);
			//JDBC 수행 4단계
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = 1;//행이 있으면 count를 1로
			}
		}catch(Exception e) {
			count = 2;//오류 발생
			e.printStackTrace();
		}finally {
			//자원정리
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return count;
	}
	//사용자 회원 가입
	public void insertMember(String me_id,String me_passwd,
			String me_name,String me_phone) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			//JDBC 수행 1,2단계
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "INSERT INTO member (me_id,me_passwd,me_name,me_phone) VALUES (?,?,?,?)";
			//JDBC 수행 3단계
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setString(1, me_id);
			pstmt.setString(2, me_passwd);
			pstmt.setString(3, me_name);
			pstmt.setString(4, me_phone);
			//JDBC 수행 4단계
			pstmt.executeUpdate();
			System.out.println("회원 가입이 완료되었습니다.");
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//자원정리
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	//사용자 로그인 체크
	public boolean loginCheck(String me_id,String me_passwd) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		boolean flag = false;
		try {
			//JDBC 수행 1,2단계
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "SELECT me_id FROM member WHERE me_id=? AND me_passwd=?";
			//JDBC 수행 3단계
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setString(1, me_id);
			pstmt.setString(2, me_passwd);
			//JDBC 수행 4단계
			rs = pstmt.executeQuery();
			if(rs.next()) {//행이 있을 때만 로그인 처리가 됨
				flag = true;//flag가 true인 경우에만 로그인이 된다.
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//자원정리
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return flag;
	}
	//도서 대출 여부 확인
	//(도서번호(bk_num)로 검색해서 re_status의 값이 0이면 대출 가능, 1이면 대출 불가능)
	public int getStatusReservation(int bk_num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		ResultSet rs = null;
		int count = -1;
		try {
			//JDBC 수행 1,2단계
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "SELECT re_status FROM sbook LEFT OUTER JOIN "
					+ "(SELECT * FROM reservation WHERE re_status=1) USING(bk_num) "
					+ "WHERE bk_num=?";
			//JDBC 수행 3단계
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, bk_num);
			//JDBC 수행 4단계
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//자원정리
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return count;
	}
	//사용자 도서 대출 등록
	public void insertReservation(int bk_num,String me_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			//JDBC 1,2단계 수행
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "INSERT INTO reservation (re_num,re_status,bk_num,me_id) "
					+ "VALUES (reservation_seq.nextval,1,?,?)";
			//JDBC 3단계 수행
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, bk_num);
			pstmt.setString(2, me_id);
			//JDBC 수행 4단계
			int count = pstmt.executeUpdate();
			System.out.println(count + "건의 도서 대출이 등록되었습니다.");
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//자원정리
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	//사용자 MY대출 목록 보기(현재 대출한 목록만 표시)
	public void selectMyList(String me_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		try {
			//JDBC 1,2단계 수행
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "SELECT re_num,bk_num,bk_name,re_regdate,TO_CHAR(re_modifydate,'YYYY-MM-DD') re_modifydate,re_status "
					+ "FROM reservation JOIN sbook USING(bk_num) WHERE me_id=? ORDER BY re_regdate";
			//JDBC 3단계 수행
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setString(1, me_id);
			//JDBC 4단계 수행
			rs = pstmt.executeQuery();
			System.out.println("----------------------------------------------");
			if(rs.next()) {
				System.out.println("도서대출번호\t도서번호\t도서명\t\t대출날짜\t\t반납날짜\t\t도서대출상태");
				do {
					System.out.print(rs.getInt("re_num"));
					System.out.print("\t");
					System.out.print(rs.getInt("bk_num"));
					System.out.print("\t");
					System.out.print(rs.getString("bk_name"));
					System.out.print("\t");
					System.out.print(rs.getDate("re_regdate"));
					System.out.print("\t");
					
					String re_modifydate = rs.getString("re_modifydate");
					if(re_modifydate == null) re_modifydate = "\t";
					System.out.print(re_modifydate);
					System.out.print("\t");
					int re_status = rs.getInt("re_status");
					if(re_status == 1) {
						System.out.println("대출중");
					}else {
						System.out.println("반납 완료");
					}	
				}while(rs.next());
			}else {
				System.out.println("대출한 책이 없습니다.");
			}
			System.out.println("----------------------------------------------");
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//자원정리
			DBUtil.executeClose(rs, pstmt, conn);
		}
	}
	//사용자 반납 가능 여부
	//(대출번호(re_num)와 회원아이디(me_id)를 함께 조회해서 re_status가 1인 것은 반납 가능 re_status가 0이면 반납 불가능)
	public int getStatusBack(int re_num,String me_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		ResultSet rs = null;
		int count = -1;
		try {
			//JDBC 1,2단계 수행
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "SELECT re_status FROM reservation WHERE re_num=? AND me_id=?";
			//JDBC 수행 3단계
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, re_num);
			pstmt.setString(2, me_id);
			//JDBC 수행 4단계
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}else {
				count = 0;
			}
		}catch(Exception e) {
			count = 2;
			e.printStackTrace();
		}finally {
			//자원정리
			DBUtil.executeClose(null, pstmt, conn);
		}
		return count;
	}
	//사용자 반납 처리
	public void updateReservation(int re_num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			//JDBC 1,2단계 수행
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "UPDATE reservation SET re_modifydate=SYSDATE,re_status=0 WHERE re_num=? ";
			//JDBC 3단계 수행
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, re_num);
			//JDBC 4단계 수행
			int count = pstmt.executeUpdate();
			System.out.println(count + "건의 도서가 반납되었습니다.");
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//자원정리
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
}
