package edu.kh.jdbc.board.view;

import java.util.List;
import java.util.Scanner;

import edu.kh.jdbc.board.model.dto.Board;
import edu.kh.jdbc.board.model.dto.Comment;
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

	public void selectDetailBoardList() throws Exception{
		
		System.out.println("\n[게시글 상세 조회(+ 댓글 기능)]\n");
		
		List<Board> list = service.selectDetailBoardList();
		List<Comment> list1 = service.selectDetailBoardList1();
		
		for(Board board : list) {
			System.out.println(board);
		}
		
	}

	public void insertBoard() throws Exception{
		
		System.out.println("\n[게시글 작성]\n");
		
		// 회원 번호를 꼭 입력해야하는데 사용자가 자신의 회원 번호를 어떻게 알지..? loginMember에서 가져와야하는건가..?
		System.out.print("회원 번호 : ");
		int num = sc.nextInt();
		sc.nextLine();
		
		System.out.print("게시글 제목 : ");
		String title = sc.nextLine();
		
		System.out.print("게시글 내용 : ");
		String content = sc.nextLine();
		
		int result = service.insertBoard(num, title, content);
		
		if(result > 0) {
			System.out.println("게시글 작성 성공");
			
		} else {
			System.out.println("게시글 작성 실패");
			
		}
		
	}

	public void searchBoard() throws Exception{
		
		System.out.println("\n[게시글 검색]\n");
		
		System.out.print("검색할 단어 입력 : ");
		String searchWord = sc.next();
		
		List<Board> list = service.searchBoard(searchWord);
		
		for(Board board : list) {
			System.out.println(board);
		}
		
	}
	
	
		
}
