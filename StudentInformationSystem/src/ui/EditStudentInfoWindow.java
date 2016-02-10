package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import data.Student;
import data.StudentDAO;

public class EditStudentInfoWindow extends StudentInfoWindow {

	private final Student student;

	public EditStudentInfoWindow(StudentDAO studentDAO, Student student, MainWindow window) {
		super(studentDAO);
		this.student = student;
		setTitle("修改学生信息");
		loadStudent();
		resetButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				EditStudentInfoWindow.this.loadStudent();
			}

		});
		doneButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Student editedStudent = getEditedStudent();
				if (editedStudent != null) {
					try {
						int success = studentDAO.updateStudent(student, editedStudent);
						if (success > 0) {
							JOptionPane.showMessageDialog(EditStudentInfoWindow.this, "修改成功！");
						}
						window.loadData();
					} catch (Exception err) {
						System.err.println(err.getMessage());
						JOptionPane.showMessageDialog(EditStudentInfoWindow.this, "修改失败！请联系管理员！", "错误",
								JOptionPane.ERROR_MESSAGE);
					}
					EditStudentInfoWindow.this.setVisible(false);
				}
			}

		});
	}

	private void loadStudent() {
		nameField.setText(student.getName());
		idField.setText(Integer.toString(student.getId()));
		dateOfBirthField.setText(student.getDateOfBirth().toString());
		sexSelection.setSelectedItem(student.getSex());
		levelSelection.setSelectedItem(student.getLevel());
	}

}
