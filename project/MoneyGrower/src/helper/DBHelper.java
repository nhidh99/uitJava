package helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHelper {

	public static Connection getConnection() throws SQLException {
		String connString = "jdbc:mysql://localhost:3306/QuanLiTaiChinh?autoReconnect=true&useSSL=false";
		String username = "root";
		String password = "root";
		return DriverManager.getConnection(connString, username, password);
	}
}