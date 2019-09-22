package model;

import java.sql.SQLException;
import java.util.*;

public class StudentBUS {
	public static ArrayList<StudentDTO> loadStudents() throws SQLException {
		return StudentDAO.loadStudents();
	}

	public static boolean addStudent(StudentDTO student) throws SQLException {
		return StudentDAO.addStudent(student);
	}

	public static boolean deleteStudentById(String studentId) throws SQLException {
		return StudentDAO.deleteStudentById(studentId);
	}

	public static boolean updateStudent(StudentDTO student) throws SQLException {
		return StudentDAO.updateStudent(student);
	}
}