package pcCafe.useStatus.seatservice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import pcCafe.main.JdbcTemplate;
import pcCafe.main.Main;
import pcCafe.main.MemberMenu;
import pcCafe.useStatus.SeatServiceManager;
import pcCafe.useStatus.ServiceManager;

public class StopUse {
	
	//이용 종료
	public boolean stopUse() {
		SeatServiceManager ssm = new SeatServiceManager();
		System.out.println("종료하시겠습니까?");
		System.out.println("1. 네       2. 아니오");
		String inputStr = Main.SC.nextLine().trim();
		int input = Integer.parseInt(inputStr);
		if (input ==1) {
			calRemainTime(); 
			changeYN(); return false;
		}else if (input==2) {
			return true;
			
		}else {
		 System.out.println("다시 선택하세요");
		 return true;
		}
	}
	
	public void calRemainTime() {
		Connection conn; //커넥션받기
		try {
			conn = JdbcTemplate.getConnection();
			
			//useNum에 해당하는 줄의 종료시간을 업데이트
			String sql5 = "UPDATE PC_USE SET PC_ENDTIME = SYSDATE WHERE USE_NUM = ?";
			PreparedStatement pstmt5 = conn.prepareStatement(sql5);
			pstmt5.setInt(1, ServiceManager.useNum);
			int result5 = pstmt5.executeUpdate();
			conn.commit();
			if(result5==1) {
				System.out.println("종료되었습니다.");
			}else {
				System.out.println("올바른 값이 아닙니다.");
			}
			
			//시작시간, 종료시간 조회
			String sql1 ="SELECT TO_CHAR(PC_STARTTIME,'DD HH24:MI:SS') AS S_TIME, TO_CHAR(PC_ENDTIME,'DD HH24:MI:SS')AS E_TIME FROM PC_USE WHERE USE_NUM = ?";
			//회원번호에 맞는 회원의 적립시간 조회
			String sql2 = "SELECT MEM_TIME FROM MEMBER WHERE MEM_NUM = ?"; 
			//회원번호에 맞는 회원의 적립시간을 바꿔줌
			String sql3 = "UPDATE MEMBER SET MEM_TIME = ? WHERE MEM_NUM = ?";
			
			//SQL1
			PreparedStatement pstmt1 = conn.prepareStatement(sql1);
			pstmt1.setInt(1, ServiceManager.useNum);
			ResultSet rs1 = pstmt1.executeQuery();
			rs1.next();
			String startTime=rs1.getString("S_TIME");
			String endTime=rs1.getString("E_TIME");
			
			SimpleDateFormat sdf1 = new SimpleDateFormat("dd HH:mm:ss");
			
			Date startDate = sdf1.parse(startTime);
			Date endDate =sdf1.parse(endTime);
			
			long time1 = startDate.getTime();
			long time2 = endDate.getTime();
			long diff = time2-time1;
			long diffMin = (diff/1000)/60%60;
			int diffMin2 = (int)diffMin;
			
			// 적립 시간 꺼내와서 적립시간 - DIFFMIN
			PreparedStatement pstmt2 = conn.prepareStatement(sql2);
			pstmt2.setInt(1,MemberMenu.memberNum); 
			ResultSet rs2 = pstmt2.executeQuery();
			rs2.next();
			int memTime = rs2.getInt("MEM_TIME");
			int rtime = memTime - diffMin2;
			
			//남은 시간을 회원 테이블 적립시간에 업데이트
			PreparedStatement pstmt3 = conn.prepareStatement(sql3);
			pstmt3.setInt(1,rtime); 
			pstmt3.setInt(2,MemberMenu.memberNum); 
			int result = pstmt3.executeUpdate();
			conn.commit();
			if(result == 1) {
				System.out.println("--------------------------------");
				System.out.println("남은 시간은 " + rtime + "분 입니다.");
				System.out.println("안녕히 가세요.");
				System.out.println("--------------------------------");
			} else {
				System.out.println("올바른 값이 아닙니다.");
			}
			
			conn.close();
		} catch (Exception e) {
	    	System.out.println("올바른 값이 아닙니다.");
		}
	}
	
	public void changeYN() {
		
		Connection conn;
		conn = JdbcTemplate.getConnection();
		//이용 종료 시 좌석 사용 여부 업데이트
		String sql = "UPDATE SEAT SET USAGE_YN = 'N' WHERE SEAT_NUM = ?";
		
		//sql1
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,ServiceManager.seatNum);
			int result = pstmt.executeUpdate();
			System.out.println(result);
			conn.commit();
			conn.close();
		} catch (SQLException e) {
			System.out.println("올바른 값이 아닙니다.");
		}
		
	}
	
}
