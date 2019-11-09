package BUS;

import java.sql.SQLException;
import java.util.List;

import DAO.NganSachDAO;
import DTO.NganSachDTO;

public class NganSachBUS {

	public static boolean insertNganSach(NganSachDTO nganSach) throws SQLException {
		return NganSachDAO.insertNganSach(nganSach);
	}

	public static List<NganSachDTO> getDSNganSach(int maNguoiDung) throws SQLException {
		return NganSachDAO.getDSNganSach(maNguoiDung);
	}
}
