package controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import BUS.NguoiDungBUS;
import DTO.NguoiDungDTO;
import helper.AlertHelper;
import helper.ConfirmDialogHelper;
import helper.PopupHelper;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class DangNhapController implements Initializable {
	
	private Stage mainStage;
	private MainController mainController;
	
	@FXML
	TextField tfTaiKhoan;
	
	@FXML
	PasswordField pfMatKhau;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		mainStage = PopupHelper.createStage("/application/main.fxml", 550, 825);
		mainStage.getScene().getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, e -> {
			if (ConfirmDialogHelper.confirm("Xác nhận thoát?")) {
				PopupHelper.createStage("/application/login.fxml", 375, 560).show();
			} else {
				e.consume();
			}
		});
		FXMLLoader loader = (FXMLLoader) mainStage.getUserData();
		mainController = loader.getController();
	}
	
	public void handleDangNhap() {
		String username = tfTaiKhoan.getText();
		String password = pfMatKhau.getText();

		try {
			if (NguoiDungBUS.checkDangNhap(username, password)) {
				Stage loginStage = (Stage) tfTaiKhoan.getScene().getWindow();
				NguoiDungDTO nguoiDung = NguoiDungBUS.getNguoiDungByUsername(username);
				loginStage.close();
				mainController.initialize(nguoiDung);
				mainStage.showAndWait();
			} else {
				AlertHelper.showAlert("Lỗi", "Không thể đăng nhập", "Sai tên tài khoản hoặc mật khẩu");
				tfTaiKhoan.requestFocus();
			}
		} catch (SQLException e) {
			AlertHelper.showAlert("Lỗi", "Không thể đăng nhập", "Lỗi database");
		}
	}
	
	public void handleDangKi() {
		Stage signInStage = PopupHelper.createStage("/application/signup.fxml", 375, 700);
		signInStage.showAndWait();
	}
	
	public void handleThoat() {
		Platform.exit();
	}
}
