package ui;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JFrame;

import data.StudentCourseTableModel;
import data.StudentCourse.Origin;
import data.Student;
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
	private JTable courseTable;
	private StudentCourseDAO studentCourseDAO;
	private Student student;
	private Origin displayOrigin;

	/**
	 * Create the frame.
	 */
	public StudentCourseWindow(Student student, MainWindow window)
			throws Exception {
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
		controlPanel.add(addCourseButton);

		JButton editInformation = new JButton("修改信息");
		controlPanel.add(editInformation);

		JButton button_1 = new JButton("删除课程");
		controlPanel.add(button_1);

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
						courseTable.setModel(new StudentCourseTableModel(studentCourseDAO
								.getCoursesForStudentIdFromOrigin(student.getId(), selected)));
					} catch (Exception ex) {
						System.err.println("Error in quering database.");
					}
				}
			}

		});
		controlPanel.add(comboBox);

		courseTable = new JTable(new StudentCourseTableModel(
				studentCourseDAO.getCoursesForStudentIdFromOrigin(student.getId(), displayOrigin)));
		courseTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablePanel.setViewportView(courseTable);
		getContentPane().setLayout(groupLayout);

	}
}
