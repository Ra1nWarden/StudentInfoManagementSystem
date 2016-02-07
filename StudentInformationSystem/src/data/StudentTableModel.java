package data;

import java.sql.Date;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public final class StudentTableModel extends AbstractTableModel {
	
	private String[] columnNames = {"姓名", "学号", "性别", "年级", "出生日期"};
	
	private List<Student> students;
	
	public StudentTableModel(List<Student> students) {
		this.students = students;
	}

	@Override
	public int getRowCount() {
		return students.size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}
	
	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}
	
	public Student valueAtRow(int row) {
		return students.get(row);
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Student student = students.get(rowIndex);
		switch(columnIndex) {
		case 0:
			return student.getName();
		case 1:
			return student.getId();
		case 2:
			return student.getSex();
		case 3:
			return student.getLevel();
		case 4:
			return student.getDateOfBirth();
		default:
			return "";
		}
	}

}
