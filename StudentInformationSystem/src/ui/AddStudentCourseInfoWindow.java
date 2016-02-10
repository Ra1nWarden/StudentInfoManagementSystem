package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import data.Student;
import data.StudentCourse;
import data.StudentCourseDAO;

public class AddStudentCourseInfoWindow extends StudentCourseInfoWindow {
	
	public AddStudentCourseInfoWindow(StudentCourseDAO studentCourseDAO, Student student, StudentCourseWindow window) {
		super(studentCourseDAO);
		setTitle("新增课程信息");
		studentNameField.setText(student.getName());
		studentNameField.setEditable(false);
		studentIdField.setText(Integer.toString(student.getId()));
		studentIdField.setEditable(false);
		resetButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				studentNameField.setText("");
				studentIdField.setText("");
				courseNameField.setText("");
				courseIdField.setText("");
				scoreField.setText("");
			}

		});

		doneButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				StudentCourse editedStudentCourse = getEditedStudentCourse();
				if (editedStudentCourse != null) {
					try {
						int success = studentCourseDAO.addStudentCourse(editedStudentCourse);
						if (success > 0) {
							JOptionPane.showMessageDialog(AddStudentCourseInfoWindow.this, "添加成功！");
						}
						window.loadData();
					} catch (Exception err) {
						System.err.println(err.getMessage());
						JOptionPane.showMessageDialog(AddStudentCourseInfoWindow.this, "添加失败！请联系管理员！", "错误",
								JOptionPane.ERROR_MESSAGE);
					}
					AddStudentCourseInfoWindow.this.setVisible(false);
				}
			}

		});
	}

}
