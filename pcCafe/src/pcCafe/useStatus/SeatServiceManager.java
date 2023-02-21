package pcCafe.useStatus;


import pcCafe.main.Main;
import pcCafe.useStatus.seatservice.AdditonalPayment;
import pcCafe.useStatus.seatservice.CheckRemainTime;
import pcCafe.useStatus.seatservice.OrderProduct;
import pcCafe.useStatus.seatservice.StopUse;

public class SeatServiceManager {
	
	
	// 좌석 선택 시 나오는 화면
	public void afterChooseSeat() {
		OrderProduct op = new OrderProduct();
		CheckRemainTime crt = new CheckRemainTime();
		AdditonalPayment ap = new AdditonalPayment();
		StopUse su = new StopUse();
		boolean run = true;
		while(run) {
			
			System.out.println("====================================  회원메뉴 >   ====================================================");
			System.out.println("                                                                                       "); 
			System.out.println("    ┌───────────┐  ┌───────────────┐  ┌─────────────┐  ┌─────────────┐  ");
			System.out.println("    │1. 상품 주문  │  │2. 남은 시간 조회 │  │ 3. 추가 결제  │  │ 4. 이용 종료  │  ");
			System.out.println("    └───────────┘  └───────────────┘  └─────────────┘  └─────────────┘ ");
			System.out.println("=====================================================================================================");
			System.out.print("번호를 선택하세요. >>>>>>>> ");
			
			try {
				String inputStr = Main.SC.nextLine().trim();
				int input = Integer.parseInt(inputStr);
				switch(input){
				case 1 : op.orderFood();break;
				case 2 : crt.showRemainTime(); break;
				case 3 : ap.showTimeTable(); break;
				case 4 : run = su.stopUse(); break;
				default : System.out.println("다시 선택하세요");
				}
			}catch (Exception e) {
				System.out.println("\n잘못된 입력입니다.");
			}
			}
		
	}

}
