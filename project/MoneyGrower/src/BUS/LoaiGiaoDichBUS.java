package BUS;

import java.sql.SQLException;
import java.util.List;

import DAO.LoaiGiaoDichDAO;
import DTO.LoaiGiaoDichDTO;

public class LoaiGiaoDichBUS {
	public static LoaiGiaoDichDTO getLoaiGiaoDichById(Integer id) throws SQLException {		
		return LoaiGiaoDichDAO.getLoaiGiaoDichById(id);
	}

	public static List<LoaiGiaoDichDTO> getDSLoaiGiaoDichThu() throws SQLException {
		return LoaiGiaoDichDAO.getDSLoaiGiaoDichThu();
	}
	
	public static List<LoaiGiaoDichDTO> getDSLoaiGiaoDichChi() throws SQLException {
		return LoaiGiaoDichDAO.getDSLoaiGiaoDichChi();
	}
}
