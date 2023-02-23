package pcCafe.admin.member;

import pcCafe.main.Main;

public class MemberManager {
	public boolean showMenu(){
		MemberService ms = new MemberService();
		boolean run = true;
		while(run) {
			System.out.println();
			System.out.println("====================================  회원 관리 >   ====================================================");
			System.out.println("    ┌──────────────────┐  ┌────────────────┐  ┌───────────────────┐     ┌────────────┐                           ");
			System.out.println("    │ 1. 모든 회원정보 조회 │  │ 2. 비밀번호 초기화  │  │ 3. 회원 휴먼계정 전환  │      │ 9. 뒤로 가기 │                           ");
			System.out.println("    └──────────────────┘  └────────────────┘  └───────────────────┘     └────────────┘                          ");
			System.out.println("=====================================================================================================");
			System.out.print("메뉴를 선택하세요. > ");
			String num = Main.SC.nextLine().trim();
			if (num.equals("1")) {
				ms.showMemberInfo(); 
			}else if (num.equals("2")) {
				ms.resetPwd();
			}else if (num.equals("3")) {
				ms.out();
			}else if (num.equals("9")) {
				run = false;
			}else {
				System.out.println("==================================");
				System.out.println("||      올바른 번호를 입력하세요      ||");
				System.out.println("==================================");;
			}
		}
		return true;
		
	}
	

}
