package pcCafe.main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MemberDao {
	
	    //싱글톤 패턴 적용

	    private static MemberDao instance;

	    private MemberDao() {}

	    public static MemberDao getInstance(){
	        if(instance == null){
	            instance = new MemberDao();
	        }
	        return instance;
	    }

	    public int insertMember(Connection conn, MemberData memberData) throws SQLException{
	        PreparedStatement pstmt = null;
	        int result = 0;

	        String sql = "INSERT INTO MEMBER VALUES (MEMBER_SEQ.NEXTVAL,?,?,?,?,?,'N',0)";

	        try {
	            pstmt = conn.prepareStatement(sql);
	            pstmt.setString(1, memberData.getUserId());
	            pstmt.setString(2, memberData.getUserPwd());
	            pstmt.setString(3, memberData.getUserName());
	            pstmt.setString(4, memberData.getUserBirth());
	            pstmt.setString(5, memberData.getUserPhone());
	            result = pstmt.executeUpdate();
	        }finally {
	            JdbcTemplate.close(pstmt);
	        }

	        return  result;
	    }

	    public MemberData select(Connection conn, String id, String pwd) throws SQLException{
	        PreparedStatement pstmt = null;
	        ResultSet rset = null;
	        MemberData member = null;

	        String sql = "SELECT * FROM MEMBER WHERE MEM_ID= ? AND MEM_PWD = ? AND QUIT_YN = 'N'";

	        try {
	            pstmt = conn.prepareStatement(sql);
	            pstmt.setString(1, id);
	            pstmt.setString(2, pwd);
	            rset = pstmt.executeQuery();

	            if (rset.next()) {
	                member = new MemberData(rset.getInt("MEMBER"), rset.getString("MEM_ID"), rset.getString("MEM_PWD"), rset.getString("MEM_NAME"), rset.getString("MEM_BIRTH"), rset.getString("MEM_PHONE"), rset.getString("QUIT_YN"), rset.getInt("MEM_TIME"));
	            }
	        } finally {
	            JdbcTemplate.close(rset);
	            JdbcTemplate.close(pstmt);
	        }

	        return  member;
	    }

	    /*public ArrayList<MemberModel> search(Connection conn, String keyword, String option) throws SQLException{
	        PreparedStatement pstmt = null;
	        ResultSet rset= null;
	        ArrayList<MemberModel> list = new ArrayList<MemberModel>();

	        String query = "SELECT * FROM MEMBER WHERE " + option + " LIKE '%' || ? || '%'";

	        try {
	            pstmt = conn.prepareStatement(query);
	            pstmt.setString(1, keyword);
	            rset = pstmt.executeQuery();

	            while (rset.next()) {
	                MemberModel memberModel = new MemberModel(rset.getInt("MEMBER"), rset.getString("MEM_ID"), rset.getString("MEM_PWD"), rset.getString("MEM_NAME"), rset.getString("MEM_BIRTH"), rset.getString("MEM_PHONE"), rset.getString("QUIT_YN"), rset.getInt("MEM_TIME"));
	                list.add(memberModel);
	            }
	        }finally {
	            JdbcTemplate.close(rset);
	            JdbcTemplate.close(pstmt);
	        }

	        return pstmt;
	    }*/
	}
	


