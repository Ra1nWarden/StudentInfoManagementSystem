package ui;

import data.Student;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.GridLayout;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditStudentInfoWindow extends StudentDetailWindow {
	private JTextField nameField;
	private JTextField idField;
	private JTextField sexField;
	private JTextField levelField;
	private JTextField dateOfBirthField;

	/**
	 * Create the panel.
	 */
	public EditStudentInfoWindow() {
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
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1);
		panel_1.setLayout(new FormLayout(new ColumnSpec[] {
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
		panel_1.add(nameLabel, "2, 2, center, center");
		
		nameField = new JTextField();
		panel_1.add(nameField, "4, 2, center, center");
		nameField.setColumns(10);
		
		JLabel idLabel = new JLabel("学号：");
		panel_1.add(idLabel, "2, 4, center, center");
		
		idField = new JTextField();
		panel_1.add(idField, "4, 4, center, center");
		idField.setColumns(10);
		
		JLabel sexLabel = new JLabel("性别：");
		panel_1.add(sexLabel, "2, 6, center, default");
		
		sexField = new JTextField();
		panel_1.add(sexField, "4, 6, center, center");
		sexField.setColumns(10);
		
		JLabel levelLabel = new JLabel("年级：");
		panel_1.add(levelLabel, "2, 8, center, default");
		
		levelField = new JTextField();
		panel_1.add(levelField, "4, 8, center, center");
		levelField.setColumns(10);
		
		JLabel dateOfBirthLabel = new JLabel("出生日期：");
		panel_1.add(dateOfBirthLabel, "2, 10, center, default");
		
		dateOfBirthField = new JTextField();
		panel_1.add(dateOfBirthField, "4, 10, center, center");
		dateOfBirthField.setColumns(10);
		
		JButton doneButton = new JButton("确定");
		doneButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel_1.add(doneButton, "2, 14, center, default");
		
		JButton resetButton = new JButton("重置");
		panel_1.add(resetButton, "4, 14, center, center");

	}

	@Override
	public void loadStudent(Student student) {
		// TODO Auto-generated method stub
		
	}
}
