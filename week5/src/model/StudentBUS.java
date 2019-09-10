package model;

import java.util.*;

public class StudentBUS {
	public static ArrayList<StudentDTO> loadStudents() {
		return StudentDAO.loadStudents();
	}
}