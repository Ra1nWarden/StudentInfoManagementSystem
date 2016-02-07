package ui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import data.Student;
import data.StudentDAO;
import data.StudentTableModel;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.border.LineBorder;

import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;

public final class MainWindow {

	private JFrame frame;
	private JTable table;
	private StudentDAO studentDAO;

	/**
	 * Create the application.
	 */
	public MainWindow() {
		try {
			initialize();
			loadData();
		} catch(Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	public void show() {
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() throws Exception {
		frame = new JFrame();
		frame.setTitle("中法项目学生管理系统");
		frame.setBounds(100, 100, 752, 619);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel buttonPanel = new JPanel();
		
		studentDAO = new StudentDAO();
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setBackground(Color.WHITE);
		
		JScrollPane tablePanel = new JScrollPane(table);
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(tablePanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 740, Short.MAX_VALUE)
						.addComponent(buttonPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 740, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(23)
					.addComponent(tablePanel, GroupLayout.DEFAULT_SIZE, 479, Short.MAX_VALUE)
					.addGap(33)
					.addComponent(buttonPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(23))
		);
		
		JButton button = new JButton("添加学生");
		buttonPanel.add(button);
		
		JButton checkCourseButton = new JButton("查看课程");
		//checkCourseButton.addActionListener(new TableSelectionButtonListener(table, frame));
		buttonPanel.add(checkCourseButton);
		
		JButton editInformationButton = new JButton("修改信息");
		editInformationButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int selection = table.getSelectedRow();
				if(selection == -1) {
					JOptionPane.showMessageDialog(frame, "请选中要操作的学生。", "非法操作", JOptionPane.ERROR_MESSAGE);
				} else {
					// Show specific details of a student
					Student selectedStudent = ((StudentTableModel) table.getModel()).valueAtRow(table.convertRowIndexToModel(selection));
					EditStudentInfoWindow editWindow = new EditStudentInfoWindow(studentDAO, selectedStudent, MainWindow.this);
					editWindow.setVisible(true);
				}
			}
			
		});
		buttonPanel.add(editInformationButton);
		
		JButton deleteEntryButton = new JButton("删除");
		deleteEntryButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int selection = table.getSelectedRow();
				if(selection == -1) {
					JOptionPane.showMessageDialog(frame, "请选中要操作的学生。", "非法操作", JOptionPane.ERROR_MESSAGE);
				} else {
					// Show specific details of a student
					Student selectedStudent = ((StudentTableModel) table.getModel()).valueAtRow(table.convertRowIndexToModel(selection));
					int confirmationIndex = JOptionPane.showConfirmDialog(frame, "确定删除" + selectedStudent.getName() + "？", "删除学生信息", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if(confirmationIndex == JOptionPane.YES_OPTION) {
						try {
							int success = studentDAO.deleteStudent(selectedStudent);
							if(success > 0) {
								JOptionPane.showMessageDialog(frame, "删除成功！");
								loadData();
							}
						} catch(Exception err) {
							JOptionPane.showMessageDialog(frame, "删除失败！", "错误", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			}
			
		});
		buttonPanel.add(deleteEntryButton);

		frame.getContentPane().setLayout(groupLayout);
	}
	
	public void loadData() throws Exception {
		table.setModel(new StudentTableModel(studentDAO.getAllStudents()));
	}
	
}
