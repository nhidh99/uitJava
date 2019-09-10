package controller;

import model.*;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class StudentController implements Initializable {

    private ObservableList<StudentDTO> students = FXCollections.observableArrayList();
	
	@FXML
	private TableView<StudentDTO> tableView;

	@FXML
	private TableColumn<StudentDTO, String> number;   
	
	@FXML
	public TableColumn<StudentDTO, String> studentId;

	@FXML
	public TableColumn<StudentDTO, String> firstName;

	@FXML
	public TableColumn<StudentDTO, String> lastName;

	@FXML
	public TableColumn<StudentDTO, String> gender;

	@FXML
	public TableColumn<StudentDTO, String> address;

	@FXML
	public TableColumn<StudentDTO, String> phoneNumber;

	public void initialize(URL location, ResourceBundle resources) {
        for (StudentDTO std : StudentBUS.loadStudents()) {
        	students.add(std);
        }
        number.setCellValueFactory(new PropertyValueFactory<>("Number"));
        studentId.setCellValueFactory(new PropertyValueFactory<>("StudentId"));
        firstName.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
        lastName.setCellValueFactory(new PropertyValueFactory<>("LastName"));
        gender.setCellValueFactory(new PropertyValueFactory<>("Gender"));
        address.setCellValueFactory(new PropertyValueFactory<>("Address"));
        phoneNumber.setCellValueFactory(new PropertyValueFactory<>("PhoneNumber"));
        tableView.setItems(students);
	}
}
