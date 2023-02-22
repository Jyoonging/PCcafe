package pcCafe.admin.seat;

import pcCafe.main.Main;

public class SeatManager {
	
	public boolean serviceMenu() {
		SeatService seats = new SeatService();
		boolean run = true;
		while(run) {
			System.out.println();
			System.out.println("====================================  관리자 메뉴 >   ====================================================");
			System.out.println("                                                                                       "); 
			System.out.println("    ┌────────────────┐  ┌──────────────┐  ┌─────────────┐    ┌─────────────┐                                   ");
			System.out.println("    │ 1. 모든 좌석 조회  │  │ 2. 상품 관리    │  │ 3. 좌석 관리  │    │ 9. 뒤로 가기   │                                       ");
			System.out.println("    └────────────────┘  └──────────────┘  └─────────────┘    └─────────────┘                                 ");
			System.out.println("=====================================================================================================");
			System.out.print("메뉴를 선택하세요. > ");
			System.out.print("  : ");
			String select = Main.SC.nextLine().trim();
			if(select.equals("1")) {
				seats.showSeat();
			}else if(select.equals("2")) {
				seats.editSeat();			
			}else if(select.equals("3")) {
				seats.addSeat();
			}else if(select.equals("9")) {
				run = false;
			}
			else {
				System.out.println("");
				System.out.println("==================================");
				System.out.println("||      올바른 번호를 입력하세요      ||");
				System.out.println("==================================");
			}
			
		} return true;
	}

}
	

