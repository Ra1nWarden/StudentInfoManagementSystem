package ui;

import javax.swing.JFrame;

import data.Student;

public abstract class StudentDetailWindow extends JFrame {
	
	public abstract void loadStudent(Student student);

}
