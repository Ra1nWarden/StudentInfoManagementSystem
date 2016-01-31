package data;

import javax.swing.table.AbstractTableModel;

public class CourseTableModel extends AbstractTableModel {

	private String[] columnNames = {"课程名", "课号", "学生姓名", "学生学号", "学生成绩"};
	
	// Mock data, type does not match yet
	private Object[][] data = {{"初级经济学", "54321", "王子豪", "12345", "93.2"}};

	@Override
	public int getRowCount() {
		return data.length;
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}
	
	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}
	
	public Course valueAtRow(int row) {
		Course.Builder builder = new Course.Builder();
		return builder.name((String) data[row][0])
				.id((long) data[row][1])
				.studentName((String) data[row][2])
				.studentId((long) data[row][3])
				.score((double) data[row][4])
				.build();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return data[rowIndex][columnIndex];
	}

}
