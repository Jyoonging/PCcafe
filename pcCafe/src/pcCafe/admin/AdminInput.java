package pcCafe.admin;

import pcCafe.main.Main;

public class AdminInput {
	
	public AdminData login() {
		System.out.print("관리자 아이디 입력   : ");
		String adminId = Main.SC.nextLine().trim();
		System.out.print("관리자 비밀번호 입력 : ");
		String adminPwd = Main.SC.nextLine().trim();
		
		AdminData data = new AdminData();
		data.setAdminId(adminId);
		data.setAdminPwd(adminPwd);
		return data;
	}
		
}