package DTO;

public class NguoiDungDTO {
	private Integer maNguoiDung;
	private String tenTaiKhoan;
	private String matKhau;
	private String tenNguoiDung;
	private Long tongSoDu;

	public NguoiDungDTO(Integer maNguoiDung, String tenTaiKhoan, String matKhau, String tenNguoiDung,
			Long tongSoDu) {
		this.maNguoiDung = maNguoiDung;
		this.tenTaiKhoan = tenTaiKhoan;
		this.matKhau = matKhau;		
		this.tenNguoiDung = tenNguoiDung;
		this.tongSoDu = tongSoDu;
	}

	public String getTenTaiKhoan() {
		return tenTaiKhoan;
	}

	public void setTenTaiKhoan(String tenTaiKhoan) {
		this.tenTaiKhoan = tenTaiKhoan;
	}

	public String getMatKhau() {
		return matKhau;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}

	public String getTenNguoiDung() {
		return tenNguoiDung;
	}

	public void setTenNguoiDung(String tenNguoiDung) {
		this.tenNguoiDung = tenNguoiDung;
	}

	public Long getTongSoDu() {
		return tongSoDu;
	}

	public void setTongSoDu(Long tongSoDu) {
		this.tongSoDu = tongSoDu;
	}

	public Integer getMaNguoiDung() {
		return maNguoiDung;
	}

	public void setMaNguoiDung(Integer maNguoiDung) {
		this.maNguoiDung = maNguoiDung;
	}
}
