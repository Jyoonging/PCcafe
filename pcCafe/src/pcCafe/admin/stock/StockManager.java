package pcCafe.admin.stock;

import pcCafe.admin.AdminService;
import pcCafe.main.Main;

public class StockManager {
	AdminService as = new AdminService();
	StockLog sl = new StockLog();
	
	
	public boolean serviceMenu() {
		StockService ss = new StockService();
		StockLog sl = new StockLog();
		boolean run = true;
		while(run) {
			System.out.println();
			System.out.println("====================================  관리자 메뉴 >   ====================================================");
			System.out.println("                                                                                       "); 
			System.out.println("    ┌────────────────┐  ┌────────────────┐  ┌────────────────┐                                           ");
			System.out.println("    │ 1. 상품 목록 조회  │  │ 2. 상품 수량 추가 │  │   3. 가격 변경   │                                                  ");
			System.out.println("    └────────────────┘  └────────────────┘  └────────────────┘                                           ");
			System.out.println("    ┌─────────────────┐  ┌──────────────┐  ┌──────────────┐                                           ");
			System.out.println("    │ 4.신규 상품  추가  │  │  5. 입고 내역  │  │   9. 뒤로 가기  │                                                  ");
			System.out.println("    └─────────────────┘  └──────────────┘  └──────────────┘                                           ");
			System.out.println("=====================================================================================================");
			System.out.print("메뉴를 선택하세요. > ");
			System.out.print("  : ");
			String select = Main.SC.nextLine().trim();
			
			if(select.equals("1")) {
				ss.showProduct();
			}else if(select.equals("2")) {
				ss.volume();			
			}else if(select.equals("3")) {
				ss.price();
			}else if(select.equals("4")) {
				ss.add();
			}else if(select.equals("5")) {
				sl.showLog();
			}else if(select.equals("9")) {
				run = false;
			}else {
				System.out.println("올바른 번호를 입력하세요!!"); 
			}
		}return true;
	}

}
