package DTO;

public class LoaiGiaoDichDTO {
	private Integer maLoaiGiaoDich;
	private String tenLoaiGiaoDich;
	private Boolean giaoDichThu;
	private String bieuTuong;

	public LoaiGiaoDichDTO(Integer maLoaiGiaoDich, String tenLoaiGiaoDich, Boolean giaoDichThu, String bieuTuong) {
		this.maLoaiGiaoDich = maLoaiGiaoDich;
		this.tenLoaiGiaoDich = tenLoaiGiaoDich;
		this.giaoDichThu = giaoDichThu;
		this.bieuTuong = bieuTuong;
	}

	public Integer getMaLoaiGiaoDich() {
		return maLoaiGiaoDich;
	}

	public void setMaLoaiGiaoDich(Integer maLoaiGiaoDich) {
		this.maLoaiGiaoDich = maLoaiGiaoDich;
	}

	public String getTenLoaiGiaoDich() {
		return tenLoaiGiaoDich;
	}

	public void setTenLoaiGiaoDich(String tenLoaiGiaoDich) {
		this.tenLoaiGiaoDich = tenLoaiGiaoDich;
	}

	public Boolean getGiaoDichThu() {
		return giaoDichThu;
	}

	public void setGiaoDichThu(Boolean giaoDichThu) {
		this.giaoDichThu = giaoDichThu;
	}

	public String getBieuTuong() {
		return bieuTuong;
	}

	public void setBieuTuong(String bieuTuong) {
		this.bieuTuong = bieuTuong;
	}

}
