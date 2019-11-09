package BUS;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import DAO.GiaoDichDAO;
import DTO.GiaoDichDTO;

public class GiaoDichBUS {
	public static List<GiaoDichDTO> getDSGiaoDichOfMonth(Integer userID, LocalDate date) 
			throws SQLException {
		return GiaoDichDAO.getDSGiaoDichOfDay(userID, date);
	}

	public static boolean insertGiaoDich(GiaoDichDTO giaoDich) throws SQLException {
		return GiaoDichDAO.insertGiaoDich(giaoDich);
	}
}
