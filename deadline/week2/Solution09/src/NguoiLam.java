
public abstract class NguoiLam {
	protected String hoTen;
	protected String ngaySinh;
	protected String chucVu;
	protected int heSoLuong;
	protected final long LUONG_CO_BAN = 1200000;
	
	public NguoiLam(String hoTen, String ngaySinh, int heSoLuong) {
		this.hoTen = hoTen;
		this.ngaySinh = ngaySinh;
		this.heSoLuong = heSoLuong;
	}
	
	public String toString() {
		return String.format(
				"- Ho ten: %s\n"
				+ "- Ngay sinh: %s\n"
				+ "- He so luong: %d\n"
				+ "- Chuc vu: %s\n",
				hoTen, ngaySinh, heSoLuong, chucVu);
	}
	
	public long getSalary() {
		return heSoLuong * LUONG_CO_BAN;
	}
}