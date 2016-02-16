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
			while (result.next()) {
				ret.add(convertToStudent(result));
			}
		} finally {
			if (statement != null) {
				statement.close();
			}
			if (result != null) {
				result.close();
			}
		}
		return ret;
	}

	public int deleteStudent(Student student) throws SQLException {
		PreparedStatement statement = null;
		PreparedStatement studentCourseStatement = null;
		try {
			studentCourseStatement = connection.prepareStatement("delete from student_courses where student_id=" + student.getId());
			studentCourseStatement.executeUpdate();
			statement = connection.prepareStatement("delete from students where student_id=?");
			statement.setInt(1, student.getId());
			return statement.executeUpdate();
		} finally {
			if (statement != null) {
				statement.close();
			}
		}
	}

	public int updateStudent(Student oldStudent, Student newStudent) throws SQLException {
		PreparedStatement statement = null;
		try {
			if (oldStudent.getId() != newStudent.getId() || !oldStudent.getName().equals(newStudent.getName())) {
				PreparedStatement studentCourseStatement = connection
						.prepareStatement("update student_info_system.student_courses "
								+ "set student_name=?, student_id=? " + "where student_id=" + oldStudent.getId());
				studentCourseStatement.setString(1, newStudent.getName());
				studentCourseStatement.setInt(2, newStudent.getId());
				studentCourseStatement.executeUpdate();
			}
			statement = connection.prepareStatement("update student_info_system.students "
					+ "set name=?, sex=?, date_of_birth=?, level=?, student_id=? " + "where student_id="
					+ oldStudent.getId());
			statement.setString(1, newStudent.getName());
			statement.setString(2, newStudent.getSex() == Sex.Male ? "M" : "F");
			statement.setDate(3, newStudent.getDateOfBirth());
			statement.setString(4, newStudent.getLevel().toString());
			statement.setInt(5, newStudent.getId());
			return statement.executeUpdate();
		} finally {
			if (statement != null) {
				statement.close();
			}
		}
	}

	public int addStudent(Student newStudent) throws SQLException {
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement("insert into student_info_system.students "
					+ "(name, sex, date_of_birth, level, student_id) " + "VALUES (?, ?, ?, ?, ?)");
			statement.setString(1, newStudent.getName());
			statement.setString(2, newStudent.getSex() == Sex.Male ? "M" : "F");
			statement.setDate(3, newStudent.getDateOfBirth());
			statement.setString(4, newStudent.getLevel().toString());
			statement.setInt(5, newStudent.getId());
			return statement.executeUpdate();
		} finally {
			if (statement != null) {
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
		if (level.equals("大一"))
			builder.level(Level.Freshman);
		else if (level.equals("大二"))
			builder.level(Level.Sophomore);
		else if (level.equals("大三"))
			builder.level(Level.Junior);
		else
			builder.level(Level.Senior);
		builder.id(rs.getInt("student_id"));
		return builder.build();
	}

}
