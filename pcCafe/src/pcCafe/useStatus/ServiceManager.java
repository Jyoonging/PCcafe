package pcCafe.useStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import pcCafe.main.JdbcTemplate;
import pcCafe.main.Main;
import pcCafe.main.MemberController;
import pcCafe.main.MemberData;
import pcCafe.main.MemberMenu;
import pcCafe.product.PurchaseProduct;
import pcCafe.time.PurchaseTime;

public class ServiceManager {
	
	static MemberController ds = new MemberController();
    private MemberData MD = new MemberData();
    MemberMenu mm = new MemberMenu();
    SeatServiceManager ssm = new SeatServiceManager();
    public static int seatNum;
    
    	//회원고유번호 변수 지정
  		public int memberNum() {
  			Connection conn;
  			try {
  				conn = JdbcTemplate.getConnection();
  				String sql = "SELECT MEM_NUM FROM MEMBER WHERE MEM_ID = ? ";
  				PreparedStatement pstmt = conn.prepareStatement(sql);
  				pstmt.setString(1, MD.getUserId());
  				ResultSet rs = pstmt.executeQuery();
  				
  				// 결과집합에서 데이터 꺼내기
  				if(rs.next()) {
  					int MEM_NUM = rs.getInt("MEM_NUM");
  					conn.close();
  					return MEM_NUM;
  					}
  					else {
  						System.out.println("맞는 회원번호가 없습니다");
  						return 0;
  					}
  			} catch (Exception e) {
  				System.err.println("Error: " + e.getMessage());
  				return 0;
  			}
  		}
    
		public void showMenu() {
			System.out.println("====================================  회원메뉴 >   ====================================================");
			System.out.println("                                                                                       "); 
			System.out.println("    ┌───────────┐  ┌───────────┐  ┌─────────┐  ");
			System.out.println("    │1. 요금 선택 │  │2. 좌석 선택 │  │ 3. 종료  │  ");
			System.out.println("    └───────────┘  └───────────┘  └─────────┘   ");
			System.out.println("=====================================================================================================");
			System.out.print("메뉴를 선택하세요. > ");
			
			PurchaseTime pt = new PurchaseTime();
			while(true) {
				String inputStr = Main.SC.nextLine().trim();
				int input = Integer.parseInt(inputStr);
				if(input == 1) {
					pt.showTimeTable();
					}
					else if(input == 2) { 
					chooseSeat();
					}else if(input == 3) { 
				return;} else {
				System.out.println("다시 선택하세요");
				}
			}
		}
		
		// 요금 선택
		public void fee() {
			System.out.println("나중에 예린님거 추가~~");
		};
		
		//좌석 선택
		public void chooseSeat() {
		    // 적립 시간 조회
		    String sql = "SELECT MEM_TIME FROM MEMBER WHERE MEM_NUM = ?";
		    Connection conn = JdbcTemplate.getConnection();
			try {
				PreparedStatement pstmt;
				pstmt = conn.prepareStatement(sql);
				 pstmt.setInt(1, MemberMenu.memberNum);
				    ResultSet rs = pstmt.executeQuery();
				    // 결과 집합에서 데이터 꺼내서 조건문
				    rs.next();
				        int memTime = rs.getInt("MEM_TIME");
				        if (memTime == 0) {
				        	System.out.println("남은 시간이 없습니다.");
				        	System.out.println("결제를 먼저 해주세요");
				        	showMenu();
				        } else if(memTime != 0) {
				        	showSeat();
				        }
				    
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   
		}
			
		// 좌석 배열 보여주기 (select 좌석 정보)
		public void showSeat() {
		    // SQL 실행
		    String sql = "SELECT SEAT_NUM, SEAT_TYPE, USAGE_YN FROM SEAT";
		    Connection conn;
		    try {
		        conn = JdbcTemplate.getConnection();
		        PreparedStatement pstmt = conn.prepareStatement(sql);
		        ResultSet rs = pstmt.executeQuery();

		        // 결과집합에서 데이터 꺼내기
		        while (rs.next()) {
		            seatNum = rs.getInt("SEAT_NUM");
		            String SEAT_TYPE = rs.getString("SEAT_TYPE");
		            String USAGE_YN = rs.getString("USAGE_YN");

		            System.out.print(seatNum + "번" + " ◻︎ ");
		            System.out.print(" || ");

		            if (SEAT_TYPE.equals("금연")) {
		                System.out.print(" 금연 좌석 ");
		            } else {
		                System.out.print(" 흡연 좌석 ");
		            }

		            System.out.print(" || ");

		            if (USAGE_YN.equals("Y")) {
		                System.out.println("사용 중 ... ");
		            } else {
		                System.out.println("사용 가능");
		            }
		        }
		        System.out.println();
		        System.out.print("좌석을 선택하세요. >>>>>>>  ");
		        // 좌석 사용 여부 메소드 불러오기
		        usage_YN();
		        // conn 정리
		        conn.close();
		    } catch (Exception e) {
		    	System.err.println("Error: " + e.getMessage());
		    }
		}

		//좌석 사용여부
		public void usage_YN() {
		    int input = Main.SC.nextInt();
		    // 저장된 좌석 번호로 사용 여부 조회
		    String sql1 = "SELECT SEAT_NUM, USAGE_YN FROM SEAT WHERE SEAT_NUM = ?";
		    // 좌석 선택과 동시에 이용내역 INSERT
		    String sql2 = "INSERT INTO PC_USE(USE_NUM,SEAT_NUM,MEM_NUM, PC_STARTTIME) VALUES(PC_USE_SEQ.NEXTVAL,?,?,SYSDATE)";
		    // 좌석 선택 성공 시 멤버테이블 사용 여부 업데이트
		    String sql3 = "UPDATE SEAT SET USAGE_YN = 'Y' WHERE SEAT_NUM = ?";
		    Connection conn;
		    try {
		        conn = JdbcTemplate.getConnection();
		        PreparedStatement pstmt1 = conn.prepareStatement(sql1);
		        pstmt1.setInt(1, input);
		        ResultSet rs = pstmt1.executeQuery();
		        
		        // 결과 꺼내서 사용 여부 알려주기
		        rs.next(); 
		            String USAGE_YN = rs.getString("USAGE_YN");
		            seatNum = rs.getInt("SEAT_NUM");
		            if (USAGE_YN.equals("Y")) {
		                System.out.println("이미 사용 중인 좌석입니다. ");
		                showSeat();
		            } else {
		                System.out.println(seatNum + "번 좌석을 선택하셨습니다.");
		        PreparedStatement pstmt2 = conn.prepareStatement(sql2);
		        pstmt2.setInt(1,seatNum);
		        pstmt2.setInt(2,MemberMenu.memberNum);
		        int result1 = pstmt2.executeUpdate();
		        //좌선 선택함과 동시에 좌석테이블 사용 여부 업데이트
		        PreparedStatement pstmt3 = conn.prepareStatement(sql3);
		        pstmt3.setInt(1,seatNum);
		        int result2 = pstmt3.executeUpdate();
        		        if(result2 ==1) {
        		        	System.out.println("이용이 시작되었습니다.");
        		        	ssm.afterChooseSeat();
        		        } else {
        		        	System.out.println("이용 실패");
        			    }
		            }
		          
		        // conn 정리
		        conn.close();
		    } catch (Exception e) {
		    	System.err.println("Error: " + e.getMessage());
		    }
		}
		
		

}