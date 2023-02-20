package pcCafej.admin.stock;

import pcCafe.admin.AdminService;
import pcCafe.main.Main;

public class StockManager {
	AdminService as = new AdminService();
	StockLog sl = new StockLog();
	
	
	public boolean serviceMenu() {
		StockService ss = new StockService();
		boolean run = true;
		while(run) {
			System.out.println();
			System.out.println("==================================");
			System.out.println("=========상품 관리 번호를 선택=========");
			System.out.println("|| 1.목록 보기, 2.수량 추가, 3.가격변경||");
			System.out.println("|| 4.상품 추가, 5.로그 보기, 9.뒤로가기||");
			System.out.println("==================================");
			System.out.print(" :  "   );
			String select = Main.SC.nextLine().trim();
			
			if(select.equals("1")) {
				ss.showProduct();
			}else if(select.equals("2")) {
				ss.volume();			
			}else if(select.equals("3")) {
				ss.price();
			}else if(select.equals("4")) {
				ss.add();
			}else if(select.equals("5")){
				sl.showLog();
				
			}else if(select.equals("9")) {
				run = false;
			}else {
				System.out.println("올바른 번호를 입력하세요!!"); 
			}
		}return true;
	}

}
