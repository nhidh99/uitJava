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
		String query = "INSERT INTO NganSach(MaLoaiGiaoDich, MaNguoiDung, NgayBatDau, NgayKetThuc, GiaTri) "
				+ "VALUES (?,?,?,?,?)";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setInt(1, nganSach.getMaLoaiGiaoDich());
		statement.setInt(2, nganSach.getMaNguoiDung());
		statement.setString(3, nganSach.getNgayBatDau().toString());
		statement.setString(4, nganSach.getNgayKetThuc().toString());
		statement.setLong(5, nganSach.getGiaTri());
		int records = statement.executeUpdate();
		conn.close();
		return records > 0;
	}

	public static List<NganSachDTO> getDSNganSach(int maNguoiDung) throws SQLException {
		Connection conn = DBHelper.getConnection();
		String query = "SELECT * FROM NganSach WHERE MaNguoiDung = ? ORDER BY MaNganSach DESC";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setInt(1, maNguoiDung);
		ResultSet rs = statement.executeQuery();
		
		List<NganSachDTO> output = new ArrayList<>();
		while (rs.next()) {
			NganSachDTO nganSach = new NganSachDTO(rs.getInt("MaNganSach"), 
					rs.getInt("MaLoaiGiaoDich"), maNguoiDung, 
					rs.getTimestamp("NgayBatDau").toLocalDateTime().toLocalDate(), 
					rs.getTimestamp("NgayKetThuc").toLocalDateTime().toLocalDate(), 
					rs.getLong("GiaTri"));
			nganSach.setSuDung(rs.getLong("SuDung"));
			output.add(nganSach);
		}
		conn.close();
		return output;
	}

	public static boolean deleteNganSach(Integer maNganSach) throws SQLException {
		Connection conn = DBHelper.getConnection();
		String query = "DELETE FROM NganSach WHERE MaNganSach = ?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setInt(1, maNganSach);
		int records = statement.executeUpdate();
		conn.close();
		return records > 0;
	}
 
	public static boolean updateNganSach(NganSachDTO nganSach) throws SQLException {
		Connection conn = DBHelper.getConnection();
		String query = "UPDATE NganSach SET MaLoaiGiaoDich = ?, NgayBatDau = ?, NgayKetThuc = ?, GiaTri = ? WHERE MaNganSach = ?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setInt(1, nganSach.getMaLoaiGiaoDich());
		statement.setString(2, nganSach.getNgayBatDau().toString());
		statement.setString(3, nganSach.getNgayKetThuc().toString());
		statement.setLong(4, nganSach.getGiaTri());
		statement.setInt(5, nganSach.getMaNganSach());
		int records = statement.executeUpdate();
		conn.close();
		return records > 0;
	}

}
