package data;

import javax.swing.table.AbstractTableModel;

public class StudentTableModel extends AbstractTableModel {
	
	private String[] columnNames = {"姓名", "学号", "性别", "年级", "出生日期"};

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}
	
	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}

}
