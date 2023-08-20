package edu.kh.jdbc.board.model.dto;

public class Board {
	
	private int boardNo;
	private String boardTitle;
	private String boardContent;
	private String createDt;
	private int readCount;
	private String deleteFl;
	private int memberNo;
	
	public Board() {}

	public Board(int boardNo, String boardTitle, String boardContent, String createDt, int readCount, String deleteFl,
			int memberNo) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.createDt = createDt;
		this.readCount = readCount;
		this.deleteFl = deleteFl;
		this.memberNo = memberNo;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public String getBoardContent() {
		return boardContent;
	}

	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}

	public String getCreateDt() {
		return createDt;
	}

	public void setCreateDt(String createDt) {
		this.createDt = createDt;
	}

	public int getReadCount() {
		return readCount;
	}

	public void setReadCount(int readCount) {
		this.readCount = readCount;
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

	@Override
	public String toString() {
		return "Board [boardNo=" + boardNo + ", boardTitle=" + boardTitle + ", boardContent=" + boardContent
				+ ", createDt=" + createDt + ", readCount=" + readCount + ", deleteFl=" + deleteFl + ", memberNo="
				+ memberNo + "]";
	}
}
