package controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import BUS.NguoiDungBUS;
import helper.ConfirmDialogHelper;
import helper.PopupHelper;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class LoginController implements Initializable {
	
	private Stage mainStage;
	private MainController mainController;
	
	@FXML
	TextField tfTaiKhoan;
	
	@FXML
	PasswordField pfMatKhau;
	
	public void handleDangNhap() {
		String username = tfTaiKhoan.getText();
		String password = pfMatKhau.getText();

		try {
			if (NguoiDungBUS.checkDangNhap(username, password)) {
				Stage loginStage = (Stage) tfTaiKhoan.getScene().getWindow();
				loginStage.close();
				mainStage.showAndWait();
			} else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Lỗi");
				alert.setHeaderText("Không thể đăng nhập!");
				alert.setContentText("Sai tên tài khoản hoặc mật khẩu!");
				alert.showAndWait();
			}
		} catch (SQLException e) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Lỗi");
			alert.setHeaderText("Không thể đăng nhập!");
			alert.setContentText("Lỗi database!");
			alert.showAndWait();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		mainStage = PopupHelper.createStage("/application/main.fxml", 550, 825);
		mainStage.getScene().getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, e -> {
			if (ConfirmDialogHelper.confirm("Xác nhận thoát?")) {
				PopupHelper.createStage("/application/login.fxml", 375, 465).show();
			} else {
				e.consume();
			}
		});
		FXMLLoader loader = (FXMLLoader) mainStage.getUserData();
		mainController = loader.getController();
	}
}
