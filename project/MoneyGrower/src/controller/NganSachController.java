package controller;

import java.net.URL;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import BUS.LoaiGiaoDichBUS;
import BUS.NganSachBUS;
import DTO.LoaiGiaoDichDTO;
import DTO.NganSachDTO;
import helper.AlertHelper;
import helper.ConfirmDialogHelper;
import helper.DateFormatHelper;
import helper.MoneyFormatHelper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

public class NganSachController implements Initializable {
	@FXML
	TextField tfGiaTri;
	@FXML
	VBox vbButtons;
	@FXML
	Button btnXacNhan;
	@FXML
	Button btnHuyBo;

	@FXML
	Label lbFormatGiaTri;
	@FXML
	Label lbTieuDe;
	@FXML
	DatePicker dpNgayBatDau;
	@FXML
	DatePicker dpNgayKetThuc;

	@FXML
	ComboBox<LoaiGiaoDichDTO> cbbLoaiNganSach;
	@FXML
	ComboBox<String> cbbMocThoiGian;

	Integer maNguoiDung;
	Integer maNganSach;
	boolean isUpdateForm;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initCbbLoaiNganSach();
		initCbbMocThoiGian();
		initDatePickers();
		initTfGiaTri();
	}

	public void initialize(NganSachDTO nganSach) {
		int indexLoaiNganSach = cbbLoaiNganSach.getItems().stream().map(LoaiGiaoDichDTO::getMaLoaiGiaoDich)
				.collect(Collectors.toList()).indexOf(nganSach.getMaLoaiGiaoDich());
		cbbLoaiNganSach.getSelectionModel().select(indexLoaiNganSach);
		dpNgayBatDau.setValue(nganSach.getNgayBatDau());
		dpNgayKetThuc.setValue(nganSach.getNgayKetThuc());
		tfGiaTri.setText(nganSach.getGiaTri().toString());

		lbTieuDe.setText("THÔNG TIN NGÂN SÁCH");
		btnXacNhan.setText("Sửa ngân sách");
		btnHuyBo.setText("Đóng");

		maNganSach = nganSach.getMaNganSach();
		maNguoiDung = nganSach.getMaNguoiDung();
		isUpdateForm = true;
	}

	public void initialize(int maNguoiDung) {
		this.maNguoiDung = maNguoiDung;
		vbButtons.getChildren().remove(1);
		cbbMocThoiGian.getSelectionModel().selectFirst();
	}

	private void initDatePickers() {
		dpNgayBatDau.setConverter(DateFormatHelper.getDatePickerFormatter());
		dpNgayBatDau.setValue(LocalDate.now());
		dpNgayKetThuc.setConverter(DateFormatHelper.getDatePickerFormatter());
		dpNgayKetThuc.setValue(LocalDate.now());

		dpNgayBatDau.valueProperty().addListener((obs, oldValue, newValue) -> {
			if (newValue != oldValue) {
				cbbMocThoiGian.setValue("Mốc thời gian");
			}
		});

		dpNgayKetThuc.valueProperty().addListener((obs, oldValue, newValue) -> {
			if (newValue != oldValue) {
				cbbMocThoiGian.setValue("Mốc thời gian");
			}
		});
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

	private void initCbbMocThoiGian() {
		cbbMocThoiGian.valueProperty().addListener((obs, oldValue, newValue) -> {
			LocalDate beginDate = null, endDate = null;
			switch (newValue) {
			case "Tuần này": {
				beginDate = LocalDate.now().with(DayOfWeek.MONDAY);
				endDate = LocalDate.now().with(DayOfWeek.SUNDAY);
				break;
			}

			case "Tháng này": {
				beginDate = LocalDate.now().withDayOfMonth(1);
				endDate = LocalDate.now().withDayOfMonth(LocalDate.now().lengthOfMonth());
				break;
			}

			case "Quý này": {
				int quarter = (LocalDate.now().getMonthValue() / 3) + 1;
				int year = LocalDate.now().getYear();

				switch (quarter) {
				case 1: {
					beginDate = LocalDate.of(year, 1, 1);
					endDate = LocalDate.of(year, 3, 31);
					break;
				}
				case 2: {
					beginDate = LocalDate.of(year, 4, 1);
					endDate = LocalDate.of(year, 6, 30);
					break;
				}
				case 3: {
					beginDate = LocalDate.of(year, 7, 1);
					endDate = LocalDate.of(year, 9, 30);
					break;
				}
				case 4: {
					beginDate = LocalDate.of(year, 10, 1);
					endDate = LocalDate.of(year, 12, 31);
					break;
				}
				}
				break;
			}

			case "Năm nay": {
				int year = LocalDate.now().getYear();
				beginDate = LocalDate.of(year, 1, 1);
				endDate = LocalDate.of(year, 12, 31);
				break;
			}
			}

			if (beginDate != null && endDate != null) {
				dpNgayBatDau.setValue(beginDate);
				dpNgayKetThuc.setValue(endDate);
			}
		});

		cbbMocThoiGian.setItems(FXCollections.observableArrayList("Tuần này", "Tháng này", "Quý này", "Năm nay"));
	}

	private void initCbbLoaiNganSach() {
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
		cbbLoaiNganSach.setButtonCell(cellFactory.call(null));
		cbbLoaiNganSach.setCellFactory(cellFactory);

		ObservableList<LoaiGiaoDichDTO> dsLoaiGiaoDich = FXCollections.observableArrayList();
		try {
			LoaiGiaoDichBUS.getDSLoaiGiaoDichChi().stream().forEach(gd -> dsLoaiGiaoDich.add(gd));
		} catch (SQLException ex) {
			AlertHelper.showAlert("Lỗi", "Không thể tải danh sách loại thu chi", "Lỗi database");
		}
		cbbLoaiNganSach.setItems(dsLoaiGiaoDich);
		cbbLoaiNganSach.getSelectionModel().selectFirst();
	}

	public void handleXacNhan() {
		if (tfGiaTri.getText().isEmpty() || Long.parseLong(tfGiaTri.getText()) == 0) {
			AlertHelper.showAlert("Lỗi", "Cập nhật ngân sách thất bại", "Giá trị phải là một số dương");
			return;
		}

		LocalDate ngayBatDau = dpNgayBatDau.getValue();
		LocalDate ngayKetThuc = dpNgayKetThuc.getValue();

		if (ngayBatDau.compareTo(ngayKetThuc) > 0) {
			AlertHelper.showAlert("Lỗi", "Cập nhật ngân sách thất bại",
					"Ngày bắt đầu không được lớn hơn ngày kết thúc");
			return;
		}

		NganSachDTO nganSach = new NganSachDTO(maNganSach,
				cbbLoaiNganSach.getSelectionModel().getSelectedItem().getMaLoaiGiaoDich(), maNguoiDung, ngayBatDau,
				ngayKetThuc, Long.valueOf(tfGiaTri.getText()));

		try {
			boolean isSuccessHandle = isUpdateForm ? NganSachBUS.updateNganSach(nganSach)
					: NganSachBUS.insertNganSach(nganSach);

			if (isSuccessHandle) {
				AlertHelper.showAlert("Thành công", "Cập nhật ngân sách thành công");
				Stage stage = (Stage) tfGiaTri.getScene().getWindow();
				Runnable reloadBudgetBoard = (Runnable) stage.getScene().getUserData();
				reloadBudgetBoard.run();
				stage.close();
			} else {
				if (isUpdateForm) {
					AlertHelper.showAlert("Thất bại", "Cập nhật ngân sách thất bại");
				} else {
					AlertHelper.showAlert("Thất bại", "Cập nhật ngân sách thất bại",
							"Ngân sách cần thêm có thời hạn đã tồn tại");
				}
			}
		} catch (SQLException e) {
			AlertHelper.showAlert("Thất bại", "Cập nhật sách thất bại", "Lỗi database");
		}
	}

	public void handleXoa() {
		if (ConfirmDialogHelper.confirm("Xác nhận xoá ngân sách?")) {
			try {
				if (NganSachBUS.deleteNganSach(maNganSach)) {
					AlertHelper.showAlert("Thành công", "Đã xoá ngân sách");
					Stage stage = (Stage) tfGiaTri.getScene().getWindow();
					Runnable reloadBudgetBoard = (Runnable) stage.getScene().getUserData();
					reloadBudgetBoard.run();
					stage.close();
				}
			} catch (SQLException ex) {
				AlertHelper.showAlert("Lỗi", "Không thể xoá ngân sách", "Lỗi database");
			}
		}
	}

	public void handleHuyBo() {
		Stage stage = (Stage) tfGiaTri.getScene().getWindow();
		stage.close();
	}
}
