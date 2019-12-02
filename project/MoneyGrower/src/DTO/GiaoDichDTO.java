package DTO;

import java.time.LocalDate;

public class GiaoDichDTO {
	private Integer maGiaoDich;
	private Integer maLoaiGiaoDich;
	private Integer maNguoiDung;
	private LocalDate ngayGiaoDich;
	private Long giaTri;
	private String ghiChu;

	public GiaoDichDTO(Integer maGiaoDich, Integer maLoaiGiaoDich, Integer maNguoiDung, LocalDate ngayGiaoDich,
			Long giaTri, String ghiChu) {
		this.maGiaoDich = maGiaoDich;
		this.maLoaiGiaoDich = maLoaiGiaoDich;
		this.maNguoiDung = maNguoiDung;
		this.ngayGiaoDich = ngayGiaoDich;
		this.giaTri = giaTri;
		this.ghiChu = ghiChu;
	}

	public Integer getMaGiaoDich() {
		return maGiaoDich;
	}

	public void setMaGiaoDich(Integer maGiaoDich) {
		this.maGiaoDich = maGiaoDich;
	}

	public Integer getMaLoaiGiaoDich() {
		return maLoaiGiaoDich;
	}

	public void setMaLoaiGiaoDich(Integer maLoaiGiaoDich) {
		this.maLoaiGiaoDich = maLoaiGiaoDich;
	}

	public Integer getMaNguoiDung() {
		return maNguoiDung;
	}

	public void setMaNguoiDung(Integer maNguoiDung) {
		this.maNguoiDung = maNguoiDung;
	}

	public LocalDate getNgayGiaoDich() {
		return ngayGiaoDich;
	}

	public void setNgayGiaoDich(LocalDate ngayGiaoDich) {
		this.ngayGiaoDich = ngayGiaoDich;
	}

	public Long getGiaTri() {
		return giaTri;
	}

	public void setGiaTri(Long giaTri) {
		this.giaTri = giaTri;
	}

	public String getGhiChu() {
		return ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}
}
