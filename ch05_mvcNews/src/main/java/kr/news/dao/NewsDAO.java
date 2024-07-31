package kr.news.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.news.vo.NewsVO;
import kr.util.DBUtil;

public class NewsDAO {
	//싱글턴 패턴
	private static NewsDAO instance = new NewsDAO();
	
	public static NewsDAO getInstance() {
		return instance;
	}
	private NewsDAO() {}
	
	//뉴스 등록
	public void registerNews(NewsVO vo)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "INSERT INTO dailynews (num,title,writer,passwd,email,article,filename) "
					+ " VALUES (dailynews_seq.nextval,?,?,?,?,?,?)";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getWriter());
			pstmt.setString(3, vo.getPasswd());
			pstmt.setString(4, vo.getEmail());
			pstmt.setString(5, vo.getArticle());
			pstmt.setString(6, vo.getFilename());
			//SQL문 실행
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	//뉴스 총 개수
	public int getCount()throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		String sql = null;
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "SELECT COUNT(*) FROM dailynews";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//SQL문 실행
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);//1은 컬럼인덱스
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		
		return count;
	}
	
	//목록
	public List<NewsVO> getList(int startRow,int endRow)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<NewsVO> list = null; //리스트 변수를 선언하고 초기화
		String sql = null;
		try {
			//커넥션풀로부터 커넥션을 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "SELECT * FROM (SELECT a.*,rownum rnum "
					+ "FROM (SELECT * FROM dailynews ORDER BY num DESC)a) "
					+ "WHERE rnum >= ? AND rnum <= ?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			//SQL문 실행
			rs = pstmt.executeQuery();
			
			list = new ArrayList<NewsVO>(); //리스트 객체를 생성하여 변수에 할당하는 과정
			while(rs.next()) {
				NewsVO newsVO = new NewsVO();
				newsVO.setNum(rs.getInt("num"));
				newsVO.setTitle(rs.getString("title"));
				newsVO.setWriter(rs.getString("writer"));
				newsVO.setPasswd(rs.getString("passwd"));
				newsVO.setEmail(rs.getString("email"));
				newsVO.setArticle(rs.getString("article"));
				newsVO.setFilename(rs.getString("filename"));
				newsVO.setReg_date(rs.getDate("reg_date"));
				//자바빈을 ArrayList에 저장
				list.add(newsVO);
			}
			
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		
		return list;
	}
	
	//뉴스 상세 정보
	public NewsVO getNews(int num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		NewsVO vo = null;
		String sql = null;
		try {
			//커넥션풀로부터 커넥션을 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "SELECT * FROM dailynews WHERE num=?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, num);
			//SQL문 실행
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				vo = new NewsVO();
				vo.setNum(rs.getInt("num"));
				vo.setTitle(rs.getString("title"));
				vo.setWriter(rs.getString("writer"));
				vo.setPasswd(rs.getString("passwd"));
				vo.setEmail(rs.getString("email"));
				vo.setArticle(rs.getString("article"));
				vo.setFilename(rs.getString("filename"));
				vo.setReg_date(rs.getDate("reg_date"));
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		
		return vo;
	}
	
	//뉴스 수정
	public void updateNews(NewsVO vo)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		String sub_sql = ""; //빈문자열로 처리 (아래 if블럭으로 들어오지 않으면 빈문자열로 처리해야 되기 때문에 null이라고 하지 않음)
		int cnt = 0;
		
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//전송된 파일이 있을 경우
			if(vo.getFilename()!=null && !vo.getFilename().isEmpty()) {
				sub_sql += ",filename=?"; //이렇게 변수를 따로 만든 후 아래 SQL문에 넣어주기
			}									//업로드한 파일이 있을 경우에만 파일명을 넣어서 수정, 업로드한 파일이 없을 경우 수정 안 하니까 빈문자열이 들어감
			//SQL문 작성
			sql = "UPDATE dailynews SET title=?,writer=?,email=?,article=?" + sub_sql + " WHERE num=?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setString(++cnt, vo.getTitle());
			pstmt.setString(++cnt, vo.getWriter());
			pstmt.setString(++cnt, vo.getEmail());
			pstmt.setString(++cnt, vo.getArticle());	
			//파일명은 동적으로 동작하니까(있을 때가 있고 없을 때가 있으니까)
			if(vo.getFilename()!=null && !vo.getFilename().isEmpty()) {
				pstmt.setString(++cnt, vo.getFilename());
			}
			//파일명이 동적으로 동작하니까 번호가 불일치하는 경우가 생긴다.
			pstmt.setInt(++cnt, vo.getNum());
			//SQL문 실행
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	//뉴스 삭제
	public void deleteNews(int num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "DELETE FROM dailynews WHERE num=?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, num);
			//SQL문 실행
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}

	}
	
}
