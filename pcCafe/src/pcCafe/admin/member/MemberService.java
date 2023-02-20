package pcCafe.admin.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import pcCafe.admin.AdminMain;
import pcCafe.main.JdbcTemplate;
import pcCafe.main.Main;
import pcCafe.main.MemberData;


public class MemberService {
	private final MemberEdit ME = new MemberEdit();
	
	public void showMemberInfo() {
		try {
			
			Connection conn = JdbcTemplate.getConnection();
			String sql = "SELECT MEM_ID, MEM_NAME, MEM_TIME, QUIT_YN FROM MEMBER ";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			System.out.println("=========================================================");
			System.out.println("=====================가입한 회원 목록========================");
			while(rs.next()) {
				String id= rs.getString("MEM_ID");
				String name = rs.getString("MEM_NAME");
				int time = rs.getInt("MEM_TIME");
				String quit = rs.getString("QUIT_YN");
				System.out.print("|| 아이디 : "+ id + "  이름 : "+ name + "  잔여 시간:  "+ time + "  탈퇴 여부 : " + quit+ " ||" );
				System.out.println();
			}
			System.out.println("=========================================================");
			System.out.println("");
			conn.close();
		}catch(Exception e) {
			AdminMain.Exception();
		}
	}
	
	public void resetPwd() {
		MemberData md = ME.resetPwd();
		try {
			
			Connection conn = JdbcTemplate.getConnection();
			String sql = "UPDATE MEMBER SET MEM_PWD = '0000'  WHERE MEM_NAME = ? AND MEM_BIRTH = ? ";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, md.getUserName() );
			pstmt.setString(2, md.getUserBirth());
			int result = pstmt.executeUpdate();
			if(result == 1) {
				System.out.println("==================================");
				System.out.println("||비밀번호가 0000 으로 초기화 되었습니다||");
				System.out.println("==================================");
			}else { 
				System.out.println("==================================");
				System.out.println("||       이름 , 생년월일 오류       ||");
				System.out.println("==================================");
			}
			conn.close();
		}catch(Exception e) {
			AdminMain.Exception();
		}
		
	}
	
	
	public void out() {
		MemberData md = ME.out();
		try {
			Connection conn = JdbcTemplate.getConnection();
			String sql = "UPDATE MEMBER SET QUIT_YN = 'Y'  WHERE MEM_ID = ? AND MEM_PWD = ? AND MEM_BIRTH = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, md.getUserId() );
			pstmt.setString(2, md.getUserPwd());
			pstmt.setString(3, md.getUserBirth());
			int result = pstmt.executeUpdate();
			if(result == 1) {
				System.out.println("==================================");
				System.out.println("||    회원 탈퇴 , 휴면 상태로 전환    ||");
				System.out.println("==================================");
			}else {
				System.out.println("==================================");
				System.out.println("||          ID, PWD   오류       ||");
				System.out.println("==================================");
			}
			
			conn.close();
		}catch(Exception e) {
			AdminMain.Exception();
		}
	}

}