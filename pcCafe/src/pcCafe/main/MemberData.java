package pcCafe.main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MemberData {

	private static final int MINIMUM_ID_LENGTH = 4;

	private static final int MINIMUM_PWD_LENGTH = 4;
	private static final int MAXIMUM_PWD_LENGTH = 12;

	private static final int BIRTHDAY_LENGTH = 6;

	private static final int PHONE_LENGTH = 11;


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
		this.userId = userId;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
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
		if (!isValidUserPhone(userPhone)) {
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
		this.userId = userId;
		this.userPwd = userPwd;
		this.userName = userName;
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


	public String isValidUserId(String userId) {
		List<String> errorMessages = new ArrayList<>();
		if (userId == null || userId.length() <= MINIMUM_ID_LENGTH) {
			errorMessages.add("아이디 입력조건을 확인해주세요");
		}

		try (
				Connection conn = JdbcTemplate.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("SELECT COUNT(*) FROM MEMBER WHERE MEM_ID=?")) {
			pstmt.setString(1, userId);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			int count = rs.getInt(1);
			if (count > 0) {
				errorMessages.add("이 아이디는 이미 존재합니다.");
			}
		} catch (Exception e) {
			System.out.println("바보");
		}

		if (errorMessages.isEmpty()) {
			return null;
		} else {
			return String.join(" and ", errorMessages);
		}
	}


	public String isValidUserPwd(String userPwd) {
		if (userPwd == null || userPwd.length() < MINIMUM_PWD_LENGTH || userPwd.length() > MAXIMUM_PWD_LENGTH) {
			return "비밀번호 입력조건을 다시 확인해주세요.";
		}
		return null;
	}

	public String isValidUserBirth(String userBirth) {
		if (userBirth == null || userBirth.length() != BIRTHDAY_LENGTH || !userBirth.matches("[0-9]+")) {
			return "생년월일 입력조건을 다시 확인해주세요.";
		}
		return null;
	}


	public boolean isValidUserPhone(String userPhone) {
		return userPhone != null && userPhone.matches("[0-9]+") && userPhone.length() <= PHONE_LENGTH;
	}

	public String isValidUserName(String userName) {
		if (userName == null || userName.trim().length() == 0 || userName.contains(" ")) {
			return "이름을 입력해주세요 공백은 안돼요~";
		}
		return null;
	}

	public String isValid() {
		List<String> errorMessages = new ArrayList<>();

		String userIdErrorMessage = isValidUserId(userId);
		if (userIdErrorMessage != null) {
			errorMessages.add(userIdErrorMessage);
		}

		String userPwdErrorMessage = isValidUserPwd(userPwd);
		if(userPwdErrorMessage != null) {
			errorMessages.add(userPwdErrorMessage);
		}

		String userBirthErrorMessage = isValidUserBirth(userBirth);
		if(userBirthErrorMessage != null) {
			errorMessages.add(userBirthErrorMessage);
		}

		String userNameErroMessage = isValidUserName(userName);
		if(userNameErroMessage != null) {
			errorMessages.add(userNameErroMessage);
		}

		if (errorMessages.isEmpty()){
			return null;
		} else {
			return String.join(" and ",errorMessages );
		}

	}

}
