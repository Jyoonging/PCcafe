package pcCafe.admin.member;

import pcCafe.main.Main;
import pcCafe.main.MemberData;

public class MemberEdit {
	//리셋 비밀번호 회원정보입력
		public MemberData resetPwd() {
			System.out.print("회원 이름 : ");
			String name = Main.SC.nextLine().trim();
			System.out.print("생년월일 : ");
			String birth = Main.SC.nextLine().trim();
			MemberData md = new MemberData();
			md.setUserName(name);
			md.setUserBirth(birth);
			return md;
		}
		//탈퇴할 회원정보 입력
		public MemberData out() {
			System.out.print("회원 ID : ");
			String id = Main.SC.nextLine().trim();
			System.out.print("회원 PWD : ");			
			String pwd = Main.SC.nextLine().trim();
			System.out.print("회원 생년월일 : ");
			String birth = Main.SC.nextLine().trim();
			MemberData md = new MemberData();
			md.setUserId(id);
			md.setUserPwd(pwd);
			md.setUserBirth(birth);
			return md;
					
				
		}
	}
