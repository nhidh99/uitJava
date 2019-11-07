package controller;

import java.sql.SQLException;

import BUS.NguoiDungBUS;
import DTO.NguoiDungDTO;
import helper.AlertHelper;
import helper.MoneyFormatHelper;
import helper.PopupHelper;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

public class MainController {

	@FXML
	Label lbNguoiDung;
	@FXML
	Label lbTaiKhoan;
	@FXML
	Label lbSoDu;

	public void initialize(NguoiDungDTO nguoiDung) {
		lbNguoiDung.setText(nguoiDung.getTenNguoiDung());
		lbTaiKhoan.setText(nguoiDung.getTenTaiKhoan());
		lbSoDu.setText(MoneyFormatHelper.format(nguoiDung.getTongSoDu(), "VND"));
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
