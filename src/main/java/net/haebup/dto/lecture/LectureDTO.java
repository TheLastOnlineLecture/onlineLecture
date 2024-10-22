package net.haebup.dto.lecture;

public class LectureDTO {
	private String lectureCode;			// 강의 코드
	private String lectureName;			// 강의 이름
	private String lecturePrice;		// 강의 가격
	private String lectureRegdete;		// 강의 등록일
	private String lectureLimitDete;	// 강의 제한일
	private String teacherID;			// 강의 선생님 아이디
	
	public String getLectureCode() {
		return lectureCode;
	}
	public void setLectureCode(String lectureCode) {
		this.lectureCode = lectureCode;
	}
	public String getLectureName() {
		return lectureName;
	}
	public void setLectureName(String lectureName) {
		this.lectureName = lectureName;
	}
	public String getLecturePrice() {
		return lecturePrice;
	}
	public void setLecturePrice(String lecturePrice) {
		this.lecturePrice = lecturePrice;
	}
	public String getLectureRegdete() {
		return lectureRegdete;
	}
	public void setLectureRegdete(String lectureRegdete) {
		this.lectureRegdete = lectureRegdete;
	}
	public String getLectureLimitDete() {
		return lectureLimitDete;
	}
	public void setLectureLimitDete(String lectureLimitDete) {
		this.lectureLimitDete = lectureLimitDete;
	}
	public String getTeacherID() {
		return teacherID;
	}
	public void setTeacherID(String teacherID) {
		this.teacherID = teacherID;
	}

}
