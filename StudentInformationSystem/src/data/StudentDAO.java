package data;

import java.io.FileInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import data.Student.Level;
import data.Student.Sex;

public class StudentDAO {
	
	private Connection connection;
	
	public StudentDAO() throws Exception {
		Properties prop = new Properties();
		FileInputStream fileStream = new FileInputStream("database.properties");
		prop.load(fileStream);
		
		String username = prop.getProperty("username");
		String password = prop.getProperty("password");
		String dburl = prop.getProperty("dburl");
		
		connection = DriverManager.getConnection(dburl, username, password);
	}
	
	public List<Student> getAllStudents() throws Exception {
		List<Student> ret = new ArrayList<Student>();
		Statement statement = null;
		ResultSet result = null;
		
		try {
			statement = connection.createStatement();
			result = statement.executeQuery("select * from students");
			while(result.next()) {
				ret.add(convertToStudent(result));
			}
		} finally {
			if(statement != null) {
				statement.close();
			}
			if(result != null) {
				result.close();
			}
		}
		return ret;
	}
	
	public int deleteStudent(Student student) throws SQLException {
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement("delete from students where student_id=?");
			statement.setInt(1, student.getId());
			return statement.executeUpdate();
		} finally {
			if(statement != null) {
				statement.close();
			}
		}
	}
	
	public int updateStudent(Student oldStudent, Student newStudent) throws SQLException {
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement("update student_info_system.students "
					+ "set name=?, sex=?, date_of_birth=?, level=?, student_id=? "
					+ "where student_id=" + oldStudent.getId());
			statement.setString(1, newStudent.getName());
			statement.setString(2, newStudent.getSex() == Sex.Male ? "M" : "F");
			statement.setDate(3, newStudent.getDateOfBirth());
			statement.setString(4, newStudent.getLevel().toString());
			statement.setInt(5, newStudent.getId());
			return statement.executeUpdate();
		} finally {
			if(statement != null) {
				statement.close();
			}
		}
	}
	
	private Student convertToStudent(ResultSet rs) throws Exception {
		Student.Builder builder = new Student.Builder();
		String name = rs.getString("name");
		builder.name(name);
		Date birthday = rs.getDate("date_of_birth");
		builder.dateOfBirth(birthday);
		String sex = rs.getString("sex");
		builder.sex(sex.equals("M") ? Sex.Male : Sex.Female);
		String level = rs.getString("level");
		switch(level) {
		case "大一":
			builder.level(Level.Freshman);
		case "大二":
			builder.level(Level.Sophomore);
		case "大三":
			builder.level(Level.Junior);
		case "大四":
			builder.level(Level.Senior);
		}
		builder.id(rs.getInt("student_id"));
		return builder.build();
	}

}
