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

public class NguoiDungController implements Initializable {
	@FXML
	Label lbTenTaiKhoan;

	@FXML
	Label lbFormatSoDu;

	@FXML
	TextField tfNguoiDung;

	@FXML
	TextField tfTongSoDu;

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

	public void initialize(NguoiDungDTO nguoiDung) {
		lbTenTaiKhoan.setText(nguoiDung.getTenTaiKhoan());
		tfNguoiDung.setText(nguoiDung.getTenNguoiDung());
		pfMatKhau.setText(nguoiDung.getMatKhau());
		pfMatKhau2.setText(nguoiDung.getMatKhau());
		tfTongSoDu.setText(nguoiDung.getTongSoDu().toString());
	}

	public void handleCapNhat() {
		if (!(tfNguoiDung.getText().trim().matches("^([^0-9]{1,30})$")
				&& pfMatKhau.getText().matches("^.{6,30}$")
				&& tfTongSoDu.getText().matches("^.{1,}"))) {
			AlertHelper.showAlert("Thất bại", "Cập nhật thông tin người dùng thất bại",
					"- Tên người dùng từ 1 - 30 kí tự và không gồm số.\n"
					+ "- Mật khẩu từ 6 - 30 kí tự.\n"
					+ "- Số dư phải là một số không âm.");
			return;
		}
		
		boolean isRetypeCorrectPassword = pfMatKhau.getText().equals(pfMatKhau2.getText());
		if (isRetypeCorrectPassword) {
			NguoiDungDTO nguoiDung = new NguoiDungDTO(null, lbTenTaiKhoan.getText(), pfMatKhau.getText(),
					tfNguoiDung.getText().trim(), Long.parseLong(tfTongSoDu.getText()));
			try {
				if (NguoiDungBUS.updateNguoiDung(nguoiDung)) {
					AlertHelper.showAlert("Thành công", "Cập nhật thông tin người dùng thành công");
					Stage stage = (Stage) lbFormatSoDu.getScene().getWindow();
					Runnable reloadNguoiDung = (Runnable) stage.getScene().getUserData();
					reloadNguoiDung.run();
					stage.close();
				} else {
					AlertHelper.showAlert("Thất bại", "Cập nhật thông tin người dùng thất bại");
					tfNguoiDung.requestFocus();
				}
			} catch (SQLException ex) {
				AlertHelper.showAlert("Thất bại", "Cập nhật thông tin người dùng thất bại", "Lỗi database");
			}
		} else {
			AlertHelper.showAlert("Thất bại", "Câp nhật thông tin người dùng thất bại",
					"Mật khẩu nhập lại không trùng khớp");
		}
	}
	
	public void handleHuyBo() {
		Stage stage = (Stage) lbFormatSoDu.getScene().getWindow();
		stage.close();
	}
}