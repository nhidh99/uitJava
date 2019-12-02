package DTO;

public class TKGiaoDichDTO {
	private Integer maNguoiDung;
	private String tenLoaiGiaoDich;
	private Long tongGiaTri;
	private Integer thang;
	private Integer nam;
	
	public TKGiaoDichDTO(Integer maNguoiDung, String tenLoaiGiaoDich, Long tongGiaTri, Integer thang, Integer nam) {
		this.maNguoiDung = maNguoiDung;
		this.tenLoaiGiaoDich = tenLoaiGiaoDich;
		this.tongGiaTri = tongGiaTri;
		this.thang = thang;
		this.nam = nam;
	}

	public Integer getMaNguoiDung() {
		return maNguoiDung;
	}
	
	public void setMaNguoiDung(Integer maNguoiDung) {
		this.maNguoiDung = maNguoiDung;
	}

	public String getTenLoaiGiaoDich() {
		return tenLoaiGiaoDich;
	}

	public void setTenLoaiGiaoDich(String tenLoaiGiaoDich) {
		this.tenLoaiGiaoDich = tenLoaiGiaoDich;
	}

	public Long getTongGiaTri() {
		return tongGiaTri;
	}

	public void setTongGiaTri(Long tongGiaTri) {
		this.tongGiaTri = tongGiaTri;
	}

	public Integer getThang() {
		return thang;
	}

	public void setThang(Integer thang) {
		this.thang = thang;
	}

	public Integer getNam() {
		return nam;
	}

	public void setNam(Integer nam) {
		this.nam = nam;
	}
}
