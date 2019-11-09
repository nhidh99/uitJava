package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DTO.LoaiGiaoDichDTO;
import helper.DBHelper;

public class LoaiGiaoDichDAO {

	public static LoaiGiaoDichDTO getLoaiGiaoDichById(Integer id) throws SQLException {
		Connection conn = DBHelper.getConnection();
		String query = "SELECT * FROM LoaiGiaoDich WHERE MaLoaiGiaoDich = ?";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setInt(1, id);
		ResultSet rs = statement.executeQuery();
		rs.next();
		LoaiGiaoDichDTO output = new LoaiGiaoDichDTO(id, rs.getString("TenLoaiGiaoDich"), rs.getBoolean("GiaoDichThu"),
				rs.getString("BieuTuong"));
		conn.close();
		return output;
	}

	public static List<LoaiGiaoDichDTO> getDSLoaiGiaoDichThu() throws SQLException {
		Connection conn = DBHelper.getConnection();
		String query = "SELECT * FROM LoaiGiaoDich WHERE GiaoDichThu = true";
		Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery(query);
		List<LoaiGiaoDichDTO> output = new ArrayList<>();
		while (rs.next()) {
			LoaiGiaoDichDTO lgd = new LoaiGiaoDichDTO(rs.getInt("MaLoaiGiaoDich"), rs.getString("TenLoaiGiaoDich"),
					rs.getBoolean("GiaoDichThu"), rs.getString("BieuTuong"));
			output.add(lgd);
		}
		conn.close();
		return output;
	}
	
	public static List<LoaiGiaoDichDTO> getDSLoaiGiaoDichChi() throws SQLException {
		Connection conn = DBHelper.getConnection();
		String query = "SELECT * FROM LoaiGiaoDich WHERE GiaoDichThu = false";
		Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery(query);
		List<LoaiGiaoDichDTO> output = new ArrayList<>();
		while (rs.next()) {
			LoaiGiaoDichDTO lgd = new LoaiGiaoDichDTO(rs.getInt("MaLoaiGiaoDich"), rs.getString("TenLoaiGiaoDich"),
					rs.getBoolean("GiaoDichThu"), rs.getString("BieuTuong"));
			output.add(lgd);
		}
		conn.close();
		return output;
	}
}
