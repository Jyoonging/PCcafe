package pcCafe.useStatus.seatservice;


import pcCafe.product.PurchaseProduct;
import pcCafe.useStatus.SeatServiceManager;

public class OrderProduct {
	
	public int orderFood() {
		
		PurchaseProduct pp = new PurchaseProduct();
		int orderGo = 1;
		
		System.out.println("=====상품주문=====");
		
		//상품 보여주기
		try {
			pp.showProduct();
		} catch (Exception e) {
			System.out.println("올바른 값이 아닙니다.");
		}
		
		//orderGo 0이면 이전화면, 1이면 주문 진행
		int orderStart = pp.orderGo();
		if(orderStart == 0) {
			return 0;
		}else if(orderStart == 1) {
			
			//주문 번호 생성
			try {
				pp.createOrder();
			} catch (Exception e) {
				System.out.println("올바른 값이 아닙니다.");
			}
			
			//장바구니(주문내역)
			try {
				pp.addCart();
			} catch (Exception e) {
				System.out.println("올바른 값이 아닙니다.");
			}
			
			//추가 구매
			try {
				pp.addPurchase();
			} catch (Exception e) {
				System.out.println("올바른 값이 아닙니다.");
			}
			
			//결제
			try {
				pp.payProduct();
			} catch (Exception e) {
				System.out.println("올바른 값이 아닙니다.");
			}
			
			//결제종료
			try {
				pp.finishPay();
			} catch (Exception e) {
				System.out.println("올바른 값이 아닙니다.");
			}
		}
		return 1;
	}

}
