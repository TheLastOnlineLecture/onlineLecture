package net.haebup.dto.member;

public class MemberTypeDTO {
	
	private char memberType;
    private String memberTypeName;
    private String memberTypeDescription;

    public char getMemberType() {
        return memberType;
    }

    public void setMemberType(char memberType) {
        this.memberType = memberType;
    }

    public String getMemberTypeName() {
        return memberTypeName;
    }

    public void setMemberTypeName(String memberTypeName) {
        this.memberTypeName = memberTypeName;
    }

    public String getMemberTypeDescription() {
        return memberTypeDescription;
    }

    public void setMemberTypeDescription(String memberTypeDescription) {
        this.memberTypeDescription = memberTypeDescription;
    }
}
