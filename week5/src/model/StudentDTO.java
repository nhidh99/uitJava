package model;

import javafx.beans.property.*;

public class StudentDTO {
	private SimpleIntegerProperty number;
	private SimpleStringProperty studentId;
	private SimpleStringProperty firstName;
	private SimpleStringProperty lastName;
	private SimpleStringProperty gender;
	private SimpleStringProperty address;
	private SimpleStringProperty phoneNumber;

	public String getStudentId() {
		return studentId.get();
	}

	public void setStudentId(String studentId) {
		this.studentId = new SimpleStringProperty(studentId);
	}

	public String getFirstName() {
		return firstName.get();
	}

	public void setFirstName(String firstName) {
		this.firstName = new SimpleStringProperty(firstName);
	}

	public String getLastName() {
		return lastName.get();
	}

	public void setLastName(String lastName) {
		this.lastName = new SimpleStringProperty(lastName);
	}

	public String getGender() {
		return gender.get();
	}

	public void setGender(String gender) {
		this.gender = new SimpleStringProperty(gender);
	}

	public String getAddress() {
		return address.get();
	}

	public void setAddress(String address) {
		this.address = new SimpleStringProperty(address);
	}

	public String getPhoneNumber() {
		return phoneNumber.get();
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = new SimpleStringProperty(phoneNumber);
	}

	public Integer getNumber() {
		return number.get();
	}

	public void setNumber(int number) {
		this.number = new SimpleIntegerProperty(number);
	}
}
