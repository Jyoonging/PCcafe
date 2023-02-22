package pcCafe.admin.time;

import java.sql.Connection;
import java.sql.PreparedStatement;

import pcCafe.admin.AdminMain;
import pcCafe.main.JdbcTemplate;

public class TimeFeeService {
	private final TimeFeeInput TFI = new TimeFeeInput();
	public void add() {
		
		try {
			TimeData td = TFI.add();
			Connection conn = JdbcTemplate.getConnection();
			String sql = " INSERT INTO TIME VALUES (T_NUM.NEXTVAL, ?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, td.getTimePrice());
			pstmt.setInt(2, td.getTimeAddMin());
			int result = pstmt.executeUpdate();
			//결과에 따른 처리
			if(result == 1) {
				System.out.println("추가 성공!");

			}else {
				System.out.println("올바른 값을 입력하세요!!"); 
			}
			
			conn.close();
			
		}catch(Exception e) {
			AdminMain.Exception();
		}
		
	}
	public void edit() {
		try {
			TimeData td = TFI.edit();
			Connection conn = JdbcTemplate.getConnection();
			String sql = "UPDATE TIME SET T_TIME = ?, T_PRICE = ?  WHERE T_NUM = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, td.getTimeAddMin());
			pstmt.setInt(2, td.getTimePrice());
			pstmt.setInt(3, td.getFeeNum());
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
	
}
