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
import edu.kh.jdbc.board.model.dto.Comment;

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

	public List<Board> selectDetailBoardList(Connection conn) throws Exception{
		
		List<Board> list = new ArrayList<Board>();
		
		try {
			
			String sql = prop.getProperty("selectDetailBoardList");
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int BoardNo = rs.getInt("BOARD_NO");
				String boardTitle = rs.getString("BOARD_TITLE");
				String boardContent = rs.getString("BOARD_CONTENT");
				String createDt = rs.getString("CREATE_DT");
				int readCount = rs.getInt("READ_COUNT");
				String deleteFl = rs.getString("DELETE_FL");
				int memberNo = rs.getInt("MEMBER_NO");
				
				list.add( new Board(BoardNo, boardTitle, boardContent, createDt, readCount, deleteFl, memberNo) );
				
			}
			
			
		} finally {
			
			close(rs);
			close(pstmt);
			
		}
		
		return list;
	}

	// 얘 왜 안나오지 ..
	public List<Comment> selectBoardList1(Connection conn) throws Exception{
		
		List<Comment> list1 = new ArrayList<Comment>();
		
		try {
			
			String sql = prop.getProperty("selectDetailBoardList");
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				int commentNo = rs.getInt("COMMENT_NO");
				String commentContent = rs.getString("COMMENT_CONTENT");
				String createDt1 = rs.getString("CREATE_DT");
				String deleteFl1 = rs.getString("DELETE_FL");
				int memberNo1 = rs.getInt("MEMBER_NO");
				int boardNo1 = rs.getInt("BOARD_NO");
				
				list1.add( new Comment(commentNo, commentContent, createDt1, deleteFl1, memberNo1, boardNo1) );
				
			}
			
			
		} finally {
			
			close(rs);
			close(pstmt);
			
		}
		
		return list1;
	}

	public int insertBoard(Connection conn, int num, String title, String content) throws Exception{
		
		int result = 0;
		
		try {
			
			String sql = prop.getProperty("insertBoard");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setInt(3, num);
			
			result = pstmt.executeUpdate();
			
		} finally {
			
			close(pstmt);
			
		}
		
		return result;
	}
	
	

}
