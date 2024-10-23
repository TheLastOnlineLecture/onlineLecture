package net.haebup.dao.member;

import java.util.ArrayList;
import java.util.List;

import net.haebup.dto.member.MemberDTO;

public class MemberDAO {
	// 전체회원목록 ( 관리자용 )
	public List<MemberDTO> getMemberList(int limit, int offset, String userId){
		String sql = "SELECT user_id, user_name, "
				+ "user_nickname, user_type FROM tbl_member where user_id = ? limit = ?. offset = ?";
		List<MemberDTO> memberList = new ArrayList<MemberDTO>();
		
		
		return null;
	}
	
	// 회원가입 
	public int insertUser(String userId) {
		
		
		return 0;
	}
	
	
	// 로그인
	public MemberDTO login(MemberDTO memberDto) {
		
		
		return null;
	}
	
	
	// 회원정보수정
	public MemberDTO updateUserInfo(MemberDTO memberDto){
		
		
		return null;
	}
	
	
	// 회원탈퇴
	public MemberDTO deleteUser(MemberDTO memberDto) {
			
			
			return null;
		}
	
	
	// 전체 회원수
	public int getUserTotalCount (String userId) {
		
		return 0;
	}
	
	
	// 추후 추가
	
	
	// 아이디 찾기
	
	// 비밀번호 찾기
	
	

}
