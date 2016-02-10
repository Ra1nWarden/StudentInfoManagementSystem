package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import data.StudentCourse;
import data.StudentCourseDAO;

public class EditStudentCourseInfoWindow extends StudentCourseInfoWindow {
	
	private final StudentCourse studentCourse;

	public EditStudentCourseInfoWindow(StudentCourseDAO studentCourseDAO, StudentCourse studentCourse, StudentCourseWindow window) {
		super(studentCourseDAO);
		this.studentCourse = studentCourse;
		setTitle("修改课程信息");
		studentNameField.setEditable(false);
		studentIdField.setEditable(false);
		loadStudentCourse();
		resetButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				EditStudentCourseInfoWindow.this.loadStudentCourse();
			}

		});
		doneButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				StudentCourse editedStudentCourse = getEditedStudentCourse();
				if (editedStudentCourse != null) {
					System.out.println("edited");
					try {
						int success = studentCourseDAO.updateStudentCourse(studentCourse, editedStudentCourse);
						System.out.println("success " + success);
						if (success > 0) {
							JOptionPane.showMessageDialog(EditStudentCourseInfoWindow.this, "修改成功！");
						}
						window.loadData();
					} catch (Exception err) {
						System.err.println(err.getMessage());
						JOptionPane.showMessageDialog(EditStudentCourseInfoWindow.this, "修改失败！请联系管理员！", "错误",
								JOptionPane.ERROR_MESSAGE);
					}
					EditStudentCourseInfoWindow.this.setVisible(false);
				}
			}

		});
	}

	private void loadStudentCourse() {
		courseNameField.setText(studentCourse.getCourseName());
		courseIdField.setText(Integer.toString(studentCourse.getCourseId()));
		studentNameField.setText(studentCourse.getStudentName());
		studentIdField.setText(Integer.toString(studentCourse.getStudentId()));
		scoreField.setText(Double.toString(studentCourse.getScore()));
		originSelection.setSelectedItem(studentCourse.getOrigin());
	}

}
