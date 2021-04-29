package oracle_db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectDB {
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
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from t_book");
			StringBuilder sb = new StringBuilder();
			while (rs.next()) {
				sb.append("id").append(rs.getInt("id"));
				sb.append(", title").append(rs.getString("title"));
				sb.append(", publisher").append(rs.getString("publisher"));
				sb.append(", publish_year").append(rs.getString("publish_year"));
				sb.append(", price").append(rs.getInt("price"));
				System.out.println(sb.toString());
				sb.delete(0, sb.length());
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
