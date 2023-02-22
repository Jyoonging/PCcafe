package pcCafe.admin.stock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import pcCafe.admin.AdminMain;
import pcCafe.main.JdbcTemplate;


public class StockLog {
	
	
	
	
	public void showLog() {
		try {
			Connection conn;
			conn = JdbcTemplate.getConnection();
			String sql = "SELECT P.P_NUM , P_NAME, STOCK_NUM,ADMIN_NAME,STOCK_DATE,STOCK_QTY FROM PRODUCT_LIST P JOIN STOCK_MANAGEMENT S ON P.P_NUM=S.P_NUM JOIN ADMIN A ON S.ADMIN_NUM = A.ADMIN_NUM ORDER BY STOCK_DATE";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			System.out.println("=====================================================");
			System.out.println("=================수량 추가, 상품 추가 기록================");
			System.out.println("=====================================================");
			System.out.println("| 로그번호 | 관리자 | 상품 입고 발생 시간 | 변동수량 | 상품이름 |");
			System.out.println();
			
			while(rs.next()) {
				int lognum = rs.getInt("STOCK_NUM");
				String adname = rs.getString("ADMIN_NAME");
				String stockDate = rs.getString("STOCK_DATE");
				int stockQty = rs.getInt("STOCK_QTY");
				String stockName = rs.getString("P_NAME");
				System.out.print(lognum);
				System.out.print(" | ");
				System.out.print(adname);
				System.out.print(" | ");
				System.out.print(stockDate);
				System.out.print(" | ");
				System.out.print(stockQty);
				System.out.print(" | ");
				System.out.print(stockName);
				System.out.println();
			}
			
			//커넥션정리
			conn.close();
		} catch (Exception e) {
			AdminMain.Exception();
		}
		
		
		
		
	}

//	public  void log(int qty) {
//		
//		try {
//			System.out.println();
//			Connection conn = JdbcTemplate.getConnection();
//			String sql = "INSERT INTO STOCK_MANAGEMENT(STOCK_NUM,STOCK_DATE,ADMIN_NUM,STOCK_QTY) VALUES(STOCK_MANAGEMENT_SEQ.NEXTVAL,SYSDATE,?,?)";
//			PreparedStatement pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, AdminMain.adminNum);
//			pstmt.setInt(2, qty);
//			int result = pstmt.executeUpdate();
//			
//			if(result == 1) {
//				System.out.println("처리 성공~");
//			}else {
//				System.out.println("처리 실패...");
//			}
//			
//			conn.close();
//			
//		} catch (Exception e) {
//			AdminMain.Exception();
//		}
//	}
}
