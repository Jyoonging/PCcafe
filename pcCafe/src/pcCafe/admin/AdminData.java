package pcCafe.admin;

public class AdminData {
	private int AdminNum;
	private String AdminId;
	private String AdminPwd;
	private String AdminName;
	public int getAdminNum() {
		return AdminNum;
	}
	public void setAdminNum(int adminNum) {
		AdminNum = adminNum;
	}
	public String getAdminId() {
		return AdminId;
	}
	public void setAdminId(String adminId) {
		AdminId = adminId;
	}
	public String getAdminPwd() {
		return AdminPwd;
	}
	public void setAdminPwd(String adminPwd) {
		AdminPwd = adminPwd;
	}
	public String getAdminName() {
		return AdminName;
	}
	public void setAdminName(String adminName) {
		AdminName = adminName;
	}
	public AdminData(int adminNum, String adminId, String adminPwd, String adminName) {
		super();
		AdminNum = adminNum;
		AdminId = adminId;
		AdminPwd = adminPwd;
		AdminName = adminName;
	}
	public AdminData() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "AdminData [AdminNum=" + AdminNum + ", AdminId=" + AdminId + ", AdminPwd=" + AdminPwd + ", AdminName="
				+ AdminName + "]";
	}
}

