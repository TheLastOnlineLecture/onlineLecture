package net.haebup.dao.board;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import net.haebup.dto.board.PostDTO;
import net.haebup.utils.DatabaseUtil.DBConnPool;
import net.haebup.utils.DatabaseUtil.DbQueryUtil;

public class PostDAO {
 public List<PostDTO> getPostsByWriter(String writer) {
	 List<PostDTO> posts = new ArrayList<>();
	 String sql = "SELECT 'board_type' AS post_type, board_idx AS post_id, board_writer AS writer, " +
			        "board_title AS title, board_regdate AS post_date, board_content AS post_content " +
			        "FROM tbl_board WHERE board_writer = ? " +
			        "UNION ALL " +
			        "SELECT 'qna_type' AS post_type, qna_idx AS post_id, qna_writer AS writer, " +
			        "qna_title AS title, qna_regdate AS post_date, qna_content AS post_content " +
			        "FROM tbl_qna WHERE qna_writer = ? " +
			        "ORDER BY post_date DESC";
	return null;	
 }
}