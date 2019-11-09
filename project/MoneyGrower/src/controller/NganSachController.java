package controller;

import java.net.URL;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ResourceBundle;

import BUS.LoaiGiaoDichBUS;
import BUS.NganSachBUS;
import DTO.LoaiGiaoDichDTO;
import DTO.NganSachDTO;
import helper.AlertHelper;
import helper.DateFormatHelper;
import helper.MoneyFormatHelper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
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
	Label lbNgayBatDau;
	@FXML
	Label lbNgayKetThuc;

	@FXML
	ComboBox<LoaiGiaoDichDTO> cbbLoaiNganSach;
	@FXML
	ComboBox<String> cbbMocThoiGian;

	Integer maNguoiDung;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initCbbLoaiNganSach();
		initCbbMocThoiGian();
		initTfGiaTri();
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
			lbNgayBatDau.setText(DateFormatHelper.fromLocalDate(beginDate));
			lbNgayKetThuc.setText(DateFormatHelper.fromLocalDate(endDate));
		});

		cbbMocThoiGian.setItems(FXCollections.observableArrayList("Tuần này", "Tháng này", "Quý này", "Năm nay"));
		cbbMocThoiGian.getSelectionModel().selectFirst();
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

	public void initialize(int maNguoiDung) {
		this.maNguoiDung = maNguoiDung;
		vbButtons.getChildren().remove(1);
	}

	public void handleXacNhan() {
		LocalDate ngayBatDau = DateFormatHelper.fromString(lbNgayBatDau.getText());
		LocalDate ngayKetThuc = DateFormatHelper.fromString(lbNgayKetThuc.getText());
		
		NganSachDTO nganSach = new NganSachDTO(null, cbbLoaiNganSach.getSelectionModel().getSelectedItem().getMaLoaiGiaoDich(), 
				maNguoiDung, ngayBatDau, ngayKetThuc, Long.valueOf(tfGiaTri.getText()));
		
		try {
			if (NganSachBUS.insertNganSach(nganSach)) {
				AlertHelper.showAlert("Thành công", "Thêm ngân sách thành công");
				Stage stage = (Stage) tfGiaTri.getScene().getWindow();
				MainController controller = (MainController) stage.getScene().getUserData();
				controller.loadBudgetBoard();
				stage.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			AlertHelper.showAlert("Thất bại", "Thêm ngân sách thất bại", "Lỗi database");
		}
	}
	
	public void handleHuyBo() {
		Stage stage = (Stage) tfGiaTri.getScene().getWindow();
		stage.close();
	}
}
