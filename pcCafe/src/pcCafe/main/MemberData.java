package pcCafe.main;

public class MemberData {

		private static final int MINIMUM_ID_LENGTH = 4;

		private static final int MINIMUM_PWD_LENGTH = 4;
		private static final int MAXIMUM_PWD_LENGTH = 12;

		private  static final int BIRTHDAY_LENGTH = 6;

		private  static final int PHONE_LENGTH = 11;




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
			if(!isValidUserId(userId)){
				System.out.println("아이디 입력조건을 다시 확인해주세요");
				return;
			}
	        this.userId = userId;
	    }

	    public String getUserPwd() {
	        return userPwd;
	    }

	public void setUserPwd(String userPwd) {
		if(!isValidUserPwd(userPwd)){
			System.out.println("비밀번호 입력조건을 다시 확인해주세요");
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
			if(!isValidUserBirth(userBirth)){
				System.out.println("생년월일 입력 조건을 다시확인해주세요");
				return;
			}
	        this.userBirth = userBirth;
	    }

	    public String getUserPhone() {
	        return userPhone;
	    }

	    public void setUserPhone(String userPhone) {
			if(!isValidUserPhone(userPhone)){
				System.out.println("핸드폰번호 입력조건을 다시 확인해주세요");
			}
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
	        setUserBirth(userBirth);
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


	public boolean isValidUserId(String userId) {
		return userId != null && userId.length() > MINIMUM_ID_LENGTH;
	}

	public boolean isValidUserPwd(String userPwd) {
		return userPwd != null && userPwd.length() >= MINIMUM_PWD_LENGTH && userPwd.length() <= MAXIMUM_PWD_LENGTH;
	}

	public boolean isValidUserBirth(String userBirth) {
		return userBirth != null && userBirth.length() == BIRTHDAY_LENGTH;
	}

	public boolean isValidUserPhone(String userPhone) {
		return userPhone != null && userPhone.length() <= PHONE_LENGTH;
	}


	}



