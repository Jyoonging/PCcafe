package pcCafe.admin.stock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import pcCafe.admin.AdminMain;
import pcCafe.admin.product.ProductData;
import pcCafe.main.JdbcTemplate;



public class StockService {
	private final StockInput SI = new StockInput();
	public void showProduct(){
		try {
			
			String sql = "SELECT P_NAME,P_QTY,P_PRICE FROM PRODUCT_LIST";
			Connection conn = JdbcTemplate.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			//결과집합에서 데이터 꺼내기 
			System.out.println("==================================");
			System.out.println("=============상 품 목 록============");
			System.out.println("|| 상품 이름  상품 재고 수량  상품 가격||");
			System.out.println("==================================");
			while(rs.next()) {
				String name= rs.getString("P_NAME");
				int qty = rs.getInt("P_QTY");
				int price = rs.getInt("P_PRICE");
				System.out.printf("|| ");
				System.out.printf("[%-10s]" , name);
				System.out.printf(" ");
				System.out.printf("[%-4s]" , qty);
				System.out.printf(" ");
				System.out.printf("[%-6s]" , price);
				System.out.println();
			}
			System.out.println("==================================");
			
			//커넥션정리
			conn.close();
		}catch(Exception e) {
			AdminMain.Exception();
		}
	}
	
	public void volume() {
		try {
			ProductData pd =  SI.volume();
			Connection conn = JdbcTemplate.getConnection();
			String sql = "UPDATE PRODUCT_LIST SET P_QTY = P_QTY + ?  WHERE P_NAME = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,pd.getpQty());
			pstmt.setString(2, pd.getpName());
			int result = pstmt.executeUpdate();
			if(result == 1) {
				StockLog.log(pd.getpQty());//로그저장
			}else {
				System.out.println("상품 이름을 확인하세요");
			}
			conn.close();
		}catch(Exception e) {
			AdminMain.Exception();
		}
			
			
		
	}
	
	public void price() {
		try {
			ProductData pd = SI.price();
			Connection conn = JdbcTemplate.getConnection();
			String sql = "UPDATE PRODUCT_LIST SET P_PRICE = ?  WHERE P_NAME = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pd.getpPrice());
			pstmt.setString(2, pd.getpName());
			int result = pstmt.executeUpdate();
			if(result == 1) {
				System.out.println("변경 성공!");
			}else {
				System.out.println("상품 이름, 가격을 확인하세요");
			}
			
			conn.close();
		}catch(Exception e) {
			AdminMain.Exception();
		}
	}
	
	public void add() {
		try {
			ProductData pd = SI.add();
			Connection conn = JdbcTemplate.getConnection();
			String sql = " INSERT INTO PRODUCT_LIST VALUES (P_NUM_SEQ.NEXTVAL, ?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pd.getpName());
			pstmt.setInt(2, pd.getpPrice());
			pstmt.setInt(3, pd.getpQty());
			int result = pstmt.executeUpdate();
			//결과에 따른 처리
			if(result == 1) {
				System.out.println("추가 성공!");
				StockLog.log(pd.getpQty());

			}else {
				System.out.println("작성하신 정보를 확인하세요");
			}
			
			conn.close();
			
		}catch(Exception e) {
		AdminMain.Exception();
		}
	}

}
