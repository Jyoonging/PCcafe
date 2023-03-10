package pcCafe.main;

	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;


	public class MemberController {


	    private MemberView view = new MemberView();
	    private MemberData md = new MemberData();

	    public void join() {
			String userId = "";
			String userPwd = "";
			String userName = "";
			String userBirthday = "";
			String userPhone = "";

			String errorMessage;

			do {
				userId = view.getInput("아이디(5자리 이상)");
				errorMessage = md.isValidUserId(userId);
				if(errorMessage != null){
					view.showError(errorMessage);
				}
			} while (errorMessage != null);


			do {
				userPwd = view.getInput("비밀번호(4~12자리)");
				errorMessage = md.isValidUserPwd(userPwd);
				if(errorMessage != null){
					view.showError(errorMessage);
				}
			} while (errorMessage != null);

			do {
				userName = view.getInput("이름");
				errorMessage = md.isValidUserName(userName);
				if(errorMessage != null){
					view.showError(errorMessage);
				}
			}while (errorMessage != null);

			do {
				userBirthday = view.getInput("생년월일(6자리)");
				errorMessage = md.isValidUserBirth(userBirthday);
				if(errorMessage != null) {
					view.showError(errorMessage);
				}
			}while (errorMessage != null);

			do {
				userPhone = view.getInput("전화번호(11자리 이하)");
				errorMessage = md.isValidUserPhone(userPhone) ? null : "전화번호 입력조건을 다시 확인해주세요";
				if(errorMessage != null){
					view.showError(errorMessage);
				}
			}while (errorMessage != null);


	            md.setUserId(userId);
	            md.setUserPwd(userPwd);
	            md.setUserName(userName);
	            md.setUserBirth(userBirthday);
	            md.setUserPhone(userPhone);


	           try{ //회원가입 인서트 sql문 실행
	            Connection conn = JdbcTemplate.getConnection();
	            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO MEMBER(MEM_NUM,MEM_ID,MEM_PWD,MEM_NAME,MEM_BIRTH,MEM_PHONE) VALUES (seq_member_no.nextval,?,?,?,?,?)");
	            pstmt.setString(1, md.getUserId());
	            pstmt.setString(2, md.getUserPwd());
	            pstmt.setString(3, md.getUserName());
	            pstmt.setString(4, md.getUserBirth());
	            pstmt.setString(5, md.getUserPhone());
	            pstmt.executeUpdate();

	            //sql 문 commit 및  종료
	            JdbcTemplate.commit(conn);
	            JdbcTemplate.close(pstmt);
	            JdbcTemplate.close(conn);

	            view.showMessage("회원가입 성공.");
	        } catch (SQLException e) {
	            JdbcTemplate.rollback(JdbcTemplate.getConnection());
				System.out.println("회원가입 실패");
	        }
	    }

	    public int login() {
	        try {
	            //사용자 입력 view에서 getinput 메소드 받아옴
	            String id = view.getInput("아이디");
	            String pwd = view.getInput("패스워드");

				md.setUserId(id);
				md.setUserPwd(pwd);


	            //sql문 select문 실행 !
	            Connection conn = JdbcTemplate.getConnection();
	            PreparedStatement pstmt = conn.prepareStatement("SELECT MEM_NUM, MEM_ID,MEM_PWD,MEM_NAME FROM MEMBER WHERE MEM_ID=? AND MEM_PWD =? AND QUIT_YN = 'N'");
	            pstmt.setString(1, md.getUserId());
	            pstmt.setString(2, md.getUserPwd());
	            ResultSet rs = pstmt.executeQuery();
	            
	            if (rs.next()) {
	                //로그인 성공
	                String name = rs.getString("MEM_NAME");
	                System.out.println(name + "님 환영합니다.");
	                Main.LoginMemberNick = name;
	                System.out.println("로그인 성공.");
	                int memberNum = rs.getInt("MEM_NUM");
	                return memberNum;
	            } else {
	            	return 0;
	            }
	        }catch(SQLException e){
				e.printStackTrace();
	            JdbcTemplate.rollback(JdbcTemplate.getConnection());
				System.out.println("로그인 실패");
	            System.out.println("없는 정보입니다.");
	            }
			return 0;
	        
	        }
	    
	    }


