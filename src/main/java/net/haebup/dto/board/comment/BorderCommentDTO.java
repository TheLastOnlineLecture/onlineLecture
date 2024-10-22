package net.haebup.dto.board.comment;

public class BorderCommentDTO {
	private int commentId; 			// 댓글 아이디
	private int postIdx;			// 댓글 대상 게시물 아이디
	private String userId;  		// 댓글 작성자 아이디
	private String commentContent;	// 댓글 내용
	private String commentDate;		// 댓글 작성일
	
	
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	public int getPostIdx() {
		return postIdx;
	}
	public void setPostIdx(int postIdx) {
		this.postIdx = postIdx;
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
	public String getCommentDate() {
		return commentDate;
	}
	public void setCommentDate(String commentDate) {
		this.commentDate = commentDate;
	}
	
	
	
}
