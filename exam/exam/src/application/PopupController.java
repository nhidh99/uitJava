package application;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

public class PopupController implements Initializable {

	@FXML
	Label lbTieuDe;
	@FXML
	TextField tfTenKH;
	@FXML
	TextField tfDiaChi;
	@FXML
	DatePicker dpNgaySinh;
	@FXML
	DatePicker dpNgayDK;
	@FXML
	TextField tfSoDienThoai;
	@FXML
	TextField tfDoanhSo;

	String maKH = null;

	@FXML
	public void confirm() {
		String tenKH = tfTenKH.getText();
		String diaChi = tfDiaChi.getText();
		String soDienThoai = tfSoDienThoai.getText();
		LocalDate ngaySinh = dpNgaySinh.getValue();
		LocalDate ngayDK = dpNgayDK.getValue();
		Long doanhSo = Long.parseLong(tfDoanhSo.getText());

		boolean isSuccessExecute = false;
		if (maKH == null) {
			Model model = new Model(tenKH, diaChi, soDienThoai, ngaySinh, ngayDK, doanhSo);
			isSuccessExecute = DataAccess.insertModel(model);
		} else {
			Model model = new Model(maKH, tenKH, diaChi, soDienThoai, ngaySinh, ngayDK, doanhSo);
			isSuccessExecute = DataAccess.updateModel(model);
		}

		if (isSuccessExecute) {
			Stage stage = (Stage) tfTenKH.getScene().getWindow();
			Runnable reload = (Runnable) stage.getScene().getUserData();
			reload.run();
			stage.close();
		}
	}

	@FXML
	public void cancel() {
		Stage stage = (Stage) tfTenKH.getScene().getWindow();
		stage.close();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		dpNgaySinh.setConverter(new StringConverter<LocalDate>() {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

			@Override
			public LocalDate fromString(String dateString) {
				if (dateString == null)
					return null;
				return LocalDate.parse(dateString, formatter);
			}

			@Override
			public String toString(LocalDate localDate) {
				if (localDate == null)
					return "";
				return formatter.format(localDate);
			}
		});
		dpNgaySinh.setValue(LocalDate.now());

		dpNgayDK.setConverter(new StringConverter<LocalDate>() {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

			@Override
			public LocalDate fromString(String dateString) {
				if (dateString == null)
					return null;
				return LocalDate.parse(dateString, formatter);
			}

			@Override
			public String toString(LocalDate localDate) {
				if (localDate == null)
					return "";
				return formatter.format(localDate);
			}
		});
		dpNgayDK.setValue(LocalDate.now());
	}

	public void initModel(Model model) {
		this.maKH = model.getMaKH();
		lbTieuDe.setText("SỬA THÔNG TIN KHÁCH");
		tfTenKH.setText(model.getTenKH());
		tfDiaChi.setText(model.getDiaChi());
		tfSoDienThoai.setText(model.getSoDienThoai());
		dpNgaySinh.setValue(model.getNgaySinhValue());
		dpNgayDK.setValue(model.getNgayDKValue());
		tfDoanhSo.setText(model.getDoanhSo().toString());
	}
}
