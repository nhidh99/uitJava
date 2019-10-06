
public class GiamDoc extends NguoiLam {
	int heSoChucVu;
	
	public GiamDoc(String hoTen, String ngaySinh, int heSoLuong, int heSoChucVu) {
		super(hoTen, ngaySinh, heSoLuong);
		this.heSoChucVu = heSoChucVu;
		this.chucVu = "Giam doc";
	}

	@Override
	public String toString() {
		return super.toString() + "- He so chuc vu: " + heSoChucVu + "\n";
	}

	@Override
	public long getSalary() {
		return (heSoLuong + heSoChucVu) * LUONG_CO_BAN;
	}
}