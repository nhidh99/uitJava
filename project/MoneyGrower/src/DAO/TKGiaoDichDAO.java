package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DTO.TKGiaoDichDTO;
import helper.DBHelper;

public class TKGiaoDichDAO {

	public static List<TKGiaoDichDTO> getDSTongThu(int maNguoiDung, int thang, int nam) throws SQLException {
		Connection conn = DBHelper.getConnection();
		String query = "SELECT * FROM view_ThongKeThang WHERE Thang = ? AND Nam = ? AND MaNguoiDung = ? AND TongGiaTri > 0";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setInt(1, thang);
		statement.setInt(2, nam);
		statement.setInt(3, maNguoiDung);
		ResultSet rs = statement.executeQuery();

		List<TKGiaoDichDTO> output = new ArrayList<>();
		while (rs.next()) {
			TKGiaoDichDTO tkgd = new TKGiaoDichDTO(maNguoiDung, rs.getString("TenLoaiGiaoDich"),
					rs.getLong("TongGiaTri"), thang, nam);
			output.add(tkgd);
		}
		conn.close();
		return output;
	}

	public static List<TKGiaoDichDTO> getDSTongChi(int maNguoiDung, int thang, int nam) throws SQLException {
		Connection conn = DBHelper.getConnection();
		String query = "SELECT * FROM view_ThongKeThang WHERE Thang = ? AND Nam = ? AND MaNguoiDung = ? AND TongGiaTri < 0";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setInt(1, thang);
		statement.setInt(2, nam);
		statement.setInt(3, maNguoiDung);
		ResultSet rs = statement.executeQuery();

		List<TKGiaoDichDTO> output = new ArrayList<>();
		while (rs.next()) {
			TKGiaoDichDTO tkgd = new TKGiaoDichDTO(maNguoiDung, rs.getString("TenLoaiGiaoDich"),
					- rs.getLong("TongGiaTri"), thang, nam);
			output.add(tkgd);
		}
		conn.close();
		return output;
	}

}
