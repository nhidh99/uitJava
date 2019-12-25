package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sun.awt.ModalExclude;

public class DataAccess {

	private static Connection getConnection() throws SQLException {
		String connString = "jdbc:mysql://localhost:3306/QuanLiKhachHang?autoReconnect=true&useSSL=false";
		String username = "root";
		String password = "root";
		return DriverManager.getConnection(connString, username, password);
	}

	public static List<Model> getModelList() {
		String sql = "SELECT * FROM KhachHang";
		try (Connection conn = getConnection();
				Statement stm = conn.createStatement();
				ResultSet rs = stm.executeQuery(sql)) {

			List<Model> output = new ArrayList<>();
			while (rs.next()) {
				String maKH = rs.getString("MaKH");
				String tenKH = rs.getString("TenKH");
				String diaChi = rs.getString("DiaChi");
				String soDienThoai = rs.getString("SoDienThoai");
				LocalDate ngaySinh = rs.getTimestamp("NgaySinh").toLocalDateTime().toLocalDate();
				LocalDate ngayDK = rs.getTimestamp("NgayDK").toLocalDateTime().toLocalDate();
				Long doanhSo = rs.getLong("DoanhSo");
				Model model = new Model(maKH, tenKH, diaChi, soDienThoai, ngaySinh, ngayDK, doanhSo);
				output.add(model);
			}
			return output;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Map<String, Integer> getModelSummary() {
		String sql = "SELECT * FROM view_tk_soluong";
		try (Connection conn = getConnection();
				Statement stm = conn.createStatement();
				ResultSet rs = stm.executeQuery(sql)) {

			rs.next();
			Map<String, Integer> output = new HashMap<>();
			output.put("TongMH", rs.getInt("TongMH"));
			output.put("TongPT", rs.getInt("TongPT"));
			output.put("TongCBCT", rs.getInt("TongCBCT"));
			return output;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Integer getSoMonDaThi(LocalDate date) {
		try (Connection conn = getConnection();
				PreparedStatement stm = createMonChuaThiStatement(conn, date);
				ResultSet rs = stm.executeQuery()) {

			rs.next();
			return rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	private static PreparedStatement createMonChuaThiStatement(Connection conn, LocalDate date) throws SQLException {
		String sql = "SELECT COUNT(DISTINCT TenMH) FROM PhanCong WHERE NgayThi <= ?";
		PreparedStatement stm = conn.prepareStatement(sql);
		stm.setTimestamp(1, Timestamp.valueOf(date.atStartOfDay()));
		return stm;
	}

	public static List<String> getPhongNhieuSVNhat() {
		String sql = "SELECT DISTINCT PhongThi " + "FROM PhanCong " + "WHERE SoSV = (SELECT MAX(SoSV) FROM PhanCong)";
		try (Connection conn = getConnection();
				Statement stm = conn.createStatement();
				ResultSet rs = stm.executeQuery(sql)) {

			List<String> output = new ArrayList<>();
			while (rs.next()) {
				String phongThi = rs.getString(1);
				output.add(phongThi);
			}
			return output;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static List<String> getPhongItSVNhat() {
		String sql = "SELECT DISTINCT PhongThi " + "FROM PhanCong " + "WHERE SoSV = (SELECT MIN(SoSV) FROM PhanCong)";
		try (Connection conn = getConnection();
				Statement stm = conn.createStatement();
				ResultSet rs = stm.executeQuery(sql)) {

			List<String> output = new ArrayList<>();
			while (rs.next()) {
				String phongThi = rs.getString(1);
				output.add(phongThi);
			}
			return output;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static boolean deleteModel(String maKH) {
		String sql = String.format("DELETE FROM KhachHang WHERE MaKH = '%s'", maKH);
		try (Connection conn = getConnection(); Statement stm = conn.createStatement()) {
			int records = stm.executeUpdate(sql);
			return records > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean insertModel(Model model) {
		try (Connection conn = getConnection(); PreparedStatement stm = createInsertModelStatement(conn, model)) {
			int records = stm.executeUpdate();
			return records > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	private static PreparedStatement createInsertModelStatement(Connection conn, Model model) throws SQLException {
		String sql = "INSERT INTO KhachHang VALUES (?,?,?,?,?,?,?)";
		PreparedStatement stm = conn.prepareStatement(sql);
		stm.setString(1, String.format("KH%02d", getMaxMaKH() + 1));
		stm.setString(2, model.getTenKH());
		stm.setString(3, model.getDiaChi());
		stm.setString(4, model.getSoDienThoai());
		stm.setTimestamp(5, Timestamp.valueOf(model.getNgaySinhValue().atStartOfDay()));
		stm.setTimestamp(6, Timestamp.valueOf(model.getNgayDKValue().atStartOfDay()));
		stm.setLong(7, model.getDoanhSo());
		return stm;
	}

	public static boolean updateModel(Model model) {
		try (Connection conn = getConnection(); PreparedStatement stm = createUpdateModel(conn, model)) {
			int records = stm.executeUpdate();
			return records > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	private static PreparedStatement createUpdateModel(Connection conn, Model model) throws SQLException {
		String sql = "UPDATE KhachHang SET "
				+ "TenKH = ?, DiaChi = ?, SoDienThoai = ?, NgaySinh = ?, NgayDK = ?, DoanhSo = ? " + "WHERE MaKH = ?";
		PreparedStatement stm = conn.prepareStatement(sql);
		stm.setString(1, model.getTenKH());
		stm.setString(2, model.getDiaChi());
		stm.setString(3, model.getSoDienThoai());
		stm.setTimestamp(4, Timestamp.valueOf(model.getNgaySinhValue().atStartOfDay()));
		stm.setTimestamp(5, Timestamp.valueOf(model.getNgayDKValue().atStartOfDay()));
		stm.setLong(6, model.getDoanhSo());
		stm.setString(7, model.getMaKH());
		return stm;
	}

	public static Integer getTongSoKH() {
		String sql = "SELECT COUNT(*) FROM KhachHang";
		try (Connection conn = getConnection();
				Statement stm = conn.createStatement();
				ResultSet rs = stm.executeQuery(sql)) {
			rs.next();
			return rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Integer getTongSoKHTT() {
		String sql = "SELECT COUNT(*) FROM KhachHang WHERE DoanhSo >= 2000000";
		try (Connection conn = getConnection();
				Statement stm = conn.createStatement();
				ResultSet rs = stm.executeQuery(sql)) {
			rs.next();
			return rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Integer getTongSoKHQ1() {
		String sql = "SELECT COUNT(*) FROM KhachHang WHERE DiaChi LIKE '%, Q1,%'";
		try (Connection conn = getConnection();
				Statement stm = conn.createStatement();
				ResultSet rs = stm.executeQuery(sql)) {
			rs.next();
			return rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static List<String> getListKhachMaxDoanhSo() {
		String sql = "SELECT * FROM KhachHang WHERE DoanhSo = (SELECT MAX(DoanhSo) FROM KhachHang)";
		try (Connection conn = getConnection();
				Statement stm = conn.createStatement();
				ResultSet rs = stm.executeQuery(sql)) {

			List<String> output = new ArrayList<>();
			while (rs.next()) {
				String tenKH = rs.getString("TenKH");
				output.add(tenKH);
			}
			return output;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Double getDoanhSoTB() {
		String sql = "SELECT AVG(DoanhSo) FROM KhachHang";
		try (Connection conn = getConnection();
				Statement stm = conn.createStatement();
				ResultSet rs = stm.executeQuery(sql)) {
			rs.next();
			Double output = rs.getDouble(1);
			return output;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	private static Integer getMaxMaKH() {
		String sql = "SELECT MaKH FROM KhachHang ORDER BY MaKH DESC LIMIT 1";
		try (Connection conn = getConnection();
				Statement stm = conn.createStatement();
				ResultSet rs = stm.executeQuery(sql)) {
			rs.next();
			String maKH = rs.getString(1);
			return Integer.parseInt(maKH.substring(2));
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	public static List<Model> getModelThanThietList() {
		String sql = "SELECT * FROM KhachHang WHERE DoanhSo >= 2000000";
		try (Connection conn = getConnection();
				Statement stm = conn.createStatement();
				ResultSet rs = stm.executeQuery(sql)) {

			List<Model> output = new ArrayList<>();
			while (rs.next()) {
				String maKH = rs.getString("MaKH");
				String tenKH = rs.getString("TenKH");
				String diaChi = rs.getString("DiaChi");
				String soDienThoai = rs.getString("SoDienThoai");
				LocalDate ngaySinh = rs.getTimestamp("NgaySinh").toLocalDateTime().toLocalDate();
				LocalDate ngayDK = rs.getTimestamp("NgayDK").toLocalDateTime().toLocalDate();
				Long doanhSo = rs.getLong("DoanhSo");
				Model model = new Model(maKH, tenKH, diaChi, soDienThoai, ngaySinh, ngayDK, doanhSo);
				output.add(model);
			}
			return output;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static List<Model> getModelLauNhatList() {
		String sql = "SELECT * FROM KhachHang WHERE NgayDK = (SELECT MIN(NgayDK) FROM KhachHang)";
		try (Connection conn = getConnection();
				Statement stm = conn.createStatement();
				ResultSet rs = stm.executeQuery(sql)) {

			List<Model> output = new ArrayList<>();
			while (rs.next()) {
				String maKH = rs.getString("MaKH");
				String tenKH = rs.getString("TenKH");
				String diaChi = rs.getString("DiaChi");
				String soDienThoai = rs.getString("SoDienThoai");
				LocalDate ngaySinh = rs.getTimestamp("NgaySinh").toLocalDateTime().toLocalDate();
				LocalDate ngayDK = rs.getTimestamp("NgayDK").toLocalDateTime().toLocalDate();
				Long doanhSo = rs.getLong("DoanhSo");
				Model model = new Model(maKH, tenKH, diaChi, soDienThoai, ngaySinh, ngayDK, doanhSo);
				output.add(model);
			}
			return output;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static List<Model> getModelGanNhatList() {
		String sql = "SELECT * FROM KhachHang WHERE NgayDK = (SELECT MAX(NgayDK) FROM KhachHang)";
		try (Connection conn = getConnection();
				Statement stm = conn.createStatement();
				ResultSet rs = stm.executeQuery(sql)) {

			List<Model> output = new ArrayList<>();
			while (rs.next()) {
				String maKH = rs.getString("MaKH");
				String tenKH = rs.getString("TenKH");
				String diaChi = rs.getString("DiaChi");
				String soDienThoai = rs.getString("SoDienThoai");
				LocalDate ngaySinh = rs.getTimestamp("NgaySinh").toLocalDateTime().toLocalDate();
				LocalDate ngayDK = rs.getTimestamp("NgayDK").toLocalDateTime().toLocalDate();
				Long doanhSo = rs.getLong("DoanhSo");
				Model model = new Model(maKH, tenKH, diaChi, soDienThoai, ngaySinh, ngayDK, doanhSo);
				output.add(model);
			}
			return output;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static List<Model> getModelSapSinhNhatList() {
		String sql = "SELECT * FROM view_kh_snn";
		try (Connection conn = getConnection();
				Statement stm = conn.createStatement();
				ResultSet rs = stm.executeQuery(sql)) {

			List<Model> output = new ArrayList<>();
			while (rs.next()) {
				String maKH = rs.getString("MaKH");
				String tenKH = rs.getString("TenKH");
				String diaChi = rs.getString("DiaChi");
				String soDienThoai = rs.getString("SoDienThoai");
				LocalDate ngaySinh = rs.getTimestamp("NgaySinh").toLocalDateTime().toLocalDate();
				LocalDate ngayDK = rs.getTimestamp("NgayDK").toLocalDateTime().toLocalDate();
				Long doanhSo = rs.getLong("DoanhSo");
				Model model = new Model(maKH, tenKH, diaChi, soDienThoai, ngaySinh, ngayDK, doanhSo);
				output.add(model);
			}
			return output;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
