package controller;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import BUS.GiaoDichBUS;
import BUS.LoaiGiaoDichBUS;
import BUS.NguoiDungBUS;
import DTO.GiaoDichDTO;
import DTO.LoaiGiaoDichDTO;
import DTO.NguoiDungDTO;
import helper.AlertHelper;
import helper.DateFormatHelper;
import helper.MoneyFormatHelper;
import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;

public class GiaoDichController implements Initializable {

	@FXML
	TextField tfGhiChu;
	@FXML
	TextField tfGiaTri;
	@FXML
	Label lbFormatGiaTri;
	@FXML
	DatePicker dpNgayGiaoDich;
	@FXML
	ComboBox<String> cbbLoaiThuChi;
	@FXML
	ComboBox<LoaiGiaoDichDTO> cbbLoaiGiaoDich;

	Integer maNguoiDung;
	Integer maGiaoDich;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initCbbLoaiGiaoDich();
		initCbbLoaiThuChi();
		initDpNgayGiaoDich();
		initTfGiaTri();
	}

	public void initialize(Integer maNguoiDung) {
		this.maNguoiDung = maNguoiDung;
	}

	public void initialize(Integer maNguoiDung, Integer maGiaoDich) {
		this.maNguoiDung = maNguoiDung;
		this.maGiaoDich = maGiaoDich;
	}

	private void initTfGiaTri() {
		tfGiaTri.textProperty().addListener((obs, oldValue, newValue) -> {
			if (!newValue.matches("\\d*")) {
				tfGiaTri.setText(newValue.replaceAll("[^\\d]", ""));
			}
			if (newValue.length() > 12) {
				tfGiaTri.setText(newValue.substring(0, newValue.length() - 1));
			}

			try {
				lbFormatGiaTri.setText(String.format("Giá trị: %s",
						MoneyFormatHelper.format(Long.parseLong(tfGiaTri.getText()), "VND")));
			} catch (NumberFormatException ex) {
				lbFormatGiaTri.setText("Giá trị: 0 VND");
			}
		});
	}

	private void initDpNgayGiaoDich() {
		dpNgayGiaoDich.setConverter(DateFormatHelper.getDatePickerFormatter());
		dpNgayGiaoDich.setValue(LocalDate.now());
	}

	private void initCbbLoaiGiaoDich() {
		cbbLoaiThuChi.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
			ObservableList<LoaiGiaoDichDTO> dsLoaiGiaoDich = FXCollections.observableArrayList();
			try {
				if (newValue.equals("Giao dịch thu")) {
					LoaiGiaoDichBUS.getDSLoaiGiaoDichThu().stream().forEach(gd -> dsLoaiGiaoDich.add(gd));
				} else {
					LoaiGiaoDichBUS.getDSLoaiGiaoDichChi().stream().forEach(gd -> dsLoaiGiaoDich.add(gd));
				}
			} catch (SQLException ex) {
				AlertHelper.showAlert("Lỗi", "Không thể tải danh sách loại thu chi", "Lỗi database");
			}
			cbbLoaiGiaoDich.setItems(dsLoaiGiaoDich);
			cbbLoaiGiaoDich.getSelectionModel().selectFirst();
		});
	}

	private void initCbbLoaiThuChi() {
		Callback<ListView<LoaiGiaoDichDTO>, ListCell<LoaiGiaoDichDTO>> cellFactory = new Callback<ListView<LoaiGiaoDichDTO>, ListCell<LoaiGiaoDichDTO>>() {
			@Override
			public ListCell<LoaiGiaoDichDTO> call(ListView<LoaiGiaoDichDTO> lvLoaiGiaoDich) {
				final ListCell<LoaiGiaoDichDTO> lcLoaiGiaoDich = new ListCell<LoaiGiaoDichDTO>() {
					@Override
					protected void updateItem(LoaiGiaoDichDTO loaiGiaoDich, boolean empty) {
						super.updateItem(loaiGiaoDich, empty);
						if (loaiGiaoDich != null) {
							this.setText(loaiGiaoDich.getTenLoaiGiaoDich());
						} else
							this.setText(null);
					}
				};
				return lcLoaiGiaoDich;
			}
		};
		cbbLoaiGiaoDich.setButtonCell(cellFactory.call(null));
		cbbLoaiGiaoDich.setCellFactory(cellFactory);

		ObservableList<String> dsLoaiThuChi = FXCollections.observableArrayList("Giao dịch chi", "Giao dịch thu");
		cbbLoaiThuChi.setItems(dsLoaiThuChi);
		cbbLoaiThuChi.getSelectionModel().selectFirst();
	}

	public void handleXacNhan() {
		Long giaTri = Long.valueOf(tfGiaTri.getText());
		if (cbbLoaiGiaoDich.getSelectionModel().getSelectedItem().getGiaoDichThu() == false) {
			giaTri = -giaTri;
		}
		
		GiaoDichDTO giaoDich = new GiaoDichDTO(null, cbbLoaiGiaoDich.getSelectionModel().getSelectedItem().getMaLoaiGiaoDich(),
				maNguoiDung, dpNgayGiaoDich.getValue(), giaTri, tfGhiChu.getText());
		try {
			if (GiaoDichBUS.insertGiaoDich(giaoDich)) {
				AlertHelper.showAlert("Thành công", "Thêm giao dịch thành công");
				Stage stage = (Stage) lbFormatGiaTri.getScene().getWindow();
				MainController controller = (MainController) stage.getScene().getUserData();
				controller.loadPaymentBoard();
				stage.close();
			} else {
				AlertHelper.showAlert("Thất bại", "Thêm giao dịch thất bại");
				tfGiaTri.requestFocus();
			}
		} catch (SQLException ex) {
			AlertHelper.showAlert("Thất bại", "Thêm giao dịch thất bại", "Lỗi database");
		}
	}
	
	public void handleHuyBo() {
		Stage stage = (Stage) lbFormatGiaTri.getScene().getWindow();
		stage.close();
	}
}
