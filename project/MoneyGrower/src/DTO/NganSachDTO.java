package DTO;

import java.time.LocalDate;

public class NganSachDTO {
	private Integer maNganSach;
	private Integer maLoaiGiaoDich;
	private Integer maNguoiDung;
	private LocalDate ngayBatDau;
	private LocalDate ngayKetThuc;
	private Long giaTri;
	private Long suDung;
	
	public NganSachDTO(Integer maNganSach, Integer maLoaiGiaoDich, Integer maNguoiDung, LocalDate ngayBatDau,
			LocalDate ngayKetThuc, Long giaTri) {
		this.maNganSach = maNganSach;
		this.maLoaiGiaoDich = maLoaiGiaoDich;
		this.maNguoiDung = maNguoiDung;
		this.ngayBatDau = ngayBatDau;
		this.ngayKetThuc = ngayKetThuc;
		this.giaTri = giaTri;
	}

	public Integer getMaNganSach() {
		return maNganSach;
	}
	
	public void setMaNganSach(Integer maNganSach) {
		this.maNganSach = maNganSach;
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
	
	public LocalDate getNgayBatDau() {
		return ngayBatDau;
	}
	
	public void setNgayBatDau(LocalDate ngayBatDau) {
		this.ngayBatDau = ngayBatDau;
	}
	
	public LocalDate getNgayKetThuc() {
		return ngayKetThuc;
	}
	
	public void setNgayKetThuc(LocalDate ngayKetThuc) {
		this.ngayKetThuc = ngayKetThuc;
	}
	
	public Long getGiaTri() {
		return giaTri;
	}
	
	public void setGiaTri(Long giaTri) {
		this.giaTri = giaTri;
	}

	public Long getSuDung() {
		return suDung;
	}

	public void setSuDung(long suDung) {
		this.suDung = suDung;
	}
}
