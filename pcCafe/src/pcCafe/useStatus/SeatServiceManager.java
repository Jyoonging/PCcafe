package pcCafe.useStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import pcCafe.main.JdbcTemplate;
import pcCafe.main.Main;
import pcCafe.useStatus.seatservice.AdditonalPayment;
import pcCafe.useStatus.seatservice.CheckRemainTime;
import pcCafe.useStatus.seatservice.OrderProduct;
import pcCafe.useStatus.seatservice.StopUse;

public class SeatServiceManager {
	
	OrderProduct op = new OrderProduct();
	CheckRemainTime crt = new CheckRemainTime();
	AdditonalPayment ap = new AdditonalPayment();
	StopUse su = new StopUse();
	
	// 좌석 선택 시 나오는 화면
	public void afterChooseSeat() {
		
		System.out.println("====================================  회원메뉴 >   ====================================================");
		System.out.println("                                                                                       "); 
		System.out.println("    ┌───────────┐  ┌───────────────┐  ┌─────────────┐  ┌─────────────┐  ");
		System.out.println("    │1. 상품 주문 │  │2. 남은 시간 조회 │  │ 3. 추가 결제  │  │ 4. 이용 종료  │  ");
		System.out.println("    └───────────┘  └───────────────┘  └─────────────┘  └─────────────┘ ");
		System.out.println("=====================================================================================================");
		System.out.print("번호를 선택하세요. >>>>>>>> ");
		
		String inputStr = Main.SC.nextLine().trim();
		int input = Integer.parseInt(inputStr);
		switch(input){
		case 1 : op.orderFood(); break;
		case 2 : crt.showRemainTime(); break;
		case 3 : ap.showTimeTable(); break;
		case 4 : su.stopUse(); break;
		default : System.out.println("다시 선택하세요");
		}
	}
	
		
	
	

}
