package DTO;

import java.sql.Timestamp;
import java.time.LocalDate;

public class GiaoDichThangDTO {
	private Integer maNguoiDung;
	private LocalDate ngayGiaoDich;
	private Long triGia;
	
	public GiaoDichThangDTO(Integer maNguoiDung, Timestamp timestamp, Long triGia) {
		this.maNguoiDung = maNguoiDung;
		this.ngayGiaoDich = timestamp.toLocalDateTime().toLocalDate();
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
