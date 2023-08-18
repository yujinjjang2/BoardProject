package edu.kh.jdbc.member.model.dto;

public class Member {
	
	private int memberNo; // 회원번호
	private String memberId; // 회원 아이디
	private String memberPw; // 비밀번호
	private String memberName; // 이름
	private String memberGender; // 성별
	private String enrollDate; // 가입일
	private String upregisterFlag; // 탈퇴여부
	
	public Member() {}
	
	public Member(String memberId, String memberPw, String memberName, String memberGender) {
		this.memberId = memberId;
		this.memberPw = memberPw;
		this.memberName = memberName;
		this.memberGender = memberGender;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMemberPw() {
		return memberPw;
	}

	public void setMemberPw(String memberPw) {
		this.memberPw = memberPw;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberGender() {
		return memberGender;
	}

	public void setMemberGender(String memberGender) {
		this.memberGender = memberGender;
	}

	public String getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(String enrollDate) {
		this.enrollDate = enrollDate;
	}

	public String getUpregisterFlag() {
		return upregisterFlag;
	}

	public void setUpregisterFlag(String upregisterFlag) {
		this.upregisterFlag = upregisterFlag;
	}

	@Override
	public String toString() {
		return "Member [memberNo=" + memberNo + ", memberId=" + memberId + ", memberPw=" + memberPw + ", memberName="
				+ memberName + ", memberGender=" + memberGender + ", enrollDate=" + enrollDate + ", upregisterFlag="
				+ upregisterFlag + "]";
	}
	
	
}
