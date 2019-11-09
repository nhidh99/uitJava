package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DTO.GiaoDichThangDTO;
import helper.DBHelper;

public class GiaoDichThangDAO {
	public static List<GiaoDichThangDTO> getDSGiaoDichThang(Integer maNguoiDung, Integer thang, Integer nam) throws SQLException {
		Connection conn = DBHelper.getConnection();
		String query = "SELECT * FROM view_GiaoDichThang " 
		+ "WHERE MaNguoiDung = ? AND MONTH(NgayGiaoDich) = ? AND YEAR(NgayGiaoDich) = ? "
				+ "ORDER BY NgayGiaoDich DESC";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setInt(1, maNguoiDung);
		statement.setInt(2, thang);
		statement.setInt(3, nam);
		ResultSet rs = statement.executeQuery();

		List<GiaoDichThangDTO> output = new ArrayList<>();
		while (rs.next()) {
			GiaoDichThangDTO gdt = new GiaoDichThangDTO(maNguoiDung,
					rs.getTimestamp("NgayGiaoDich").toLocalDateTime().toLocalDate(), 
					rs.getLong("TongGiaTri"));
			output.add(gdt);
		}
		conn.close();
		return output;
	}
}
