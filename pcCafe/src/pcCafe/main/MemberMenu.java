package pcCafe.main;


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
	    	}else if ("3".equals(input)) {
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
			System.out.println("              /_/ /_/\\____/_/  /_/_/  |_/_/ |_/ /_/    \\____/_____/_/  |_/_/ |_/\\____/ ");
			System.out.println("                                                                                           ");
			System.out.println("=====================================================================================================");
			System.out.println("                                휴먼피시방에 오신 것을 환영합니다.                                   ");
	        System.out.println("1. 회원가입");
	        System.out.println("2. 로그인");
	        System.out.println("3. 관리자 모드");
	        System.out.println("9. 종료");
	    }


	}



