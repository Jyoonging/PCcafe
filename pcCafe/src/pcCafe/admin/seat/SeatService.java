package pcCafe.admin.seat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import pcCafe.admin.AdminMain;
import pcCafe.main.JdbcTemplate;



public class SeatService {
	private final SeatInput SI = new SeatInput();
	SeatManager seatm = new SeatManager();
	
	public void showSeat() {
		System.out.println("좌석보기");
		try {
			
			String sql = "select SEAT_NUM, BROKEN_YN, MONITOR_TYPE, SEAT_TYPE, USAGE_YN FROM SEAT ORDER BY SEAT_NUM ";
			Connection conn = JdbcTemplate.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			//결과집합에서 데이터 꺼내기 
			System.out.println("===================================================================");
			System.out.println("==========================모든 좌석 상태==============================");
			System.out.println();
			while(rs.next()) {
				String no= rs.getString("SEAT_NUM");
				String broken = rs.getString("BROKEN_YN");
				String mType = rs.getString("MONITOR_TYPE");
				String sType = rs.getString("SEAT_TYPE");
				String u = rs.getString("USAGE_YN");
				System.out.print("| 좌석번호 : "+ no + " | 고장여부 : "+ broken + " | 모니터 :  "+ mType + " | 타입 : " + sType  + " | 이용상태 : " + u +"|");
				System.out.println();
			}
			System.out.println("==================================================================");
			System.out.println("");
			conn.close();
		}catch(Exception e) {
			AdminMain.Exception();
		}
		
	}
	
	public void editSeat() {
		System.out.println("==================================");
		System.out.println("|              좌석변경             |");
		System.out.println("==================================");
		SeatData data = SI.editSeat();
		try {
			Connection conn = JdbcTemplate.getConnection();
			String sql = "UPDATE SEAT SET BROKEN_YN = ?, MONITOR_TYPE = ?||'인치' , SEAT_TYPE = ? , USAGE_YN = ?  WHERE SEAT_NUM = ? ";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, data.getBrokenYN());
			pstmt.setString(2, data.getMonitorType() );
			pstmt.setString(3,data.getSeatType() );
			pstmt.setString(4, data.getUsageYN());
			pstmt.setInt(5,data.getSeatNum());
			int result = pstmt.executeUpdate();
			if(result == 1) {
				System.out.println("==================================");
				System.out.println("||           변경 성공 !          ||");
				System.out.println("==================================");
			}else {
				System.out.println("==================================");
				System.out.println("||            입력 오류           ||");
				System.out.println("==================================");
			}
			conn.close();
		}catch(Exception e) {
			AdminMain.Exception();
		}
			
	}
	
	
	public void addSeat() {
		System.out.println("좌석 추가");
		SeatData data = SI.addSeat();
		try {
			Connection conn = JdbcTemplate.getConnection();
			String sql = "INSERT INTO SEAT(SEAT_NUM,MONITOR_TYPE,SEAT_TYPE) VALUES(SEAT_SEQ.NEXTVAL,?||'인치',?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1,data.getBrokenYN());
			pstmt.setString(1, data.getMonitorType());
			pstmt.setString(2, data.getSeatType());
			int result = pstmt.executeUpdate();
			if(result == 1) {
				System.out.println("==================================");
				System.out.println("||       신규 좌석 추가 성공 !       ||");
				System.out.println("==================================");
			}else {
				
				System.out.println("==================================");
				System.out.println("||      입력값 오류로 좌석 추가 실패   ||");
				System.out.println("==================================");
			}
			conn.close();
		}catch(Exception e) {
			AdminMain.Exception();
		}
	}
	
}
