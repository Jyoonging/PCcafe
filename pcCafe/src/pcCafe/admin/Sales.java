package pcCafe.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import pcCafe.admin.time.TimeData;
import pcCafe.main.JdbcTemplate;
import pcCafe.main.Main;
import pcCafe.main.MemberData;

public class Sales {
	
	public void showMenu() {
		System.out.println("강분님꾸며주세요. 세일즈클래스의 showMenu메소드입니다.");
		System.out.println("1. 지금까지의 총 매출액");
		System.out.println("2. 특정 회원의 매출액");
		System.out.println("3. 월간 하루.. 매출액 분석 뭐라표현하지?"); 
	}
	
	//2023년 2월 22일의 매출 : 
	//2023년 2월 23일의 매출 :	
	//월간 최대 매출 금액 : 23일의 매출금액 
	//월간 최대 매출 날짜 : 
	public boolean analyzeSales() {
		HashMap<Integer,Integer> map = new HashMap();
		
		Calendar today = Calendar.getInstance();
		int lastDate = today.getActualMaximum(Calendar.DATE);
		int yearToday = today.get(Calendar.YEAR);
        int monthToday = today.get(Calendar.MONTH);
        int dateToday = today.get(Calendar.DATE);
		int openDate =20; //지정함.
        //2023년 2월 20일이 개업일이라고하자
        //날짜가 23년 2월20일 전이면 조회안됨.
        //해당 달 조회기능 넣고 2월의 매출 조회
		//궁금점 : 3월 1일로 넘어가면 어떻게 됨?
		
		for(int i=openDate; i<=dateToday ;i++) {
			//왜 굳이 배열로 받음?
			int daySum = viewDayTimeSales(i) + viewDayProductSales(i);
			map.put(i,daySum);
			
			//최대 최소 구하기
			System.out.println(yearToday+"년"+monthToday+"월"+i+"일 매출액 :" +daySum);
			System.out.println("-시간권 총 결제 금액 : "+viewDayTimeSales(i));
			System.out.println("-상품구매 총 결제 금액 : "+viewDayProductSales(i));
			
		}
		int max = map.get(openDate);
		int min =map.get(openDate);
		int maxDate=0;
		int minDate=0;
		//최대최소구하기 분리
		//i 20 get(20) = 50만원 max 50만원  max=50aksjs maxdate=20 min 50마넌 mindate 20일
		//i 21 get(21)=5800원 max 50만원 안돌아감 max안돌아감
		//min min50마넌 min이 5800원으로바뀜 mindate 21일
		//i 22됐어 
		/*
		 * 22일임.
		 * 현재 max50마넌 min5000원
		 * get(22) ->30마넌
		 * 첫번째 if실행안됨
		 * 두번째 if문도 지나감
		 * 
		 * 그렇게 23일이 되는거야
		 */
		//i 23
		for(int i=openDate; i<=dateToday ;i++) {
			
			if(max<=map.get(i)) {
				max=map.get(i);
				maxDate= i;
			}if(min>=map.get(i)) {
				min=map.get(i);
				minDate =i;
			}
			
		}
		
		
		
		System.out.println("※이번달 최대 매출 : "+max+"원");
		System.out.println("※최대 매출 경신 날짜 :"+maxDate+"일");
		System.out.println("※이번달 최소 매출 : "+min+"원");
		System.out.println("※최소 매출 날짜 : "+minDate+"일");
		
		
		
		//21일부터 개업했다고 치자 (더미데이터 건들기..)
		
		return true;
	}
	
