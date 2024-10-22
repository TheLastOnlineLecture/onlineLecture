package net.haebup.dto.member.payment;

public class PayMentDTO {
	private String paymentIdx;
    private String userId;
    private String lectureCode;
    private String paymentDate;
    private char paymentStatus;
    private String lectureStartDate;

    public String getPaymentIdx() {
        return paymentIdx;
    }

    public void setPaymentIdx(String paymentIdx) {
        this.paymentIdx = paymentIdx;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLectureCode() {
        return lectureCode;
    }

    public void setLectureCode(String lectureCode) {
        this.lectureCode = lectureCode;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public char getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(char paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getLectureStartDate() {
        return lectureStartDate;
    }

    public void setLectureStartDate(String lectureStartDate) {
        this.lectureStartDate = lectureStartDate;
    }

}
