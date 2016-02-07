package ui;

import data.Student;
import data.Student.Level;
import data.Student.Sex;
import data.StudentDAO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.GridLayout;
import javax.swing.JPanel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class StudentInfoWindow extends JFrame {
	protected JTextField nameField;
	protected JTextField idField;
	protected JTextField dateOfBirthField;
	protected JComboBox<Sex> sexSelection;
	protected JComboBox<Level> levelSelection;
	protected JButton doneButton;
	protected JButton resetButton;
	protected StudentDAO studentDAO;

	protected static final Level[] levelSelections = new Level[] { Level.Freshman, Level.Sophomore, Level.Junior,
			Level.Senior };
	protected static final Sex[] sexSelections = new Sex[] { Sex.Female, Sex.Male };

	/**
	 * Create the panel.
	 */
	public StudentInfoWindow(StudentDAO studentDAO) {
		this.studentDAO = studentDAO;
		setBounds(100, 100, 470, 281);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout
				.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGap(0, 840, Short.MAX_VALUE));
		groupLayout
				.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGap(0, 683, Short.MAX_VALUE));
		getContentPane().setLayout(new GridLayout(1, 0, 0, 0));

		JPanel formPanel = new JPanel();
		getContentPane().add(formPanel);
		formPanel.setLayout(new FormLayout(
				new ColumnSpec[] { ColumnSpec.decode("59px"), ColumnSpec.decode("100px"), FormSpecs.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("140px:grow"), FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("39px"), },
				new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("26px"), FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, }));

		JLabel nameLabel = new JLabel("姓名：");
		formPanel.add(nameLabel, "2, 2, center, center");

		nameField = new JTextField();
		formPanel.add(nameField, "4, 2, center, center");
		nameField.setColumns(10);

		JLabel idLabel = new JLabel("学号：");
		formPanel.add(idLabel, "2, 4, center, center");
		idField = new JTextField();
		formPanel.add(idField, "4, 4, center, center");
		idField.setColumns(10);

		JLabel sexLabel = new JLabel("性别：");
		formPanel.add(sexLabel, "2, 6, center, default");

		sexSelection = new JComboBox<Sex>();
		for (Sex each : sexSelections) {
			sexSelection.addItem(each);
		}
		formPanel.add(sexSelection, "4, 6, center, default");

		JLabel levelLabel = new JLabel("年级：");
		formPanel.add(levelLabel, "2, 8, center, default");

		levelSelection = new JComboBox<Level>();
		for (Level each : levelSelections) {
			levelSelection.addItem(each);
		}
		formPanel.add(levelSelection, "4, 8, center, default");

		JLabel dateOfBirthLabel = new JLabel("出生日期：");
		formPanel.add(dateOfBirthLabel, "2, 10, center, default");

		dateOfBirthField = new JTextField();
		formPanel.add(dateOfBirthField, "4, 10, center, center");
		dateOfBirthField.setColumns(10);

		doneButton = new JButton("确定");
		doneButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		formPanel.add(doneButton, "2, 14, center, default");

		resetButton = new JButton("重置");
		formPanel.add(resetButton, "4, 14, center, center");

	}
	
	private boolean checkInput() {
		if(nameField.getText().isEmpty()) {
			return false;
		}
		String studentIdRegex = "[0-9]+";
		String dateRegex = "[0-9]{4}-[0-9]{2}-[0-9]{2}";
		return idField.getText().matches(studentIdRegex) && dateOfBirthField.getText().matches(dateRegex);
	}

	protected Student getEditedStudent() {
		if(checkInput()) {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			try {
				Student.Builder builder = new Student.Builder()
					.name(nameField.getText())
					.id(Integer.parseInt(idField.getText()))
					.sex((Sex) sexSelection.getSelectedItem())
					.level((Level) levelSelection.getSelectedItem())
					.dateOfBirth(new Date(formatter.parse(dateOfBirthField.getText()).getTime()));
				return builder.build();
			} catch(Exception e) {
				System.err.println("Wrong format");
				JOptionPane.showMessageDialog(this, "请以年-月-日的方式输入出生日期！");
				return null;
			}
		} else {
			 JOptionPane.showMessageDialog(this, "输入不合法！");
			 return null;
		}
	}

}
