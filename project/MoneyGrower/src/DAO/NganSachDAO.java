package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DTO.NganSachDTO;
import helper.DBHelper;

public class NganSachDAO {

	public static boolean insertNganSach(NganSachDTO nganSach) throws SQLException {
		Connection conn = DBHelper.getConnection();
		try {
			String query = "INSERT INTO NganSach(MaLoaiGiaoDich, MaNguoiDung, NgayBatDau, NgayKetThuc, GiaTri) "
					+ "VALUES (?,?,?,?,?)";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, nganSach.getMaLoaiGiaoDich());
			statement.setInt(2, nganSach.getMaNguoiDung());
			statement.setString(3, nganSach.getNgayBatDau().toString());
			statement.setString(4, nganSach.getNgayKetThuc().toString());
			statement.setLong(5, nganSach.getGiaTri());
			int records = statement.executeUpdate();
			return records > 0;
		} finally {
			conn.close();
		}
	}

	public static List<NganSachDTO> getDSNganSach(int maNguoiDung) throws SQLException {
		Connection conn = DBHelper.getConnection();
		try {
			String query = "SELECT * FROM NganSach WHERE MaNguoiDung = ? ORDER BY MaNganSach DESC";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, maNguoiDung);
			ResultSet rs = statement.executeQuery();

			List<NganSachDTO> output = new ArrayList<>();
			while (rs.next()) {
				NganSachDTO nganSach = new NganSachDTO(rs.getInt("MaNganSach"), rs.getInt("MaLoaiGiaoDich"),
						maNguoiDung, rs.getTimestamp("NgayBatDau").toLocalDateTime().toLocalDate(),
						rs.getTimestamp("NgayKetThuc").toLocalDateTime().toLocalDate(), rs.getLong("GiaTri"));
				nganSach.setSuDung(rs.getLong("SuDung"));
				output.add(nganSach);
			}
			return output;
		} finally {
			conn.close();
		}
	}

	public static boolean deleteNganSach(Integer maNganSach) throws SQLException {
		Connection conn = DBHelper.getConnection();
		try {
			String query = "DELETE FROM NganSach WHERE MaNganSach = ?";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, maNganSach);
			int records = statement.executeUpdate();
			return records > 0;
		} finally {
			conn.close();
		}
	}

	public static boolean updateNganSach(NganSachDTO nganSach) throws SQLException {
		Connection conn = DBHelper.getConnection();
		try {
			String query = "UPDATE NganSach SET MaLoaiGiaoDich = ?, NgayBatDau = ?, NgayKetThuc = ?, GiaTri = ? WHERE MaNganSach = ?";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, nganSach.getMaLoaiGiaoDich());
			statement.setString(2, nganSach.getNgayBatDau().toString());
			statement.setString(3, nganSach.getNgayKetThuc().toString());
			statement.setLong(4, nganSach.getGiaTri());
			statement.setInt(5, nganSach.getMaNganSach());
			int records = statement.executeUpdate();
			return records > 0;
		} finally {
			conn.close();
		}
	}

	public static boolean checkNganSach(NganSachDTO nganSach) throws SQLException {
		Connection conn = DBHelper.getConnection();
		try {
			String query = "SELECT EXISTS (SELECT * FROM NganSach "
					+ "WHERE MaNguoiDung = ? "
					+ "AND MaLoaiGiaoDich = ? "
					+ "AND (NgayBatDau BETWEEN ? AND ? "
					+ "OR NgayKetThuc BETWEEN ? AND ?) LIMIT 1)";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, nganSach.getMaNguoiDung());
			statement.setInt(2, nganSach.getMaLoaiGiaoDich());
			statement.setString(3, nganSach.getNgayBatDau().toString());
			statement.setString(4, nganSach.getNgayKetThuc().toString());
			statement.setString(5, nganSach.getNgayBatDau().toString());
			statement.setString(6, nganSach.getNgayKetThuc().toString());
			ResultSet rs = statement.executeQuery();
			rs.next();
			boolean output = rs.getBoolean(1);
			return output;
		} finally {
			conn.close();
		}
	}

}
