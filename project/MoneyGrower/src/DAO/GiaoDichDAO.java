package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import DTO.GiaoDichDTO;
import helper.DBHelper;

public class GiaoDichDAO {

	public static List<GiaoDichDTO> getDSGiaoDichOfDay(Integer userID, LocalDate date) throws SQLException {
		Connection conn = DBHelper.getConnection();
		try {
			String query = "SELECT * FROM GiaoDich WHERE MaNguoiDung = ? AND NgayGiaoDich = ?";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, userID);
			statement.setString(2, date.toString());
			ResultSet rs = statement.executeQuery();
			List<GiaoDichDTO> output = new ArrayList<>();
			while (rs.next()) {
				GiaoDichDTO gd = new GiaoDichDTO(rs.getInt("MaGiaoDich"), rs.getInt("MaLoaiGiaoDich"), userID, date,
						rs.getLong("GiaTri"), rs.getString("GhiChu"));
				output.add(gd);
			}
			return output;
		} finally {
			conn.close();
		}
	}

	public static boolean insertGiaoDich(GiaoDichDTO giaoDich) throws SQLException {
		Connection conn = DBHelper.getConnection();
		try {
			String query = "INSERT INTO GiaoDich(MaNguoiDung, MaLoaiGiaoDich, NgayGiaoDich, GiaTri, GhiChu) "
					+ "VALUES (?,?,?,?,?)";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, giaoDich.getMaNguoiDung());
			statement.setInt(2, giaoDich.getMaLoaiGiaoDich());
			statement.setString(3, giaoDich.getNgayGiaoDich().toString());
			statement.setLong(4, giaoDich.getGiaTri());
			if (giaoDich.getGhiChu().isEmpty()) {
				statement.setNull(5, Types.VARCHAR);
			} else
				statement.setString(5, giaoDich.getGhiChu().trim());
			int records = statement.executeUpdate();
			return records > 0;
		} finally {
			conn.close();
		}
	}

	public static boolean updateGiaoDich(GiaoDichDTO giaoDich) throws SQLException {
		Connection conn = DBHelper.getConnection();
		try {
			String query = "UPDATE GiaoDich SET MaLoaiGiaoDich = ?, NgayGiaoDich = ?, GiaTri = ?, GhiChu = ? "
					+ "WHERE MaGiaoDich = ?";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, giaoDich.getMaLoaiGiaoDich());
			statement.setString(2, giaoDich.getNgayGiaoDich().toString());
			statement.setLong(3, giaoDich.getGiaTri());
			if (giaoDich.getGhiChu().isEmpty()) {
				statement.setNull(4, Types.VARCHAR);
			} else
				statement.setString(4, giaoDich.getGhiChu().trim());
			statement.setInt(5, giaoDich.getMaGiaoDich());
			int records = statement.executeUpdate();
			return records > 0;
		} finally {
			conn.close();
		}
	}

	public static boolean deleteGiaoDich(Integer maGiaoDich) throws SQLException {
		Connection conn = DBHelper.getConnection();
		try {
			String query = "DELETE FROM GiaoDich WHERE MaGiaoDich = ?";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, maGiaoDich);
			int records = statement.executeUpdate();
			return records > 0;
		} finally {
			conn.close();
		}
	}
}
