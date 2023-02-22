package pcCafe.useStatus.seatservice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import pcCafe.admin.time.TimeData;
import pcCafe.main.JdbcTemplate;
import pcCafe.main.Main;
import pcCafe.main.MemberMenu;
import pcCafe.useStatus.SeatServiceManager;
import pcCafe.useStatus.ServiceManager;


public class AdditonalPayment {
	
	
	// 추가결제
	// 현재 시간-시작시간 = 현재까지 이용한 시간
	// 새로 결제한 시간권 시간 + 현재까지 이용한 시간 => 적립시간에 업데이트
	public void showTimeTable(){
		try {
			Connection conn = JdbcTemplate.getConnection();
			System.out.println("========================================");
			System.out.println("             ┌──────────────┐              ");
			System.out.println("             │   피시방 요금   │               ");
			System.out.println("             └──────────────┘              ");
			System.out.println("========================================");
		
			String sql = "SELECT * FROM TIME";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			//시간권 목록 보여주기
			while(rs.next()) {
				System.out.println(rs.getString("T_NUM")+".가격:"+rs.getString("T_PRICE")+"원,시간:"+rs.getString("T_TIME")+"분");
			}
			System.out.println("0.이전화면으로 돌아가기");
			
			//클로즈
			conn.close();
			inputFee();
			
		}catch(SQLException se) {
			System.out.println("DB에러 발생. DB관리자에 문의하세요.");
			System.out.println("오류상세 정보"+se.toString());
		}catch(Exception e) {
			System.out.println("커넥션 오류 발생.");
			System.out.println(e.toString());
		}
}
	
	//요금제 입력받기
	//입력받은 요금제로 데이터 변수에 저장하기
	//feeNum: 사용자로부터 입력받은 요금제 고유번호
	//timeAddMin: 요금제 번호에 맞는 요금제 시간, 추가시간같은 개념
	//timePrice: 요금제 번호에 맞는 요금제 가격
	//timeMin: 	회원테이블에서 받아온 적립시간
	public void inputFee() {
		ServiceManager sm = new ServiceManager();
		try {
			TimeData data = new TimeData();
			System.out.print("요금제를 선택하세요.:");
			String inputNum = Main.SC.nextLine().trim();
			if(inputNum.equals("0")) {
				return;
			}
			
			int feeInput = Integer.parseInt(inputNum);
			data.setFeeNum(feeInput);
				
			//feeNum을 받는 동시에 timeAddMin구하기 가능함
			//timeMin도 당장 받아올수있음. 세게의 변수를 한꺼번에 받아서 data 로 묶자
		
			Connection conn = JdbcTemplate.getConnection();
			//timeAddMin구하기 : 요금 결제시 고객이 선택한 추가시간임.
			String sql1 = "SELECT T_TIME, T_PRICE FROM TIME WHERE T_NUM = ?";
			PreparedStatement pstmt1 = conn.prepareStatement(sql1); //se
			pstmt1.setInt(1,data.getFeeNum()); //se
			ResultSet rs = pstmt1.executeQuery(); //se
			rs.next(); //se
			data.setTimeAddMin(rs.getInt("T_TIME"));//se
			//int timeAddMin = rs.getInt("T_TIME");
			data.setTimePrice(rs.getInt("T_PRICE")); //se
			//int timePrice = rs.getInt("T_PRICE");
	
			//timeMin받아오기 : 회원테이블의 남은 시간
			String sql2 = "SELECT MEM_TIME FROM MEMBER WHERE MEM_NUM= ?";
			PreparedStatement pstmt2 = conn.prepareStatement(sql2); //se
			pstmt2.setInt(1, MemberMenu.memberNum); //se
			ResultSet rs2 = pstmt2.executeQuery(); //se
			if(rs2.next()) { //se
				data.setTimeMin(rs2.getInt("MEM_TIME")); //se
			//int timeMin = rs2.getInt("MEM_TIME");
			}
				
			conn.close(); //se
			
			//다음단계로 넘어가기
			getInfo(data);
		}catch(Exception e) {
		
		}
		
	}	
	
	public void getInfo(TimeData data) {
		System.out.print(data.getTimeAddMin()+"분, "+data.getTimePrice()+"원 결제 하시겠습니까? (y/n)");
		String input = Main.SC.nextLine().trim();
		if(input.equalsIgnoreCase("y")) {
			System.out.println("결제가 완료되었습니다.");
			addPayList(data);
		}else if(input.equalsIgnoreCase("n")) {
			System.out.println("결제가 취소되었습니다. 이전 화면으로 돌아갑니다.");
			//뒤로가기하면 시간권선택화면으로 돌아가기 
		}else {
			System.out.println("잘못입력하셨습니다. 이전 화면으로 돌아갑니다.");
		}
	}
	
