package pcCafe.admin.usePC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import pcCafe.admin.AdminMain;
import pcCafe.main.JdbcTemplate;

public class UsePc {
	
	public boolean findUserInfo() {
		boolean run = true;
		while(run) {
			
			try {
				System.out.print("찾으실 회원 이름 : ");
				String findUserName = AdminMain.SC.nextLine().trim();
				Connection conn = JdbcTemplate.getConnection();
				String sql = "SELECT MEM_NAME , P.SEAT_NUM , USAGE_YN FROM MEMBER M JOIN PC_USE P ON M.MEM_NUM = P.USE_NUM JOIN SEAT S ON P.SEAT_NUM = S.SEAT_NUM WHERE MEM_NAME = ? AND USAGE_YN = 'Y'";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, findUserName);
				ResultSet rs = pstmt.executeQuery();
				if(rs.next() == false) {
					System.out.println("이용중 아닌디?");
				}else{
					System.out.print("이름     :  ");
					System.out.println(rs.getString("MEM_NAME"));
					System.out.print("좌석번호 :  ");
					System.out.println(rs.getString("seat_NUM"));
					System.out.println();
					
				}
				System.out.println("1. 계속찾기,  2. 뒤로가기");
				System.out.print("  : " );
				String c = AdminMain.SC.nextLine().trim();
				if("2".equals(c)) {
					run = false;
					break;
				}
				System.out.println();
			} catch (Exception e) {
				AdminMain.Exception();
			}
		}
		return true;
	}

}
