package BUS;

import java.sql.SQLException;
import java.util.List;

import DAO.GiaoDichThangDAO;
import DTO.GiaoDichThangDTO;

public class GiaoDichThangBUS {
	public static List<GiaoDichThangDTO> getDSGiaoDichThang(int maNguoiDung) throws SQLException {
		return GiaoDichThangDAO.getDSGiaoDichThang(maNguoiDung);
	}
}
