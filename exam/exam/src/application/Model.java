package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Model {
	private String maKH;
	private String tenKH;
	private String diaChi;
	private String soDienThoai;
	private LocalDate ngaySinh;
	private LocalDate ngayDK;
	private Long doanhSo;
	
	public Model(String maKH, String tenKH, String diaChi, String soDienThoai, LocalDate ngaySinh, LocalDate ngayDK,
			Long doanhSo) {
		this.maKH = maKH;
		this.tenKH = tenKH;
		this.diaChi = diaChi;
		this.soDienThoai = soDienThoai;
		this.ngaySinh = ngaySinh;
		this.ngayDK = ngayDK;
		this.doanhSo = doanhSo;
	}

	public Model(String tenKH, String diaChi, String soDienThoai, LocalDate ngaySinh, LocalDate ngayDK, Long doanhSo) {
		super();
		this.tenKH = tenKH;
		this.diaChi = diaChi;
		this.soDienThoai = soDienThoai;
		this.ngaySinh = ngaySinh;
		this.ngayDK = ngayDK;
		this.doanhSo = doanhSo;
	}

	public String getMaKH() {
		return maKH;
	}

	public void setMaKH(String maKH) {
		this.maKH = maKH;
	}

	public String getTenKH() {
		return tenKH;
	}

	public void setTenKH(String tenKH) {
		this.tenKH = tenKH;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getSoDienThoai() {
		return soDienThoai;
	}

	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}

	public LocalDate getNgaySinhValue() {
		return ngaySinh;
	}
	
	public String getNgaySinh() {
		return ngaySinh.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}

	public void setNgaySinh(LocalDate ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public LocalDate getNgayDKValue() {
		return ngayDK;
	}
	
	public String getNgayDK() {
		return ngayDK.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}

	public void setNgayDK(LocalDate ngayDK) {
		this.ngayDK = ngayDK;
	}

	public Long getDoanhSo() {
		return doanhSo;
	}

	public void setDoanhSo(Long doanhSo) {
		this.doanhSo = doanhSo;
	}
	
	public Integer getDiemTichLuy() {
		return (int) (doanhSo / 100_000);
	}
}