	//시간권 결제내역테이블에 결제 내역 추가하기
	public void addPayList(TimeData data) {
		try {
		//커넥션 받아오기
		Connection conn = JdbcTemplate.getConnection();
		
		//SQL문작성 : 결제내역 인서트 
		//feeNum은 static 멤버변수로 선언하여 어디서든 이용가능하게끔 
		String sql= "INSERT INTO TIME_PAYMENT VALUES (TP_NUM.NEXTVAL,?,SYSDATE,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql); 
		
		pstmt.setInt(1, data.getFeeNum()); 
		pstmt.setInt(2, MemberMenu.memberNum); 
		
		int result = pstmt.executeUpdate();
		conn.commit();
		if(result == 1) {
			updateTimeDate(data);
		}else {
			System.out.println("결제 실패. 전단계로 돌아갑니다.");
		}
		
		//커넥션 종료
		conn.close(); 
		}catch(Exception e) {
		}finally { //오류와 상관없이 무조건 실행되는 구
			
		}
		
	}
	//추가시간 적립하기 
	//memNum이라는 값을 받아오게 만들었음. 
	public void updateTimeDate(TimeData data){
		SeatServiceManager ssm = new SeatServiceManager();
		Connection conn;
		try{
			conn = JdbcTemplate.getConnection();//exception
			String sql3 = "SELECT USE_NUM FROM PC_USE WHERE MEM_NUM = ? ORDER BY USE_NUM DESC ";
			PreparedStatement pstmt3 = conn.prepareStatement(sql3);
			pstmt3.setInt(1, MemberMenu.memberNum);
			ResultSet rs3 = pstmt3.executeQuery();
			rs3.next();
			//가장 최신의 use_num을 가져와서 저장
			int use_num = rs3.getInt("USE_NUM");
			
			//SQL문 작성(update) : 적립시간에 결제시간 더한 값 회원정보에 업데이트하기 
			//(시간권 결제한 시간 + (적립시간-(현재시간-사용시작시간 = 현재까지 이용한 시간)))
			String sql1 = "SELECT TO_CHAR(PC_STARTTIME,'DD HH24:MI:SS') AS S_TIME FROM PC_USE WHERE USE_NUM = ?";
			String sql2 = "SELECT MEM_TIME FROM MEMBER WHERE MEM_NUM = ?";
			String sql4 = "UPDATE MEMBER SET MEM_TIME = ? WHERE MEM_NUM = ?";
			PreparedStatement pstmt1 = conn.prepareStatement(sql1); 
			PreparedStatement pstmt2 = conn.prepareStatement(sql2);
			PreparedStatement pstmt4 = conn.prepareStatement(sql4);
			// 이용 번호로 이용 시작 시간 조회
			pstmt1.setInt(1, use_num);
			ResultSet rs1 = pstmt1.executeQuery();
			rs1.next();
			String startTime=rs1.getString("S_TIME");
			SimpleDateFormat sdf1 = new SimpleDateFormat("dd HH:mm:ss");
			//현재 시간- 시작 시간 = 지금까지 이용한 시간
			Date now = new Date();
			String nowTime = sdf1.format(now);
			Date nowT = sdf1.parse(nowTime);
			Date startDate = sdf1.parse(startTime);
			String startT = sdf1.format(startDate);
			
			long time1 = startDate.getTime();
			long time2 = now.getTime();
			
			long diff = time2-time1;
			long diffMin = (diff/1000)/60%60;
			int diffMin2 = (int)diffMin;
			
			//적립시간 찾아와서 적립시간 - 이용 시간 = 현재 남은 시간
			pstmt2.setInt(1, MemberMenu.memberNum);
			ResultSet rs2 = pstmt2.executeQuery();
			rs2.next();
			int memTime = rs2.getInt("MEM_TIME");
			int rtime = memTime - diffMin2;
			int totalT = data.getTimeAddMin()+rtime;
			
			//적립 시간 업데이트
			pstmt4.setInt(1, totalT);
			pstmt4.setInt(2, MemberMenu.memberNum);
			int result = pstmt4.executeUpdate();
			conn.commit();
			if(result == 1) {
				System.out.println(data.getTimeAddMin() +"분이 추가되었습니다.");
				System.out.println("남은 시간 : " + totalT + "분");
			}else {
			}
		conn.close();
		}catch(Exception e) {
				System.out.println("올바른 값이 아닙니다.");
			}
	}
	
}
