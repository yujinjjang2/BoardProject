package edu.kh.jdbc.member.view;

import java.util.List;
import java.util.Scanner;

import static edu.kh.jdbc.common.Session.*;
import edu.kh.jdbc.member.model.dto.Member;
import edu.kh.jdbc.member.model.service.MemberService;

public class MemberView {
	
	private Scanner sc = new Scanner(System.in);
	private MemberService service = new MemberService();
	
	/** 회원 기능
	 * 1. 내 정보 조회
	 * 
	 */
	public void selectMyInfo() {
		System.out.println("\n[내 정보 조회]\n");
		
//		System.out.print("아이디 : ");
//		String myId = sc.next();
		
		System.out.print("비밀번호 : ");
		String myPw = sc.next();
		
		System.out.print("비밀번호 확인 : ");
		String myPw2 = sc.next();
		
		try {
			
			if(myPw.equals(myPw2)) {
				
				Member member = service.selectMyInfo(loginMember.getMemberId());
				
				System.out.println(member);
				System.out.println();
				System.out.println("내 정보 조회 완료");
				
			} else {
				
				System.out.println("비밀번호 불일치");
				
			}
			
			
		} catch(Exception e) {
			System.out.println("\n******내 정보 조회 중 예외 발생******\n");
			e.printStackTrace();
		}
		
	}


	/** 회원 기능
	 * 2. 회원 목록 조회(아이디, 이름, 성별)
	 * 
	 */
	public void selecetMemberInfo() throws Exception{
		
		System.out.println("\n[회원 목록 조회(아이디, 이름, 성별)]\n");
		
		List<Member> list = service.selectMemberInfo();
		
		for(Member member : list) {
			System.out.println(member);
		}
	}


	public void updateMyInfo() throws Exception{
		
		System.out.println("\n[내 정보 수정(이름, 성별)]\n");
		
//		System.out.print("수정할 내 아이디 : ");
//		String updateMyId = sc.next();
		
		System.out.print("수정할 내 이름 : ");
		String updateMyNm = sc.next();
		
		System.out.print("수정할 내 성별 : ");
		String updateMyGender = sc.next();
		
		Member member = new Member(loginMember.getMemberId(), updateMyNm, updateMyGender);
		
		int result = MemberService.updateMyInfo(member);
		
		if(result > 0) {
			System.out.println("내 정보 수정 성공");
		} else {
			System.out.println("내 정보 수정 실패");
		}
		
	}


	public void updatePw() throws Exception{
		System.out.println("\n비밀번호 변경(현재 비밀번호, 새 비밀번호, 새 비밀번호 확인)\n");
		
//		System.out.print("변경할 내 아이디 : ");
//		String updateMyId = sc.next();
		
		System.out.print("현재 비밀번호 : ");
		String nowPw = sc.next();
		
		System.out.print("새 비밀번호 : ");
		String newPw = sc.next();
		
		System.out.print("새 비밀번호 확인 : ");
		String newPw2 = sc.next();
		
		if(newPw.equals(newPw2)) {
			
			int result = service.updatePw(loginMember.getMemberId(), newPw);
			
			if(result > 0) {
				System.out.println("비밀번호 변경 성공");
			} else {
				System.out.println("비밀번호 변경 실패");
			}
			
		} else {
			System.out.println("새 비밀번호 불일치");
		}
		 
	}


	public void withdraw() throws Exception{
		
		System.out.println("\n회원 탈퇴(보안코드, 비밀번호, UPDATE)\n");
		
		System.out.println("보안코드 : " + securityCode);
		System.out.print("화면에 보이는 보안코드 입력 : ");
		int inputSecurityCode = sc.nextInt();
		sc.nextLine();
		
		if(inputSecurityCode == securityCode) {
			
			System.out.print("정말 탈퇴하시겠습니까?(Y/N) : ");
			String answer = sc.next().toUpperCase();
			
			if(answer.equals("Y")) {
				
				System.out.print("비밀번호 입력 : ");
				String pw = sc.next();
				
				int result = service.withdraw(pw);
				
				if(result > 0) {
					System.out.println("회원 탈퇴 성공");
				} else {
					System.out.println("회원 탈퇴 실패");
				}
				
			} else {
				System.out.println("회원 탈퇴 취소");
			}
			
		} else {
			System.out.println("보안코드 불일치");
		}
		
		
	}

}
