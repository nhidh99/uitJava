
public class NhanVien extends NguoiLam {
	String tenDonVi;
	
	public NhanVien(String hoTen, String ngaySinh, int heSoLuong, String tenDonVi) {
		super(hoTen, ngaySinh, heSoLuong);
		this.tenDonVi = tenDonVi;
		this.chucVu = "Nhan vien";
	}
	
	@Override
	public String toString() {
		return super.toString() + "- Ten don vi: " + tenDonVi + "\n";
	}
}