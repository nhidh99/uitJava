package BUS;

import java.sql.SQLException;

import DAO.NguoiDungDAO;

public class NguoiDungBUS {
	public static boolean checkDangNhap(String tenTaiKhoan, String matKhau) throws SQLException {
		return NguoiDungDAO.checkDangNhap(tenTaiKhoan, matKhau);
	}
}
