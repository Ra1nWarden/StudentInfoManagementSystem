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
		builder.id(rs.getLong("student_id"));
		return builder.build();
	}

}
