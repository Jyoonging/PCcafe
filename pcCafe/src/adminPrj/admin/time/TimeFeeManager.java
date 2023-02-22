package adminPrj.admin.time;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import pcCafe.admin.AdminMain;
import pcCafe.main.JdbcTemplate;
import pcCafe.main.Main;


public class TimeFeeManager {
	
	public boolean showTimeFee() {
		TimeFeeService tfs = new TimeFeeService();
		boolean run = true;
		while(run) {
			try {
				Connection conn = JdbcTemplate.getConnection();
				String sql = "SELECT T_NUM, T_PRICE, T_TIME FROM TIME ORDER BY T_NUM";
				PreparedStatement pstmt = conn.prepareStatement(sql);;
				ResultSet rs = pstmt.executeQuery();
				
				System.out.println("================================");
				System.out.println("==============목 록==============");
				System.out.println("||  번호  ||   가격   ||  시간  ||");
				System.out.println();
				while(rs.next()) {
					int no= rs.getInt("T_NUM");
					int price = rs.getInt("T_PRICE");
					int time = rs.getInt("T_TIME");
					System.out.printf("[%-8s]" , no);
					System.out.printf("[%-10s]" , price+"원");
					System.out.printf("[%-8s]" , time+"분");
					System.out.println();
				}
				System.out.println("================================");
				System.out.println("===============시간권=============");
				System.out.println("1. 수정   ,   2. 추가    ,  9. 뒤로");
				System.out.print(":  "   );
				String select = Main.SC.nextLine().trim();
				if(select.equals("1")) {
					tfs.edit();
				}else if(select.equals("2")) {
					tfs.add();			
				}else if(select.equals("9")) {
					run = false;
				}
				else {
					 System.out.println("올바른 번호를 입력하세요!!"); 
				}
				//커넥션정리
				conn.close();
				
			} catch (Exception e) {
				AdminMain.Exception();
			}
		}
		return true;
	}

}
