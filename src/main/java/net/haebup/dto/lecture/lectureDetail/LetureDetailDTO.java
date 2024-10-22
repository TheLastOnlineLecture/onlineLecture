package net.haebup.dto.lecture.lectureDetail;

public class LetureDetailDTO {
	private int lectureDetailIdx;			// 강의 상세 아이디
	private String lectureCode;				// 강의 코드
	private String lectureDetailContent;	// 강의 상세 내용
	
	public int getLectureDetailIdx() {
		return lectureDetailIdx;
	}
	public void setLectureDetailIdx(int lectureDetailIdx) {
		this.lectureDetailIdx = lectureDetailIdx;
	}
	public String getLectureCode() {
		return lectureCode;
	}
	public void setLectureCode(String lectureCode) {
		this.lectureCode = lectureCode;
	}
	public String getLectureDetailContent() {
		return lectureDetailContent;
	}
	public void setLectureDetailContent(String lectureDetailContent) {
		this.lectureDetailContent = lectureDetailContent;
	}
	

}
