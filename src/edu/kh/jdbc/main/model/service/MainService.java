package edu.kh.jdbc.main.model.service;

import java.sql.Connection;

import static edu.kh.jdbc.common.JDBCTemplate.*;
import edu.kh.jdbc.main.model.dao.MainDAO;
import edu.kh.jdbc.member.model.dto.Member;

public class MainService {

	private MainDAO dao = new MainDAO();
	
	/** 로그인 서비스
	 * @param memberId
	 * @param memberPw
	 * @return
	 */
	public Member login(String memberId, String memberPw) throws Exception{
		
		// 1. Connection 생성
		Connection conn = getConnection();
		
		// 2. DAO 호출
		Member member = dao.login(conn, memberId, memberPw);
		
		// 3. Connection 반환
		close(conn);
		
		// 4. 결과 반환
		return member;
	}
	
	public int signUp(Member member) throws Exception {
		
		Connection conn = getConnection();
		
		int result = dao.signUp(conn, member);
		
		close(conn);
		
		return result;
	}

	/** 회원 기능
	 * 1. 내 정보 조회 서비스
	 * @return
	 */
	public Member selectMyInfo(String myId) throws Exception{
		
		Connection conn = getConnection();
		
		Member member = dao.selectMyInfo(conn, myId);
		
		close(conn);
		
		return member;
	}

}
