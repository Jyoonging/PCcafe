package pcCafe.main;

import java.sql.Connection;
import java.util.Scanner;

public class Main {

	public static final Scanner SC = new Scanner(System.in);
	public static String LoginMemberNick;
	
	public static void main(String[] args) {
		System.out.println("main");
		
		Connection conn = JdbcTemplate.getConnection();

        MemberMenu mm = new MemberMenu();
        while(true){
            boolean isFinish;
			try {
				isFinish = mm.startService();
				 if(isFinish){break;}
			} catch (Exception e) {
				
				e.printStackTrace();
			}
           
        }
        System.out.println("===== 프로그램 종료 =====");
		
		
	}

}
