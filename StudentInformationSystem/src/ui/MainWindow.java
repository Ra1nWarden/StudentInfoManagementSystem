package ui;

import javax.swing.JFrame;
import javax.swing.JTable;

import data.StudentDAO;
import data.StudentTableModel;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;
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
		table = new JTable(new StudentTableModel(studentDAO.getAllStudents()));
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
		//editInformationButton.addActionListener(new TableSelectionButtonListener(table, frame));
		buttonPanel.add(editInformationButton);
		
		JButton deleteEntryButton = new JButton("删除");
		//deleteEntryButton.addActionListener(new TableSelectionButtonListener(table, frame));
		buttonPanel.add(deleteEntryButton);

		frame.getContentPane().setLayout(groupLayout);
	}
}
