
public class SinhVien {	
	private String hoTen;
	private float diemTB;
	
	public SinhVien(String hoTen, float diemTB) {
		this.hoTen = hoTen;
		this.diemTB = diemTB;
	}
	
	public String getHoTen() {
		return hoTen;
	}
	
	public float getDiemTB() {
		return diemTB;
	}
	
	public String toString() {
		return String.format("- Ho ten: %s\n"
				+ "- Diem trung binh: %.2f",
				hoTen, diemTB);
	}
}