package net.haebup.dao.member;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.lang.StringBuilder;
import net.haebup.dto.member.MemberDTO;
import net.haebup.utils.DatabaseUtil.DBConnPool;
import net.haebup.utils.DatabaseUtil.DbQueryUtil;

public class MemberDAO {

	// 전체회원목록 ( 관리자용 )
	public List<MemberDTO> getMemberList(int limit, int offset) throws SQLException {
		String sql = "SELECT user_id, user_name, "
				+ "user_nickname, user_type FROM tbl_member "
				+ "ORDER BY regdate DESC LIMIT = ? OFFSET = ?";
		List<MemberDTO> memberList = new ArrayList<MemberDTO>();

		try (Connection conn = DBConnPool.getConnection();
				DbQueryUtil dbUtil = new DbQueryUtil(conn, sql, new Object[] { limit, offset })) {
			ResultSet rs = dbUtil.executeQuery();
			while (rs.next()) {
				MemberDTO memberDTO = new MemberDTO();
				memberDTO.setUserId(rs.getString("user_id"));
				memberDTO.setUserName(rs.getString("user_name"));
				memberDTO.setUserNickname(rs.getString("user_nickname"));
				memberDTO.setUserType(rs.getString("user_type"));
				memberList.add(memberDTO);
			}
			return memberList;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("오류확인." + e);
		}
	}

