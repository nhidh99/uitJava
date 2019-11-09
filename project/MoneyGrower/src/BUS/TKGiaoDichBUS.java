package BUS;

import java.sql.SQLException;
import java.util.List;

import DAO.TKGiaoDichDAO;
import DTO.TKGiaoDichDTO;

public class TKGiaoDichBUS {
	public static List<TKGiaoDichDTO> getDSTongThu(int maNguoiDung, int thang, int nam) throws SQLException {
		return TKGiaoDichDAO.getDSTongThu(maNguoiDung, thang, nam);
	}
	
	public static List<TKGiaoDichDTO> getDSTongChi(int maNguoiDung, int thang, int nam) throws SQLException {
		return TKGiaoDichDAO.getDSTongChi(maNguoiDung, thang, nam);
	}
}
