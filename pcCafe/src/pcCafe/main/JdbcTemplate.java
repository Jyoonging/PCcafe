package pcCafe.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcTemplate {
	
	//커넥션 객체 리턴

    public static Connection getConnection() {
        Connection conn = null;
        try {String driver = "oracle.jdbc.driver.OracleDriver";
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String id = "C##KH";
        String pwd = "KH";
        conn = DriverManager.getConnection(url,id,pwd);

            //자동 커밋 방지
            conn.setAutoCommit(false);

        } catch (SQLException e){
            e.printStackTrace();
        }
        return conn;
    }

    public static void close(Connection conn) {
        try {
            if(conn != null && !conn.isClosed())
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void close(PreparedStatement stmt) {
        try {
            if(stmt != null && !stmt.isClosed())
                stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void close(ResultSet rset) {
        try {
            if(rset != null && !rset.isClosed())
                rset.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void commit(Connection conn) {
        try {
            if(conn != null & !conn.isClosed())
                conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void rollback(Connection conn) {
        try {
            if(conn != null & !conn.isClosed())
                conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
