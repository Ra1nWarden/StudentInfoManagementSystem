package ui;

import javax.swing.JFrame;

import data.StudentCourse;

public abstract class CourseDetailWindow extends JFrame {
	
	public abstract void loadCourse(StudentCourse course);

}
