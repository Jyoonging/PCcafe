package pcCafe.product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws Exception {
		
		JdbcTemplate j = new JdbcTemplate();
		Connection conn = j.getConnection();
		Scanner sc = new Scanner(System.in);
		PurchaseProduct pp = new PurchaseProduct();
		
		System.out.println("=====상품주문=====");
		
		//상품 보여주기
		pp.showProduct();
		
		//주문 번호 생성
		pp.createOrder();

		//장바구니(주문내역)
		pp.addCart();
		
		//추가 구매
		pp.addPurchase();
		
		//결제
		pp.payProduct();
		
		//결제종료
		pp.finishPay();
		System.out.println("상품을 준비하는 중입니다......");
		
		//자원 정리
		conn.close();
		
		System.out.println("프로그램 종료");
		
	}
	
}
	
