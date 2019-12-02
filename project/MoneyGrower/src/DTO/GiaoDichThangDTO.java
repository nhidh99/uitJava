package DTO;

import java.time.LocalDate;

public class GiaoDichThangDTO {
	private Integer maNguoiDung;
	private LocalDate ngayGiaoDich;
	private Long triGia;
	
	public GiaoDichThangDTO(Integer maNguoiDung, LocalDate ngayGiaoDich, Long triGia) {
		this.maNguoiDung = maNguoiDung;
		this.ngayGiaoDich = ngayGiaoDich;
		this.triGia = triGia;
	}
	
	public Long getTriGia() {
		return triGia;
	}
	
	public void setTriGia(Long triGia) {
		this.triGia = triGia;
	}

	public LocalDate getNgayGiaoDich() {
		return ngayGiaoDich;
	}

	public void setNgayGiaoDich(LocalDate ngayGiaoDich) {
		this.ngayGiaoDich = ngayGiaoDich;
	}

	public Integer getMaNguoiDung() {
		return maNguoiDung;
	}

	public void setMaNguoiDung(Integer maNguoiDung) {
		this.maNguoiDung = maNguoiDung;
	}
}
