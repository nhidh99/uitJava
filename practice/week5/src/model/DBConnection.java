package model;

import java.sql.*;

public class DBConnection {
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection("jdbc:sqlserver://localhost\\SQLEXPRESS01;" 
				+ "DatabaseName=QuanLiHocSinh;"
				+ "IntegratedSecurity=true");
	}
}