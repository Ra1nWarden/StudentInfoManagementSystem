package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import data.Student;
import data.StudentDAO;

public class AddStudentInfoWindow extends StudentInfoWindow {
	
	public AddStudentInfoWindow(StudentDAO studentDAO, MainWindow window) {
		super(studentDAO);
		setTitle("新增学生信息");
		resetButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				nameField.setText("");
				idField.setText("");
				dateOfBirthField.setText("");
			}
			
		});
		
		doneButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Student editedStudent = getEditedStudent();
				if(editedStudent != null) {
					try {
						int success = studentDAO.addStudent(editedStudent);
						if(success > 0) {
							JOptionPane.showMessageDialog(AddStudentInfoWindow.this, "添加成功！");
						}
						window.loadData();
					} catch(Exception err) {
						System.err.println(err.getMessage());
						JOptionPane.showMessageDialog(AddStudentInfoWindow.this, "添加失败！请联系管理员！", "错误", JOptionPane.ERROR_MESSAGE);
					}
					AddStudentInfoWindow.this.setVisible(false);
				}
			}
		
		});
	}

}
