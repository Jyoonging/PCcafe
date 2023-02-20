package pcCafe.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import adminPrj.admin.time.TimeFeeManager;
import pcCafe.admin.member.MemberManager;
import pcCafe.admin.seat.SeatManager;
import pcCafe.admin.stock.StockManager;
import pcCafe.admin.usePC.UsePc;
import pcCafe.main.JdbcTemplate;



public class AdminService {

	private static final AdminInput AI = new AdminInput();
	//로그인
	public int adminLogin(){
		//로그인 정보 입력창
		AdminData data = AI.login();
		Connection conn;
		try {
			conn = JdbcTemplate.getConnection();
			String sql = "SELECT ADMIN_ID,ADMIN_PWD,ADMIN_NAME, ADMIN_NUM FROM ADMIN WHERE ADMIN_ID = ? AND ADMIN_PWD = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, data.getAdminId());
			pstmt.setString(2, data.getAdminPwd());
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				int ADMIN_NUM = rs.getInt("ADMIN_NUM");
				String name = rs.getString("ADMIN_NAME");
				System.out.println(" 관리자 이름 :  "+  name + " 님 환영합니다.");
				conn.close();
				return ADMIN_NUM;
			}else {
				System.out.println("==========================");
				System.out.println("=========로그인 실패=========");
				System.out.println("==========================");
				return 0;
			}
			
		} catch (SQLException e) {
			AdminMain.Exception();
			return 0 ;
		}
		
		
		
		
	}

	public void startService(){
		StockManager sm = new StockManager();
		MemberManager mm = new MemberManager();
		SeatManager seatm = new SeatManager();
		UsePc us = new UsePc();
		TimeFeeManager tfm = new TimeFeeManager();
		boolean adminRun;
		do {
			System.out.println();
			System.out.println("==================================");
			System.out.println("============관리자로 시작============");
			System.out.println("|1. 회원관리, 2. 상품관리, 3. 좌석 관리|");
			System.out.println("|4. 사람찾기, 5. 시간,요금관리, 9. 종료|");
			System.out.println("==================================");
			System.out.print("  : ");
			String input = AdminMain.SC.nextLine().trim();
			if("1".equals(input)) {
				adminRun = mm.showMenu();
			}else if("2".equals(input)){
				adminRun = sm.serviceMenu();
			}else if("3".equals(input)){
				adminRun = seatm.serviceMenu();
			}else if("4".equals(input)){
				adminRun = us.findUserInfo();
			}else if("5".equals(input)){
				adminRun = tfm.showTimeFee();
			}else if("9".equals(input)){
				adminRun= false;
				System.out.println("종료합니다");
				break ;
			}else{ 
				System.out.println("없는 목록 번호! 다시 접속하세요");
				adminRun = true;
				break;
			}
		

		}while(adminRun == true);
	
	}
}