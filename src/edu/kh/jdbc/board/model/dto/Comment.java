package edu.kh.jdbc.board.model.dto;

public class Comment {
	
	private int commentNo;
	private String commentContent;
	private String createDt;
	private String deleteFl;
	private int memberNo;
	private int boardNo;
	
	public Comment() {}

	public Comment(int commentNo, String commentContent, String createDt, String deleteFl, int memberNo, int boardNo) {
		super();
		this.commentNo = commentNo;
		this.commentContent = commentContent;
		this.createDt = createDt;
		this.deleteFl = deleteFl;
		this.memberNo = memberNo;
		this.boardNo = boardNo;
	}

	public int getCommentNo() {
		return commentNo;
	}

	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public String getCreateDt() {
		return createDt;
	}

	public void setCreateDt(String createDt) {
		this.createDt = createDt;
	}

	public String getDeleteFl() {
		return deleteFl;
	}

	public void setDeleteFl(String deleteFl) {
		this.deleteFl = deleteFl;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	@Override
	public String toString() {
		return "Comment [commentNo=" + commentNo + ", commentContent=" + commentContent + ", createDt=" + createDt
				+ ", deleteFl=" + deleteFl + ", memberNo=" + memberNo + ", boardNo=" + boardNo + "]";
	}

}