	// 회원가입
	public int insertUser(MemberDTO memberDto) throws SQLException {
		String sql = "INSERT INTO `TBL_MEMBER` (user_id, user_pwd, user_name, user_nickname, "
				+ "user_email, user_phone, user_birth ,user_type) value(?, ?, ?, ?, ?, ?, ?, 'S01')";
		try (Connection conn = DBConnPool.getConnection();
				DbQueryUtil dbUtil = new DbQueryUtil(conn, sql,
						new Object[] { memberDto.getUserId(), memberDto.getUserPwd(), memberDto.getUserName(),
								memberDto.getUserNickname(), memberDto.getUserEmail(), memberDto.getUserPhone(),
								memberDto.getUserBirth() })) {
			return dbUtil.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("회원가입 중 오류가 발생하였습니다." + e);
		}
	}
	// 회원정보 조회
	public MemberDTO getUserInfo(String userId) throws SQLException {
		MemberDTO memberDto = new MemberDTO();
		String sql = "SELECT user_id, user_type,user_name,user_nickname,user_email,user_phone FROM tbl_member WHERE user_id = ?";
		try (Connection conn = DBConnPool.getConnection();
				DbQueryUtil dbUtil = new DbQueryUtil(conn, sql, new String[] { userId })) {
			ResultSet rs = dbUtil.executeQuery();
			if (rs.next()) {
				memberDto.setUserType(rs.getString("user_type"));
				memberDto.setUserId(rs.getString("user_id"));
				memberDto.setUserName(rs.getString("user_name"));
				memberDto.setUserNickname(rs.getString("user_nickname"));
				memberDto.setUserEmail(rs.getString("user_email"));
				memberDto.setUserPhone(rs.getString("user_phone"));
				return memberDto;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("회원정보 조회 중 오류가 발생하였습니다." + e);
		}
		return null;
	}

	//마일리지 조회
	public int getMileage(String userId) throws SQLException {
		String sql = "SELECT mileage FROM tbl_member WHERE user_id = ?";
		try (Connection conn = DBConnPool.getConnection();
				DbQueryUtil dbUtil = new DbQueryUtil(conn, sql, new String[] { userId })) {
			ResultSet rs = dbUtil.executeQuery();
			if (rs.next()) {
				return rs.getInt("mileage");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("마일리지 조회 중 오류가 발생하였습니다." + e);
		}
		return 0;
	}

	// 학생 로그인
	public MemberDTO loginStudent(String userId) throws SQLException {
		String sql = "SELECT user_id, user_pwd, user_type,user_name,user_nickname,user_email,user_phone FROM tbl_member WHERE user_id = ? AND user_type LIKE 'S%' AND user_type != 'N'";
		try (Connection conn = DBConnPool.getConnection();
				DbQueryUtil dbUtil = new DbQueryUtil(conn, sql, new String[] { userId })) {
			ResultSet rs = dbUtil.executeQuery();
			MemberDTO memberDto = new MemberDTO();
			if (rs.next()) {
				memberDto.setUserType(rs.getString("user_type"));
				// memberDto.setUserType("S");
				memberDto.setUserPwd(rs.getString("user_pwd"));
				memberDto.setUserId(rs.getString("user_id"));
				memberDto.setUserName(rs.getString("user_name"));
				memberDto.setUserNickname(rs.getString("user_nickname"));
				memberDto.setUserEmail(rs.getString("user_email"));
				memberDto.setUserPhone(rs.getString("user_phone"));
				return memberDto;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("로그인 중 오류가 발생하였습니다." + e);
		}
		return null;
	}
	// 선생님 로그인
	public MemberDTO loginTeacher(String userId) throws SQLException {
		String sql = "SELECT user_id, user_pwd, user_type,user_name,user_nickname,user_email,user_phone FROM tbl_member WHERE user_id = ? AND user_type LIKE 'T%' AND user_type != 'N'";
		try (Connection conn = DBConnPool.getConnection();
				DbQueryUtil dbUtil = new DbQueryUtil(conn, sql, new String[] { userId })) {
			ResultSet rs = dbUtil.executeQuery();
			MemberDTO memberDto = new MemberDTO();
			if (rs.next()) {
				// memberDto.setUserType(rs.getString("user_type"));
				memberDto.setUserType("T");
				memberDto.setUserPwd(rs.getString("user_pwd"));
				memberDto.setUserId(rs.getString("user_id"));
				memberDto.setUserName(rs.getString("user_name"));
				memberDto.setUserNickname(rs.getString("user_nickname"));
				memberDto.setUserEmail(rs.getString("user_email"));
				memberDto.setUserPhone(rs.getString("user_phone"));
				return memberDto;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("로그인 중 오류가 발생하였습니다." + e);
		}
		return null;
	}
	//어드민 로그인
	public MemberDTO loginAdmin(String userId) throws SQLException {
		String sql = "SELECT user_id, user_pwd, user_type,user_name,user_nickname,user_email,user_phone FROM tbl_member WHERE user_id = ? AND user_type LIKE 'A%' AND user_type != 'N'";
		try (Connection conn = DBConnPool.getConnection();
				DbQueryUtil dbUtil = new DbQueryUtil(conn, sql, new String[] { userId })) {
			ResultSet rs = dbUtil.executeQuery();
			MemberDTO memberDto = new MemberDTO();
			if (rs.next()) {
				// memberDto.setUserType(rs.getString("user_type"));
				memberDto.setUserType("A");
				memberDto.setUserPwd(rs.getString("user_pwd"));
				memberDto.setUserId(rs.getString("user_id"));
				memberDto.setUserName(rs.getString("user_name"));
				memberDto.setUserNickname(rs.getString("user_nickname"));
				memberDto.setUserEmail(rs.getString("user_email"));
				memberDto.setUserPhone(rs.getString("user_phone"));
				return memberDto;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("로그인 중 오류가 발생하였습니다." + e);
		}
		return null;
	}

	// 회원정보수정 ( 닉네임, 이메일, 핸드폰 가능) (관리자는 타입변경 가능)
	public int updateUserInfo(MemberDTO memberDto) throws SQLException {
		String sql = "UPDATE tbl_member SET user_nickname =? , user_phone =? , user_email=? WHERE user_id = ? ";
		try (Connection conn = DBConnPool.getConnection();
				DbQueryUtil dbUtil = new DbQueryUtil(conn, sql, new Object[] { memberDto.getUserNickname(),
						memberDto.getUserPhone(), memberDto.getUserEmail(), memberDto.getUserId() })) {
			return dbUtil.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("회원정보수정 중 오류가 발생하였습니다." + e);
		}
	}
	// 회원탈퇴
	public int deleteMember(String userId) throws SQLException {
		String sql = "UPDATE tbl_member SET user_pwd = NULL , user_type = 'N', user_name = NULL, user_nickname = NULL , user_email = NULL , user_phone = NULL ,user_birth = NULL, user_regdate = NULL WHERE user_id = ? ";
		try (Connection conn = DBConnPool.getConnection();
				DbQueryUtil dbUtil = new DbQueryUtil(conn, sql, new Object[] { userId })) {
			return dbUtil.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("회원탈퇴 중 오류가 발생하였습니다." + e);
		}
	}

	// 타입으로 회원수 조회
	public int getUserTotalCount(String type) throws SQLException {
		String sql = "SELECT COUNT(*) FROM tbl_member WHERE user_type = ?";
		try (Connection conn = DBConnPool.getConnection();
				DbQueryUtil dbUtil = new DbQueryUtil(conn, sql, new Object[] { type })) {
			ResultSet rs = dbUtil.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("회원수 조회 중 오류가 발생하였습니다." + e);
		}
		return 0;
	}

	//관리자용 사용자 삭제
	public int deleteUserAdmin(String[] userIds) throws SQLException{
		//"UPDATE tbl_member SET user_type = 'N' WHERE user_id IN(?"
		StringBuilder sql = new StringBuilder("UPDATE tbl_member SET user_type = 'N' WHERE user_id IN(");
		for (int i = 0; i < userIds.length; i++) {
            sql.append(i == 0 ? "?" : ", ?");
        }
        sql.append(")");
        try(Connection conn = DBConnPool.getConnection();
            DbQueryUtil dbUtil = new DbQueryUtil(conn, sql.toString(), userIds)){
                return dbUtil.executeUpdate();
        }
	}
	
	public int updateMileage(String userId, int newMileage) throws SQLException {
		String sql = "UPDATE tbl_member SET mileage = ? WHERE user_id = ?";
		try (Connection conn = DBConnPool.getConnection();
				DbQueryUtil dbUtil = new DbQueryUtil(conn, sql, new Object[] { newMileage, userId })) {
			return dbUtil.executeUpdate();
		}
	}


	

	// ============= 추후 추가 =================

	// 아이디 찾기

	// 비밀번호 찾기

}
