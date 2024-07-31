package kr.story.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.story.vo.StoryVO;
import kr.util.DBUtil;

public class StoryDAO {
	//싱글턴 패턴
	private static StoryDAO instance = new StoryDAO();
	
	public static StoryDAO getInstance() {
		return instance;
	}
	private StoryDAO() {}
	
	//글 저장
	public void insert(StoryVO vo)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			conn = DBUtil.getConnection();
			sql = "INSERT INTO story (snum,title,content,ip,num) VALUES (story_seq.nextval,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getIp());
			pstmt.setInt(4, vo.getNum());
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			//자원정리
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	//사원게시판의 총 개수
	public int getCount()throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int count = 0;
		try {
			//커넥션풀로부터 커넥션을 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "SELECT COUNT(*) FROM story";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//SQL문 실행
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			//자원 정리
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return count;
	}

	//글 목록
	public List<StoryVO> getList(int startRow, int endRow)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<StoryVO> list = null;
		String sql = null;
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT * FROM (SELECT a.*, rownum rnum "
					+ "FROM (SELECT * FROM story JOIN semployee USING(num) ORDER BY snum DESC)a) "
					+ "WHERE rnum >= ? AND rnum <=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			list = new ArrayList<StoryVO>();
			while(rs.next()) {//while문을 이용하여 루프를 돌면서 데이터를 읽어옴
				//자바빈에서는 하나의 데이터나 하나의 객체만 return 가능
				StoryVO storyVO = new StoryVO();
				storyVO.setSnum(rs.getInt("snum"));
				storyVO.setTitle(rs.getString("title"));
				storyVO.setId(rs.getString("id"));
				storyVO.setReg_date(rs.getDate("reg_date"));
				//자바빈을 ArrayList에 저장
				//우리가 자바빈을 반환하는 것이 아니라 ArrayList를 반환하기 때문에 최종적으로 자바빈을 ArrayList에 저장해야 한다
				list.add(storyVO);
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return list;
	}
	//글 상세
	public StoryVO getStory(int num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StoryVO vo = null;
		String sql = null;
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT * FROM story JOIN semployee USING (num) WHERE snum=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				vo = new StoryVO();
				vo.setSnum(rs.getInt("snum"));
				vo.setTitle(rs.getString("title"));
				vo.setId(rs.getString("id"));
				vo.setContent(rs.getString("content"));
				vo.setIp(rs.getString("ip"));
				vo.setReg_date(rs.getDate("reg_date"));
				vo.setNum(rs.getInt("num"));
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return vo;
	}
	//글 수정
	public void update(StoryVO vo)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "UPDATE story JOIN semployee USING(num) SET title=?,id=?,content=?,ip=? WHERE snum=?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getId());
			pstmt.setString(3,  vo.getContent());
			pstmt.setString(4, vo.getIp());
			pstmt.setInt(5, vo.getSnum());
			//SQL문 실행
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			//자원 정리
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	//글 삭제
	public void delete(int snum)throws Exception{
		
	}
}
