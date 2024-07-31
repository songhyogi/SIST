package kr.spring.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.board.dao.BoardDAO;
import kr.spring.board.vo.BoardVO;

@Service
@Transactional //하위에 있는 모든 메서드에 트랜잭션 처리가 됨
public class BoardServiceImpl implements BoardService{
	
	@Autowired //자동으로 의존관계 주입
	private BoardDAO boardDAO;//BoardServiceImpl이 정상적으로 동작하려면 BoardDAO의 인스턴스가 필요함을 의미
	
	@Override
	public void insertBoard(BoardVO board) {
		boardDAO.insertBoard(board);
		
	}

	@Override
	public int getBoardCount() {
		return boardDAO.getBoardCount();
	}

	@Override
	public List<BoardVO> getBoardList(int startRow, int endRow) {
		//변수를 만들 필요 없이 바로 반환
		return boardDAO.getBoardList(startRow, endRow);
	}

	@Override
	public BoardVO getBoard(int num) {
		return boardDAO.getBoard(num);
	}

	@Override
	public void updateBoard(BoardVO board) {
		boardDAO.updateBoard(board);
		
	}

	@Override
	public void deleteBoard(int num) {
		boardDAO.deleteBoard(num);
		
	}

}