	public int viewDayProductSales(int date) {
		String day = "2023-02-"+Integer.toString(date);
		Connection conn=JdbcTemplate.getConnection();
		try {
			String sql1 = "SELECT SUM(TOTAL_PRICE) FROM PRODUCT_PAYMENT WHERE TO_CHAR(P_PAY_DATE, 'YYYY-MM-DD') = ?";
			PreparedStatement pstmt1 = conn.prepareStatement(sql1);
			pstmt1.setString(1, day);
			ResultSet rs01 =  pstmt1.executeQuery();
			rs01.next();
			int daySales =rs01.getInt("SUM(TOTAL_PRICE)");
			
			return daySales;
			
		}catch(Exception e) {
			System.out.println("세일즈 3번째 매출액분석쪽에서 오류났음");
			return 5; //터무니없이 작은 금액이 나오면 오류났다고 생각할수있게 만듦.
		}
		
	}
	public int viewDayTimeSales(int date) {
		String day = "2023-02-"+Integer.toString(date);
		Connection conn=JdbcTemplate.getConnection();
		try {
			String sql1 = "SELECT SUM(TP_PRICE) FROM TIME_PAYMENT WHERE TO_CHAR(T_PAY_DATE,'YYYY-MM-DD')= ?";
			PreparedStatement pstmt1 = conn.prepareStatement(sql1);
			pstmt1.setString(1, day);
			ResultSet rs01 =  pstmt1.executeQuery();
			rs01.next();
			int daySales =rs01.getInt("SUM(TP_PRICE)");
			
			return daySales;
			
		}catch(Exception e) {
			System.out.println("세일즈 3번째 매출액분석쪽에서 오류났음");
			return 3; //터무니없이 작은 금액이 나오면 오류났다고 생각할수있게 만듦.
		}
		//지정한 날의 매출액 구하기
		
		//결제일시의 yy dd mm 이 일치하는 곳의 결제금액의 합
		//시간권 : 결제금액의 합
		//상품 : 총 주문 금액의 합
	}
	public boolean showTotalSales() {
		//sum값 TP_PRICE + SUM 
		//Total_price의 총 값 가져오기 이용번호의 총 주문금액 가지고와야함.
		
		//커넥션호출
		Connection conn = JdbcTemplate.getConnection();
		try {
			//sql실행
			String sql1= "SELECT SUM(TP_PRICE) AS 총합 FROM TIME_PAYMENT";
			PreparedStatement pstmt1 = conn.prepareStatement(sql1);
			ResultSet rs1 = pstmt1.executeQuery();
			rs1.next();
			int timeSum = rs1.getInt("총합");
			
			String sql2= "SELECT  SUM(TOTAL_PRICE) AS 총합 FROM PRODUCT_PAYMENT";
			PreparedStatement pstmt2 = conn.prepareStatement(sql2);
			ResultSet rs2 = pstmt2.executeQuery();
			rs2.next();
			int productSum = rs2.getInt("총합");
			
			System.out.println("시간제 금액:"+timeSum+"원, 상품 금액 :"+productSum+"원");
			return true;
		}catch(Exception e) {
			System.out.println("왜오류나는지 모르겠는데요? 이거슨 세일즈의 36번째 줄에서 발생한 예외입니다.");
			return true;
		}
	}

		public boolean viewSalesforMember() {
			TimeData data = new TimeData();
			MemberData md = new MemberData();
			String userId = getUserId();
		
			try {
				//sql실행 : 시간금액총합 구하기
				Connection conn = JdbcTemplate.getConnection();
				String sql1 = "SELECT SUM(TP_PRICE) AS 시간금액총합 FROM TIME_PAYMENT WHERE MEM_NUM = (SELECT MEM_NUM FROM MEMBER WHERE MEM_ID = ?)";
				PreparedStatement pstmt1 = conn.prepareStatement(sql1);
				pstmt1.setString(1,userId);
				ResultSet rs01 = pstmt1.executeQuery();
				rs01.next();
				int sumTimePrice = rs01.getInt("시간금액총합");
				
				//sql실행 : 상품금액총합 구하기 
				String sql2 = "SELECT SUM(TOTAL_PRICE) AS 상품금액총합 FROM PRODUCT_PAYMENT WHERE use_num in ( SELECT use_num FROM PC_USE WHERE MEM_NUM =(SELECT MEM_NUM FROM MEMBER WHERE MEM_ID=?))";
				PreparedStatement pstmt2 = conn.prepareStatement(sql2);
				pstmt2.setString(1,userId);
				ResultSet rs02 = pstmt2.executeQuery();
				rs02.next();
				int sumProductPrice = rs02.getInt("상품금액총합");
				
				int sumTotal =sumTimePrice + sumProductPrice;
				int memNum= adminUserData(userId);
				if(sumTotal==0) {
					if(memNum!=0) {
						System.out.println("조회된 결제 내역이 없습니다.");	
						return true;
					}else {
						return true;
					}
				} else {
					System.out.println("시간 사용금액 총합: "+sumTimePrice+"원, 상품 구매 총합 : "+sumProductPrice+"원");
					System.out.println("총합: "+sumTotal+"원");
					return true;
					
				}
					
			
			
			}catch(Exception e) {
				System.out.println("몰루? 당신이 만약 이오류를 발견한다면..? Sales의 68번줄로 오세요.");
				return true;
			}
			
		}
		
		public String getUserId() {
			System.out.println("아이디 입력 : ");
			String userId = Main.SC.nextLine().trim();
			return userId;
		}	
		public int adminUserData(String memId) {
			try {
				Connection conn = JdbcTemplate.getConnection();
				String sql = " SELECT MEM_NUM FROM MEMBER WHERE MEM_ID = ? ";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1,memId);
				ResultSet rs = pstmt.executeQuery();
				rs.next();
				int memNum = rs.getInt("MEM_NUM");
				return memNum;
				
			}catch(Exception e) {
				System.out.println("없는 회원입니다. 올바르게 입력했는지 확인해주세요.");
				return 0;
			}
			
		}
		
}

