package net.haebup.dto.member.payment;

public class CartItemDTO {
    private String lectureCode;
    private String lectureName;
    private int lecturePrice;
    private int paymentIdx;

    // Getters and Setters
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

    public int getPaymentIdx() {
        return paymentIdx;
    }

    public void setPaymentIdx(int paymentIdx) {
        this.paymentIdx = paymentIdx;
    }
}
