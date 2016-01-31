package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;

import data.Course;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class EditCourseInfoWindow extends CourseDetailWindow {
	private JTextField courseField;
	private JTextField courseIdField;
	private JTextField studentNameField;
	private JTextField studentLabelField;
	private JTextField scoreField;

	public void show() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditCourseInfoWindow frame = new EditCourseInfoWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public EditCourseInfoWindow() {
		setBounds(100, 100, 353, 272);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel formPanel = new JPanel();
		getContentPane().add(formPanel, BorderLayout.CENTER);
		formPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("center:max(53dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(64dlu;default):grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(0dlu;default)"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JLabel courseLabel = new JLabel("课程名：");
		formPanel.add(courseLabel, "4, 2, center, center");
		
		courseField = new JTextField();
		formPanel.add(courseField, "6, 2, center, center");
		courseField.setColumns(10);
		
		JLabel courseIdLabel = new JLabel("课号：");
		formPanel.add(courseIdLabel, "4, 4, center, center");
		
		courseIdField = new JTextField();
		formPanel.add(courseIdField, "6, 4, center, center");
		courseIdField.setColumns(10);
		
		JLabel studentNameLabel = new JLabel("学生姓名：");
		formPanel.add(studentNameLabel, "4, 6, center, center");
		
		studentNameField = new JTextField();
		formPanel.add(studentNameField, "6, 6, center, default");
		studentNameField.setColumns(10);
		
		JLabel studentIdLabel = new JLabel("学生学号：");
		formPanel.add(studentIdLabel, "4, 8, center, center");
		
		studentLabelField = new JTextField();
		formPanel.add(studentLabelField, "6, 8, center, center");
		studentLabelField.setColumns(10);
		
		JLabel scoreLabel = new JLabel("分数：");
		formPanel.add(scoreLabel, "4, 10, center, center");
		
		scoreField = new JTextField();
		formPanel.add(scoreField, "6, 10, center, center");
		scoreField.setColumns(10);
		
		JButton doneButton = new JButton("确定");
		formPanel.add(doneButton, "4, 14, center, center");
		
		JButton resetButton = new JButton("重置");
		formPanel.add(resetButton, "6, 14, center, center");

	}

	@Override
	public void loadCourse(Course course) {
		// TODO Auto-generated method stub
		
	}

}
