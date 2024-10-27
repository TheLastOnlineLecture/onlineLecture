package net.haebup.dto.board.comment;

public class MyCommentDTO {
	private int myCommentIdx;         // 댓글 아이디
    private String myCommentWriter;    // 댓글 작성자
    private String myCommentContent;   // 댓글 내용
    private String myCommentRegdate;
    private String myCommentType;
    
	public String getMyCommentType() {
		return myCommentType;
	}
	public void setMyCommentType(String myCommentType) {
		this.myCommentType = myCommentType;
	}
	public int getMyCommentIdx() {
		return myCommentIdx;
	}
	public void setMyCommentIdx(int myCommentIdx) {
		this.myCommentIdx = myCommentIdx;
	}
	public String getMyCommentWriter() {
		return myCommentWriter;
	}
	public void setMyCommentWriter(String myCommentWriter) {
		this.myCommentWriter = myCommentWriter;
	}
	public String getMyCommentContent() {
		return myCommentContent;
	}
	public void setMyCommentContent(String myCommentContent) {
		this.myCommentContent = myCommentContent;
	}
	public String getMyCommentRegdate() {
		return myCommentRegdate;
	}
	public void setMyCommentRegdate(String myCommentRegdate) {
		this.myCommentRegdate = myCommentRegdate;
	}  

}
