package pcCafe.main;

public class MemberView {
	

	    public String getInput(String prompt){
	        System.out.print(prompt + ":");
	        return ScannerUtil.SC.nextLine();
	    }

	    public String showMessage(String message) {
	        System.out.println(message);
	        return ScannerUtil.SC.nextLine();
	    }

	    public  void showError(String error) {
	        System.err.println(error);
	    }

}
