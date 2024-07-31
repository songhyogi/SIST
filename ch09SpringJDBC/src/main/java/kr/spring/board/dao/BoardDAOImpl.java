package kr.spring.board.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import kr.spring.board.vo.BoardVO;

@Repository
public class BoardDAOImpl implements BoardDAO{
	private static final String INSERT_SQL = "INSERT INTO aboard (num,writer,title,passwd,content,reg_date) VALUES (aboard_seq.nextval,?,?,?,?,SYSDATE)";
	private static final String SELECT_COUNT_SQL = "SELECT COUNT(*) FROM aboard";
	private static final String SELECT_LIST_SQL = "SELECT * FROM (SELECT a.*, rownum rnum FROM(SELECT * FROM aboard ORDER BY reg_date DESC)a) WHERE rnum >= ? AND rnum <=?";
	private static final String SELECT_DETAIL_SQL = "SELECT * FROM aboard WHERE num=?";
	private static final String UPDATE_SQL = "UPDATE aboard SET writer=?,title=?,content=? WHERE num=?";
	private static final String DELETE_SQL = "DELETE FROM aboard WHERE num=?";
	
	//하나의 레코드의 데이터를 자바빈에 매핑
	private RowMapper<BoardVO> rowMapper = new RowMapper<BoardVO>() {
		public BoardVO mapRow(ResultSet rs, int rowNum)throws SQLException{//rowNum에 행번호가 전달
			BoardVO board = new BoardVO();
			board.setNum(rs.getInt("num"));
			board.setWriter(rs.getString("writer"));
			board.setTitle(rs.getString("title"));
			board.setPasswd(rs.getString("passwd"));
			board.setContent(rs.getString("content"));
			board.setReg_date(rs.getDate("reg_date"));
			return board;
		}
	};
	@Autowired
	private JdbcTemplate jdbcTemplate;//setter는 @Autowired가 자동 생성
	
	@Override
	public void insertBoard(BoardVO board) {
		jdbcTemplate.update(INSERT_SQL,new Object[] {board.getWriter(),board.getTitle(),board.getPasswd(),board.getContent()});//?에 데이터바인딩을 하는데 Object 배열 안에 담아서 넘겨준다.
		
	}

	@Override
	public int getBoardCount() {//int로 하면 기본값이 0인데 Integer로 하면 기본값이 null이다. 그래서 값이 없을 경우 문제가 생기기 때문에 int로 할지 Integer로 할지는 선택사항이다.
		
		return jdbcTemplate.queryForObject(SELECT_COUNT_SQL, Integer.class);//내부적으로 SELECT_COUNT_SQL을 실행한 다음에 정수형태로 반환한다는 의미 
	}

	@Override
	public List<BoardVO> getBoardList(int startRow, int endRow) {
		List<BoardVO> list = jdbcTemplate.query(SELECT_LIST_SQL, new Object[] {startRow,endRow},rowMapper);//SQL문장, sql에 전달될 데이터.
		return list;// query가 list형태로 만듦
	}

	@Override
	public BoardVO getBoard(int num) {
		
		return jdbcTemplate.queryForObject(SELECT_DETAIL_SQL, new Object[] {num},rowMapper);
	}

	@Override
	public void updateBoard(BoardVO board) {
		jdbcTemplate.update(UPDATE_SQL, new Object[] {board.getWriter(),board.getTitle(),board.getContent(),board.getNum()});
		
	}

	@Override
	public void deleteBoard(int num) {
		jdbcTemplate.update(DELETE_SQL, new Object[] {num});
		
	}

}
