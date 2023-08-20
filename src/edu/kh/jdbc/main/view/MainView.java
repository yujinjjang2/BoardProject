package edu.kh.jdbc.main.view;

import java.util.Scanner;

import edu.kh.jdbc.board.view.BoardView;
import edu.kh.jdbc.board.view.CommentView;
import edu.kh.jdbc.common.Session;
import edu.kh.jdbc.main.model.service.MainService;
import edu.kh.jdbc.member.model.dto.Member;
import edu.kh.jdbc.member.view.MemberView;

public class MainView {
	
	private Scanner sc = new Scanner(System.in);
	
	private MainService service = new MainService();
	
	private MemberView mView = new MemberView();
	private BoardView bView = new BoardView();
	private CommentView cView = new CommentView();
	
	/** 메인 메뉴 출력
	 * 
	 */
	public void mainMenu() {
		
		int input = 0;
		
		do {
			
			try {
				
				if(Session.loginMember == null) { // 로그인 X
					
					System.out.println("\n ============== 회원제 게시판 프로그램 ============== \n");
					System.out.println("1. 로그인");
					System.out.println("2. 회원가입");
					System.out.println("0. 프로그램 종료");
					
					System.out.print("\n메뉴선택 : ");
					input = sc.nextInt();
					sc.nextLine(); // 입력 버퍼 개행문자 제거
					
					switch(input) {
					case 1: login(); break;
					case 2: signUp(); break;
					case 0: System.out.println("\n=====프로그램 종료=====\n"); break;
					default: System.out.println("\n*** 메뉴 번호만 입력해 주세요 ***\n");
					}
					
				} else { // 로그인 O
					
					System.out.println("\n ============== 로그인 메뉴 ============== \n");
					System.out.println("1. 회원 기능");
					System.out.println("2. 게시판 기능");
					System.out.println("3. 로그아웃");
					System.out.println("0. 프로그램 종료");
					
					System.out.print("\n메뉴선택 : ");
					input = sc.nextInt();
					sc.nextLine(); // 입력 버퍼 개행문자 제거
					
					switch(input) {
					case 1 : 
						
						System.out.println("\n ============== 회원 기능 ============== \n");
						System.out.println("1. 내 정보 조회");
						System.out.println("2. 회원 목록 조회(아이디, 이름, 성별)");
						System.out.println("3. 내 정보 수정(이름, 성별)");
						System.out.println("4. 비밀번호 변경(현재 비밀번호, 새 비밀번호, 새 비밀번호 확인)");
						System.out.println("5. 회원 탈퇴(보안코드, 비밀번호, UPDATE)");
						System.out.println("9. 메인 메뉴로 돌아가기");
						System.out.println("0. 프로그램 종료");
						
						System.out.println();
						
						System.out.print("메뉴 선택 : ");
						input = sc.nextInt();
						sc.nextLine();
						
						switch(input) {
						case 1: mView.selectMyInfo(); break;
						case 2: mView.selecetMemberInfo(); break;
						case 3: mView.updateMyInfo(); break;
						case 4: mView.updatePw(); break;
						case 5: mView.withdraw(); break;
						case 9: System.out.println("\n=====메인 메뉴로 돌아갑니다=====\n"); break;
						case 0: System.out.println("\n=====프로그램 종료=====\n"); break;
						default: System.out.println("\n*** 메뉴 번호만 입력해 주세요 ***\n");
						}
						
						break;
					case 2 : 
						
						System.out.println("\n ============== 게시판 기능 ============== \n");
						System.out.println("1. 게시글 목록 조회");
						System.out.println("2. 게시글 상세 조회(+ 댓글 기능)");
						System.out.println("3. 게시글 작성");
						System.out.println("4. 게시글 검색");
						System.out.println("9. 메인 메뉴로 돌아가기");
						System.out.println("0. 프로그램 종료");
						
						System.out.println();
						
						System.out.print("메뉴 선택 : ");
						input = sc.nextInt();
						sc.nextLine();
						
						switch(input) {
						case 1: bView.selectBoardList(); break;
						case 2: break;
						case 3: break;
						case 4: break;
						case 9: System.out.println("\n=====메인 메뉴로 돌아갑니다=====\n");break;
						case 0: System.out.println("\n=====프로그램 종료=====\n"); break;
						default: System.out.println("\n*** 메뉴 번호만 입력해 주세요 ***\n");
						}
						
						break;
					case 3 : 
						System.out.println("\n === 로그아웃 되었습니다 === \n");
						
						Session.loginMember = null;
						// 참조하고 있던 로그인 회원 객체를 없앰
						
						break;
						
					case 0 : System.out.println("\n=====프로그램 종료=====\n"); break;
					default : System.out.println("\n*** 메뉴 번호만 입력해 주세요 ***\n");
					}
					
				}
				
				
				
			} catch(Exception e) {
				e.printStackTrace();
			}
			
			
		} while(input != 0);
		
	}

	/** 로그인
	 * 
	 */
	private void login() {
		System.out.println("\n[로그인]\n");
		
		System.out.print("아이디 : ");
		String memberId = sc.next();
		
		System.out.print("비밀번호 : ");
		String memberPw = sc.next();
		
		try {
			
			// 로그인 서비스 호출 후 결과 반환받기
			// -> 반환 받은 결과는 Session.loginMember에 저장
			Session.loginMember = service.login(memberId, memberPw);
			
			if(Session.loginMember == null) { // 로그인 실패
				System.out.println("\n** 아이디 / 비밀번호가 일치하지 않습니다. **\n");
			}else {
				System.out.printf("\n === %s 님 환영합니다 === \n\n",
									Session.loginMember.getMemberName()
						);
			}
			
		} catch(Exception e) {
			System.out.println("\n******로그인 중 예외 발생******\n");
			e.printStackTrace();
		}
		
	}
	
	/** 회원가입 : 비밀번호 일치하려면 DB 한번 갔다와야하는데 ..??
	 * 
	 */
	private void signUp() {
		
		try {
			System.out.println("\n[회원가입]\n");
			
			System.out.print("아이디 : ");
			String memberId = sc.next();
			
			System.out.print("비밀번호 : ");
			String memberPw = sc.next();
			
			System.out.print("비밀번호 확인 : ");
			String memberPw2 = sc.next();
			
			System.out.print("이름 : ");
			String memberNm = sc.next();
			
			System.out.print("성별(M/F) : ");
			String memberGender = sc.next().toUpperCase();
			
			if(memberPw.equals(memberPw2)) {
				
				Member member = new Member(memberId, memberPw, memberNm, memberGender);
				
				int result = service.signUp(member);
				
				if(result > 0) {
					System.out.println("회원가입 성공");
					
				} else {
					System.out.println("회원가입 실패");
					
				}
				
			} else {
				System.out.println("입력한 비밀번호가 틀렸습니다.");
			}
		
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
