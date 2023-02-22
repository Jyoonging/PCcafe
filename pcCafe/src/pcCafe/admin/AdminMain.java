package pcCafe.admin;

import java.util.Scanner;

public class AdminMain {
	//관리자 번호 변수 지정
	public static int adminNum;
	//스캐너
	public static final Scanner SC = new Scanner(System.in);
	
	
	public void adminmain() {
		System.out.println("==================================");
		System.out.println("     ┌──────────────────┐         ");
		System.out.println("     |   관리자  로그인     |         ");
		System.out.println("     └──────────────────┘        ");
		System.out.println("==================================");
		System.out.println();
		AdminService as = new AdminService();
		//로그인 & 관리자 번호 가져오기
		adminNum = as.adminLogin();
		
		//로그인 이후 서비스 시작
		if(adminNum != 0) {
			as.startService();
		}
		System.out.println("종료");
	}
	//예외 catch 출력문
	public static void Exception() {
		System.out.println("==================================");
		System.out.println("     ┌──────────────────┐         ");
		System.out.println("     |     잘못된 정보!    |         ");
		System.out.println("     └──────────────────┘        ");
		System.out.println("==================================");
	}

}
