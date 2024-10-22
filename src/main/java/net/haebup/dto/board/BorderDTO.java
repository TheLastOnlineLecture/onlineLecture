package net.haebup.dto.board;

public class BorderDTO {
	private int boardIdx;			// 게시물 아이디
	private String boardTitle;		// 게시물 제목
	private String boarContent;		// 게시물 내용
	private String boarRegdate;		// 게시물 등록일
	private char boarType;		// 게시물 유형 (P: 자유게시판, N: 공지사항, D: 자료실, C: 강의 공지, R: 수강후기)
	private String boardWriter;		// 게시물 작성자 아이디
	
	
	public int getBoardIdx() {
		return boardIdx;
	}
	public void setBoardIdx(int boardIdx) {
		this.boardIdx = boardIdx;
	}
	public String getBoardTitle() {
		return boardTitle;
	}
	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}
	public String getBoarContent() {
		return boarContent;
	}
	public void setBoarContent(String boarContent) {
		this.boarContent = boarContent;
	}
	public String getBoarRegdate() {
		return boarRegdate;
	}
	public void setBoarRegdate(String boarRegdate) {
		this.boarRegdate = boarRegdate;
	}
	public char getBoarType() {
		return boarType;
	}
	public void setBoarType(char boarType) {
		this.boarType = boarType;
	}
	public String getBoardWriter() {
		return boardWriter;
	}
	public void setBoardWriter(String boardWriter) {
		this.boardWriter = boardWriter;
	}
	

}
