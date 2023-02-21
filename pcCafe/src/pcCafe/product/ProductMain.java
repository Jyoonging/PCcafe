package pcCafe.product;

import java.sql.Connection;
import java.util.Scanner;

import pcCafe.main.JdbcTemplate;

public class ProductMain {
	public void prooduct() {
		
		JdbcTemplate j = new JdbcTemplate();
		Connection conn = j.getConnection();
		Scanner sc = new Scanner(System.in);
		PurchaseProduct pp = new PurchaseProduct();
		
		System.out.println("=====상품주문=====");
		
		//상품 보여주기
		try {
			pp.showProduct();
			conn.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//주문 번호 생성
		try {
			pp.createOrder();
			conn.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//장바구니(주문내역)
		try {
			pp.addCart();
			conn.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//추가 구매
		try {
			pp.addPurchase();
			conn.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//결제
		pp.payProduct();
		
		//결제종료
		try {
			pp.finishPay();
			conn.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("상품을 준비하는 중입니다......");
		
		//자원 정리
		
		System.out.println("프로그램 종료");
		
	}
	
}
	


