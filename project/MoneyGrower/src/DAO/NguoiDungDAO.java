package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DTO.NguoiDungDTO;
import helper.DBHelper;

public class NguoiDungDAO {

	public static boolean checkDangNhap(String tenTaiKhoan, String matKhau) throws SQLException {
		Connection conn = DBHelper.getConnection();
		try {
			String query = "call login_NguoiDung(?,?)";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, tenTaiKhoan);
			statement.setString(2, matKhau);
			ResultSet rs = statement.executeQuery();
			rs.next();
			boolean output = rs.getBoolean(1);
			return output;
		} finally {
			conn.close();
		}
	}

	public static NguoiDungDTO getNguoiDungByUsername(String username) throws SQLException {
		Connection conn = DBHelper.getConnection();
		try {
			String query = "SELECT * FROM NguoiDung WHERE TenTaiKhoan = ?";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, username);
			ResultSet rs = statement.executeQuery();
			rs.next();
			NguoiDungDTO output = new NguoiDungDTO(rs.getInt("MaNguoiDung"), rs.getString("TenTaiKhoan"),
					rs.getString("MatKhau"), rs.getString("TenNguoiDung"), rs.getLong("TongSoDu"));
			return output;
		} finally {
			conn.close();
		}
	}

	public static boolean checkDangKi(NguoiDungDTO nguoiDung) throws SQLException {
		Connection conn = DBHelper.getConnection();
		try {
			String query = "SELECT EXISTS (SELECT * FROM NguoiDung WHERE TenTaiKhoan = ? LIMIT 1)";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, nguoiDung.getTenTaiKhoan());
			ResultSet rs = statement.executeQuery();
			rs.next();
			boolean output = rs.getBoolean(1);
			return output;
		} finally {
			conn.close();
		}
	}

	public static boolean dangKi(NguoiDungDTO nguoiDung) throws SQLException {
		Connection conn = DBHelper.getConnection();
		try {
			String query = "INSERT INTO NguoiDung(TenTaiKhoan, MatKhau, TenNguoiDung, TongSoDu) " + "VALUES (?,?,?,?)";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, nguoiDung.getTenTaiKhoan());
			statement.setString(2, nguoiDung.getMatKhau());
			statement.setString(3, nguoiDung.getTenNguoiDung());
			statement.setLong(4, nguoiDung.getTongSoDu());
			int records = statement.executeUpdate();
			return records > 0;
		} finally {
			conn.close();
		}
	}

	public static boolean updateNguoiDung(NguoiDungDTO nguoiDung) throws SQLException {
		Connection conn = DBHelper.getConnection();
		try {
			String query = "UPDATE NguoiDung SET MatKhau = ?, TenNguoiDung = ?, TongSoDu = ? WHERE TenTaiKhoan = ?";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, nguoiDung.getMatKhau());
			statement.setString(2, nguoiDung.getTenNguoiDung());
			statement.setLong(3, nguoiDung.getTongSoDu());
			statement.setString(4, nguoiDung.getTenTaiKhoan());
			int records = statement.executeUpdate();
			return records > 0;
		} finally {
			conn.close();
		}
	}

	public static Long getSoDu(Integer maNguoiDung) throws SQLException {
		Connection conn = DBHelper.getConnection();
		try {
			String query = "SELECT TongSoDu FROM NguoiDung WHERE MaNguoiDung = ?";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, maNguoiDung);
			ResultSet rs = statement.executeQuery();
			rs.next();
			Long output = rs.getLong(1);
			return output;
		} finally {
			conn.close();
		}
	}

	public static NguoiDungDTO getNguoiDungById(int id) throws SQLException {
		Connection conn = DBHelper.getConnection();
		try {
			String query = "SELECT * FROM NguoiDung WHERE MaNguoiDung = ?";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			rs.next();
			NguoiDungDTO output = new NguoiDungDTO(rs.getInt("MaNguoiDung"), rs.getString("TenTaiKhoan"),
					rs.getString("MatKhau"), rs.getString("TenNguoiDung"), rs.getLong("TongSoDu"));
			return output;
		} finally {
			conn.close();
		}
	}
}
