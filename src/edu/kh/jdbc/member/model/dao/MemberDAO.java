package edu.kh.jdbc.member.model.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import static edu.kh.jdbc.common.JDBCTemplate.*;
import edu.kh.jdbc.member.model.dto.Member;

public class MemberDAO {
	
	
	// JDBC 객체 참조 변수
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	// xml에 작성된 SQL을 읽어와 저장할 객체 참조변수
	private Properties prop;
	
	// 기본생성자로 객체 생성 시 XML 읽어오기
	public MemberDAO() {
		try {
			prop = new Properties();
			
			prop.loadFromXML(new FileInputStream("member-sql.xml"));
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	

	/** 회원 목록 조회 SQL 수행
	 * @param conn
	 * @return
	 */
	public List<Member> selectMemberList(Connection conn) throws Exception{
		
		// 결과 저장용 변수 선언 
		List<Member> memberList = new ArrayList<Member>();
		
		try {
			
			String sql = prop.getProperty("selectMemberList");
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				String memberId = rs.getString("MEMBER_ID");
				String memberName = rs.getString("MEMBER_NM");
				String memberGender = rs.getString("성별");
				
//				Member member = new Member();
//				member.setMemberId(memberId);
//				member.setMemberName(memberName);
//				member.setMemberGender(memberGender);
//				
//				memberList.add(member);
				
				memberList.add( new Member(memberId, memberName, memberGender) );
			}
			
			
		} finally {
			close(rs);
			close(stmt);
		}
		
		
		return memberList;
	}



	/** 회원 정보 수정 SQL 수행
	 * @param conn
	 * @param memberName
	 * @param memberGender
	 * @param memberNo
	 * @return
	 */
	public int updateMember(Connection conn, String memberName, 
				String memberGender, int memberNo) throws Exception{
		
		int result = 0;
		
		try {
			String sql = prop.getProperty("updateMember");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memberName);
			pstmt.setString(2, memberGender);
			pstmt.setInt(3, memberNo);
			
			result = pstmt.executeUpdate();
			
			
		} finally {
			close(pstmt);
		}
		
		
		return result;
	}



	/** 비밀번호 변경 SQL 수행
	 * @param conn
	 * @param current
	 * @param newPw1
	 * @param memberNo
	 * @return result
	 */
	public int updatePassword(Connection conn, String current,
								String newPw1, int memberNo) throws Exception{
		
		int result = 0;
		
		try {
			
			String sql = prop.getProperty("updatePassword");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, newPw1);
			pstmt.setString(2, current);
			pstmt.setInt(3, memberNo);
			
			result = pstmt.executeUpdate();
			
		} finally {
			
			close(pstmt);
			
		}
		
		return result;
	}



	/** 회원 탈퇴 SQL 수행
	 * @param conn
	 * @param memberPw
	 * @param memberNo
	 * @return
	 */
	public int unRegisterMember(Connection conn, String memberPw,
								int memberNo) throws Exception{
		
		int result = 0;
		
		try {
			
			String sql = prop.getProperty("unRegisterMember");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, memberNo);
			pstmt.setString(2, memberPw);
			
			result = pstmt.executeUpdate();
			
		} finally {
			
			close(pstmt);
			
		}
		
		return result;
	}
	
}


//package edu.kh.jdbc.member.model.dao;
//
//import static edu.kh.jdbc.common.JDBCTemplate.*;
//
//import java.io.FileInputStream;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Properties;
//
//import edu.kh.jdbc.member.model.dto.Member;
//import static edu.kh.jdbc.common.Session.*;
//
//public class MemberDAO {
//	
//	private Statement stmt;
//	private static PreparedStatement pstmt;
//	private ResultSet rs;
//	
//	private static Properties prop;
//	
//	public MemberDAO() {
//		
//		try {
//			
//			prop = new Properties();
//			prop.loadFromXML( new FileInputStream("member-sql.xml") );
//			
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
//		
//	}
//	/**회원 기능
//	 * 1. 내 정보 조회 DAO
//	 * @param conn
//	 * @return
//	 */
//	public Member selectMyInfo(Connection conn, String myId) throws Exception{
//		
//		Member member = null;
//		
//		try {
//			
//			String sql = prop.getProperty("selectMyInfo");
//			
//			pstmt = conn.prepareStatement(sql);
//			
//			pstmt.setString(1, myId);
//			
//			rs = pstmt.executeQuery();
//			
//			if(rs.next()) {
//				
//				int memberNo = rs.getInt("MEMBER_NO");
//				String memberId = rs.getString("MEMBER_ID");
//				String memberPw = rs.getString("MEMBER_PW");
//				String memberNM = rs.getString("MEMBER_NM");
//				String memberGender = rs.getString("MEMBER_GENDER");
//				String enrollDt = rs.getString("ENROLL_DT");
//				String unregisterFl = rs.getString("UNREGISTER_FL");
//				
//				member = new Member();
//				
//				member.setMemberNo(memberNo);
//				member.setMemberId(memberId);
//				member.setMemberPw(memberPw);
//				member.setMemberName(memberNM);
//				member.setMemberGender(memberGender);
//				member.setEnrollDate(enrollDt);
//				member.setUpregisterFlag(unregisterFl);
//				
//			}
//			
//		} finally {
//			
//			close(rs);
//			close(pstmt);
//			
//		}
//		
//		return member;
//	}
//
//	public List<Member> selectMemberInfo(Connection conn) throws Exception{
//		
//		List<Member> list = new ArrayList<Member>();
//		
//		try {
//			
//			String sql = prop.getProperty("selectMemberInfo");
//			
//			pstmt = conn.prepareStatement(sql);
//			
//			rs = pstmt.executeQuery();
//			
//			while(rs.next()) {
//				
//				String memberId = rs.getString("MEMBER_ID");
//				String memberNm = rs.getString("MEMBER_NM");
//				String memberGender = rs.getString("MEMBER_GENDER");
//				
//				list.add( new Member(memberId, memberNm, memberGender) );
//				
//			}
//			
//			
//		} finally {
//			
//			close(rs);
//			close(pstmt);
//			
//		}
//		
//		return list;
//	}
//	public static int updateMyInfo(Connection conn, Member member) throws Exception{
//		
//		int result = 0;
//		
//		try {
//			
//			String sql = prop.getProperty("updateMyInfo");
//			
//			pstmt = conn.prepareStatement(sql);
//			
//			pstmt.setString(1, member.getMemberName());
//			pstmt.setString(2, member.getMemberGender());
//			pstmt.setString(3, member.getMemberId());
//			
//			result = pstmt.executeUpdate();
//			
//		} finally {
//			
//			close(pstmt);
//			
//		}
//		
//		return result;
//	}
//	public int updatePw(Connection conn, String updateMyId, String newPw) throws Exception{
//		
//		int result = 0;
//		
//		try {
//			
//			String sql = prop.getProperty("updatePw");
//			
//			pstmt = conn.prepareStatement(sql);
//			
//			pstmt.setString(1, newPw);
//			pstmt.setString(2, updateMyId);
//			
//			result = pstmt.executeUpdate();
//			
//		} finally {
//			
//			close(pstmt);
//			
//		}
//		
//		return result;
//	}
//	public int withdraw(Connection conn, String pw) throws Exception{
//		
//		int result = 0;
//		
//		try {
//			
//			String sql = prop.getProperty("withdraw");
//			
//			pstmt = conn.prepareStatement(sql);
//			
//			pstmt.setString(1, pw);
//			
//			result = pstmt.executeUpdate();
//			
//		} finally {
//			
//			close(pstmt);
//			
//		}
//		
//		return result;
//	}
//}