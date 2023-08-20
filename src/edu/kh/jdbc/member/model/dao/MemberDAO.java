package edu.kh.jdbc.member.model.dao;

import static edu.kh.jdbc.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import edu.kh.jdbc.member.model.dto.Member;
import static edu.kh.jdbc.common.Session.*;

public class MemberDAO {
	
	private Statement stmt;
	private static PreparedStatement pstmt;
	private ResultSet rs;
	
	private static Properties prop;
	
	public MemberDAO() {
		
		try {
			
			prop = new Properties();
			prop.loadFromXML( new FileInputStream("member-sql.xml") );
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	/**회원 기능
	 * 1. 내 정보 조회 DAO
	 * @param conn
	 * @return
	 */
	public Member selectMyInfo(Connection conn, String myId) throws Exception{
		
		Member member = null;
		
		try {
			
			String sql = prop.getProperty("selectMyInfo");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, myId);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				int memberNo = rs.getInt("MEMBER_NO");
				String memberId = rs.getString("MEMBER_ID");
				String memberPw = rs.getString("MEMBER_PW");
				String memberNM = rs.getString("MEMBER_NM");
				String memberGender = rs.getString("MEMBER_GENDER");
				String enrollDt = rs.getString("ENROLL_DT");
				String unregisterFl = rs.getString("UNREGISTER_FL");
				
				member = new Member();
				
				member.setMemberNo(memberNo);
				member.setMemberId(memberId);
				member.setMemberPw(memberPw);
				member.setMemberName(memberNM);
				member.setMemberGender(memberGender);
				member.setEnrollDate(enrollDt);
				member.setUpregisterFlag(unregisterFl);
				
			}
			
		} finally {
			
			close(rs);
			close(pstmt);
			
		}
		
		return member;
	}

	public List<Member> selectMemberInfo(Connection conn) throws Exception{
		
		List<Member> list = new ArrayList<Member>();
		
		try {
			
			String sql = prop.getProperty("selectMemberInfo");
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				String memberId = rs.getString("MEMBER_ID");
				String memberNm = rs.getString("MEMBER_NM");
				String memberGender = rs.getString("MEMBER_GENDER");
				
				list.add( new Member(memberId, memberNm, memberGender) );
				
			}
			
			
		} finally {
			
			close(rs);
			close(pstmt);
			
		}
		
		return list;
	}
	public static int updateMyInfo(Connection conn, Member member) throws Exception{
		
		int result = 0;
		
		try {
			
			String sql = prop.getProperty("updateMyInfo");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, member.getMemberName());
			pstmt.setString(2, member.getMemberGender());
			pstmt.setString(3, member.getMemberId());
			
			result = pstmt.executeUpdate();
			
		} finally {
			
			close(pstmt);
			
		}
		
		return result;
	}
	public int updatePw(Connection conn, String updateMyId, String newPw) throws Exception{
		
		int result = 0;
		
		try {
			
			String sql = prop.getProperty("updatePw");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, newPw);
			pstmt.setString(2, updateMyId);
			
			result = pstmt.executeUpdate();
			
		} finally {
			
			close(pstmt);
			
		}
		
		return result;
	}
	public int withdraw(Connection conn, String pw) throws Exception{
		
		int result = 0;
		
		try {
			
			String sql = prop.getProperty("withdraw");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, pw);
			
			result = pstmt.executeUpdate();
			
		} finally {
			
			close(pstmt);
			
		}
		
		return result;
	}
}
