package application;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.control.TableColumn;
import javafx.scene.control.Label;
import javafx.scene.control.ComboBox;

public class MainController implements Initializable {

	@FXML
	TableView<Model> tvKH;
	@FXML
	TableColumn<Model, Integer> tcSTT;
	@FXML
	TableColumn<Model, String> tcMaKH;
	@FXML
	TableColumn<Model, String> tcTenKH;
	@FXML
	TableColumn<Model, String> tcDiaChi;
	@FXML
	TableColumn<Model, String> tcSoDienThoai;
	@FXML
	TableColumn<Model, String> tcNgaySinh;
	@FXML
	TableColumn<Model, String> tcNgayDK;
	@FXML
	TableColumn<Model, Long> tcDoanhSo;
	@FXML
	TableColumn<Model, Integer> tcDiemTichLuy;
	@FXML
	ComboBox<String> cbbChucNang;

	@FXML
	Label lbTK_TongSoKH;
	@FXML
	Label lbTK_SoKHTT;
	@FXML
	Label lbTK_KhachMaxDoanhSo;
	@FXML
	Label lbTK_SoKHQ1;
	@FXML
	Label lbTK_TBDoanhSo;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initTable();
		initCombobox();
		loadTable();
		loadSummary();
	}

	void initTable() {
		tcSTT.setCellValueFactory(
				column -> new ReadOnlyObjectWrapper<Integer>(tvKH.getItems().indexOf(column.getValue()) + 1));
		tcMaKH.setCellValueFactory(new PropertyValueFactory<>("MaKH"));
		tcTenKH.setCellValueFactory(new PropertyValueFactory<>("TenKH"));
		tcDiaChi.setCellValueFactory(new PropertyValueFactory<>("DiaChi"));
		tcSoDienThoai.setCellValueFactory(new PropertyValueFactory<>("SoDienThoai"));
		tcNgaySinh.setCellValueFactory(new PropertyValueFactory<>("NgaySinh"));
		tcNgayDK.setCellValueFactory(new PropertyValueFactory<>("NgayDK"));
		tcDoanhSo.setCellValueFactory(new PropertyValueFactory<>("DoanhSo"));
		tcDiemTichLuy.setCellValueFactory(new PropertyValueFactory<>("DiemTichLuy"));
	}

	void initCombobox() {
		cbbChucNang.getItems().add("All");
		cbbChucNang.getItems().add("Thân thiết");
		cbbChucNang.getItems().add("Lâu nhất");
		cbbChucNang.getItems().add("Gần nhất");
		cbbChucNang.getItems().add("Sắp sinh nhật");
		cbbChucNang.getSelectionModel().selectFirst();

		cbbChucNang.valueProperty().addListener((obs, oldVal, newVal) -> {
			switch (newVal) {
			case "All":
				loadTable();
				break;
			case "Thân thiết":
				loadTableKHTT();
				break;
			case "Lâu nhất":
				loadTableKHLN();
				break;

			case "Gần nhất":
				loadTableKHGN();
				break;

			case "Sắp sinh nhật":
				loadTableKHSNN();
				break;
			}
		});
	}

	void loadTableKHLN() {
		ObservableList<Model> modelList = FXCollections.observableArrayList();
		DataAccess.getModelLauNhatList().stream().forEach(model -> modelList.add(model));
		tvKH.setItems(modelList);
	}

	void loadTableKHGN() {
		ObservableList<Model> modelList = FXCollections.observableArrayList();
		DataAccess.getModelGanNhatList().stream().forEach(model -> modelList.add(model));
		tvKH.setItems(modelList);
	}

	void loadTableKHSNN() {
		ObservableList<Model> modelList = FXCollections.observableArrayList();
		DataAccess.getModelSapSinhNhatList().stream().forEach(model -> modelList.add(model));
		tvKH.setItems(modelList);
	}

	void loadTableKHTT() {
		ObservableList<Model> modelList = FXCollections.observableArrayList();
		DataAccess.getModelThanThietList().stream().forEach(model -> modelList.add(model));
		tvKH.setItems(modelList);
	}

	void loadTable() {
		ObservableList<Model> modelList = FXCollections.observableArrayList();
		DataAccess.getModelList().stream().forEach(model -> modelList.add(model));
		tvKH.setItems(modelList);
	}

	void loadSummary() {
		Integer tongSoKH = DataAccess.getTongSoKH();
		Integer tongSoKHTT = DataAccess.getTongSoKHTT();
		Integer tongSoKHQ1 = DataAccess.getTongSoKHQ1();
		List<String> listKHMaxDoanhSo = DataAccess.getListKhachMaxDoanhSo();
		Double tbDoanhSoKH = DataAccess.getDoanhSoTB();

		lbTK_TongSoKH.setText(tongSoKH.toString());
		lbTK_SoKHTT.setText(tongSoKHTT.toString());
		lbTK_SoKHQ1.setText(tongSoKHQ1.toString());
		lbTK_KhachMaxDoanhSo.setText(String.join(", ", listKHMaxDoanhSo));
		lbTK_TBDoanhSo.setText(tbDoanhSoKH.toString());
	}

	Runnable reload = () -> {
		loadTable();
		loadSummary();
	};

	@FXML
	public void delete() {
		try {
			Model model = tvKH.getSelectionModel().getSelectedItem();
			if (DataAccess.deleteModel(model.getMaKH())) {
				reload.run();
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void insert() {
		Stage stage = PopupHelper.createStage("/application/popup.fxml", 370, 430);
		stage.getScene().setUserData(reload);
		stage.showAndWait();
	}

	@FXML
	public void update() {
		Model model = tvKH.getSelectionModel().getSelectedItem();
		Stage stage = PopupHelper.createStage("/application/popup.fxml", 370, 430);
		FXMLLoader loader = (FXMLLoader) stage.getUserData();
		PopupController controller = loader.getController();
		controller.initModel(model);
		stage.getScene().setUserData(reload);
		stage.showAndWait();
	}
}
