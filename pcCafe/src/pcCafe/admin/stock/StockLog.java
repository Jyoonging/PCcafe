package pcCafe.admin.stock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import pcCafe.admin.AdminMain;
import pcCafe.main.JdbcTemplate;


public class StockLog {
	
	
	
	public static void log(int qty) {
		try {
			System.out.println();
			Connection conn = JdbcTemplate.getConnection();
			String sql = "INSERT INTO STOCK_MANAGEMENT(STOCK_NUM,STOCK_DATE,ADMIN_NUM,STOCK_QTY) VALUES(STOCK_MANAGEMENT_SEQ.NEXTVAL,SYSDATE,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, AdminMain.adminNum);
			pstmt.setInt(2, qty);
			int result = pstmt.executeUpdate();
			
			if(result == 1) {
				System.out.println("처리 성공~");
			}else {
				System.out.println("처리 실패...");
			}
			
			conn.close();
			
		} catch (Exception e) {
			AdminMain.Exception();
		}
	}
	
	public void showLog() {
		try {
			Connection conn;
			conn = JdbcTemplate.getConnection();
			String sql = "SELECT * FROM STOCK_MANAGEMENT ORDER BY STOCK_DATE ";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			System.out.println("==================================");
			System.out.println("=======수량 추가, 상품 추가 기록=======");
			System.out.println("==================================");
			System.out.println("| NO | AD | T I M E | QTY | NAME |");
			System.out.println();
			
			while(rs.next()) {
				String lognum = rs.getString("STOCK_NUM");
				String adnum = rs.getString("ADMIN_NUM");
				String stockDate = rs.getString("STOCK_DATE");
				String stockQty = rs.getString("STOCK_QTY");
				int pCode = rs.getInt("P_NUM");
				System.out.print(lognum);
				System.out.print(" | ");
				System.out.print(adnum);
				System.out.print(" | ");
				System.out.print(stockDate);
				System.out.print(" | ");
				System.out.print(stockQty);
				System.out.print(" | ");
				System.out.print(pCode);
				System.out.println();
			}
			
			//커넥션정리
			conn.close();
		} catch (Exception e) {
			AdminMain.Exception();
		}
		
		
		
		
	}

}
