package ui;

import javax.swing.JFrame;

import data.Course;

public abstract class CourseDetailWindow extends JFrame {
	
	public abstract void loadCourse(Course course);

}
