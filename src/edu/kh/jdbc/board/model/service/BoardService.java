package edu.kh.jdbc.board.model.service;

import static edu.kh.jdbc.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import edu.kh.jdbc.board.model.dao.BoardDAO;
import edu.kh.jdbc.board.model.dto.Board;

public class BoardService {
	
	private BoardDAO dao = new BoardDAO();

	public List<Board> selectBoardList() throws Exception{
		
		Connection conn = getConnection();
		
		List<Board> list = dao.selectBoardList(conn);
		
		close(conn);
		
		return list;
	}

}
