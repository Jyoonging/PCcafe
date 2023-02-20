package pcCafe.main;

import java.sql.Connection;
import java.util.Scanner;

import pcCafe.useStatus.ServiceManager;

public class Main {

	public static final Scanner SC = new Scanner(System.in);
	public static String LoginMemberNick;
	
	public static void main(String[] args) {
		
		Connection conn = JdbcTemplate.getConnection();

        MemberMenu mm = new MemberMenu();
        while(true){
            boolean isFinish;
			try {
				isFinish = mm.startService();
				 if(isFinish){break;}
			} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
			}
        }
        System.out.println("===== 프로그램 종료 =====");
        
        
		
	}

}
