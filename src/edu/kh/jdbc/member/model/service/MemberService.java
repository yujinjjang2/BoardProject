package edu.kh.jdbc.member.model.service;

import java.sql.Connection;
import java.util.List;
import java.util.Random;

import static edu.kh.jdbc.common.JDBCTemplate.*;
import edu.kh.jdbc.member.model.dao.MemberDAO;
import edu.kh.jdbc.member.model.dto.Member;

public class MemberService {
	
	private MemberDAO dao = new MemberDAO();
	

	/** 회원 목록 조회 서비스
	 * @return 
	 */
	public List<Member> selectMemberList() throws Exception{
		
		Connection conn = getConnection();
		
		List<Member> memberList = dao.selectMemberList(conn);
		
		close(conn);
		
		return memberList;
	}


	/** 회원 정보 수정 서비스
	 * @param memberName
	 * @param memberGender
	 * @param memberNo
	 * @return
	 */
	public int updateMember(String memberName, String memberGender, int memberNo) throws Exception {
		Connection conn = getConnection();
		
		int result = dao.updateMember(conn, memberName, memberGender, memberNo);
		
		// 트랜 잭션 처리
		if(result > 0) commit(conn);
		else			rollback(conn);
		
		close(conn);
		
		return result;
	}


	/** 비밀번호 변경 서비스
	 * @param current
	 * @param newPw1
	 * @param memberNo
	 * @return result
	 */
	public int updatePassword(String current, String newPw1, int memberNo) throws Exception{
		
		Connection conn = getConnection();
		
		int result = dao.updatePassword(conn, current, newPw1, memberNo);
		
		if(result > 0) commit(conn);
		else			rollback(conn);
		
		close(conn);
		
		return result;
	}


	/** 숫자 6자리 보안코드 생성 서비스
	 * @return
	 */
	public String createSecurityCode() {
		// Service : 무조건 DAO로 가는 것이 아닌 필요한 함수를 모아두는 곳
		//           이라고 생각하기
		
//		StringBuffer code = new StringBuffer();
		String code = "";
		
		// StringBuffer : 문자열을 추가/변경 할 때 주로 사용하는 자료형
		// StringBuffer 자료형 append 메서드를 문자열을 추가할 수 있다.
		
		Random ran = new Random(); // 난수 생성 객체
		
		for(int i = 0; i < 6; i++) {
			int x = ran.nextInt(10); // 0 이상 10 미만 정수 0 ~ 9
//			code.append(x); // [ 574021 ]
			code = code + x;
		}
		
//		String str = "안녕";
//		str += "하세여";
//		str += " 안녕히가세요";
		
		// 메서드를 이용한 코드가 깔끔한 코드
//		code.append("안녕");
//		code.append("하세요");
		System.out.println(code);
//		return code.toString();
		return code;
	}


	/** 회원 탈퇴 서비스
	 * @param memberPw
	 * @param memberNo
	 * @return
	 */
	public int unRegisterMember(String memberPw, int memberNo) throws Exception{
		
		Connection conn = getConnection();
		
		int result = dao.unRegisterMember(conn, memberPw, memberNo);
		
		if(result > 0) commit(conn);
		else			rollback(conn);
		
		close(conn);
		
		return result;
	}

}



//package edu.kh.jdbc.member.model.service;
//
//import static edu.kh.jdbc.common.JDBCTemplate.*;
//
//import java.sql.Connection;
//import java.util.List;
//
//import edu.kh.jdbc.member.model.dao.MemberDAO;
//import edu.kh.jdbc.member.model.dto.Member;
//
//public class MemberService {
//
//	private MemberDAO dao = new MemberDAO();
//	/** 회원 기능
//	 * 1. 내 정보 조회 서비스
//	 * @return
//	 */
//	public Member selectMyInfo(String myId) throws Exception{
//		
//		Connection conn = getConnection();
//		
//		Member member = dao.selectMyInfo(conn, myId);
//		
//		close(conn);
//		
//		return member;
//	}
//
//	public List<Member> selectMemberInfo() throws Exception{
//		
//		Connection conn = getConnection();
//		
//		List<Member> list = dao.selectMemberInfo(conn);
//		
//		close(conn);
//		
//		return list;
//	}
//
//	public static int updateMyInfo(Member member) throws Exception{
//		
//		Connection conn = getConnection();
//		
//		int result = MemberDAO.updateMyInfo(conn, member);
//		
//		if(result > 0) commit(conn);
//		else			rollback(conn);
//			
//		close(conn);
//		
//		return result;
//	}
//
//	public int updatePw(String updateMyId, String newPw) throws Exception{
//		
//		Connection conn = getConnection();
//		
//		int result = dao.updatePw(conn, updateMyId, newPw);
//		
//		if(result > 0) commit(conn);
//		else			rollback(conn);
//		
//		close(conn);
//		
//		return result;
//	}
//
//	public int withdraw(String pw) throws Exception{
//		
//		Connection conn = getConnection();
//		
//		int result = dao.withdraw(conn, pw);
//		
//		if(result > 0) commit(conn);
//		else			rollback(conn);
//		
//		close(conn);
//		
//		return result;
//	}
//}