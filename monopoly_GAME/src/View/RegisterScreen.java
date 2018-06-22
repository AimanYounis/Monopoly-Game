package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;

public class RegisterScreen extends JFrame {

	private JPanel contentPane;
	private JTextField UsernameTxt;
	private JTextField EmailTxt;
	private JTextField PasswordTxt;
	private JTextField PasswordConfirmTxt;
	private JButton btnNewButton;
	private JLabel lblPasswordError;
	private JLabel lblUsernameError ;
	

	/**
	 * Create the frame.
	 */
	public RegisterScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1150, 811);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblConfirmPassword = new JLabel("Confirm Password :");
		lblConfirmPassword.setBounds(351, 575, 110, 14);
		contentPane.add(lblConfirmPassword);
		
		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setBounds(351, 538, 110, 14);
		contentPane.add(lblPassword);
		
		JLabel lblNewLabel_3 = new JLabel("Email :");
		lblNewLabel_3.setBounds(351, 495, 110, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_2 = new JLabel("Username :");
		lblNewLabel_2.setBounds(351, 449, 110, 14);
		contentPane.add(lblNewLabel_2);
	    
	    UsernameTxt = new JTextField();
	    UsernameTxt.setBounds(471, 446, 126, 20);
	    contentPane.add(UsernameTxt);
	    UsernameTxt.setColumns(10);
	    
	    EmailTxt = new JTextField();
	    EmailTxt.setBounds(471, 492, 126, 20);
	    contentPane.add(EmailTxt);
	    EmailTxt.setColumns(10);
	    
	    PasswordTxt = new JTextField();
	    PasswordTxt.setBounds(471, 535, 126, 20);
	    contentPane.add(PasswordTxt);
	    PasswordTxt.setColumns(10);
	    
	    PasswordConfirmTxt = new JTextField();
	    PasswordConfirmTxt.setText("");
	    PasswordConfirmTxt.setBounds(471, 572, 126, 20);
	    contentPane.add(PasswordConfirmTxt);
	    PasswordConfirmTxt.setColumns(10);
		
		btnNewButton = new JButton("");
		btnNewButton.setBounds(430, 647, 163, 39);
		btnNewButton.setIcon(new ImageIcon("img/RegisterButton.PNG"));
		contentPane.add(btnNewButton);
		
		lblPasswordError = new JLabel("");
		lblPasswordError.setBounds(642, 538, 202, 14);
		contentPane.add(lblPasswordError);
		
		lblUsernameError = new JLabel("");
		lblUsernameError.setBounds(642, 449, 202, 14);
		contentPane.add(lblUsernameError);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("img/Back2.png"));
		lblNewLabel_1.setBounds(288, 133, 600, 206);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("img/Back.jpg"));
		lblNewLabel.setBounds(5, 5, 1124, 762);
		contentPane.add(lblNewLabel);
		
		
	}
	
	public void SetRegisterActionListener(ActionListener listener) {
		this.btnNewButton.addActionListener(listener);
	}
	public String GetUsername() {
		return UsernameTxt.getText();
	}
	public String GetEmail() {
		return EmailTxt.getText();
	}
	public String GetPassword() {
	return PasswordTxt.getText();
	}
	public String GetPasswordConfirm() {
		return PasswordConfirmTxt.getText();
	}
	public void SetPasswordError(String error) {
		lblPasswordError.setText(error);
	}
	public void SetUsernameError(String error) {
		lblUsernameError.setText(error);
	}
	
	}
