package oracle_db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class prepaeredUpdateDB2 {
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:orcl"; //url 은 자원이다.
	private static final String USER_ID = "system"; 
	private static final String PASSWORD = "1234"; 
	
	private static Connection conn() {
		try {
			//주어진(매개변수) 문자열을 기준으로 클래스 찾기 -> class.forname
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Success");
			Connection connection = DriverManager.getConnection(URL, USER_ID, PASSWORD);
			System.out.println("Connection Success");
			return connection;
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static void main(String[] args) {
		Connection conn = conn();
		if(conn == null)
			return;
		try {
			//prepareStatement = 준비는 딱 한번만 합니다.
			PreparedStatement stmt = conn.prepareStatement("update t_book set price = ? where id = ?");
			stmt.setInt(1, 7500);//첫번째 물음표자리는 7500 들어감
			stmt.setInt(2, 2);//두번째 물음표 자리는 2가 들어감
			stmt.executeUpdate();
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
