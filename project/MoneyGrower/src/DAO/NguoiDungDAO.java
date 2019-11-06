package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import helper.DBHelper;

public class NguoiDungDAO {

	public static boolean checkDangNhap(String tenTaiKhoan, String matKhau) throws SQLException {
		Connection conn = DBHelper.getConnection();
		String query = "call login_NguoiDung(?,?)";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, tenTaiKhoan);
		statement.setString(2, matKhau);
		statement.execute();
		ResultSet rs = statement.executeQuery();
		rs.next();
		boolean output = rs.getBoolean(1);
		conn.close();
		return output;
	}

}
