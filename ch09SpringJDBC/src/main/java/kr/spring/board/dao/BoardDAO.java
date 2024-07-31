package kr.spring.board.dao;

import java.util.List;

import kr.spring.board.vo.BoardVO;

public interface BoardDAO {
	public void insertBoard(BoardVO board);//이렇게 추상메서드 형태로 만들어서 클래스에 implement한다.
	public int getBoardCount();
	public List<BoardVO> getBoardList(int startRow,int endRow);
	public BoardVO getBoard(int num);
	public void updateBoard(BoardVO board);
	public void deleteBoard(int num);
}
