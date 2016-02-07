package ui;

import data.StudentDAO;

public class AddStudentInfoWindow extends StudentInfoWindow {
	
	public AddStudentInfoWindow(StudentDAO studentDAO) {
		super(studentDAO);
		setTitle("新增学生信息");
	}

}
