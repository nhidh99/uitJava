package model;

import java.sql.*;
import java.util.*;
import java.util.regex.*;

public class StudentDAO {

	public static ArrayList<StudentDTO> loadStudents() throws SQLException {
		Connection conn = DBConnection.getConnection();
		Statement statement = conn.createStatement();
		String sql = "SELECT * FROM Student";
		ResultSet rs = statement.executeQuery(sql);

		ArrayList<StudentDTO> students = new ArrayList<StudentDTO>();
		int count = 1;
		while (rs.next()) {
			StudentDTO student = new StudentDTO();
			student.setNumber(count++);
			student.setStudentId(rs.getString(1));
			student.setLastName(rs.getString(2));
			student.setFirstName(rs.getString(3));
			student.setGender(rs.getString(4));
			student.setAddress(rs.getString(5));
			student.setPhoneNumber(rs.getString(6));
			students.add(student);
		}
		conn.close();
		return students;
	}

	public static boolean validateStudent(StudentDTO student) {
		Pattern namePattern = Pattern.compile("^[\\p{L} ]+$");
		Pattern numPattern = Pattern.compile("^[0-9]*$");
		return namePattern.matcher(student.getFirstName()).matches()
				&& namePattern.matcher(student.getLastName()).matches()
				&& numPattern.matcher(student.getPhoneNumber()).matches() && !student.getAddress().trim().isEmpty();
	}

	public static boolean addStudent(StudentDTO student) throws SQLException {
		if (validateStudent(student)) {
			Connection conn = DBConnection.getConnection();
			Statement statement = conn.createStatement();
			String sql = String.format(
					"INSERT INTO STUDENT(StudentId, LastName, FirstName, Gender, Address, PhoneNumber) "
							+ "VALUES ('%s', N'%s', N'%s', N'%s', N'%s', '%s')",
					student.getStudentId(), student.getLastName(), student.getFirstName(), student.getGender(),
					student.getAddress(), student.getPhoneNumber());
			statement.executeUpdate(sql);
			conn.close();
			return true;
		} else {
			return false;
		}
	}

	public static boolean deleteStudentById(String studentId) throws SQLException {
		Connection conn = DBConnection.getConnection();
		Statement statement = conn.createStatement();
		String sql = "DELETE FROM Student WHERE StudentId = " + studentId;
		statement.executeUpdate(sql);
		conn.close();
		return true;
	}

	public static boolean updateStudent(StudentDTO student) throws SQLException {
		if (validateStudent(student)) {
			Connection conn = DBConnection.getConnection();
			Statement statement = conn.createStatement();
			String sql = String.format(
					"UPDATE Student " + "SET FirstName = N'%s'," + "LastName = N'%s'," + "Gender = N'%s',"
							+ "Address = N'%s'," + "PhoneNumber = '%s' " + "WHERE StudentId = '%s'",
					student.getFirstName(), student.getLastName(), student.getGender(), student.getAddress(),
					student.getPhoneNumber(), student.getStudentId());
			statement.executeUpdate(sql);
			conn.close();
			return true;
		} else {
			return false;
		}
	}
}