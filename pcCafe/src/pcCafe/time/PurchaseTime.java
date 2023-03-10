package pcCafe.time;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import pcCafe.admin.time.TimeData;
import pcCafe.main.*;
import pcCafe.useStatus.ServiceManager;

public final class PurchaseTime {

	
	
	public boolean showTimeTable(){
		try {
			Connection conn = JdbcTemplate.getConnection();
			System.out.println("=========피시방 요금===========");
		
		
			String sql = "SELECT * FROM TIME ORDER BY T_NUM";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			//시간권 목록 보여주기
			while(rs.next()) {
				System.out.println(rs.getString("T_NUM")+".가격:"+rs.getString("T_PRICE")+"원,시간:"+rs.getString("T_TIME")+"분");
			}
			//0이면 돌아가는 거 구현해야함. 
			System.out.println("0.이전화면으로 돌아가기");
		
			//클로즈
			conn.close();
			
			 int run = inputFee();
			 if(run == 1) {
				 return false;
			 }else {
				 return true;
			 }
			 
		}catch(Exception e) {
			System.out.println("정확한 숫자를 입력해주세요.");
			return true;
		}
}
	
	//요금제 입력받기
	//입력받은 요금제로 데이터 변수에 저장하기
	//feeNum: 사용자로부터 입력받은 요금제 고유번호
	//timeAddMin: 요금제 번호에 맞는 요금제 시간, 추가시간같은 개념
	//timePrice: 요금제 번호에 맞는 요금제 가격
	//timeMin: 	회원테이블에서 받아온 적립시간
	public int inputFee() {
		try {
			TimeData data = new TimeData();
			System.out.print("요금제를 선택하세요.:");
			String feeInput = Main.SC.nextLine().trim();
			if(feeInput.equals("0")) {
				System.out.println("이전 화면으로 돌아갑니다.");
				return 0;
			}else {
			
				int inputNum = Integer.parseInt(feeInput);
				data.setFeeNum(inputNum);
				
		
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
				return 0;
			}
		}catch(Exception e) {
			System.out.println("정확한 숫자를 입력해주세요.");
			return 1;
			//이전단계
		}
		
		
		//timeAddMin과timeMin과feeNum을 한꺼번에 데이터로 관리하기
	
		
		
	}	
	
	//100분, 2000원 결제 하시겠습니까? 결제안전장치기능
	//카드결제, 현금결제 결제 방식 선택받기
	public void getInfo(TimeData data) {
		int change =0;
		System.out.print(data.getTimeAddMin()+"분, "+data.getTimePrice()+"원 결제 하시겠습니까? (y/n)");
		//
		String input = Main.SC.nextLine().trim();
		if(input.equalsIgnoreCase("y")) {
			//카드결제인지 현금결제인지 묻기
			System.out.println("어떤 방식으로 결제하시겠습니까?");
			System.out.println("1.카드결제 2.현금결제");
			
			//스캐너로 값입력받기
			String input2 = Main.SC.nextLine().trim();
			
			//switch문으로 선택하기
			if("1".equals(input2)) {
				payCard(data);
			}else if("2".equals(input2)) {
				change = payCash(data); 
				if(change<0) {
					System.out.println("결제 실패입니다. 전단계로 돌아갑니다.");
				}else {
					System.out.println("거스름돈 : "+change +"원");
					addPayList(data);
				}
			}else {
				System.out.println("잘못된 입력입니다. 전 단계로 돌아갑니다.");
			}
				
		}else if(input.equalsIgnoreCase("n")) {
			System.out.println("결제가 취소되었습니다. 이전 화면으로 돌아갑니다.");
			//뒤로가기하면 시간권선택화면으로 돌아가기 
		}else {
			System.out.println("잘못입력하셨습니다. 이전 화면으로 돌아갑니다.");
		}
		
	}
	public void payCard(TimeData data) {
		System.out.println("카드결제가 완료되었습니다.");
		addPayList(data);
	}
	
	public int payCash(TimeData data) {
		//고객에게 받은 금액
		System.out.print("내실 현금을 입력해주세요.:");
		String input = Main.SC.nextLine();
		int inputCash = Integer.parseInt(input);
		
		//요금제 가격 알아와야함
		int timePrice = data.getTimePrice();
		
		//내실금액에서 요금제 가격을 뺀 금액구해서 int 거스름돈 반환하기change
		int change = inputCash - timePrice;
		return change;
		
	}
	
	
	//시간권 결제내역테이블에 결제 내역 추가하기
	public void addPayList(TimeData data) {
		try {
		//커넥션 받아오기
		Connection conn = JdbcTemplate.getConnection(); //Exception e 
		
		//SQL문작성 : 결제내역 인서트 
		//feeNum은 static 멤버변수로 선언하여 어디서든 이용가능하게끔 
		String sql= "INSERT INTO TIME_PAYMENT VALUES (TP_NUM.NEXTVAL,?,SYSDATE,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql); //SQLException e
		

		pstmt.setInt(1, data.getFeeNum()); //SQLException e
		pstmt.setInt(2, MemberMenu.memberNum); //SQLException e
		pstmt.setInt(3, data.getTimePrice());
		int result = pstmt.executeUpdate(); //SQLException e
		
		if(result ==1) {
			updateTimeDate(data);
		}else {
			System.out.println("결제 실패. 전단계로 돌아갑니다.");
			//돌아가는 코드 작성해야함.
		}
		
		//커넥션 종료
		conn.close(); //SQLException e
		}catch(Exception e) {
			System.out.println("정확한 숫자를 입력해주세요.");
		}
		
	}

	
	//추가시간 적립하기 
	//memNum이라는 값을 받아오게 만들었음. 
	public void updateTimeDate(TimeData data){
		try{
			Connection conn = JdbcTemplate.getConnection();//exception
			ServiceManager sm = new ServiceManager();
		
			//SQL문 작성(update) : 적립시간에 결제시간 더한 값 회원정보에 업데이트하기 
			String sql02 ="UPDATE MEMBER SET MEM_TIME =MEM_TIME + ? WHERE MEM_NUM = ?";
			PreparedStatement pstmt02 = conn.prepareStatement(sql02); //se
			pstmt02.setInt(1, data.getTimeAddMin()); //se
			pstmt02.setInt(2, MemberMenu.memberNum); //se
			int result = pstmt02.executeUpdate(); //se
			if(result == 1) {
				System.out.println(data.getTimeAddMin()+"분이 추가되었습니다.");
				conn.commit();
				//강분님께 화면 받아서 그 화면으로 돌아가기 
			}else {
				System.out.println("오류발생. 전단계로 돌아갑니다.");
			}
			conn.close();
		
		}catch(Exception e) {
			System.out.println("정확한 숫자를 입력해주세요.");
		}
	}
	
	
}