package pcCafe.useStatus.seatservice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import pcCafe.main.JdbcTemplate;
import pcCafe.main.MemberMenu;
import pcCafe.useStatus.SeatServiceManager;

public class CheckRemainTime {
	
	
	//남은 시간 조회 메소드
		public void showRemainTime() {
			SeatServiceManager ssm = new SeatServiceManager();
			Connection conn;
			try {
				conn = JdbcTemplate.getConnection();
				String sql3 = "SELECT USE_NUM FROM PC_USE WHERE MEM_NUM = ? ORDER BY USE_NUM DESC ";
				
				PreparedStatement pstmt3 = conn.prepareStatement(sql3);
				pstmt3.setInt(1, MemberMenu.memberNum);
				ResultSet rs3 = pstmt3.executeQuery();
				rs3.next();
				//가장 최신의 use_num을 가져와서 저장
				int useNum = rs3.getInt("USE_NUM");
				
				// 사용 좌석으로 시작시간 조회
				String sql1 ="SELECT TO_CHAR(PC_STARTTIME,'DD HH24:MI:SS') AS S_TIME FROM PC_USE WHERE USE_NUM = ?";
				// 회원번호로 이름, 적립 시간 조회
				String sql2 = "SELECT MEM_NAME,MEM_TIME FROM MEMBER WHERE MEM_NUM = ?";
				
				PreparedStatement pstmt1 = conn.prepareStatement(sql1);
				pstmt1.setInt(1, useNum);
				ResultSet rs1 = pstmt1.executeQuery();
				rs1.next();
					String startTime=rs1.getString("S_TIME");
					
					SimpleDateFormat sdf1 = new SimpleDateFormat("dd HH:mm:ss");
					//현재 시간
					Date now = new Date();
					String nowTime = sdf1.format(now);
					Date nowT = sdf1.parse(nowTime);
					Date startDate = sdf1.parse(startTime);
					
					long time1 = startDate.getTime();
					long time2 = now.getTime();
					
					// 현재 시간 - 시작 시간 = 이용 시간
					long diff = time2-time1;
					long diffMin = (diff/1000)/60%60;
					int diffMin2 = (int)diffMin;
					
				//적립시간 찾아와서 적립시간 - 이용 시간 = 현재 남은 시간
				PreparedStatement pstmt2 = conn.prepareStatement(sql2);
				pstmt2.setInt(1, MemberMenu.memberNum);
				ResultSet rs2 = pstmt2.executeQuery();
				rs2.next();
					int memTime = rs2.getInt("MEM_TIME");
					String memName = rs2.getString("MEM_NAME");
					int rtime = memTime - diffMin2;
					
					System.out.println(memName + "님의 남은 시간은 " + rtime + "분 입니다.");
				
				// conn 정리
		        conn.close();
			} catch (Exception e) {
				System.out.println("올바른 값이 아닙니다.");
			}
		}
	

}
