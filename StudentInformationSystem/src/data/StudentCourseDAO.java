package data;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import data.Student.Level;
import data.Student.Sex;
import data.StudentCourse.Origin;

public class StudentCourseDAO {

	private Connection connection;

	public StudentCourseDAO() throws Exception {
		Properties prop = new Properties();
		FileInputStream fileStream = new FileInputStream("database.properties");
		prop.load(fileStream);

		String username = prop.getProperty("username");
		String password = prop.getProperty("password");
		String dburl = prop.getProperty("dburl");

		connection = DriverManager.getConnection(dburl, username, password);
	}

	public List<StudentCourse> getCoursesForStudentIdFromOrigin(int studentId, Origin origin) throws Exception {
		List<StudentCourse> ret = new ArrayList<StudentCourse>();
		PreparedStatement statement = null;
		ResultSet result = null;

		try {
			statement = connection.prepareStatement("select * from student_info_system.student_courses where student_id=? and origin=?");
			statement.setInt(1, studentId);
			statement.setString(2, origin.toString());
			result = statement.executeQuery();
			while (result.next()) {
				ret.add(convertToStudentCourse(result));
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
	
	

	private StudentCourse convertToStudentCourse(ResultSet rs) throws Exception {
		return new StudentCourse.Builder().courseName(rs.getString("name")).courseId(rs.getInt("course_id"))
				.studentId(rs.getInt("student_id")).studentName(rs.getString("student_name"))
				.score(rs.getDouble("score"))
				.origin(rs.getString("origin").equals("国内") ? Origin.Local : Origin.Overseas)
				.id(rs.getInt("id")).build();
	}

	public int deleteStudentCourse(StudentCourse selectedStudentCourse) throws Exception {
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement("delete from student_courses where id=?");
			statement.setInt(1, selectedStudentCourse.getId());
			return statement.executeUpdate();
		} finally {
			if (statement != null) {
				statement.close();
			}
		}
	}

	public int addStudentCourse(StudentCourse editedStudentCourse) throws SQLException {
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement("insert into student_info_system.student_courses "
					+ "(name, student_id, student_name, course_id, score, origin) " + "VALUES (?, ?, ?, ?, ?, ?)");
			statement.setString(1, editedStudentCourse.getCourseName());
			statement.setInt(2, editedStudentCourse.getStudentId());
			statement.setString(3, editedStudentCourse.getStudentName());
			statement.setInt(4, editedStudentCourse.getCourseId());
			statement.setDouble(5, editedStudentCourse.getScore());
			statement.setString(6, editedStudentCourse.getOrigin() == Origin.Local ? "国内" : "国外");
			return statement.executeUpdate();
		} finally {
			if (statement != null) {
				statement.close();
			}
		}
	}

	public int updateStudentCourse(StudentCourse studentCourse, StudentCourse editedStudentCourse) throws SQLException {
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement("update student_info_system.student_courses "
					+ "set name=?, student_id=?, student_name=?, course_id=?, score=?, origin=? " + "where id=?");
			statement.setString(1, editedStudentCourse.getCourseName());
			statement.setInt(2, editedStudentCourse.getStudentId());
			statement.setString(3, editedStudentCourse.getStudentName());
			statement.setInt(4, editedStudentCourse.getCourseId());
			statement.setDouble(5, editedStudentCourse.getScore());
			statement.setString(6, editedStudentCourse.getOrigin() == Origin.Local ? "国内" : "国外");
			statement.setInt(7, studentCourse.getId());
			System.out.println(statement.toString());
			return statement.executeUpdate();
		} finally {
			if (statement != null) {
				statement.close();
			}
		}
	}

}
