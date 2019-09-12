package controller;

import model.*;

import java.util.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

public class StudentController implements Initializable {

	private ObservableList<StudentDTO> students = FXCollections.observableArrayList();

	@FXML
	private TableView<StudentDTO> tvStudent;

	@FXML
	private TableColumn<StudentDTO, String> tcNumber;

	@FXML
	private TableColumn<StudentDTO, String> tcStudentId;

	@FXML
	private TableColumn<StudentDTO, String> tcFirstName;

	@FXML
	private TableColumn<StudentDTO, String> tcLastName;

	@FXML
	private TableColumn<StudentDTO, String> tcGender;

	@FXML
	private TableColumn<StudentDTO, String> tcAddress;

	@FXML
	private TableColumn<StudentDTO, String> tcPhoneNumber;

	@FXML
	private ChoiceBox<String> cbGender;

	@FXML
	private TextField tfStudentId;

	@FXML
	private TextField tfLastName;

	@FXML
	private TextField tfFirstName;

	@FXML
	private TextField tfAddress;

	@FXML
	private TextField tfPhoneNumber;

	@FXML
	private Button btnDeleteStudent;
	
	@FXML
	private Button btnUpdateStudent;

	private void loadGenderChoices() {
		cbGender.setItems(FXCollections.observableArrayList("Nam", "Nữ"));
		cbGender.setValue("Nam");
	}

	private void loadStudentsColumns() {
		tcNumber.setCellValueFactory(new PropertyValueFactory<>("Number"));
		tcStudentId.setCellValueFactory(new PropertyValueFactory<>("StudentId"));
		tcFirstName.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
		tcLastName.setCellValueFactory(new PropertyValueFactory<>("LastName"));
		tcGender.setCellValueFactory(new PropertyValueFactory<>("Gender"));
		tcAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
		tcPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("PhoneNumber"));
	}

	private void loadStudentsTable() {
		ArrayList<StudentDTO> studentList;
		try {
			studentList = StudentBUS.loadStudents();
			if (studentList != null) {
				for (StudentDTO std : studentList) {
					students.add(std);
				}
				tvStudent.setItems(students);
			} else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Error");
				alert.setHeaderText("Cannot load students list!");
				alert.setContentText("Something wrong when retrieve data from database!");
				alert.showAndWait();
			}
		} catch (SQLException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Cannot load students!");
			alert.setContentText("Something wrong with database connection!");
			e.printStackTrace();
		}
	}

	private void initComponent() {
		loadGenderChoices();
		loadStudentsColumns();
		loadStudentsTable();

		// row enter listener in tableView Student
		tvStudent.getSelectionModel().selectedIndexProperty().addListener(e -> {
			StudentDTO selectedStudent = tvStudent.getSelectionModel().getSelectedItem();
			tfStudentId.setText(selectedStudent.getStudentId());
			tfFirstName.setText(selectedStudent.getFirstName());
			tfLastName.setText(selectedStudent.getLastName());
			tfAddress.setText(selectedStudent.getAddress());
			tfPhoneNumber.setText(selectedStudent.getPhoneNumber());
			cbGender.setValue(selectedStudent.getGender());
		});

		if (tvStudent.getItems().size() > 0) {
			tvStudent.getSelectionModel().selectFirst();
			btnDeleteStudent.setDisable(false);
			btnUpdateStudent.setDisable(false);
		} else {
			btnDeleteStudent.setDisable(true);
			btnUpdateStudent.setDisable(true);
		}
		tfStudentId.requestFocus();
	}

	private void reloadViewAfterUpdateStudents() {
		tfStudentId.clear();
		tfFirstName.clear();
		tfLastName.clear();
		tfAddress.clear();
		tfPhoneNumber.clear();
		tvStudent.getItems().clear();
		loadStudentsTable();
		tfStudentId.requestFocus();

		if (tvStudent.getItems().size() > 0) {
			tvStudent.getSelectionModel().selectFirst();
			btnDeleteStudent.setDisable(false);
			btnUpdateStudent.setDisable(false);
		} else {
			btnDeleteStudent.setDisable(true);
			btnUpdateStudent.setDisable(true);
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initComponent();
	}

	@FXML
	private void addStudent() {
		StudentDTO student = new StudentDTO();
		student.setStudentId(tfStudentId.getText());
		student.setFirstName(tfFirstName.getText());
		student.setLastName(tfLastName.getText());
		student.setGender(cbGender.getSelectionModel().getSelectedItem());
		student.setPhoneNumber(tfPhoneNumber.getText());
		student.setAddress(tfAddress.getText());

		try {
			if (StudentBUS.addStudent(student)) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Success");
				alert.setHeaderText("Successfully add student");
				alert.setContentText(
						String.format("Successfully add student %s %s", student.getLastName(), student.getFirstName()));
				alert.showAndWait();
				reloadViewAfterUpdateStudents();
			} else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Error");
				alert.setHeaderText("Cannot add student!");
				alert.setContentText("Please check these conditions:\n" + "- The information fields cannot be blank\n"
						+ "- Student Id must be unique.\n" + "- First/last name only contains letter and spacing.\n"
						+ "- Phone number only contains number.\n");
				alert.showAndWait();
			}
		} catch (SQLException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Cannot add student!");
			alert.setContentText("Something wrong with database connection or duplicate Student Id!");
			alert.showAndWait();
		}
	}

	@FXML
	private void deleteStudent() {
		try {
			String studentId = tvStudent.getSelectionModel().getSelectedItem().getStudentId();

			if (StudentBUS.deleteStudentById(studentId)) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Success");
				alert.setHeaderText("Successfully delete student");
				alert.setContentText("Successfully delete student with Id: " + studentId);
				alert.showAndWait();
				reloadViewAfterUpdateStudents();
			} else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Error");
				alert.setHeaderText("Cannot delete student!");
				alert.setContentText("Something wrong with database connection!");
				alert.showAndWait();
			}
		} catch (SQLException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Cannot delete student!");
			alert.setContentText("Something wrong with database connection!");
			alert.showAndWait();
		}
	}
	
	@FXML
	private void updateStudent() {
		try {
			String studentId = tvStudent.getSelectionModel().getSelectedItem().getStudentId();
			StudentDTO student = new StudentDTO();
			student.setStudentId(studentId);
			student.setFirstName(tfFirstName.getText());
			student.setLastName(tfLastName.getText());
			student.setGender(cbGender.getSelectionModel().getSelectedItem());
			student.setPhoneNumber(tfPhoneNumber.getText());
			student.setAddress(tfAddress.getText());
			
			if (StudentBUS.updateStudent(student)) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Success");
				alert.setHeaderText("Successfully update student");
				alert.setContentText("Successfully update student with Id: " + student.getStudentId());
				alert.showAndWait();
				reloadViewAfterUpdateStudents();
			} else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Error");
				alert.setHeaderText("Cannot update student!");
				alert.setContentText("Please check these conditions:\n" + "- The information fields cannot be blank\n"
						+ "- Student Id must be unique.\n" + "- First/last name only contains letter and spacing.\n"
						+ "- Phone number only contains number.\n");
				alert.showAndWait();
			}
		} catch (SQLException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Cannot update student!");
			alert.setContentText("Something wrong with database connection!");
			alert.showAndWait();
		}
	}
}