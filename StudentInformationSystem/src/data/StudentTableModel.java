package data;

import java.sql.Date;

import javax.swing.table.AbstractTableModel;

public final class StudentTableModel extends AbstractTableModel {
	
	private String[] columnNames = {"姓名", "学号", "性别", "年级", "出生日期"};
	
	// Mock data type does not match yet.
	private Object[][] data = {{"王子豪", "12345", "男", "大四", "1992-10-16"}};

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
	
	public Student valueAtRow(int row) {
		Student.Builder builder = new Student.Builder();
		return builder.name((String) data[row][0])
				.id((long) data[row][1])
			    .sex((int) data[row][2])
			    .level((int) data[row][3])
			    .dateOfBirth((Date) data[row][4])
			    .build();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return data[rowIndex][columnIndex];
	}

}
