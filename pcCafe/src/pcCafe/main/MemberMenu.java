package pcCafe.main;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import pcCafe.admin.AdminMain;
import pcCafe.useStatus.ServiceManager;

public class MemberMenu {
	
		public static int memberNum;
	    MemberController ds = new MemberController();
	    public boolean startService() throws Exception{
	        //선택지 보여주기
	        displayMenu();

	        //입력받기
	        String input = Main.SC.nextLine();

	        if("9".equals(input)){
	            return true;
	        }else {
	        	processService(input);
	        	
	        	return false;
	        }
	        //선택지에 따라 로직 실행
	    }

	    private void processService(String input) throws Exception {
	    	ServiceManager sm = new ServiceManager();
	    	AdminMain am = new AdminMain();
	    	if("1".equals(input)) {
	    		ds.join();
	    	}else if("2".equals(input)) {
	    		memberNum = ds.login();
	    		
    			if(memberNum != 0) {
    				sm.showMenu(); 
    			}else {
    				AdminMain.Exception();
    			}
	    	}else if("3".equals(input)) {
	    		findMemberId();
	    	}else if ("4".equals(input)) {
	    		am.adminmain();
	        }else {
	        	System.out.println("잘못 입력하셨습니다");
	        }

	    }

	    public void displayMenu(){
	    	System.out.println("                  __  ____  ____  ______    _   __   ____  __________  ___    _   ________ ");
			System.out.println("                 / / / / / / /  |/  /   |  / | / /  / __ \\/ ____/ __ )/   |  / | / / ____/");
			System.out.println("                / /_/ / / / / /|_/ / /| | /  |/ /  / /_/ / /   / __  / /| | /  |/ / / __   ");
			System.out.println("               / __  / /_/ / /  / / ___ |/ /|  /  / ____/ /___/ /_/ / ___ |/ /|  / /_/ /   ");
			System.out.println("              /_/ /_/\\____/_/  /_/_/  |_/_/ |_/  /_/    \\____/_____/_/  |_/_/ |_/\\____/ ");
			System.out.println("                                                                                           ");
			System.out.println("=====================================================================================================");
			System.out.println("                                휴먼피시방에 오신 것을 환영합니다.                                   ");
	        System.out.println("1. 회원가입");
	        System.out.println("2. 로그인");
	        System.out.println("3. 아이디찾기");
	        System.out.println("4. 관리자 모드");
	        System.out.println("9. 종료");
	    }
	    
	    //멤버아이디 찾기 메소드
	    public void findMemberId(){
	    	try{
	    		//생년월일, 이름 받기
	    		System.out.print("이름 : ");
	    		String inputName =  Main.SC.nextLine().trim();
	    		System.out.print("생년월일 : ");
	    		String inputBirth = Main.SC.nextLine().trim();
	    		
	    		Connection conn = JdbcTemplate.getConnection();
	    		String sql = "SELECT MEM_ID FROM MEMBER WHERE MEM_NAME =? AND MEM_BIRTH = ?";
	    		PreparedStatement pstmt = conn.prepareStatement(sql);
	    		pstmt.setString(1, inputName);
	    		pstmt.setString(2, inputBirth);
	    		ResultSet rs = pstmt.executeQuery();
	    		rs.next();
	    		String memberId = rs.getString("MEM_ID");
	    		System.out.println(inputName +"님의 아이디는 "+memberId +" 입니다.");
	    		
	    	}catch(Exception e) {
	    		System.out.println("일치하는 정보의 회원이 없습니다.");
	    	}
	    	

	    }

	}



