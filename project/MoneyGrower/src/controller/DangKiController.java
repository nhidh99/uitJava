package controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import BUS.NguoiDungBUS;
import DTO.NguoiDungDTO;
import helper.AlertHelper;
import helper.MoneyFormatHelper;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DangKiController implements Initializable {
	@FXML
	TextField tfTenNguoiDung;

	@FXML
	TextField tfTaiKhoan;

	@FXML
	TextField tfTongSoDu;

	@FXML
	Label lbFormatSoDu;

	@FXML
	PasswordField pfMatKhau;

	@FXML
	PasswordField pfMatKhau2;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		tfTongSoDu.textProperty().addListener((obs, oldValue, newValue) -> {
			if (!newValue.matches("\\d*")) {
				tfTongSoDu.setText(newValue.replaceAll("[^\\d]", ""));
			}
			if (newValue.length() > 12) {
				tfTongSoDu.setText(newValue.substring(0, newValue.length() - 1));
			}

			try {
				lbFormatSoDu.setText(String.format("Số dư: %s",
						MoneyFormatHelper.format(Long.parseLong(tfTongSoDu.getText()), "VND")));
			} catch (NumberFormatException ex) {
				lbFormatSoDu.setText("Số dư: 0 VND");
			}
		});
	}

	public void handleDangKi() {
		if (!(tfTenNguoiDung.getText().trim().matches("^([^0-9]{1,30})$")
				&& tfTaiKhoan.getText().trim().matches("^.[A-Za-z0-9]{6,30}$")
				&& pfMatKhau.getText().matches("^.{6,30}$")
				&& tfTongSoDu.getText().matches("^.{1,}"))) {
			AlertHelper.showAlert("Thất bại", "Cập nhật thông tin người dùng thất bại",
					"- Tên người dùng từ 1 - 30 kí tự và không gồm số.\n"
					+ "- Tên tài khoản từ 6 - 30 kí tự chỉ gồm số, chữ thường, chữ hoa.\n"
					+ "- Mật khẩu từ 6 - 30 kí tự.\n"
					+ "- Số dư phải là một số không âm.");
			return;
		}
		
		boolean isRetypeCorrectPassword = pfMatKhau.getText().equals(pfMatKhau2.getText());
		if (isRetypeCorrectPassword) {
			NguoiDungDTO nguoiDung = new NguoiDungDTO(null, tfTaiKhoan.getText(), pfMatKhau.getText(),
					tfTenNguoiDung.getText(), Long.parseLong(tfTongSoDu.getText()));
			try {
				if (NguoiDungBUS.dangKi(nguoiDung)) {
					AlertHelper.showAlert("Thành công", "Đã tạo thành công người dùng " + nguoiDung.getTenNguoiDung());
					Stage stage = (Stage) tfTaiKhoan.getScene().getWindow();
					stage.close();
				} else {
					AlertHelper.showAlert("Lỗi", "Tên đăng nhập đã tồn tại");
					tfTaiKhoan.requestFocus();
				}
			} catch (SQLException ex) {
				AlertHelper.showAlert("Lỗi", "Lỗi database");
			}
		} else {
			AlertHelper.showAlert("Lỗi", "Mật khẩu nhập lại không trùng khớp");
		}
	}

	public void handleThoat() {
		Stage stage = (Stage) tfTaiKhoan.getScene().getWindow();
		stage.close();
	}
}
