package app;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import data.StudentTableModel;
import ui.StudentDetailWindow;

public final class TableSelectionButtonListener implements ActionListener {

	private final JTable table;
	private final JFrame parentFrame;
	private final StudentDetailWindow detailWindow;
	
	public TableSelectionButtonListener(JTable table, JFrame parent, StudentDetailWindow detailWindow) {
		this.table = table;
		this.parentFrame = parent;
		this.detailWindow = detailWindow;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		int selection = table.getSelectedRow();
		if(selection == -1) {
			JOptionPane.showMessageDialog(parentFrame, "请选中要操作的学生。", "非法操作", JOptionPane.ERROR_MESSAGE);
		} else {
			// Show specific details of a student
			detailWindow.loadStudent(((StudentTableModel) table.getModel()).valueAtRow(table.convertRowIndexToModel(selection)));
		}
	}	

}
