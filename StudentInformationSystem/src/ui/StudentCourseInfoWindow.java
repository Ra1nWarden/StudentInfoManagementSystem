package ui;

import javax.swing.JFrame;

import data.StudentCourse;
import data.StudentCourse.Origin;
import data.StudentCourseDAO;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class StudentCourseInfoWindow extends JFrame {
	protected JTextField courseNameField;
	protected JTextField courseIdField;
	protected JTextField studentNameField;
	protected JTextField studentIdField;
	protected JTextField scoreField;
	protected JComboBox<Origin> originSelection;
	protected JButton doneButton;
	protected JButton resetButton;
	protected StudentCourseDAO studentCourseDAO;

	/**
	 * Create the frame.
	 */
	public StudentCourseInfoWindow(StudentCourseDAO studentCourseDAO) {
		this.studentCourseDAO = studentCourseDAO;
		setBounds(100, 100, 353, 272);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel formPanel = new JPanel();
		getContentPane().add(formPanel, BorderLayout.CENTER);
		formPanel.setLayout(new FormLayout(
				new ColumnSpec[] { FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
						FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("center:max(53dlu;default)"),
						FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("max(64dlu;default):grow"),
						FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("max(0dlu;default)"), },
				new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, }));

		JLabel courseLabel = new JLabel("课程名：");
		formPanel.add(courseLabel, "4, 2, center, center");

		courseNameField = new JTextField();
		formPanel.add(courseNameField, "6, 2, center, center");
		courseNameField.setColumns(10);

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

		studentIdField = new JTextField();
		formPanel.add(studentIdField, "6, 8, center, center");
		studentIdField.setColumns(10);

		JLabel scoreLabel = new JLabel("分数：");
		formPanel.add(scoreLabel, "4, 10, center, center");

		scoreField = new JTextField();
		formPanel.add(scoreField, "6, 10, center, center");
		scoreField.setColumns(10);

		JLabel originLabel = new JLabel("项目：");
		formPanel.add(originLabel, "4, 12, center, default");

		originSelection = new JComboBox<Origin>();
		originSelection.addItem(Origin.Local);
		originSelection.addItem(Origin.Overseas);
		formPanel.add(originSelection, "6, 12, center, default");

		doneButton = new JButton("确定");
		formPanel.add(doneButton, "4, 14, center, center");

		resetButton = new JButton("重置");
		formPanel.add(resetButton, "6, 14, center, center");

	}

	private boolean checkInput() {
		if (studentNameField.getText().isEmpty() || studentIdField.getText().isEmpty()
				|| courseNameField.getText().isEmpty() || courseIdField.getText().isEmpty()
				|| scoreField.getText().isEmpty()) {
			return false;
		}
		String numberRegex = "[0-9]+";
		String scoreRegex = "[0-9]+(\\.[0-9]+)?";
		return studentIdField.getText().matches(numberRegex) && courseIdField.getText().matches(numberRegex) && scoreField.getText().matches(scoreRegex);
	}

	public StudentCourse getEditedStudentCourse() {
		if(checkInput()) {
			return new StudentCourse.Builder()
					.courseId(Integer.parseInt(courseIdField.getText()))
					.courseName(courseNameField.getText())
					.studentId(Integer.parseInt(studentIdField.getText()))
					.studentName(studentNameField.getText())
					.score(Double.parseDouble(scoreField.getText()))
					.origin((Origin) originSelection.getSelectedItem())
					.build();
		} else {
			JOptionPane.showMessageDialog(this, "输入不合法！");
			return null;
		}

	}

}
