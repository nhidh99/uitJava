
public class QuanLy extends NguoiLam {
	int soNVQuanLy;
	
	public QuanLy(String hoTen, String ngaySinh, int heSoLuong, int soNVQuanLy) {
		super(hoTen, ngaySinh, heSoLuong);
		this.soNVQuanLy = soNVQuanLy;
		this.chucVu = "Quan ly";
	}

	@Override
	public String toString() {
		return super.toString() + "- So nhan vien quan ly: " + soNVQuanLy + "\n";
	}
}
