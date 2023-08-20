package edu.kh.jdbc.board.view;

import java.util.List;
import java.util.Scanner;

import edu.kh.jdbc.board.model.dto.Board;
import edu.kh.jdbc.board.model.service.BoardService;
import edu.kh.jdbc.member.model.service.MemberService;

public class BoardView {

	private Scanner sc = new Scanner(System.in);
	private BoardService service = new BoardService();

	public void selectBoardList() throws Exception{
		
		System.out.println("\n[게시글 목록 조회]\n");
		
		List<Board> list = service.selectBoardList();
		
		for(Board board : list) {
			System.out.println(board);
		}
		
	}
		
}
