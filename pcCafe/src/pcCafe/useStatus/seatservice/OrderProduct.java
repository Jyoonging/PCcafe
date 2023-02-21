package pcCafe.useStatus.seatservice;


import pcCafe.product.PurchaseProduct;
import pcCafe.useStatus.SeatServiceManager;

public class OrderProduct {
	
	public void orderFood() {
		
		
		PurchaseProduct pp = new PurchaseProduct();
		
//상품 보여주기
		try {
			pp.showProduct();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//주문 번호 생성
		try {
			pp.createOrder();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//장바구니(주문내역)
		try {
			pp.addCart();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//추가 구매
		try {
			pp.addPurchase();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//결제
		pp.payProduct();
		
		//결제종료
		try {
			pp.finishPay();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("상품을 준비하는 중입니다......");
		
		
		
		}

}
