package pcCafe.main;

import java.util.Scanner;

public class MemberView {
	
	Scanner sc = new Scanner(System.in);
	
	    public String getInput(String prompt){
	        System.out.print(prompt + ":");
	        return sc.nextLine();
	    }

	    public String showMessage(String message) {
	        System.out.println(message);
	        return sc.nextLine();
	    }

	    public  void showError(String error) {
	        System.err.println(error);
	    }

}
