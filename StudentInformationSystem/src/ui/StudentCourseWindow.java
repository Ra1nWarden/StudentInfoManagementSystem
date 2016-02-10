package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import data.StudentCourseTableModel;
import data.StudentTableModel;
import data.StudentCourse.Origin;
import data.Student;
import data.StudentCourse;
import data.StudentCourseDAO;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.ListSelectionModel;

public class StudentCourseWindow extends JFrame {
	private JTable table;
	private StudentCourseDAO studentCourseDAO;
	private Student student;
	private Origin displayOrigin;

	/**
	 * Create the frame.
	 */
	public StudentCourseWindow(Student student, MainWindow window) throws Exception {
		this.studentCourseDAO = new StudentCourseDAO();
		this.student = student;
		this.displayOrigin = Origin.Local;
		setTitle("已完成课程");
		setBounds(100, 100, 755, 490);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JScrollPane tablePanel = new JScrollPane();

		JPanel controlPanel = new JPanel();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout
				.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
						groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addGroup(Alignment.LEADING,
												groupLayout.createSequentialGroup().addGap(14).addComponent(
														controlPanel, GroupLayout.DEFAULT_SIZE, 735, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup().addContainerGap().addComponent(tablePanel,
								GroupLayout.DEFAULT_SIZE, 654, Short.MAX_VALUE))).addContainerGap()));
		groupLayout
				.setVerticalGroup(
						groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup().addContainerGap()
										.addComponent(tablePanel, GroupLayout.PREFERRED_SIZE, 386,
												GroupLayout.PREFERRED_SIZE)
										.addGap(18)
										.addComponent(controlPanel, GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
										.addGap(18)));

		JButton addCourseButton = new JButton("添加课程");
		addCourseButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				AddStudentCourseInfoWindow addWindow = new AddStudentCourseInfoWindow(studentCourseDAO, student, StudentCourseWindow.this);
				addWindow.setVisible(true);
				
			}
			
		});
		controlPanel.add(addCourseButton);

		JButton editInformation = new JButton("修改信息");
		editInformation.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int selection = table.getSelectedRow();
				if (selection == -1) {
					JOptionPane.showMessageDialog(StudentCourseWindow.this, "请选中要操作的课程。", "非法操作", JOptionPane.ERROR_MESSAGE);
				} else {
					StudentCourse selectedStudentCourse = ((StudentCourseTableModel) table.getModel())
							.valueAtRow(table.convertRowIndexToModel(selection));
					EditStudentCourseInfoWindow editWindow = new EditStudentCourseInfoWindow(studentCourseDAO, selectedStudentCourse,
							StudentCourseWindow.this);
					editWindow.setVisible(true);
				}
				
			}
			
		});
		controlPanel.add(editInformation);

		JButton deleteButton = new JButton("删除课程");
		deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selection = table.getSelectedRow();
				if (selection == -1) {
					JOptionPane.showMessageDialog(StudentCourseWindow.this, "请选中要操作的课程。", "非法操作",
							JOptionPane.ERROR_MESSAGE);
				} else {
					// Show specific details of a student
					StudentCourse selectedStudentCourse = ((StudentCourseTableModel) table.getModel())
							.valueAtRow(table.convertRowIndexToModel(selection));
					int confirmationIndex = JOptionPane.showConfirmDialog(StudentCourseWindow.this,
							"确定删除" + selectedStudentCourse.getCourseName() + "？", "删除课程信息", JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE);
					if (confirmationIndex == JOptionPane.YES_OPTION) {
						try {
							int success = studentCourseDAO.deleteStudentCourse(selectedStudentCourse);
							if (success > 0) {
								JOptionPane.showMessageDialog(StudentCourseWindow.this, "删除成功！");
								loadData();
							}
						} catch (Exception err) {
							JOptionPane.showMessageDialog(StudentCourseWindow.this, "删除失败！", "错误",
									JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			}
		});
		controlPanel.add(deleteButton);

		JComboBox<Origin> comboBox = new JComboBox<Origin>();
		comboBox.addItem(Origin.Local);
		comboBox.addItem(Origin.Overseas);
		comboBox.setSelectedItem(Origin.Local);
		comboBox.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				Origin selected = (Origin) e.getItem();
				if (selected != displayOrigin) {
					StudentCourseWindow.this.displayOrigin = selected;
					try {
						table.setModel(new StudentCourseTableModel(
								studentCourseDAO.getCoursesForStudentIdFromOrigin(student.getId(), selected)));
					} catch (Exception ex) {
						System.err.println("Error in quering database.");
					}
				}
			}

		});
		controlPanel.add(comboBox);

		table = new JTable(new StudentCourseTableModel(
				studentCourseDAO.getCoursesForStudentIdFromOrigin(student.getId(), displayOrigin)));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablePanel.setViewportView(table);
		getContentPane().setLayout(groupLayout);

	}

	public void loadData() throws Exception {
		table.setModel(new StudentCourseTableModel(
				studentCourseDAO.getCoursesForStudentIdFromOrigin(student.getId(), displayOrigin)));
	}
}
