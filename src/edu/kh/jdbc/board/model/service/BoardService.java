package edu.kh.jdbc.board.model.service;

import static edu.kh.jdbc.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import edu.kh.jdbc.board.model.dao.BoardDAO;
import edu.kh.jdbc.board.model.dto.Board;
import edu.kh.jdbc.board.model.dto.Comment;

public class BoardService {
	
	private BoardDAO dao = new BoardDAO();

	public List<Board> selectBoardList() throws Exception{
		
		Connection conn = getConnection();
		
		List<Board> list = dao.selectBoardList(conn);
		
		close(conn);
		
		return list;
	}

	public List<Board> selectDetailBoardList() throws Exception{
		
		Connection conn = getConnection();
		
		List<Board> list = dao.selectDetailBoardList(conn);
		
		close(conn);
		
		return list;
	}

	public List<Comment> selectDetailBoardList1() throws Exception{
		
		Connection conn = getConnection();
		
		List<Comment> list = dao.selectBoardList1(conn);
		
		close(conn);
		
		return list;
	}

	public int insertBoard(int num, String title, String content) throws Exception{

		Connection conn = getConnection();
		
		int result = dao.insertBoard(conn, num, title, content);
		
		if(result > 0) commit(conn);
		else			rollback(conn);
		
		close(conn);
		
		return result;
	}

	public List<Board> searchBoard(String searchWord) throws Exception{
		
		Connection conn = getConnection();
		
		
		
		return null;
	}

}
