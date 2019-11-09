package controller;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import BUS.NganSachBUS;
import BUS.NguoiDungBUS;
import BUS.TKGiaoDichBUS;
import DTO.NganSachDTO;
import DTO.NguoiDungDTO;
import DTO.TKGiaoDichDTO;
import custom.BudgetBox;
import custom.PaymentBoard;
import helper.AlertHelper;
import helper.MoneyFormatHelper;
import helper.PopupHelper;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.chart.PieChart;

public class MainController implements Initializable {

	@FXML
	Label lbMaNguoiDung;
	@FXML
	Label lbNguoiDung;
	@FXML
	Label lbTaiKhoan;
	@FXML
	Label lbSoDu;
	@FXML
	Label lbTongThu;
	@FXML
	Label lbTongChi;

	@FXML
	TilePane tpSoGiaoDich;
	@FXML
	TilePane tpNganSach;

	@FXML
	Spinner<Integer> snThangTK;
	@FXML
	Spinner<Integer> snNamTK;

	@FXML
	PieChart pcThongKe;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		snThangTK.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 12, LocalDate.now().getMonthValue()));
		snNamTK.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(1900, 2100, LocalDate.now().getYear()));
	}

	public void initialize(NguoiDungDTO nguoiDung) {
		if (nguoiDung.getMaNguoiDung() != null) {
			lbMaNguoiDung.setText(nguoiDung.getMaNguoiDung().toString());
		}
		lbNguoiDung.setText(nguoiDung.getTenNguoiDung());
		lbTaiKhoan.setText(nguoiDung.getTenTaiKhoan());
		lbSoDu.setText(MoneyFormatHelper.format(nguoiDung.getTongSoDu(), "VND"));

		try {
			loadPaymentBoard();
			loadBudgetBoard();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public void loadPaymentBoard() throws NumberFormatException, SQLException {
		tpSoGiaoDich.getChildren().clear();
		tpSoGiaoDich.getChildren().add(new PaymentBoard(Integer.parseInt(lbMaNguoiDung.getText()), this));

		try {
			if (pcThongKe.getTitle().contains("Tổng chi")) {
				handleThongKeTongThu();
			} else
				handleThongKeTongChi();
		} catch (Exception e) {
		}
	}

	public void loadBudgetBoard() throws SQLException {
		tpNganSach.getChildren().clear();
		for (NganSachDTO nganSach : NganSachBUS.getDSNganSach(Integer.parseInt(lbMaNguoiDung.getText()))) {
			tpNganSach.getChildren().add(new BudgetBox(nganSach));
		}
	}

	public void handleThemGiaoDich() {
		Stage stage = PopupHelper.createStage("/application/payment.fxml", 370, 700);
		FXMLLoader loader = (FXMLLoader) stage.getUserData();
		GiaoDichController controller = loader.getController();
		controller.initialize(Integer.parseInt(lbMaNguoiDung.getText()));
		stage.getScene().setUserData(this);
		stage.showAndWait();
	}

	public void handleCapNhatNguoiDung() {
		try {
			NguoiDungDTO nguoiDung = NguoiDungBUS.getNguoiDungByUsername(lbTaiKhoan.getText());
			Stage stage = PopupHelper.createStage("/application/user.fxml", 370, 700);
			FXMLLoader loader = (FXMLLoader) stage.getUserData();
			NguoiDungController controller = loader.getController();
			controller.initialize(nguoiDung);
			stage.getScene().setUserData(this);
			stage.showAndWait();
		} catch (SQLException ex) {
			AlertHelper.showAlert("Lỗi", "Không thể cập nhật người dùng", "Lỗi database");
		}
	}

	public void handleThongKeTongThu() {
		int thang = snThangTK.getValue();
		int nam = snNamTK.getValue();
		int maNguoiDung = Integer.parseInt(lbMaNguoiDung.getText());

		try {
			pcThongKe.getData().clear();
			List<TKGiaoDichDTO> dsThongKe = TKGiaoDichBUS.getDSTongThu(maNguoiDung, thang, nam);
			dsThongKe.stream().forEach(tk -> {
				pcThongKe.getData().add(new PieChart.Data(tk.getTenLoaiGiaoDich(), tk.getTongGiaTri()));
			});

			Long tongThu = dsThongKe.stream().mapToLong(TKGiaoDichDTO::getTongGiaTri).sum();
			if (tongThu == 0) {
				AlertHelper.showAlert("Không có báo cáo", "Trong tháng không có giao dịch thu");
				pcThongKe.setTitle(null);
			} else {
				pcThongKe.setTitle("Tổng thu: " + MoneyFormatHelper.format(tongThu, "VND"));
			}
		} catch (SQLException ex) {
			AlertHelper.showAlert("Lỗi", "Không thể tải danh sách thống kê", "Lỗi database");
		}
	}

	public void handleThongKeTongChi() {
		int thang = snThangTK.getValue();
		int nam = snNamTK.getValue();
		int maNguoiDung = Integer.parseInt(lbMaNguoiDung.getText());

		try {
			pcThongKe.getData().clear();
			List<TKGiaoDichDTO> dsThongKe = TKGiaoDichBUS.getDSTongChi(maNguoiDung, thang, nam);
			dsThongKe.stream().forEach(tk -> {
				pcThongKe.getData().add(new PieChart.Data(tk.getTenLoaiGiaoDich(), tk.getTongGiaTri()));
			});

			Long tongChi = dsThongKe.stream().mapToLong(TKGiaoDichDTO::getTongGiaTri).sum();
			if (tongChi == 0) {
				AlertHelper.showAlert("Không có báo cáo", "Trong tháng không có giao dịch chi");
				pcThongKe.setTitle(null);
			} else {
				pcThongKe.setTitle("Tổng chi: " + MoneyFormatHelper.format(tongChi, "VND"));
			}
		} catch (SQLException ex) {
			AlertHelper.showAlert("Lỗi", "Không thể tải danh sách thống kê", "Lỗi database");
		}
	}

	public void handleThemNganSach() {
		Stage stage = PopupHelper.createStage("/application/budget.fxml", 370, 700);
		FXMLLoader loader = (FXMLLoader) stage.getUserData();
		NganSachController controller = loader.getController();
		controller.initialize(Integer.parseInt(lbMaNguoiDung.getText()));
		stage.getScene().setUserData(this);
		stage.showAndWait();
	}

	public void handleDangXuat() {
		Window window = (Stage) lbNguoiDung.getScene().getWindow();
		window.fireEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSE_REQUEST));
	}
}
