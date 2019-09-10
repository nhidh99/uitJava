package model;

import java.sql.*;

public class DBConnection {
	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(
					"jdbc:sqlserver://localhost\\SQLEXPRESS01;"
					+ "DatabaseName=QuanLiHocSinh;"
					+ "IntegratedSecurity=true");
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
}