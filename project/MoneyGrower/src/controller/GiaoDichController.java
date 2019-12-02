package controller;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import BUS.GiaoDichBUS;
import BUS.LoaiGiaoDichBUS;
import DTO.GiaoDichDTO;
import DTO.LoaiGiaoDichDTO;
import helper.AlertHelper;
import helper.ConfirmDialogHelper;
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
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;

public class GiaoDichController implements Initializable {

	@FXML
	TextField tfGhiChu;
	@FXML
	TextField tfGiaTri;
	@FXML
	Label lbTieuDe;
	@FXML
	Label lbFormatGiaTri;
	@FXML
	DatePicker dpNgayGiaoDich;
	@FXML
	ComboBox<String> cbbLoaiThuChi;
	@FXML
	ComboBox<LoaiGiaoDichDTO> cbbLoaiGiaoDich;
	@FXML
	VBox vbButtons;
	@FXML
	Button btnXacNhan;
	@FXML
	Button btnHuyBo;

	Integer maNguoiDung;
	Integer maGiaoDich;
	boolean isUpdateForm;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initCbbLoaiGiaoDich();
		initCbbLoaiThuChi();
		initDpNgayGiaoDich();
		initTfGiaTri();
	}

	public void initialize(Integer maNguoiDung) {
		this.maNguoiDung = maNguoiDung;
		vbButtons.getChildren().remove(1);
		tfGiaTri.requestFocus();
	}

	public void initialize(GiaoDichDTO giaoDich) {
		this.maGiaoDich = giaoDich.getMaGiaoDich();
		if (giaoDich.getGiaTri() < 0) {
			cbbLoaiThuChi.getSelectionModel().selectFirst();
		} else {
			cbbLoaiThuChi.getSelectionModel().selectLast();
		}

		cbbLoaiGiaoDich.getSelectionModel()
				.select(cbbLoaiGiaoDich.getItems().stream()
						.filter(lgd -> lgd.getMaLoaiGiaoDich().equals(giaoDich.getMaLoaiGiaoDich()))
						.collect(Collectors.toList()).get(0));

		lbTieuDe.setText("CHI TIẾT GIAO DỊCH");
		btnXacNhan.setText("Sửa giao dịch");
		btnHuyBo.setText("Đóng");
		dpNgayGiaoDich.setValue(giaoDich.getNgayGiaoDich());
		tfGhiChu.setText(giaoDich.getGhiChu() == null ? "" : giaoDich.getGhiChu());
		tfGiaTri.setText(giaoDich.getGiaTri().toString());
		isUpdateForm = true;
		tfGiaTri.requestFocus();
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
		if (tfGhiChu.getText().trim().length() > 20 || tfGiaTri.getText().isEmpty()
				|| Long.valueOf(tfGiaTri.getText()) == 0) {
			AlertHelper.showAlert("Thất bại", "Cập nhật giao dịch thất bại",
					"- Ghi chú không quá 20 kí tự.\n" + "- Giá trị phải là một số dương");
			return;
		}

		Long giaTri = Long.valueOf(tfGiaTri.getText());
		if (cbbLoaiGiaoDich.getSelectionModel().getSelectedItem().getGiaoDichThu() == false) {
			giaTri = -giaTri;
		}

		GiaoDichDTO giaoDich = new GiaoDichDTO(maGiaoDich,
				cbbLoaiGiaoDich.getSelectionModel().getSelectedItem().getMaLoaiGiaoDich(), maNguoiDung,
				dpNgayGiaoDich.getValue(), giaTri, tfGhiChu.getText().trim());

		try {
			boolean isSuccessHandle = isUpdateForm ? GiaoDichBUS.updateGiaoDich(giaoDich)
					: GiaoDichBUS.insertGiaoDich(giaoDich);
			if (isSuccessHandle) {
				AlertHelper.showAlert("Thành công", "Cập nhật giao dịch thành công");
				Stage stage = (Stage) lbFormatGiaTri.getScene().getWindow();
				Runnable reloadPaymentBoard = (Runnable) stage.getScene().getUserData();
				reloadPaymentBoard.run();
				stage.close();
			} else {
				AlertHelper.showAlert("Thất bại", "Cập nhật giao dịch thất bại");
				tfGiaTri.requestFocus();
			}
		} catch (SQLException ex) {
			AlertHelper.showAlert("Thất bại", "Cập nhật giao dịch thất bại", "Lỗi database");
		}
	}

	public void handleHuyBo() {
		Stage stage = (Stage) lbFormatGiaTri.getScene().getWindow();
		stage.close();
	}

	public void handleXoa() {
		if (ConfirmDialogHelper.confirm("Xác nhận xoá giao dịch?")) {
			try {
				if (GiaoDichBUS.deleteGiaoDich(maGiaoDich)) {
					AlertHelper.showAlert("Thành công", "Xoá giao dịch thành công");
					Stage stage = (Stage) lbFormatGiaTri.getScene().getWindow();
					Runnable reloadPaymentBoard = (Runnable) stage.getScene().getUserData();
					reloadPaymentBoard.run();
					stage.close();
				} else {
					AlertHelper.showAlert("Thất bại", "Xoá giao dịch thất bại");
				}
			} catch (SQLException ex) {
				AlertHelper.showAlert("Thất bại", "Xoá giao dịch thất bại", "Lỗi database");
			}
		}
	}
}
