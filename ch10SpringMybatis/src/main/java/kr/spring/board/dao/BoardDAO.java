package kr.spring.board.dao;

import java.util.List;
import java.util.Map;

import kr.spring.board.vo.BoardVO;

public interface BoardDAO {
	public void insertBoard(BoardVO board);
	public int selectBoardCount();
	public List<BoardVO> selectBoardList(Map<String,Integer> map);//마이바티스는 하나의 인자만을 사용하므로 Map으로 묶어서 보내야 한다.
	public BoardVO selectBoard(int num);
	public void updateboard(BoardVO board);
	public void deleteBoard(int num);
}
