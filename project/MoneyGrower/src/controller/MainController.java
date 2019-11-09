package controller;

import java.sql.SQLException;
import java.time.LocalDate;

import BUS.NguoiDungBUS;
import DTO.NguoiDungDTO;
import custom.PaymentBoard;
import helper.AlertHelper;
import helper.MoneyFormatHelper;
import helper.PopupHelper;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

public class MainController {

	@FXML
	Label lbMaNguoiDung;
	@FXML
	Label lbNguoiDung;
	@FXML
	Label lbTaiKhoan;
	@FXML
	Label lbSoDu;

	@FXML
	TilePane tpSoGiaoDich;

	public void initialize(NguoiDungDTO nguoiDung) {
		if (nguoiDung.getMaNguoiDung() != null) {
			lbMaNguoiDung.setText(nguoiDung.getMaNguoiDung().toString());
		}
		lbNguoiDung.setText(nguoiDung.getTenNguoiDung());
		lbTaiKhoan.setText(nguoiDung.getTenTaiKhoan());
		lbSoDu.setText(MoneyFormatHelper.format(nguoiDung.getTongSoDu(), "VND"));
		loadPaymentBoard();
	}

	public void loadPaymentBoard() {
		try {
			tpSoGiaoDich.getChildren().clear();
			tpSoGiaoDich.getChildren().add(new PaymentBoard(Integer.parseInt(lbMaNguoiDung.getText()),
					LocalDate.now().getMonthValue(), LocalDate.now().getYear()));
		} catch (SQLException e) {
			AlertHelper.showAlert("Lỗi", "Lỗi kết nối CSDL!");
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

	public void handleDangXuat() {
		Window window = (Stage) lbNguoiDung.getScene().getWindow();
		window.fireEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSE_REQUEST));
	}
}
