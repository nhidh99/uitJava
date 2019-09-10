package model;

import java.sql.*;
import java.util.*;

public class StudentDAO {
	
	public static ArrayList<StudentDTO> loadStudents() {
		Connection conn = DBConnection.getConnection();
		if (conn == null) return null;
		
		try {
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
		catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}