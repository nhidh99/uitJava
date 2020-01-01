package DTO;

public class LoaiGiaoDichDTO {
	private Integer maLoaiGiaoDich;
	private String tenLoaiGiaoDich;
	private Boolean giaoDichThu;
	private String bieuTuong;

	public LoaiGiaoDichDTO(Integer maLoaiGiaoDich, String tenLoaiGiaoDich, Boolean giaoDichThu) {
		this.maLoaiGiaoDich = maLoaiGiaoDich;
		this.tenLoaiGiaoDich = tenLoaiGiaoDich;
		this.giaoDichThu = giaoDichThu;
		this.bieuTuong = getBieuTuongByTen(tenLoaiGiaoDich);
	}

	private String getBieuTuongByTen(String tenLoaiGiaoDich) {
		switch (tenLoaiGiaoDich) {
		case "Bạn bè":
		case "Gia đình":
			return "👥";

		case "Di chuyển":
		case "Du lịch":
			return "🚎";

		case "Sức khoẻ":
		case "Bảo hiểm":
			return "🏥";

		case "Quà tặng":
		case "Thưởng":
		case "Được tặng":
			return "🎁";

		case "Ăn uống":
			return "🥗";

		case "Hoá đơn":
		case "Chi phí":
			return "📋";

		case "Mua sắm":
			return "️🛒";

		case "Giáo dục":
			return "🎓";

		case "Kinh doanh":
		case "Bán đồ":
			return "📦";

		case "Cho vay":
		case "Lương":
		case "Tiền lãi":
		case "Thu nợ":
		case "Đi vay":
		case "Trả nợ":
			return "💵";

		default:
			return "?";
		}
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

}
