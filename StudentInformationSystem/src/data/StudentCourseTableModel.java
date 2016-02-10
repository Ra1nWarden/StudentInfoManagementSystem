package data;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class StudentCourseTableModel extends AbstractTableModel {

	private String[] columnNames = {"课程名", "课号", "学生姓名", "学生学号", "学生成绩"};
	
	private List<StudentCourse> studentCourses;
	
	public StudentCourseTableModel(List<StudentCourse> studentCourses) {
		this.studentCourses = studentCourses;
	}

	@Override
	public int getRowCount() {
		return studentCourses.size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}
	
	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}
	
	public StudentCourse valueAtRow(int row) {
		return studentCourses.get(row);
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch(columnIndex) {
		case 0:
			return studentCourses.get(rowIndex).getCourseName();
		case 1:
			return studentCourses.get(rowIndex).getCourseId();
		case 2:
			return studentCourses.get(rowIndex).getStudentName();
		case 3:
			return studentCourses.get(rowIndex).getStudentId();
		case 4:
			return studentCourses.get(rowIndex).getScore();
		default:
			return "";
		}
	}

}
