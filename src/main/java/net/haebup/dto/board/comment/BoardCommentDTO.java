package net.haebup.dto.board.comment;

public class BoardCommentDTO {
	private int commentIdx; 			// 댓글 아이디
	private int boardIdx;			// 댓글 대상 게시물 아이디
	private String userId;  		// 댓글 작성자 아이디
	private String commentContent;	// 댓글 내용
	private String commentRegdate;		// 댓글 작성일
	
	
		public int getCommentIdx() {
		return commentIdx;
	}
	public void setCommentIdx(int commentIdx) {
		this.commentIdx = commentIdx;
	}
	public int getBoardIdx() {
		return boardIdx;
	}
	public void setBoardIdx(int boardIdx) {
		this.boardIdx = boardIdx;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getCommentContent() {
		return commentContent;
	}
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	public String getCommentRegdate() {
		return commentRegdate;
	}
	public void setCommentRegdate(String commentRegdate) {
		this.commentRegdate = commentRegdate;
	}
	
	
	
}
