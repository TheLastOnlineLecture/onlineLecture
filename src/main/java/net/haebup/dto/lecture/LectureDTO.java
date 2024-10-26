package net.haebup.dto.lecture;

public class LectureDTO {
	private String lectureCode;			// 강의 코드
	private String lectureName;			// 강의 이름
	private int lecturePrice;			// 강의 가격
	private String lectureRegdate;		// 강의 등록일
	private String lectureLimitDate;		// 강의 제한일
	private String teacherId;			// 강의 선생님 아이디
	private String teacherName;			// 강의 선생님 이름
	private String lectureStartDate;	// 강의 시작일

	public String getLectureStartDate() {
		return lectureStartDate;
	}
	public void setLectureStartDate(String lectureStartDate) {
		this.lectureStartDate = lectureStartDate;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}	
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
	public int getLecturePrice() {
		return lecturePrice;
	}
	public void setLecturePrice(int lecturePrice) {
		this.lecturePrice = lecturePrice;
	}
	public String getLectureRegdate() {
		return lectureRegdate;
	}
	public void setLectureRegdate(String lectureRegdate) {
		this.lectureRegdate = lectureRegdate;
	}
	public String getLectureLimitDate() {
		return lectureLimitDate;
	}
	public void setLectureLimitDate(String lectureLimitDate) {
		this.lectureLimitDate = lectureLimitDate;
	}
	public String getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}

}
