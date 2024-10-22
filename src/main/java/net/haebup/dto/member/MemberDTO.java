package net.haebup.dto.member;

public class MemberDTO {
	
	private String userId;
    private String password;
    private String salt;
    private String userName;
    private String userNickname;
    private String userEmail;
    private String userPhone;
    private String userBirth;
    private String userRegdate;
    private char userType;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserBirth() {
        return userBirth;
    }

    public void setUserBirth(String userBirth) {
        this.userBirth = userBirth;
    }

    public String getUserRegdate() {
        return userRegdate;
    }

    public void setUserRegdate(String userRegdate) {
        this.userRegdate = userRegdate;
    }

    public char getUserType() {
        return userType;
    }

    public void setUserType(char userType) {
        this.userType = userType;
    }
	
}
