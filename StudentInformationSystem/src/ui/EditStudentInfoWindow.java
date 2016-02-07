package ui;

import data.Student;
import data.StudentDAO;

import javax.swing.JLabel;
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
import java.awt.event.ActionEvent;

public final class EditStudentInfoWindow extends JFrame {
	private JTextField nameField;
	private JTextField idField;
	private JTextField sexField;
	private JTextField levelField;
	private JTextField dateOfBirthField;
	private StudentDAO studentDAO;

	/**
	 * Create the panel.
	 */
	public EditStudentInfoWindow(StudentDAO studentDAO) {
		this.studentDAO = studentDAO;
		setBounds(100, 100, 400, 228);
		setTitle("修改学生信息");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGap(0, 840, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGap(0, 683, Short.MAX_VALUE)
		);
		getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel formPanel = new JPanel();
		getContentPane().add(formPanel);
		formPanel.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("59px"),
				ColumnSpec.decode("100px"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("140px:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("39px"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("26px"),
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
		
		sexField = new JTextField();
		formPanel.add(sexField, "4, 6, center, center");
		sexField.setColumns(10);
		
		JLabel levelLabel = new JLabel("年级：");
		formPanel.add(levelLabel, "2, 8, center, default");
		
		levelField = new JTextField();
		formPanel.add(levelField, "4, 8, center, center");
		levelField.setColumns(10);
		
		JLabel dateOfBirthLabel = new JLabel("出生日期：");
		formPanel.add(dateOfBirthLabel, "2, 10, center, default");
		
		dateOfBirthField = new JTextField();
		formPanel.add(dateOfBirthField, "4, 10, center, center");
		dateOfBirthField.setColumns(10);
		
		JButton doneButton = new JButton("确定");
		doneButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		formPanel.add(doneButton, "2, 14, center, default");
		
		JButton resetButton = new JButton("重置");
		formPanel.add(resetButton, "4, 14, center, center");

	}

	public void loadStudent(Student student) {
		
	}
}
