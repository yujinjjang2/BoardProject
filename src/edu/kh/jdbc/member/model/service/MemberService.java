package edu.kh.jdbc.member.model.service;

import static edu.kh.jdbc.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import edu.kh.jdbc.member.model.dao.MemberDAO;
import edu.kh.jdbc.member.model.dto.Member;

public class MemberService {

	private MemberDAO dao = new MemberDAO();
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

	public List<Member> selectMemberInfo() throws Exception{
		
		Connection conn = getConnection();
		
		List<Member> list = dao.selectMemberInfo(conn);
		
		close(conn);
		
		return list;
	}

	public static int updateMyInfo(Member member) throws Exception{
		
		Connection conn = getConnection();
		
		int result = MemberDAO.updateMyInfo(conn, member);
		
		if(result > 0) commit(conn);
		else			rollback(conn);
			
		close(conn);
		
		return result;
	}

	public int updatePw(String updateMyId, String newPw) throws Exception{
		
		Connection conn = getConnection();
		
		int result = dao.updatePw(conn, updateMyId, newPw);
		
		if(result > 0) commit(conn);
		else			rollback(conn);
		
		close(conn);
		
		return result;
	}

	public int withdraw(String pw) throws Exception{
		
		Connection conn = getConnection();
		
		int result = dao.withdraw(conn, pw);
		
		if(result > 0) commit(conn);
		else			rollback(conn);
		
		close(conn);
		
		return result;
	}
}
