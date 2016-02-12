package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

public class LoginWindow extends JFrame {

	private JPanel contentPane;
	private JTextField usernameField;
	private JPasswordField passwordField;

	/**
	 * Create the frame.
	 */
	public LoginWindow() {
		setTitle("登录系统");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 260, 154);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(new FormLayout(
				new ColumnSpec[] { FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
						FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("default:grow"), },
				new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, }));

		JLabel lblNewLabel = new JLabel("用户名：");
		panel.add(lblNewLabel, "2, 2, center, default");

		usernameField = new JTextField();
		panel.add(usernameField, "6, 2, center, default");
		usernameField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("密码：");
		panel.add(lblNewLabel_1, "2, 4, center, default");

		passwordField = new JPasswordField();
		panel.add(passwordField, "6, 4, center, default");
		passwordField.setColumns(10);

		JButton loginButton = new JButton("登录");
		loginButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (usernameField.getText().equals("root") && passwordField.getText().equals("root")) {
					MainWindow mainWindow = new MainWindow();
					LoginWindow.this.setVisible(false);
					mainWindow.show();
				} else {
					JOptionPane.showMessageDialog(LoginWindow.this, "用户名密码不匹配", "登录失败", JOptionPane.ERROR_MESSAGE);
				}
			}

		});
		panel.add(loginButton, "4, 6, center, default");
	}

}
