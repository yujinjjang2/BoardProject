package edu.kh.jdbc.board.model.service;

import java.sql.Connection;
import java.util.List;

import edu.kh.jdbc.board.model.dao.BoardDAO;
import edu.kh.jdbc.board.model.dto.Board;
import static edu.kh.jdbc.common.JDBCTemplate.*;

public class BoardService {
	
	private BoardDAO dao = new BoardDAO();

	/** 게시글 목록 조회 서비스
	 * @return
	 */
	public List<Board> selectAllBoard() throws Exception{
		
		// 커넥션 생성
		Connection conn = getConnection();
		
		// DAO 메서드 호출
		List<Board> boardList = dao.selectAllBoard(conn);
		
		close(conn);
		
		return boardList;
	}

	/** 게시글 상세 조회 서비스
	 * @param input
	 * @param memberNo
	 * @return board
	 */
	public Board selectBoard(int input, int memberNo) throws Exception{
		
		Connection conn = getConnection();
		
		Board board = dao.selectBoard(conn, input); // 게시글 상세 조회
		
		if(board != null) { // 게시글이 조회된 경우
			// 조회수 증가
			// 단, 게시글 작성자와 로그인한 회원이 다를 경우에만 증가
			if(board.getMemberNo() != memberNo) {
			// 조회한 게시글 작성한 회원번호 != 로그인한 회원번호
				
				// 조회수 증가 DAO 메서드 호출
				int result = dao.updateReadCount(conn, input);
				
				// 트랜잭션 제어
				if(result > 0) {
					commit(conn);
					
					// 조회된 board 의 조회수가 0
					// DB의 조회수는 1
					// -> 미리 조회해둔 결과의 read_count를 1증가 해줘야 한다!
					board.setReadCount( board.getReadCount() + 1 );
					
				} else {
					rollback(conn);
				}
				
			}
		}
		
		close(conn);
		
		return board;
	}

	/** 게시글 수정 서비스
	 * @param boardTitle
	 * @param string
	 * @param boardNo
	 * @return result
	 */
	public int updateBoard(String boardTitle, String boardContent, int boardNo) throws Exception{
		
		Connection conn = getConnection();
		
		int result = dao.updateBoard(conn, boardTitle, boardContent, boardNo);
		
		if(result > 0) commit(conn);
		else			rollback(conn);
		
		close(conn);
		
		return result;
	}

	public int deleteBoard(int boardNo) throws Exception{
		
		Connection conn = getConnection();
		
		int result = dao.deleteBoard(conn, boardNo);
		
		if(result > 0) commit(conn);
		else			rollback(conn);
		
		close(conn);
		
		return result;
	}

	/** 게시글 삽입 서비스
	 * @param boardTitle
	 * @param string
	 * @param memberNo
	 * @return
	 */
	public int insertBoard(String boardTitle, String boardContent, int memberNo) throws Exception{
		
		Connection conn = getConnection();
		
		// 다음 게시글 번호 생성 -> 6
		int boardNo = dao.nextBoardNo(conn);
		
		// 제목, 내용, 회원번호, 다음 게시글 번호(6)
		int result = dao.insertBoard(conn, boardTitle, boardContent, memberNo, boardNo);
		
		
		if(result > 0) {
			commit(conn);
			result = boardNo;
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result; // 삽입성공시 다음 게시글 번호 (6)
					   // 실패시 0
	}

	/** 게시글 검색
	 * @param condition
	 * @param query
	 * @return boardList
	 * @throws Exception
	 */
	public List<Board> searchBoard(int condition, String query) throws Exception{
		Connection conn = getConnection();
		
		List<Board> boardList = dao.searchBoard(conn, condition, query);
		
		close(conn);
		
		return boardList;
	}


}

//package edu.kh.jdbc.board.model.service;
//
//import static edu.kh.jdbc.common.JDBCTemplate.*;
//
//import java.sql.Connection;
//import java.util.List;
//
//import edu.kh.jdbc.board.model.dao.BoardDAO;
//import edu.kh.jdbc.board.model.dto.Board;
//import edu.kh.jdbc.board.model.dto.Comment;
//
//public class BoardService {
//	
//	private BoardDAO dao = new BoardDAO();
//
//	public List<Board> selectBoardList() throws Exception{
//		
//		Connection conn = getConnection();
//		
//		List<Board> list = dao.selectBoardList(conn);
//		
//		close(conn);
//		
//		return list;
//	}
//
//	public List<Board> selectDetailBoardList() throws Exception{
//		
//		Connection conn = getConnection();
//		
//		List<Board> list = dao.selectDetailBoardList(conn);
//		
//		close(conn);
//		
//		return list;
//	}
//
//	public List<Comment> selectDetailBoardList1() throws Exception{
//		
//		Connection conn = getConnection();
//		
//		List<Comment> list = dao.selectBoardList1(conn);
//		
//		close(conn);
//		
//		return list;
//	}
//
//	public int insertBoard(int num, String title, String content) throws Exception{
//
//		Connection conn = getConnection();
//		
//		int result = dao.insertBoard(conn, num, title, content);
//		
//		if(result > 0) commit(conn);
//		else			rollback(conn);
//		
//		close(conn);
//		
//		return result;
//	}
//
//	public List<Board> searchBoard(String searchWord) throws Exception{
//		
//		Connection conn = getConnection();
//		
//		
//		
//		return null;
//	}
//
//}
