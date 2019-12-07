package service;

import java.sql.SQLException;

import helper.DBHelper;
import model.UserModel;

public class LoginService {

	public UserModel getUserByUsername(String username) throws SQLException, ClassNotFoundException {
		var conn = DBHelper.getConnection();
		try {
			String query = "SELECT * FROM NguoiDung WHERE TenTaiKhoan = ?";
			var statement = conn.prepareStatement(query);
			statement.setString(1, username);
			var rs = statement.executeQuery();
			rs.next();
			
			var user = UserModel.getInstance();
			user.setUserID(rs.getInt("MaNguoiDung"));
			user.setUsername(username);
			user.setPassword(rs.getString("MatKhau"));
			user.setName(rs.getString("TenNguoiDung"));
			user.setIncome(rs.getLong("TongSoDu"));
			return user;
		} finally {
			conn.close();
		}
	}

	public boolean checkCorrectUser(String username, String password) throws SQLException, ClassNotFoundException {
		var conn = DBHelper.getConnection();
		try {
			String query = "call login_NguoiDung(?,?)";
			var statement = conn.prepareStatement(query);
			statement.setString(1, username);
			statement.setString(2, password);
			var rs = statement.executeQuery();
			rs.next();
			boolean output = rs.getBoolean(1);
			return output;
		} finally {
			conn.close();
		}
	}

}
