package BUS;

import java.sql.SQLException;

import DAO.NguoiDungDAO;
import DTO.NguoiDungDTO;

public class NguoiDungBUS {
	public static boolean checkDangNhap(String tenTaiKhoan, String matKhau) throws SQLException {
		return NguoiDungDAO.checkDangNhap(tenTaiKhoan, matKhau);
	}

	public static NguoiDungDTO getNguoiDungByUsername(String username) throws SQLException {		
		return NguoiDungDAO.getNguoiDungByUsername(username);
	}
	
	public static NguoiDungDTO getNguoiDungById(int id) throws SQLException {
		return NguoiDungDAO.getNguoiDungById(id);
	}

	public static boolean dangKi(NguoiDungDTO nguoiDung) throws SQLException {
		if (NguoiDungDAO.checkDangKi(nguoiDung)) {
			return false;
		}
		return NguoiDungDAO.dangKi(nguoiDung);
	}

	public static boolean updateNguoiDung(NguoiDungDTO nguoiDung) throws SQLException {
		return NguoiDungDAO.updateNguoiDung(nguoiDung);
	}
	
	public static Long getSoDu(Integer maNguoiDung) throws SQLException {
		return NguoiDungDAO.getSoDu(maNguoiDung);
	}

}
