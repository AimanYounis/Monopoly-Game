package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.JTextComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField usernameTextField;
	private JTextField PasswordTextField;
	private JButton LoginButton;
	private JButton RegisterButton ;
	private JLabel usernameLblError;
	private JLabel passwordLblError;


	/**
	 * Create the frame.
	 */
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1150, 811);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		passwordLblError = new JLabel("");
		passwordLblError.setBounds(759, 444, 285, 14);
		contentPane.add(passwordLblError);
		
		usernameLblError = new JLabel("");
		usernameLblError.setBounds(759, 395, 285, 14);
		contentPane.add(usernameLblError);
		
		usernameTextField = new JTextField();
		usernameTextField.setBounds(556, 392, 153, 20);
		contentPane.add(usernameTextField);
		usernameTextField.setColumns(10);
		
		PasswordTextField = new JTextField();
		PasswordTextField.setColumns(10);
		PasswordTextField.setBounds(556, 441, 153, 20);
		contentPane.add(PasswordTextField);
		
		JLabel lblPassword = new JLabel("Password : ");
		lblPassword.setBounds(443, 444, 83, 14);
		contentPane.add(lblPassword);
		
		JLabel lblUsername = new JLabel("Username : ");
		lblUsername.setBounds(443, 379, 109, 46);
		
		contentPane.add(lblUsername);
		
		 LoginButton = new JButton("New button");
		LoginButton.setBounds(556, 524, 144, 38);
		LoginButton.setIcon(new ImageIcon("img/LoginButton.PNG"));
		contentPane.add(LoginButton);
		
		RegisterButton = new JButton("New button");
		RegisterButton.setBounds(556, 591, 153, 39);
		RegisterButton.setIcon(new ImageIcon("img/RegisterButton.PNG"));
		contentPane.add(RegisterButton);
		
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon("img/Back2.png"));
		lblNewLabel_1.setBounds(288, 133, 600, 206);
		contentPane.add(lblNewLabel_1);
			
		JLabel lblNewLabel = new JLabel("");
	    lblNewLabel.setIcon(new ImageIcon("img/Back.jpg"));
	    lblNewLabel.setBounds(5, 5, 1124, 762);
	    contentPane.add(lblNewLabel);
		

	}
	public void SetUsernameError(String error){
		usernameLblError.setText(error);
	}
	public void SetPasswordError(String error) {
		passwordLblError.setText(error);
	}
	public String getUsername() {
		return usernameTextField.getText();
	}
	public String getPassword() {
		return PasswordTextField.getText();
	}
	
	public JTextField getUsernameTextField() {
		return usernameTextField;
	}

	public void setUsernameTextField(JTextField usernameTextField) {
		this.usernameTextField = usernameTextField;
	}

	public JTextField getPasswordTextField() {
		return PasswordTextField;
	}

	public void setPasswordTextField(JTextField passwordTextField) {
		PasswordTextField = passwordTextField;
	}

	public void LoginActionListener(ActionListener listener) {
		LoginButton.addActionListener(listener);
	}
	public void RegisterActionListener(ActionListener listener) {
		RegisterButton.addActionListener(listener);
	}

	
	
}
