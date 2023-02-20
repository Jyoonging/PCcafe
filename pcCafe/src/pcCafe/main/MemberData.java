package pcCafe.main;

public class MemberData {
	
	    public static MemberData createMember(ResultSet rs) throws SQLException {
	        int number = rs.getInt("MEMBER");
	        String id = rs.getString("MEM_ID");
	        String pwd = rs.getString("MEM_PWD");
	        String name = rs.getString("MEM_NAME");
	        String birth = rs.getString("MEM_BIRTH");
	        String phone = rs.getString("MEM_PHONE");
	        String quitYn = rs.getString("QUIT_YN");
	        int memTime = rs.getInt("MEM_TIME");

	        MemberData member = new MemberData(number,id,pwd,name,birth,phone,quitYn,memTime);
	        return member;
	    }

	    public static ArrayList<MemberData> findMember(String keyword) throws  SQLException{
	        ArrayList<MemberData> memberList = new ArrayList<>();

	        // Search for members that match the keyword
	        ResultSet rs = null;
	        PreparedStatement pstmt = null;
	        Connection conn = JdbcTemplate.getConnection();

	        try {
	            pstmt = conn.prepareStatement("SELECT * FROM MEMBER WHERE MEM_ID LIKE ?");
	            pstmt.setString(1, "%" + keyword + "%");
	            rs= pstmt.executeQuery();

	            while(rs.next()){
	                MemberData member = createMember(rs);
	                memberList.add(member);
	            }
	        }finally {
	            JdbcTemplate.close(rs);
	            JdbcTemplate.close(pstmt);
	            JdbcTemplate.close(conn);
	        }

	        return memberList;
	    }


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



