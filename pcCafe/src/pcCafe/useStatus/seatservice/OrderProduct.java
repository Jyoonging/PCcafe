package pcCafe.useStatus.seatservice;

import java.sql.Connection;

import pcCafe.main.JdbcTemplate;
import pcCafe.product.PurchaseProduct;

public class OrderProduct {
	
	public void orderFood() {
		
		System.out.println("지웅님 파트 끌고오기~~");
		
		JdbcTemplate j = new JdbcTemplate();
		Connection conn = j.getConnection();
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
