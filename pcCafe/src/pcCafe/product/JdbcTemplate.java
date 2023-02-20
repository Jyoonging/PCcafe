package pcCafe.product;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcTemplate {
		
	public static Connection getConnection() throws Exception {
		//연결 정보
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@129.154.52.135:1521:xe";
		String id = "C##KH";
		String pwd = "KH";
		
		//커넥션 얻기
		return DriverManager.getConnection(url, id, pwd);
	}

}