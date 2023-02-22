package pcCafe.main;

public class MemberData {

		private static final int MINIMUM_ID_LENGTH = 5;
		private static final int MAXIMUM_ID_LENGTH = 12;
		private static final int MINIMUM_PASSWORD_LENGTH = 4;

	    public int getUserNum() {
	        return userNum;
	    }

	    public void setUserNum(int userNum) {
	        this.userNum = userNum;
	    }

	    public String getUserId() {
	        return userId;
	    }

	    public void setUserId(String userId) {
			if((userId.length() <= MINIMUM_ID_LENGTH) || (userId.length() > MAXIMUM_ID_LENGTH)){
				System.out.println("아이디 입력조건을 다시 확인하세요 ");
				return;
			}
	        this.userId = userId;
	    }

	    public String getUserPwd() {
	        return userPwd;
	    }

	public void setUserPwd(String userPwd) {
		String pwdPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,16}$";
		if (!userPwd.matches(pwdPattern)) {
			System.out.println("비밀번호 입력조건을 다시 확인하세요");
			return;
		}
		this.userPwd = userPwd;
	}


	public String getUserName() {
	        return userName;
	    }

	    public void setUserName(String userName) {
	        this.userName = userName;
	    }

	    public String getUserBirth() {
	        return userBirth;
	    }

	    public void setUserBirth(String userBirth) {
	        this.userBirth = userBirth;
	    }

	    public String getUserPhone() {
	        return userPhone;
	    }

	    public void setUserPhone(String userPhone) {
	        this.userPhone = userPhone;
	    }

	    public String getQuit_yn() {
	        return quit_yn;
	    }

	    public void setQuit_yn(String quit_yn) {
	        this.quit_yn = quit_yn;
	    }

	    public int getMemTime() {
	        return memTime;
	    }

	    public void setMemTime(int memTime) {
	        this.memTime = memTime;
	    }

	    public MemberData() {
	    }

	    public MemberData(int userNum, String userId, String userPwd, String userName, String userBirth, String userPhone, String quit_yn, int memTime) {
	        this.userNum = userNum;
	        setUserId(userId);
	        setUserPwd(userPwd);
	        this.userName = userName;
	        this.userBirth = userBirth;
	        this.userPhone = userPhone;
	        this.quit_yn = quit_yn;
	        this.memTime = memTime;
	    }

	    public MemberData(String userId, String userPwd, String userName, String userBirth, String userPhone) {
	        this.userId = userId;
	        this.userPwd = userPwd;
	        this.userName = userName;
	        this.userBirth = userBirth;
	        this.userPhone = userPhone;
	    }

	    @Override
	    public String toString() {
	        return "MemberModel{" +
	                "userNum=" + userNum +
	                ", userId='" + userId + '\'' +
	                ", userPwd='" + userPwd + '\'' +
	                ", userName='" + userName + '\'' +
	                ", userBirth='" + userBirth + '\'' +
	                ", userPhone='" + userPhone + '\'' +
	                ", quit_yn='" + quit_yn + '\'' +
	                ", memTime=" + memTime +
	                '}';
	    }

	    private int userNum;
	    private String userId;
	    private String userPwd;
	    private String userName;
	    private String userBirth;
	    private String userPhone;
	    private String quit_yn;

	    private int memTime;


	}



