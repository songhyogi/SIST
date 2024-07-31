package kr.spring.board.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.spring.board.vo.BoardVO;

@Repository //DAO에는 @Repository를 넣어줘야 자동스캔 대상이 된다.
public class BoardDAOImpl implements BoardDAO{
	
	@Autowired
	private SqlSessionTemplate sqlSession; //SqlSessionTemplate 주입
	
	@Override
	public void insertBoard(BoardVO board) {
		sqlSession.insert("insertBoard",board);
	}

	@Override
	public int selectBoardCount() {//자동변환되기 때문에 int라고 해도 되고 Integer라고 해도 된다.
		return sqlSession.selectOne("selectBoardCount");//xml태그의 식별자(id)를 넣어서 읽어온다. ID를 통해서 XML의 SQL문을 읽어옴.
	}

	@Override
	public List<BoardVO> selectBoardList(Map<String, Integer> map) {
		return sqlSession.selectList("selectBoardList",map);//map안에 key와 value의 쌍으로 start endrow가 저장되어 있다.
		
	}

	@Override
	public BoardVO selectBoard(int num) {
		return sqlSession.selectOne("selectBoard",num);
	}

	@Override
	public void updateboard(BoardVO board) {
		sqlSession.update("updateBoard",board);
	}

	@Override
	public void deleteBoard(int num) {
		sqlSession.delete("deleteBoard",num);
	}

}
