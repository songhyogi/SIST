package kr.item.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.item.vo.ItemVO;
import kr.util.DBUtil;

public class ItemDAO {
	//싱글턴 패턴
	private static ItemDAO instance = new ItemDAO();
	
	public static ItemDAO getInstance() {
		return instance;
	}
	private ItemDAO() {}
	
	//관리자 - 상품 등록
	public void insertItem(ItemVO item)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "INSERT INTO zitem (item_num,name,price,quantity,photo1,photo2,detail,status) VALUES (zitem_seq.nextval,?,?,?,?,?,?,?)";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setString(1, item.getName());
			pstmt.setInt(2, item.getPrice());
			pstmt.setInt(3, item.getQuantity());
			pstmt.setString(4, item.getPhoto1());
			pstmt.setString(5, item.getPhoto2());
			pstmt.setString(6, item.getDetail());
			pstmt.setInt(7,  item.getStatus());
			//SQL문 실행
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	//관리자 - 상품 수정
	public void updateItem(ItemVO item)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		String sub_sql = "";//이미지 업로드 때문에 sub_sql을 사용 (원래 있던 이미지를 그대로 사용할 수 있고 새로 업로드할 수 있기 때문에 가변적으로 사용해야 하기 때문)
		int cnt = 0;
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			
			if(item.getPhoto1()!=null && !"".equals(item.getPhoto1())) {//photo1이 존재할 때 photo1만 변경
				sub_sql += ",photo1=?";
			}
			if(item.getPhoto2()!=null && !"".equals(item.getPhoto2())) {//photo2가 존재할 때 photo2만 변경
				sub_sql += ",photo2=?";
			}
			
			sql = "UPDATE zitem SET name=?,price=?,quantity=?,detail=?,modify_date=SYSDATE,status=?" + sub_sql + " WHERE item_num=?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setString(++cnt, item.getName());
			pstmt.setInt(++cnt, item.getPrice());
			pstmt.setInt(++cnt, item.getQuantity());
			pstmt.setString(++cnt, item.getDetail());
			pstmt.setInt(++cnt, item.getStatus());
			if(item.getPhoto1()!=null && !"".equals(item.getPhoto1())) {
				pstmt.setString(++cnt, item.getPhoto1());
			}
			if(item.getPhoto2()!=null && !"".equals(item.getPhoto2())) {
				pstmt.setString(++cnt, item.getPhoto2());
			}
			pstmt.setInt(++cnt, item.getItem_num());
			//SQL문 실행
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
		
	}
	//관리자 - 상품 삭제
	public void deleteItem(int item_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		String sql = null;
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//오토 커밋 해제
			conn.setAutoCommit(false);
			
			sql = "DELETE FROM zcart WHERE item_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, item_num);
			pstmt.executeUpdate();
			
			sql = "DELETE FROM zitem WHERE item_num=?";
			pstmt2 = conn.prepareStatement(sql);
			pstmt2.setInt(1, item_num);
			pstmt2.executeUpdate();
			
			//모든 SQL문이 성공하면 commit
			conn.commit();
		}catch(Exception e) {
			//SQL문이 하나라도 실패하면 rollback
			conn.rollback();
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt2, null);
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	//관리자/사용자 - 전체 상품 개수/검색 상품 개수
	public int getItemCount(String keyfield,String keyword,int status)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		String sub_sql = "";
		int count = 0;
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			if(keyword != null && !"".equals(keyword)) {
				if(keyfield.equals("1")) sub_sql += "AND name LIKE '%' || ? || '%'";
				else if(keyfield.equals("2")) sub_sql += "AND detail LIKE '%' || ? || '%'";
			}
			
			sql = "SELECT count (*) FROM zitem WHERE status > ? " + sub_sql;
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, status);
			if(keyword!=null && !"".equals(keyword)) {
				pstmt.setString(2, keyword);
			}
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		
		return count;
	}
	
	//관리자/사용자 - 전체 상품 목록/검색 상품 목록
	public List<ItemVO> getListItem(int start,int end,String keyfield,String keyword,int status)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ItemVO> list = null;
		String sql = null;
		String sub_sql = "";
		int cnt = 0;
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			
			if(keyword != null && !"".equals(keyword)) {
				//검색 처리
				if(keyfield.equals("1")) sub_sql += "AND name LIKE '%' || ? || '%'";
				else if (keyfield.equals("2")) sub_sql += "AND detail LIKE '%' || ? || '%'";
			}
			//SQL문 작성
			//status가 0이면 1(미표시),2(표시)번 모두 호출 - 관리자용
			//status가 1이면 2(표시)만 호출 - 사용자용
			sql = "SELECT * FROM (SELECT a.*,rownum rnum FROM "
					+ "(SELECT * FROM zitem WHERE status > ? " + sub_sql + " ORDER BY item_num DESC)a) "
							+ "WHERE rnum >= ? AND rnum <= ?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(++cnt, status);
			if(keyword!=null && !"".equals(keyword)) {
				pstmt.setString(++cnt, keyword);
			}
			pstmt.setInt(++cnt, start);
			pstmt.setInt(++cnt, end);
			//SQL문 실행
			rs = pstmt.executeQuery();
			list = new ArrayList<ItemVO>();
			while(rs.next()) {
				ItemVO item = new ItemVO();
				item.setItem_num(rs.getInt("item_num"));
				item.setName(rs.getString("name"));
				item.setPrice(rs.getInt("price"));
				item.setQuantity(rs.getInt("quantity"));
				item.setPhoto1(rs.getString("photo1"));
				item.setReg_date(rs.getDate("reg_date"));
				item.setStatus(rs.getInt("status"));
				
				list.add(item);//arraylist에 반환
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return list;
	}
	//관리자/사용자 - 상품상세
	public ItemVO getItem(int item_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ItemVO item = null;
		String sql = null;
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "SELECT * FROM zitem WHERE item_num=?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, item_num);
			//SQL문 실행
			rs = pstmt.executeQuery();
			if(rs.next()) {
				item = new ItemVO();
				item.setItem_num(rs.getInt("item_num"));
				item.setName(rs.getString("name"));
				item.setPrice(rs.getInt("price"));
				item.setQuantity(rs.getInt("quantity"));
				item.setPhoto1(rs.getString("photo1"));
				item.setPhoto2(rs.getString("photo2"));
				item.setDetail(rs.getString("detail"));
				item.setReg_date(rs.getDate("reg_date"));//겉으로 보여지지 않음
				item.setStatus(rs.getInt("status"));//사용자한테 보여지지 않고 관리차원에서 관리자에게만 보여짐
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return item;
	}
	
}
