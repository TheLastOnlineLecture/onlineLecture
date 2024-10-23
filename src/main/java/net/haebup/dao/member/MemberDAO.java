package net.haebup.dao.member;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.haebup.dto.member.MemberDTO;
import net.haebup.utils.DatabaseUtil.DBConnPool;
import net.haebup.utils.DatabaseUtil.DbQueryUtil;

public class MemberDAO {
	
	// 전체회원목록 ( 관리자용 )
	public List<MemberDTO> getMemberList(int limit, int offset, String userId){
		String sql = "SELECT user_id, user_name, "
				+ "user_nickname, user_type FROM tbl_member "
				+ "where user_id = ? ORBER BY regdate=? DESC LIMIT = ? OFFSET = ?";
		List<MemberDTO> memberList = new ArrayList<MemberDTO>();
		
		 try(Connection conn = DBConnPool.getConnection();
		            DbQueryUtil dbUtil = new DbQueryUtil(conn, sql, new Object[]{limit,offset,userId})){
		                ResultSet rs = dbUtil.executeQuery();
		                while(rs.next()){
		                    MemberDTO memberDTO = new MemberDTO();
		                    memberDTO.setUserId(rs.getString("user_id"));
		                    memberDTO.setUserName(rs.getString("user_name"));
		                    memberDTO.setUserNickname(rs.getString("user_nickname"));
		                    memberDTO.setUserType(rs.getString("user_type"));
		                    memberList.add(memberDTO);
		                }
		                return memberList;
		        }catch(SQLException e){
		            e.printStackTrace();
		            throw new RuntimeException("오류확인."+e);
		        }
	}
	
	// 회원가입 
	public int insertUser(String userId) {
		String sql = "INESRT INTO tbl_member (user_id, password, user_name, nser_nuickname, "
				+ "user_email, user_phone, user_regdate, user_type) value(?, ?, ?, ?, ?, ?, , ?)";
		
		
		return 0;
	}
	
	
	// 로그인
	public MemberDTO loginStudent(MemberDTO memberDto) {
		String sql = "SELECT user_id, password FROM tbl_member user_id WHERE user_type =?";
		
		
		return null;
	}
	
	
	// 선생님 로그인
	public MemberDTO loginTeacher(MemberDTO memberDto) {
		String sql = "SELECT user_id, `password` FROM tbl_member user_id WHERE user_type =?";
		
		
		return null;
	}
	
	// 관리자 로그인
	public MemberDTO loginAdmin(MemberDTO memberDto) {
		String sql = "SELECT user_id, `password` FROM tbl_member user_id WHERE user_type =?";
		
		
		return null;
	}
	
	
	// 회원정보수정 ( 닉네임, 이메일, 핸드폰 가능) (관리자는 타입변경 가능)
	public MemberDTO updateUserInfo(MemberDTO memberDto){
		String sql = "UPDATE tbl_member user_nickname =? , user_phone =?, user_email=? WHERE user_id = ? ";
		
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
	
	
	
	
	
	//============= 추후 추가 =================
	
	
	
	
	
	// 아이디 찾기
	
	// 비밀번호 찾기
	
	

}
