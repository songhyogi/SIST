package kr.spring.board.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.board.vo.BoardFavVO;
import kr.spring.board.vo.BoardReFavVO;
import kr.spring.board.vo.BoardReplyVO;
import kr.spring.board.vo.BoardResponseVO;
import kr.spring.board.vo.BoardVO;

@Mapper
public interface BoardMapper {
	//부모글
	public List<BoardVO> selectList(Map<String,Object> map);
	public Integer selectRowCount(Map<String,Object> map);
	public void insertBoard(BoardVO board);
	@Select("SELECT * FROM spboard JOIN spmember USING(mem_num) LEFT OUTER JOIN spmember_detail USING(mem_num) WHERE board_num=#{board_num}")
	public BoardVO selectBoard(Long board_num);
	@Update("UPDATE spboard SET hit=hit+1 WHERE board_num=#{board_num}")
	public void updateHit(Long board_num);
	public void updateBoard(BoardVO board);
	@Delete("DELETE FROM spboard WHERE board_num=#{board_num}")
	public void deleteBoard(Long board_num);
	@Update("UPDATE spboard SET filename='' WHERE board_num=#{board_num}") //파일명을 빈문자열로
	public void deleteFile(Long board_num);
	//부모글 좋아요
	@Select("SELECT * FROM spboard_fav WHERE board_num=#{board_num} AND mem_num=#{mem_num}")
	public BoardFavVO selectFav(BoardFavVO fav);
	@Select("SELECT COUNT(*) FROM spboard_fav WHERE board_num=#{board_num}")
	public Integer selectFavCount(Long board_num);
	@Insert("INSERT INTO spboard_fav (board_num,mem_num) VALUES (#{board_num},#{mem_num})")
	public void insertFav(BoardFavVO fav);
	@Delete("DELETE FROM spboard_fav WHERE board_num=#{board_num} AND mem_num=#{mem_num}")
	public void deleteFav(BoardFavVO fav);
	@Delete("DELETE FROM spboard_fav WHERE board_num=#{board_num}")
	public void deleteFavByBoardNum(Long board_num);
	//댓글
	public List<BoardReplyVO> selectListReply(Map<String,Object> map);
	@Select("SELECT COUNT(*) FROM spboard_reply WHERE board_num=#{board_num}")
	public Integer selectRowCountReply(Map<String,Object> map);
	//댓글 수정 또는 삭제 시 작성자 회원번호를 구하기 위해 사용 (작성자여야만 수정 삭제가 가능하기 때문에 작성자 회원번호를 구함)
	@Select("SELECT * FROM spboard_reply WHERE re_num=#{re_num}")
	public BoardReplyVO selectReply(Long re_num);
	public void insertReply(BoardReplyVO boardReply);
	@Update("UPDATE spboard_reply SET re_content=#{re_content},re_ip=#{re_ip},re_mdate=SYSDATE WHERE re_num=#{re_num}")
	public void updateReply(BoardReplyVO boardReply);
	@Delete("DELETE FROM spboard_reply WHERE re_num=#{re_num}")
	public void deleteReply(Long re_num);
	//부모글 삭제 시 댓글이 존재하면 부모글 삭제 전 댓글 삭제
	@Delete("DELETE FROM spboard_reply WHERE board_num=#{board_num}")
	public void deleteReplyByBoardNum(Long board_num);
	//댓글 좋아요
	@Select("SELECT * FROM spreply_fav WHERE re_num=#{re_num} AND mem_num=#{mem_num}")
	public BoardReFavVO selectReFav(BoardReFavVO fav);
	@Select("SELECT COUNT(*) FROM spreply_fav WHERE re_num=#{re_num}")
	public Integer selectReFavCount(Long re_num);
	@Insert("INSERT INTO spreply_fav (re_num,mem_num) VALUES (#{re_num},#{mem_num})")
	public void insertReFav(BoardReFavVO fav);
	@Delete("DELETE FROM spreply_fav WHERE re_num=#{re_num} AND mem_num=#{mem_num}")
	public void deleteReFav(BoardReFavVO fav);
	@Delete("DELETE FROM spreply_fav WHERE re_num=#{re_num}")
	public void deleteReFavByReNum(Long re_num);//댓글 삭제할 때 좋아요 삭제
	@Delete("DELETE FROM spreply_fav WHERE re_num IN (SELECT re_num FROM spboard_reply WHERE board_num=#{board_num})")//삭제할 내용만 검색해서 넣어주면 sql문이 내부적으로 루프를 돈다 IN연산자 사용
	public void deleteReFavByBoardNum(Long board_num);//부모글 삭제할 때 좋아요 삭제
	//답글
	public List<BoardResponseVO> selectListResponse(Long re_num);
	@Select("SELECT * FROM spboard_response WHERE te_num=#{te_num}")
	public BoardResponseVO selectResponse(Long te_num);
	public void insertResponse(BoardResponseVO boardResponse);
	@Update("UPDATE spboard_response SET te_content=#{te_content},te_ip=#{te_ip},te_mdate=SYSDATE WHERE te_num=#{te_num}")
	public void updateResponse(BoardResponseVO boardResponse);
	public void deleteResponse(Long te_num);
	//댓글 삭제 시 자식글인 답글을 모두 삭제
	@Delete("DELETE FROM spboard_response WHERE re_num=#{re_num}")
	public void deleteResponseByRenum(Long re_num);
	//부모글 삭제 시 댓글의 답글이 존재하면 댓글 번호를 구해서 답글 삭제 (board_num을 넘겨서 re_num을 구해서 re_num 아래에 있는 답글은 전부 지움)
	@Delete("DELETE FROM spboard_response WHERE re_num IN (SELECT re_num FROM spboard_reply WHERE board_num=#{board_num})")
	public void deleteResponseByBoardNum(Long board_num);//따로 할 수도 있고 합쳐서 할 수 있는데 합쳐서 해볼 예정
	//답글의 개수 구하기
	@Select("SELECT COUNT(*) FROM spboard_response WHERE re_num=#{re_num}")
	public Integer selectResponseCount(Long re_num);//답글을 댓글 번호로 그룹화시키기 때문에 re_num 밑에 있는 자식들을 구함
	
}
