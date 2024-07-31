package kr.spring.member.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.member.vo.MemberVO;

@Mapper //@Mapper가 있어야 컨테이너에 넣음
public interface MemberMapper {
	//회원관리 - 일반회원
	@Select("SELECT spmember_seq.nextval FROM dual")
	public Long selectMem_num();
	@Insert("INSERT INTO spmember (mem_num,id,nick_name) VALUES (#{mem_num},#{id},#{nick_name})")
	public void insertMember(MemberVO member);
	public void insertMember_detail(MemberVO member);
	public MemberVO selectCheckMember(String id);
	@Select("SELECT * FROM spmember JOIN spmember_detail USING(mem_num) WHERE mem_num=#{mem_num}")
	public MemberVO selectMember(Long mem_num); //long mem_num을 객체로 인식하는데 소문자 l로 작성해도 자동변환되어서 대문자 L로 인식한다.
	@Update("UPDATE spmember SET nick_name=#{nick_name} WHERE mem_num=#{mem_num}")
	public void updateMember(MemberVO member);
	public void updateMember_detail(MemberVO member);
	@Update("UPDATE spmember_detail SET passwd=#{passwd} WHERE mem_num=#{mem_num}")
	public void updatePassword(MemberVO member);
	public void deleteMember(Long mem_num);
	public void deleteMember_detail(Long mem_num);
	
	//자동 로그인
	@Update("UPDATE spmember_detail SET au_id=#{au_id} WHERE mem_num=#{mem_num}")
	public void updateAu_id(String au_id,Long mem_num);
	@Select("SELECT m.mem_num,m.id,m.auth,d.au_id,d.passwd,m.nick_name,d.email FROM spmember m JOIN spmember_detail d ON m.mem_num=d.mem_num WHERE d.au_id=#{au_id}")
	public MemberVO selectAu_id(String au_id);//정보를 읽어오는 부분
	@Update("UPDATE spmember_detail SET au_id='' WHERE mem_num=#{mem_num}")
	public void deleteAu_id(Long mem_num);//비밀번호 변경할 때 쿠키정보를 체크하는 au_id를 초기화한다.
	
	//비밀번호 찾기
	@Update("UPDATE spmember_detail SET passwd=#{passwd} WHERE mem_num=#{mem_num}")
	public void updateRandomPassword(MemberVO member);
	
	//프로필 이미지 업데이트
	@Update("UPDATE spmember_detail SET photo=#{photo},photo_name=#{photo_name} WHERE mem_num=#{mem_num}")
	public void updateProfile(MemberVO member);
	
	//채팅 회원 정보 검색
	@Select("SELECT mem_num,id,nick_name FROM spmember WHERE auth>=2 AND id LIKE '%' || #{id} || '%'")//아이디가 유니크한 정보이기 때문에 아이디 중심으로 해서 닉네임은 없어도 된다.
	public List<MemberVO> selectSearchMember(String id);
}
