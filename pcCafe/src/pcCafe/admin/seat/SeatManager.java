package pcCafe.admin.seat;

import pcCafe.main.Main;

public class SeatManager {
	
	public boolean serviceMenu() {
		SeatService seats = new SeatService();
		boolean run = true;
		while(run) {
			System.out.println("==================================");
			System.out.println("=========좌석 관리 번호를 선택=========");
			System.out.println("|| 1. 모든 좌석 보기, 2.좌석 상태 변경 ||");
			System.out.println("|| 3. 신규 좌석 추가,    9. 뒤로가기  ||");
			System.out.println("==================================");
			System.out.print("   :  "   );
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
				System.out.println("==================================");
				System.out.println("||      올바른 번호를 입력하세요      ||");
				System.out.println("==================================");
			}
			
		} return true;
	}

}
	

