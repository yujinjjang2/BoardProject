package edu.kh.jdbc.board.model.dao;

import static edu.kh.jdbc.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import edu.kh.jdbc.board.model.dto.Board;

public class BoardDAO {
	
	private Statement stmt;
	private static PreparedStatement pstmt;
	private ResultSet rs;
	
	private static Properties prop;
	
	public BoardDAO() {
		
		try {
			
			prop = new Properties();
			prop.loadFromXML( new FileInputStream("board-sql.xml") );
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	public List<Board> selectBoardList(Connection conn) throws Exception{
		List<Board> list = new ArrayList<Board>();
		
		try {
			
			String sql = prop.getProperty("selectBoardList");
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				int boardNo = rs.getInt("BOARD_NO");
				String boardTtitle = rs.getString("BOARD_TITLE");
				String boardContent = rs.getString("BOARD_CONTENT");
				String createDt = rs.getString("CREATE_DT");
				int readCount = rs.getInt("READ_COUNT");
				String deleteFl = rs.getString("DELETE_FL");
				int memberNo = rs.getInt("MEMBER_NO");
				
				list.add( new Board(boardNo, boardTtitle, boardContent, 
						createDt, readCount, deleteFl, memberNo) );
				
			}
			
		} finally {
			
			close(rs);
			close(pstmt);
			
		}
		
		return list;
	}
	
	

}
